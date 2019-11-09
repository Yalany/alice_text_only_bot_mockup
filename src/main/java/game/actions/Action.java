package game.actions;

public interface Action {
    /**
     * Performs singular atomic operation in given context
     * @param inContext context of execution or null
     */
    void run(final ActionContext inContext);
}
