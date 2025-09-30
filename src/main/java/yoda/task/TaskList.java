package yoda.task;

import yoda.exception.InvalidCommandException;
import yoda.exception.TaskOutOfRangeException;

import javax.naming.InsufficientResourcesException;
import java.util.ArrayList;

import static yoda.parser.Parser.keywordsList;

public class TaskList {
    private final ArrayList<Task> List;

    public TaskList() {
        List = new ArrayList<>();
    }

    // returns the Array List of tasks
    public ArrayList<Task> getTasks() {
        return List;
    }

    // takes an index and returns the task in that index
    public Task get(int index){
        return List.get(index);
    }

    // returns the size of List
    public int size() {
        return List.size();
    }

    @Override
    // function returns a string:
    // returns the list (if not empty)
    // returns a message (if empty)
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

    // function that adds a task to a task list
    // prints either a success or error message
    // returns the new value of count
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

    // function that removes a task from a task list based on given index
    // if task exists, task is removed
    // if task does not exist, throws an error
    public void delete(int index) throws TaskOutOfRangeException {
        if (index < 0 || index >= List.size()) {
            throw new TaskOutOfRangeException();
        } else {
            List.remove(index);
        }
    }

    // function that marks a task in a task list, depending on index
    // prints a success message if task is found
    public void mark(int index, boolean isMark) {
        if (index < 0 | index >= List.size()) {
            System.out.println("Funny. This ID matches no task of yours.");
        } else {
            Task newTask = List.get(index);
            newTask.setMark(isMark);
            List.set(index, newTask);
        }
    }


}
