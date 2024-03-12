package brady;

import com.google.inject.AbstractModule;
import org.primeframework.mvc.config.MVCConfiguration;
import org.primeframework.mvc.cors.CORSConfigurationProvider;
import org.primeframework.mvc.security.UserLoginSecurityContext;

public class OurModule extends AbstractModule {
    protected void configure() {
        bind(UserLoginSecurityContext.class).to(SomeLoginContext.class).asEagerSingleton();
        bind(CORSConfigurationProvider.class).to(OurCORS.class).asEagerSingleton();
        bind(MVCConfiguration.class).to(OurPrimeConfig.class).asEagerSingleton();
    }
}
