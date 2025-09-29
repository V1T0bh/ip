import javax.naming.InsufficientResourcesException;

/*
        Task class
        Attributes: label, isDone
        methods: setMark, setLabel, toString
     */
public class Task {
    protected String label;
    protected boolean isDone;

    public Task() {
        setLabel("");
    }

    public Task(String inputLabel) throws InsufficientResourcesException {
        if (inputLabel.isEmpty()) {
            throw new InsufficientResourcesException();
        }
        setLabel(inputLabel);
    }

    public void setLabel(String inputLabel) {
        this.label = inputLabel;
    }

    protected String taskString() {
        String mark = (isDone) ? "X" : " ";
        return "[" + mark + "] " + label;
    }

    @Override
    public String toString() {
        String mark = (isDone) ? "X" : " ";
        return "[" + mark + "] " + label;
    }

    public void setMark(boolean inputMark) {
        this.isDone = inputMark;
        if (inputMark) {
            System.out.println("Affirmative! Marked have been the task:");
        } else {
            System.out.println("Oh no, I shall unmark thy task:");
        }
        System.out.println(this);
    }

    public void setMarkSilent(boolean inputMark) {
        this.isDone = inputMark;
    }
}