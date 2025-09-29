import javax.naming.InsufficientResourcesException;

public class Todo extends Task {

    public Todo(String inputLabel) throws InsufficientResourcesException {
        super(inputLabel);
    }

    @Override
    public String toString() {
        return "[T]" + this.taskString();
    }

    public String toCommand(){
        String mark = (isDone) ? "X" : " ";
        return "todo " + label +
                " /mark " + mark;

    }
}