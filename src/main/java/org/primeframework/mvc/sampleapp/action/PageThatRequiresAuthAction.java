package org.primeframework.mvc.sampleapp.action;

import java.util.UUID;

import com.google.inject.Inject;
import org.primeframework.mvc.action.annotation.Action;
import org.primeframework.mvc.sampleapp.domain.ExampleUser;
import org.primeframework.mvc.security.UserLoginSecurityContext;

@Action(requiresAuthentication = true)
public class PageThatRequiresAuthAction extends BaseAction {
  public UUID loggedInUserId;

  private final UserLoginSecurityContext securityContext;

  @Inject
  public PageThatRequiresAuthAction(UserLoginSecurityContext securityContext) {
    this.securityContext = securityContext;
  }

  public String get() {
    loggedInUserId = ((ExampleUser) securityContext.getCurrentUser()).id;
    return "success";
  }
}
