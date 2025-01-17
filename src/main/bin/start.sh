#!/usr/bin/env bash

#
# Copyright (c) 2024, Inversoft Inc., All Rights Reserved
#
set -e

# Spit out some help
if [[ ${1} == "-h" || ${1} == "--help" ]]; then
  echo "This script starts the prime-mvc-sample service"
  echo ""
  echo "Usage:"
  echo "  start.sh [options]"
  echo ""
  echo "OPTIONS:"
  echo "  --debug    Start app in debug mode (mostly used by the dev team)"
  echo "  --help     Display this message"
  exit 0
fi

# Magic sauce
SOURCE="${BASH_SOURCE[0]}"
while [[ -h ${SOURCE} ]]; do # resolve $SOURCE until the file is no longer a symlink
  SCRIPT_DIR="$(cd -P "$(dirname "${SOURCE}")" >/dev/null && pwd)"
  SOURCE="$(readlink "${SOURCE}")"
  [[ ${SOURCE} != /* ]] && SOURCE="${SCRIPT_DIR}/${SOURCE}" # if $SOURCE was a relative symlink, we need to resolve it relative to the path where the symlink file was located
done
SCRIPT_DIR="$(cd -P "$(dirname "${SOURCE}")" > /dev/null && pwd)"
cd "${SCRIPT_DIR}/../.."
BASE_DIR=$(pwd)
cd prime-mvc-sample
APP_DIR=$(pwd)
CONFIG_DIR=${BASE_DIR}/config
JAVA_DIR=${BASE_DIR}/java
LOG_DIR=${BASE_DIR}/logs
PLUGIN_DIR=${BASE_DIR}/plugins
MARKER="app1234"
OS=$(uname -s)

JAVA_OPTS=" -Djava.awt.headless=true -D${MARKER}"
JAVA_OPTS=$(echo "${JAVA_OPTS}" | tr -d '\r')

# If we are in a non interactive shell then hide the progress but show errors
CURL_OPTS="-fSL --progress-bar"
if ! tty -s; then
  CURL_OPTS="-sSL"
fi

# shellcheck disable=SC2034
downloadJava() {
  # Declare the version and the platform and arch dependent URLs
  JAVA_VERSION=21.0.2+13
  BASE_URL_PATH=https://github.com/adoptium/temurin21-binaries/releases/download/jdk-21.0.2%2B13/
  Linux_aarch64_Filename=OpenJDK21U-jdk_aarch64_linux_hotspot_21.0.2_13.tar.gz
  Linux_x86_64_Filename=OpenJDK21U-jdk_x64_linux_hotspot_21.0.2_13.tar.gz
  Darwin_x86_64_Filename=OpenJDK21U-jdk_x64_mac_hotspot_21.0.2_13.tar.gz
  Darwin_arm64_Filename=OpenJDK21U-jdk_aarch64_mac_hotspot_21.0.2_13.tar.gz

  # Build a the URL
  ARCH=$(uname -m)
  FILENAME="${OS}_${ARCH}_Filename"
  DOWNLOAD_URL="${BASE_URL_PATH}${!FILENAME}"

  # Ensure both the 'current' and 'jdk-${JAVA_VERSION}' exist, this tells us Java is setup and at the correct version
  if [[ ! -e "${JAVA_DIR}/current" || ! -d "${JAVA_DIR}/jdk-${JAVA_VERSION}" ]]; then
    if [[ ${OS} == "Darwin" ]]; then
      if [[ -e ~/dev/java/current21 ]]; then
        # Development, just sym link to our current version of Java, because only 'current' will exist in dev, we'll always rebuild the symlink.
        cd "${JAVA_DIR}"
        rm -f current
        # Use the same version that we'll be packaging with Docker during development
        ln -s ~/dev/java/current21-jlinked current
      else
        curl ${CURL_OPTS} "${DOWNLOAD_URL}" -o "${JAVA_DIR}/openjdk-macos-${JAVA_VERSION}.tar.gz"
        tar xfz "${JAVA_DIR}/openjdk-macos-${JAVA_VERSION}.tar.gz" -C "${JAVA_DIR}"
        cd "${JAVA_DIR}"
        rm -f current
        ln -s jdk-${JAVA_VERSION}/Contents/Home current
        rm openjdk-macos-${JAVA_VERSION}.tar.gz
      fi
    elif [[ ${OS} == "Linux" ]]; then
      curl ${CURL_OPTS} "${DOWNLOAD_URL}" -o "${JAVA_DIR}/openjdk-linux-${JAVA_VERSION}.tar.gz"
      tar xfz "${JAVA_DIR}/openjdk-linux-${JAVA_VERSION}.tar.gz" -C "${JAVA_DIR}"
      cd "${JAVA_DIR}"
      rm -f current
      ln -s jdk-${JAVA_VERSION} current
      rm openjdk-linux-${JAVA_VERSION}.tar.gz
    fi
  fi
}

# Download Java if necessary
if [[ ${CLEANSPEAK_USE_GLOBAL_JAVA} != 1 ]]; then
  downloadJava

  # Set JAVA_HOME
  JAVA_HOME=${JAVA_DIR}/current
fi

# Start it up
echo "Starting prime-mvc-sample..."
echo "  --> Logging to ${LOG_DIR}/prime-mvc-sample.log"
cd "${APP_DIR}"

CLASSPATH=""
for file in lib/*; do
  CLASSPATH=${CLASSPATH}:${file}
done
CLASSPATH=${CLASSPATH:1}

if [[ ${1} == "--debug" ]]; then
  echo "Starting with debug enabled"
  JPDA_ADDRESS=${JPDA_ADDRESS:-5005}
  JPDA_SUSPEND=${JPDA_SUSPEND:-n}
  JAVA_OPTS="${JAVA_OPTS} -agentlib:jdwp=transport=dt_socket,server=y,suspend=${JPDA_SUSPEND},address=*:${JPDA_ADDRESS}"
fi

# shellcheck disable=SC2086
exec "${JAVA_HOME}/bin/java" -cp "${CLASSPATH}" ${JAVA_OPTS} org.primeframework.mvc.sampleapp.ExamplePrimeMain <&- >> "${LOG_DIR}/prime-mvc-sample.log" 2>&1
