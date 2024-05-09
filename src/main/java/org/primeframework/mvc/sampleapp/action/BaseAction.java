package org.primeframework.mvc.sampleapp.action;

import org.primeframework.mvc.action.result.annotation.Forward;
import org.primeframework.mvc.action.result.annotation.SaveRequest;

// by default, this saves request and redirects the user to / if they are unauthenticated
@SaveRequest(uri = "/login")
@Forward(code = "error", page = "error.ftl", status = 500)
public abstract class BaseAction {
}
