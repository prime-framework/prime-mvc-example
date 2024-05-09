package org.primeframework.mvc.sampleapp.action;

import java.util.UUID;

import com.google.inject.Inject;
import org.primeframework.mvc.action.annotation.Action;
import org.primeframework.mvc.action.result.annotation.ReexecuteSavedRequest;
import org.primeframework.mvc.sampleapp.domain.OurUser;
import org.primeframework.mvc.security.UserLoginSecurityContext;

@Action
@ReexecuteSavedRequest(uri = "/")
public class LoginAction extends BaseAction {
  public String username;

  private final UserLoginSecurityContext userLoginSecurityContext;

  @Inject
  public LoginAction(UserLoginSecurityContext userLoginSecurityContext) {
    this.userLoginSecurityContext = userLoginSecurityContext;
  }

  public String get() {
    return "input";
  }

  public String post() {
    System.out.println("logging in " + username);
    var user = new OurUser();
    user.id = UUID.randomUUID();
    userLoginSecurityContext.login(user);
    return "success";
  }
}
