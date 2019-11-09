package game.actions;

public final class ActionQueue implements Action {
    private ActionQueue next = null;
    private ActionQueue last = this;

    private final Action queuedAction;

    private ActionQueue(final Action action) {
        assert action != null;
        queuedAction = action;
    }

    @Override
    public void run(final ActionContext inContext) {
        execute(this, inContext);
    }

    ActionQueue add(final Action action) {
        return action == null ? this : add(new ActionQueue(action));
    }

    private ActionQueue add(final ActionQueue sequence) {
        if (sequence == null) return this;
        last.next = sequence;
        last = sequence.last;
        return this;
    }

    private static void execute(final ActionQueue queue, final ActionContext inContext) {
        var todo = queue;
        while (todo != null) {
            todo.queuedAction.run(inContext);
            todo = todo.next;
        }
    }
}
