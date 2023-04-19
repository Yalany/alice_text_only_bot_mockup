package game.actions;

import nlp.UserIntent;

import java.util.HashSet;

public final class Event {
  private final UserIntent intent;
  private final HashSet<Trigger> subscribers;

  /**
   * @param intent UserIntent for this Event to be associated with
   */
  Event(final UserIntent intent) {
    this.intent = intent;
    subscribers = new HashSet<>();
  }

  public void subscribe(final Condition condition, final Action action) {
    subscribers.add(new Trigger(condition, action));
  }

  /**
   * @param input array of String with input tokens to decide if event is thrown by input
   * @param inContext ActionContext to throw event in it if event should be thrown
   * @return true if any Action were performed upon event throw, false otherwise
   */
  public boolean run(final String[] input, final ActionContext inContext) {
    return intent.isIntended(input) && subscribers.stream().anyMatch(trigger -> trigger.run(inContext));
  }

  private class Trigger {
    private final Condition condition;
    private final Action action;

    private Trigger(final Condition condition, final Action action) {
      this.condition = condition;
      this.action = action;
    }

    private boolean validate(final ActionContext inContext) {
      return condition.validate(inContext);
    }

    private boolean run(final ActionContext inContext) {
      if (validate(inContext)) {
        action.run(inContext);
        return true;
      }
      return false;
    }
  }
}
