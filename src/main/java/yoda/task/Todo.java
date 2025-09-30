package yoda.task;

import javax.naming.InsufficientResourcesException;

/**
 * Represents a {@code Todo} task in the Yoda application.
 * <p>
 * A {@code Todo} is the simplest kind of task, consisting only of
 * a description (label) and a completion status flag inherited from {@link Task}.
 * </p>
 *
 */
public class Todo extends Task {

    /**
     * Constructs a new {@code Todo} with the specified description.
     *
     * @param inputLabel the description text of the todo.
     * @throws InsufficientResourcesException if the label is empty or invalid
     *                                        (as enforced by {@link Task}).
     */
    public Todo(String inputLabel) throws InsufficientResourcesException {
        super(inputLabel);
    }

    /**
     * Returns a string representation of this todo for user display.
     * <p>
     * Example: {@code [T][X] read book}
     * </p>
     *
     * @return a formatted string including the {@code [T]} prefix and task details.
     */
    @Override
    public String toString() {
        return "[T]" + this.taskString();
    }

    /**
     * Converts this todo into a command-like string suitable for saving to a file.
     * <p>
     * Example: {@code todo read book /mark X}
     * </p>
     *
     * @return a command-like string containing the description and mark of the todo.
     */
    public String toCommand(){
        String mark = (isDone) ? "X" : " ";
        return "todo " + label +
                " /mark " + mark;

    }
}