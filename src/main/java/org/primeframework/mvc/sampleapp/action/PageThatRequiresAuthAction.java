package org.primeframework.mvc.sampleapp.action;

import com.google.inject.Inject;
import org.primeframework.mvc.action.annotation.Action;
import org.primeframework.mvc.sampleapp.session.OurUser;
import org.primeframework.mvc.security.UserLoginSecurityContext;

import java.util.UUID;

@Action(requiresAuthentication = true)
public class PageThatRequiresAuthAction extends BaseAction {
    @Inject
    private UserLoginSecurityContext securityContext;
    public UUID loggedInUserId;

    public String get() {
        loggedInUserId = ((OurUser)securityContext.getCurrentUser()).id;
        return "success";
    }
}
