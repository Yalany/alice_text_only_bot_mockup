package game.actions;

import nlp.UserIntent;

import java.util.HashSet;

public class GameEvent {
    private final UserIntent intent;
    private final HashSet<Trigger> subscribers = new HashSet<>();

    public GameEvent(final UserIntent intent) {
        this.intent = intent;
    }

    public void subscribe(final Condition condition, final Action action) {
        subscribers.add(new Trigger(condition, action));
    }

    public UserIntent getIntent() {
        return intent;
    }

    public void run(final ActionContext inContext) {
        subscribers.forEach(trigger -> trigger.run(inContext));
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

        private void run(final ActionContext inContext) {
            if (validate(inContext)) action.run(inContext);
        }
    }
}
