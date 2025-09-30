package yoda.task;

import javax.naming.InsufficientResourcesException;

/**
 * Represents an {@code Event} task in the Yoda application.
 * <p>
 * An {@code Event} includes both a start
 * and an end time. It represents tasks that run for a
 * certain interval of time.
 * </p>
 */
public class Event extends Deadline {
    protected String start;

    /**
     * Constructs a new {@code Event} with the given description, start time, and end time.
     *
     * @param inputLabel the description text of the event.
     * @param inputFrom  the start time of the event.
     * @param inputEnd   the end time of the event.
     * @throws InsufficientResourcesException if the description or end time is empty.
     */
    public Event(String inputLabel, String inputFrom, String inputEnd) throws InsufficientResourcesException {
        super(inputLabel, inputEnd);
        if (inputEnd.isEmpty()) {
            throw new InsufficientResourcesException();
        }
        this.start = inputFrom;
    }

    /**
     * Returns a string representation of the event for user display.
     * <p>
     * Example: {@code [E][ ] project meeting (from: Mon 2pm to: Mon 4pm)}
     * </p>
     *
     * @return a formatted string with the event type, completion status, label,
     *         and start–end interval.
     */
    @Override
    public String toString() {
        return "[E]" + this.taskString() + " (from: " + this.start + " to: " + this.end + ")";
    }

    /**
     * Converts this event into a command-like string suitable for saving to a file.
     * <p>
     * Example: {@code event project meeting /from 2pm /to 4pm /mark X}
     * </p>
     *
     * @return a commandd-like string containing this event's label, start, end time, and completion status.
     */
    public String toCommand(){
        String mark = (isDone) ? "X" : " ";
        return "event " + label +
                " /from " + start +
                " /to " + end +
                " /mark " + mark;

    }
}