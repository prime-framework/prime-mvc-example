package brady;

import com.google.inject.Inject;
import io.fusionauth.http.server.HTTPRequest;
import io.fusionauth.http.server.HTTPResponse;

import java.util.Set;

public class SomeLoginContext extends SessionLoginContext {
    @Inject
    public SomeLoginContext(HTTPRequest request, HTTPResponse response) {
        super(request, response);
    }

    @Override
    public Set<String> getCurrentUsersRoles() {
        return null;
    }
}
