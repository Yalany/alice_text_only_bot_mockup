package game.actions;

public interface Condition {
    /**
     * Checks if condition is met in given context
     * @param inContext context of validation
     * @return true if condition passes in given context
     */
    boolean validate(final ActionContext inContext);
}
