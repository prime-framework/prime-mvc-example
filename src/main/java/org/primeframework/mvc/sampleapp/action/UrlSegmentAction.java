package org.primeframework.mvc.sampleapp.action;

import org.primeframework.mvc.action.annotation.Action;
import org.primeframework.mvc.action.result.annotation.Status;

@Action(value = "{urlSegmentId}")
@Status
public class UrlSegmentAction extends BaseAction {
  public String urlSegmentId;

  public String get() {
    System.out.println("got /url-segment request with value "+urlSegmentId);
    return "success";
  }
}
