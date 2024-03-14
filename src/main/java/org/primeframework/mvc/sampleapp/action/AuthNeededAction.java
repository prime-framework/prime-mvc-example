package org.primeframework.mvc.sampleapp.action;

import com.google.inject.Inject;
import org.primeframework.mvc.action.annotation.Action;
import org.primeframework.mvc.security.UserLoginSecurityContext;

@Action(requiresAuthentication = true, constraints = {"somerole"})
public class AuthNeededAction extends BaseAction {
    @Inject
    private UserLoginSecurityContext securityContext;

    public String get() {
        return "success";
    }
}
