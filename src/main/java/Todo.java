public class Todo extends Task {
    protected boolean isDone;

    public Todo(String inputLabel) {
        super(inputLabel);
    }

    @Override
    public String toString() {
        return "[T]" + this.taskString();
    }
}