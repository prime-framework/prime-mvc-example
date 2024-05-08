package org.primeframework.mvc.sampleapp.action;

import com.google.inject.Inject;
import org.primeframework.mvc.action.annotation.Action;
import org.primeframework.mvc.action.result.annotation.ReexecuteSavedRequest;
import org.primeframework.mvc.sampleapp.session.OurUser;
import org.primeframework.mvc.security.UserLoginSecurityContext;

import java.util.UUID;

@Action
@ReexecuteSavedRequest(uri = "/")
public class LoginAction extends BaseAction {
    @Inject
    private UserLoginSecurityContext userLoginSecurityContext;
    public String username;

    public String get() {
        return "input";
    }

    public String post() {
        System.out.println("logging in "+username);
        var user = new OurUser();
        user.id = UUID.randomUUID();
        userLoginSecurityContext.login(user);
        return "success";
    }
}
