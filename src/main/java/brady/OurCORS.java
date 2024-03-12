package brady;

import org.primeframework.mvc.cors.CORSConfiguration;
import org.primeframework.mvc.cors.CORSConfigurationProvider;

public class OurCORS implements CORSConfigurationProvider {
    @Override
    public CORSConfiguration get() {
        return new CORSConfiguration();
    }
}
