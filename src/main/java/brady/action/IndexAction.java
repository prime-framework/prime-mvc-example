package brady.action;

import org.primeframework.mvc.action.annotation.Action;

@Action
public class IndexAction {
    public String get() {
        return "success";
    }
}
