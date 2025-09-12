import javax.naming.InsufficientResourcesException;

public class Todo extends Task {
    protected boolean isDone;

    public Todo(String inputLabel) throws InsufficientResourcesException {
        super(inputLabel);
    }

    @Override
    public String toString() {
        return "[T]" + this.taskString();
    }
}