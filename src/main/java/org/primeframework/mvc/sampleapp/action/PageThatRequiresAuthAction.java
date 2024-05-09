package org.primeframework.mvc.sampleapp.action;

import java.util.UUID;

import com.google.inject.Inject;
import org.primeframework.mvc.action.annotation.Action;
import org.primeframework.mvc.sampleapp.session.OurUser;
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
    loggedInUserId = ((OurUser) securityContext.getCurrentUser()).id;
    return "success";
  }
}
