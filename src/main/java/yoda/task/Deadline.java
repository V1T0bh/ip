package yoda.task;

import javax.naming.InsufficientResourcesException;

/**
 * Represents a {@code Deadline} task in the Yoda application.
 * <p>
 * A {@code Deadline} includes an
 * additional end time or due date. It represents tasks that must be finished
 * by a specific point in time.
 * </p>
 *
 */
public class Deadline extends Todo {
    protected String end;

    /**
     * Constructs a new {@code Deadline} with the given description and end time.
     *
     * @param inputLabel the description text of the deadline task.
     * @param inputEnd   the end time or due date for the deadline.
     * @throws InsufficientResourcesException if the description or end time is empty.
     */
    public Deadline(String inputLabel, String inputEnd) throws InsufficientResourcesException {
        super(inputLabel);
        if (inputEnd.isEmpty()) {
            throw new InsufficientResourcesException();
        }
        this.end = inputEnd;
    }

    /**
     * Returns a string representation of the deadline for user display.
     * <p>
     * Example: {@code [D][ ] submit report (by: Friday)}
     * </p>
     *
     * @return a formatted string with the deadline type, completion status, label,
     *         and end time.
     */
    @Override
    public String toString() {
        return "[D]" + this.taskString() + " (by: " + this.end + ")";
    }

    /**
     * Converts this deadline into a command-like string suitable for saving to a file.
     * <p>
     * Example: {@code deadline submit report /by Friday /mark X}
     * </p>
     *
     * @return a command-like string containing this deadline's label, due date, and completion status.
     */
    public String toCommand(){
        String mark = (isDone) ? "X" : " ";
        return "deadline " + label +
                " /by " + end +
                "  /mark " + mark;

    }

}