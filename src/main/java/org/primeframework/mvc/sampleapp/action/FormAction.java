package org.primeframework.mvc.sampleapp.action;

import com.google.inject.Inject;
import io.fusionauth.http.HTTPValues;
import org.primeframework.mvc.action.annotation.Action;
import org.primeframework.mvc.message.MessageStore;
import org.primeframework.mvc.message.MessageType;
import org.primeframework.mvc.message.SimpleFieldMessage;
import org.primeframework.mvc.message.SimpleMessage;
import org.primeframework.mvc.message.l10n.MessageProvider;
import org.primeframework.mvc.validation.ValidationMethod;

@Action
public class FormAction extends BaseAction {
  private final MessageStore messageStore;
  private final MessageProvider messageProvider;

  public String result;

  public String yourName;

  @Inject
  public FormAction(MessageStore messageStore, MessageProvider messageProvider) {
    this.messageStore = messageStore;
    this.messageProvider = messageProvider;
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

  // runs by default on POST
  @ValidationMethod
  public void validate() {
    if (yourName == null) {
      var message = new SimpleFieldMessage(MessageType.ERROR,
          "yourName",
          "invalid[yourName]",
          messageProvider.getMessage("invalid[yourName]"));
      this.messageStore.add(message);
    }
  }
}
