package brady.action;

import com.google.inject.Inject;
import org.primeframework.mvc.action.annotation.Action;
import org.primeframework.mvc.action.result.annotation.Redirect;
import org.primeframework.mvc.security.UserLoginSecurityContext;

@Action(requiresAuthentication = true, constraints = {"somerole"})
@Redirect(code = "unauthenticated", uri = "/")
public class AuthNeededAction {
    @Inject
    private UserLoginSecurityContext securityContext;

    public String get() {
        return "success";
    }
}
