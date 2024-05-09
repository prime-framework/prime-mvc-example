package org.primeframework.mvc.sampleapp.action;

import com.google.inject.Inject;
import io.fusionauth.http.HTTPValues;
import org.primeframework.mvc.action.annotation.Action;
import org.primeframework.mvc.message.MessageStore;
import org.primeframework.mvc.message.MessageType;
import org.primeframework.mvc.message.SimpleFieldMessage;
import org.primeframework.mvc.validation.ValidationMethod;

@Action
public class FormAction extends BaseAction {
  private final MessageStore messageStore;

  public String result;

  public String yourName;

  @Inject
  public FormAction(MessageStore messageStore) {
    this.messageStore = messageStore;
  }

  public String get() {
    return "success";
  }

  public String post() {
    if (yourName != null && yourName.contains("crash")) {
      throw new RuntimeException("we have a problem");
    }
    this.result = "Hello " + yourName;
    return "success";
  }

  @ValidationMethod(httpMethods = HTTPValues.Methods.POST)
  public void validate() {
    if (yourName == null) {
      this.messageStore.add(new SimpleFieldMessage(MessageType.ERROR,
          "yourName",
          "invalid[yourName]",
          "name is required"));
    }
  }
}
