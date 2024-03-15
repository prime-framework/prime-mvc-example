package org.primeframework.mvc.sampleapp.action;

import org.primeframework.mvc.action.annotation.Action;

@Action
public class FormAction extends BaseAction {
    public String result;
    public String yourName;

    public String get() {
        return "success";
    }

    public String post() {
        this.result = "Hello "+yourName;
        return "success";
    }
}
