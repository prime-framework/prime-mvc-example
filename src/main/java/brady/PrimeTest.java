package brady;

import com.google.inject.Guice;
import org.primeframework.mvc.guice.MVCModule;

public class PrimeTest {
    public static void main(String[] args) {
        var injector = Guice.createInjector(new MVCModule(), new OurModule());
    }
}
