package yoda.task;

import javax.naming.InsufficientResourcesException;

/**
 * Represents a general task.
 * <p>
 * A {@code Task} stores a label (description) and a completion status flag.
 * It serves as primitive class for derived task types such as
 * {@link Todo}, Deadline, and Event.
 * </p>
 *
 * <h2>Attributes</h2>
 * <ul>
 *   <li>{@code label} — the description text of the task.</li>
 *   <li>{@code isDone} — whether the task has been completed.</li>
 * </ul>
 *
 * <h2>Features</h2>
 * <ul>
 *   <li>Provide constructors that validate and initialize a task label.</li>
 *   <li>Expose setters for label and completion mark.</li>
 *   <li>Format itself into human-readable and command-like strings.</li>
 *   <li>Print feedback when tasks are marked/unmarked (or perform silently if required).</li>
 * </ul>
 *
 */
public class Task {
    protected String label;
    protected boolean isDone;

    /**
     * Constructs a new empty {@code Task} with an empty label.
     */
    public Task() {
        setLabel("");
    }

    /**
     * Constructs a new {@code Task} with the given label.
     *
     * @param inputLabel the description of the task.
     * @throws InsufficientResourcesException if the input label is empty.
     */
    public Task(String inputLabel) throws InsufficientResourcesException {
        if (inputLabel.isEmpty()) {
            throw new InsufficientResourcesException();
        }
        setLabel(inputLabel);
    }

    /**
     * Updates the label (description) of this task.
     *
     * @param inputLabel the new description.
     */
    public void setLabel(String inputLabel) {
        this.label = inputLabel;
    }

    /**
     * Returns a formatted string with completion mark and label.
     * <p>
     * Example: {@code [X] read book}
     * </p>
     *
     * @return a formatted task string.
     */
    protected String taskString() {
        String mark = (isDone) ? "X" : " ";
        return "[" + mark + "] " + label;
    }

    /**
     * Returns the string representation of this task for display.
     *
     * @return a formatted string with completion mark and label.
     */
    @Override
    public String toString() {
        String mark = (isDone) ? "X" : " ";
        return "[" + mark + "] " + label;
    }

    /**
     * Converts this task into a command-like string suitable for storage.
     *
     * @return a command-like string.
     */
    public String toCommand(){
        String mark = (isDone) ? "X" : " ";
        return "task " + label +
                " /mark " + mark;

    }

    /**
     * Marks or unmarks the task, printing feedback to the user.
     *
     * @param inputMark {@code true} to mark as done, {@code false} to unmark.
     */
    public void setMark(boolean inputMark) {
        this.isDone = inputMark;
        if (inputMark) {
            System.out.println("Affirmative! Marked have been the task:");
        } else {
            System.out.println("Oh no, I shall unmark thy task:");
        }
        System.out.println(this);
    }

    /**
     * Marks or unmarks the task silently, without printing feedback.
     *
     * @param inputMark {@code true} to mark as done, {@code false} to unmark.
     */
    public void setMarkSilent(boolean inputMark) {
        this.isDone = inputMark;
    }
}