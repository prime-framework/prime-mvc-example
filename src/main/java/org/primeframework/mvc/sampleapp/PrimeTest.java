package org.primeframework.mvc.sampleapp;

import com.google.inject.Module;
import io.fusionauth.http.server.HTTPListenerConfiguration;
import io.fusionauth.http.server.HTTPServerConfiguration;
import org.primeframework.mvc.BasePrimeMain;
import org.primeframework.mvc.guice.MVCModule;

public class PrimeTest extends BasePrimeMain {
    public static void main(String[] args) {
        var instance = new PrimeTest();
        instance.registerShutdownHook();
        instance.start();
    }

    @Override
    public HTTPServerConfiguration[] configuration() {
        var httpConfig = new HTTPServerConfiguration().withListener(new HTTPListenerConfiguration(8080));
        return new HTTPServerConfiguration[]{
                httpConfig
        };
    }

    @Override
    protected Module[] modules() {
        return new Module[]{new OurModule(), new MVCModule()};
    }
}
