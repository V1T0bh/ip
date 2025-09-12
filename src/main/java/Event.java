import javax.naming.InsufficientResourcesException;

public class Event extends Deadline {
    protected String start;

    public Event(String inputLabel, String inputFrom, String inputEnd) throws InsufficientResourcesException {
        super(inputLabel, inputEnd);
        if (inputEnd.isEmpty()) {
            throw new InsufficientResourcesException();
        }
        this.start = inputFrom;
    }

    @Override
    public String toString() {
        return "[E]" + this.taskString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}