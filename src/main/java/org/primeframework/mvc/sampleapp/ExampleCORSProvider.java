package org.primeframework.mvc.sampleapp;

import org.primeframework.mvc.cors.CORSConfiguration;
import org.primeframework.mvc.cors.CORSConfigurationProvider;

public class ExampleCORSProvider implements CORSConfigurationProvider {
  @Override
  public CORSConfiguration get() {
    return new CORSConfiguration();
  }
}
