package brady;

import io.fusionauth.http.server.HTTPRequest;
import io.fusionauth.http.server.HTTPResponse;
import org.primeframework.mvc.security.UserLoginSecurityContext;

public abstract class SessionLoginContext implements UserLoginSecurityContext {
    private final HTTPRequest request;
    private final HTTPResponse response;

    protected SessionLoginContext(HTTPRequest request, HTTPResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public Object getCurrentUser() {
        return null;
    }

    @Override
    public String getSessionId() {
        return "1234";
    }

    @Override
    public boolean isLoggedIn() {
        return false;
    }

    @Override
    public void login(Object context) {

    }

    @Override
    public void logout() {

    }

    @Override
    public void updateUser(Object user) {

    }
}
