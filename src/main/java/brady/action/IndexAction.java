package brady.action;

import com.google.inject.Inject;
import io.fusionauth.http.server.HTTPRequest;
import org.primeframework.mvc.action.annotation.Action;

@Action
public class IndexAction extends BaseAction {
    @Inject
    private HTTPRequest httpRequest;

    public String baseUrl;

    public String get() {
        this.baseUrl = httpRequest.getBaseURL();
        return "success";
    }
}
