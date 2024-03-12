package brady;

import com.google.inject.Inject;
import io.fusionauth.http.server.HTTPRequest;
import io.fusionauth.http.server.HTTPResponse;
import org.primeframework.mvc.security.NoLoginSecurityContext;
import org.primeframework.mvc.security.UserLoginSecurityContext;

import java.util.Set;

public class SomeLoginContext implements UserLoginSecurityContext {
    private final HTTPRequest request;
    private final HTTPResponse response;

    @Inject
    public SomeLoginContext(HTTPRequest request, HTTPResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public Object getCurrentUser() {
        return null;
    }

    @Override
    public Set<String> getCurrentUsersRoles() {
        return null;
    }

    @Override
    public String getSessionId() {
        return null;
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
