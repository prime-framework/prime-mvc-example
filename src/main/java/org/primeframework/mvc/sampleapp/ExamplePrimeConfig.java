package org.primeframework.mvc.sampleapp;

import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Path;
import java.security.Key;
import java.security.SecureRandom;

import org.primeframework.mvc.config.AbstractMVCConfiguration;

public class ExamplePrimeConfig extends AbstractMVCConfiguration {
  private final SecretKeySpec cookieEncryptionKey;

  public ExamplePrimeConfig() {
    byte[] keyBytes = new byte[16];
    new SecureRandom().nextBytes(keyBytes);
    this.cookieEncryptionKey = new SecretKeySpec(keyBytes, "AES");
  }

  @Override
  public boolean allowUnknownParameters() {
    return false;
  }

  @Override
  public Path baseDirectory() {
    return Path.of("web");
  }

  @Override
  public Key cookieEncryptionKey() {
    return this.cookieEncryptionKey;
  }

  @Override
  public int l10nReloadSeconds() {
    return 0;
  }

  @Override
  public int templateCheckSeconds() {
    return 0;
  }

  @Override
  public boolean csrfEnabled() {
    return true;
  }
}
