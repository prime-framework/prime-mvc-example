package org.primeframework.mvc.sampleapp.action;

import com.google.inject.Inject;
import io.fusionauth.http.server.HTTPRequest;
import org.primeframework.mvc.action.annotation.Action;

@Action
public class IndexAction extends BaseAction {
  public String baseUrl;

  private final HTTPRequest httpRequest;

  @Inject
  public IndexAction(HTTPRequest httpRequest) {
    this.httpRequest = httpRequest;
  }

  public String get() {
    this.baseUrl = httpRequest.getBaseURL();
    return "success";
  }
}
