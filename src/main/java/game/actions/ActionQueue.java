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

    public static ActionQueue newHead() {
        return new ActionQueue(inContext -> {/*empty action*/});
    }

    public void add(final Action action) {
        assert action != null;
        add(new ActionQueue(action));
    }

    private void add(final ActionQueue sequence) {
        assert sequence != null;
        last.next = sequence;
        last = sequence.last;
    }

    private static void execute(final ActionQueue queue, final ActionContext inContext) {
        var todo = queue;
        while (todo != null) {
            todo.queuedAction.run(inContext);
            todo = todo.next;
        }
    }
}
