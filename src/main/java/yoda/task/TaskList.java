package yoda.task;

import yoda.exception.InvalidCommandException;
import yoda.exception.TaskOutOfRangeException;

import javax.naming.InsufficientResourcesException;
import java.util.ArrayList;

import static yoda.Yoda.inputList;
import static yoda.parser.Parser.keywordsList;

/**
 * Represents the list of tasks during the execution of the Yoda application.
 * <p>
 * A {@code TaskList} utilizes an {@link ArrayList} of {@link Task} objects and
 * provides operations for adding, deleting, marking, listing, and filtering tasks.
 * </p>
 *
 * <h2>Usage</h2>
 * <p>
 * A {@code TaskList} is typically created at startup and populated
 * either from persistent storage or user commands.
 * </p>
 */
public class TaskList {
    private final ArrayList<Task> List;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        List = new ArrayList<>();
    }

    /**
     * Returns the underlying list of tasks.
     *
     * @return the backing {@link ArrayList}.
     */
    public ArrayList<Task> getTasks() {
        return List;
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index the zero-based index.
     * @return the task at the given index.
     * @throws IndexOutOfBoundsException if the index is invalid.
     */
    public Task get(int index){
        return List.get(index);
    }

    /**
     * Returns the number of tasks in this list.
     *
     * @return the size of the list.
     */
    public int size() {
        return List.size();
    }

    /**
     * Returns a string representation of this task list.
     * <p>
     * If the list is empty, a message is returned.
     * Otherwise, a numbered listing of all tasks is returned.
     * </p>
     *
     * @return a human-readable string of all tasks.
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        if (List.isEmpty()) {
            out = new StringBuilder("This list of yours looks empty..." + "\n");
        } else {
            System.out.println("You have " + List.size() + " tasks:");
            for (int i = 0; i < List.size(); i++) {
                out.append((i + 1)).append(". ");
                out.append(List.get(i)).append("\n");
            }
        }
        return out.toString();
    }

    /**
     * Adds a new task to this list based on the current contents of {@link yoda.parser.Parser#keywordsList}.
     * <p>
     * This method supports both:
     * </p>
     * <ul>
     *   <li>User-driven additions (with success messages when {@code show = true}).</li>
     *   <li>File-driven additions (silent, with mark status restored when {@code show = false}).</li>
     * </ul>
     *
     * @param show whether to print user-facing success messages.
     */
    public void add(boolean show) {
        try {
            int lastIndex;
            Task newTask;
            if (keywordsList.get(1).isEmpty()) {
                throw new InsufficientResourcesException();
            }
            switch (keywordsList.get(0)) {
                case "todo":
                    newTask = new Todo(keywordsList.get(1));
                    lastIndex = 2;
                    break;
                case "deadline":
                    newTask = new Deadline(keywordsList.get(1), keywordsList.get(2));
                    lastIndex = 3;
                    break;
                case "event":
                    newTask = new Event(keywordsList.get(1), keywordsList.get(2), keywordsList.get(3));
                    lastIndex = 4;
                    break;
                default:
                    throw new InvalidCommandException(keywordsList.get(0) + " is not a valid command...");
            }
            List.add(newTask);
            if (show) {
                // expected: entry through user input
                System.out.println("Successfully added: ");
                System.out.println(List.get(List.size() - 1));
            } else {
                // expected: entry through file input
                if (keywordsList.get(lastIndex).equals("X")) {
                    newTask = List.get(List.size() - 1);
                    newTask.setMarkSilent(true);
                    List.set(List.size()-1, newTask);
                }
            }
        } catch (InsufficientResourcesException e) {
            System.out.print("SHEESHHH!! ");
            System.out.println("Your command has not enough arguments!");
            System.out.println("Your task was not added.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("My condolences, it seems you have too much on your plate.");
        } catch (InvalidCommandException e) {
            System.out.println("Invalid command found.");
            System.out.println("Error: " + e);
            System.out.println("Line skipped.");
        }
    };

    /**
     * Removes a task at the specified index.
     *
     * @param index the zero-based index.
     * @throws TaskOutOfRangeException if the index is invalid.
     */
    public void delete(int index) throws TaskOutOfRangeException {
        if (index < 0 || index >= List.size()) {
            throw new TaskOutOfRangeException();
        } else {
            List.remove(index);
        }
    }

    /**
     * Marks or unmarks a task at the specified index.
     * <p>
     * If the index is invalid, a message is printed instead of throwing an exception.
     * </p>
     *
     * @param index  the zero-based index of the task.
     * @param isMark {@code true} to mark as done, {@code false} to unmark.
     */
    public void mark(int index, boolean isMark) {
        if (index < 0 | index >= List.size()) {
            System.out.println("Funny. This ID matches no task of yours.");
        } else {
            Task newTask = List.get(index);
            newTask.setMark(isMark);
            List.set(index, newTask);
        }
    }

    /**
     * Returns a filtered list of tasks whose string representation contains the given filter text.
     * <p>
     * This method operates on the global {@link yoda.Yoda#inputList}.
     * </p>
     *
     * @param filter the substring to search for in task descriptions.
     * @return a new list of tasks matching the filter.
     */
    public static ArrayList<Task> filterTasks(String filter) {
        ArrayList<Task> filteredList = new ArrayList<>();

        for (Task task : inputList.getTasks()) {
            if (String.valueOf(task).contains(filter)) {
                filteredList.add(task);
            }
        }

        return filteredList;
    }

}
