package org.primeframework.mvc.sampleapp.action;

import org.primeframework.mvc.action.annotation.Action;
import org.primeframework.mvc.action.result.annotation.Forward;

@Action
@Forward(status = 404)
public class MissingAction {
  public String execute() {
    return "success";
  }
}
