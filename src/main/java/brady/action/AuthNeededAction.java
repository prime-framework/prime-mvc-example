package brady.action;

import com.google.inject.Inject;
import org.primeframework.mvc.action.annotation.Action;
import org.primeframework.mvc.security.UserLoginSecurityContext;

@Action(requiresAuthentication = true, constraints = {"somerole"})
public class AuthNeededAction {
    @Inject
    private UserLoginSecurityContext securityContext;

    public String get() {
        return "success";
    }
}