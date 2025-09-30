package yoda.task;

import javax.naming.InsufficientResourcesException;

public class Deadline extends Todo {
    protected String end;

    public Deadline(String inputLabel, String inputEnd) throws InsufficientResourcesException {
        super(inputLabel);
        if (inputEnd.isEmpty()) {
            throw new InsufficientResourcesException();
        }
        this.end = inputEnd;
    }

    @Override
    public String toString() {
        return "[D]" + this.taskString() + " (by: " + this.end + ")";
    }

    public String toCommand(){
        String mark = (isDone) ? "X" : " ";
        return "deadline " + label +
                " /by " + end +
                "  /mark " + mark;

    }

}