public class Deadline extends Todo {
    protected String end;

    public Deadline(String inputLabel, String inputEnd) {
        super(inputLabel);
        this.end = inputEnd;
    }

    @Override
    public String toString() {
        return "[D]" + this.taskString() + " (by: " + this.end + ")";
    }

}