package org.primeframework.mvc.sampleapp;

import com.google.inject.AbstractModule;
import org.primeframework.mvc.config.MVCConfiguration;
import org.primeframework.mvc.cors.CORSConfigurationProvider;
import org.primeframework.mvc.sampleapp.session.ExampleLoginContext;
import org.primeframework.mvc.security.UserLoginSecurityContext;

public class ExampleModule extends AbstractModule {
  protected void configure() {
    bind(UserLoginSecurityContext.class).to(ExampleLoginContext.class);
    bind(CORSConfigurationProvider.class).to(ExampleCORSProvider.class).asEagerSingleton();
    bind(MVCConfiguration.class).to(ExamplePrimeConfig.class).asEagerSingleton();
  }
}
