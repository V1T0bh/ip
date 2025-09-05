public class Event extends Deadline {
    protected String start;

    public Event(String inputLabel, String inputFrom, String inputEnd) {
        super(inputLabel, inputEnd);
        this.start = inputFrom;
    }

    @Override
    public String toString() {
        return "[E]" + this.taskString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}