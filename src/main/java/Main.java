import javax.naming.InsufficientResourcesException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    // CONSTANTS
    static final Scanner SCANNER = new Scanner(System.in); //  used for user input
    static final int MAX_TASKS = 100; // max no. of tasks
    static ArrayList<Task> inputList = new ArrayList<>();

    // function to split the input to array
    // [TASK TYPE, TASK LABEL, TASK START, TASK END], if exists
    public static String[] splitInput(String userInput) {
        String[] finalArray = {"", "", "", ""};
        String[] splitArray = userInput.split(" ");

        // TYPE is first word
        finalArray[0] = splitArray[0];

        if (splitArray.length == 1) {
            return finalArray;
        }

        // LABEL, START, END
        int i = 1;
        for (int j = 1; j < 4; j++) {
            boolean hasExceedLength = i >= splitArray.length;
            boolean wordContainsSlash = hasExceedLength || splitArray[i].contains("/");
            while (!(hasExceedLength || wordContainsSlash)) {
                finalArray[j] += splitArray[i] + " ";
                i += 1;

                hasExceedLength = i >= splitArray.length;
                wordContainsSlash = hasExceedLength || splitArray[i].contains("/");
            }
            finalArray[j] = finalArray[j].strip();

            // skips the word containing "/" (e.g. /by)
            i += 1;
        }

        return finalArray;
    }

    // function that takes in a task list and count
    // prints out the list (if not empty)
    // prints out a message (if empty)
    public static void printTaskList(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            System.out.println("This list of yours looks empty...");
        } else {
            System.out.println("You have " + taskList.size() + " tasks:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.print((i + 1) + ". ");
                System.out.println(taskList.get(i));
            }
        }
    }

    // function that marks a task in a task list, depending on index
    // prints a success message if task is found
    public static void markTaskList(ArrayList<Task> taskList, int index, boolean isMark) {
        if (index < 0 | index >= taskList.size()) {
            System.out.println("Funny. This ID matches no task of yours.");
        } else {
            Task newTask = taskList.get(index);
            newTask.setMark(isMark);
            taskList.set(index, newTask);
        }
    }

    // function that adds a task to a task list
    // prints either a success or error message
    // returns the new value of count
    public static void addTaskList(ArrayList<Task> taskList, String[] keywordsArray) {
        try {
            Task newTask = new Task();
            if (keywordsArray[1].isEmpty()) {
                throw new InsufficientResourcesException();
            }
            switch (keywordsArray[0]) {
                case "todo":
                    newTask = new Todo(keywordsArray[1]);
                    break;
                case "deadline":
                    newTask = new Deadline(keywordsArray[1], keywordsArray[2]);
                    break;
                case "event":
                    newTask = new Event(keywordsArray[1], keywordsArray[2], keywordsArray[3]);
                    break;
            }
            taskList.add(newTask);
            System.out.println("Successfully added: ");
            System.out.println(taskList.getLast());
        } catch (InsufficientResourcesException e) {
            System.out.print("SHEESHHH!! ");
            System.out.println("Your command has not enough arguments!");
            System.out.println("Your task was not added.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("My condolences, it seems you have too much on your plate.");
        }
    }

    public static void deleteTaskList(ArrayList<Task> taskList, int index) throws TaskOutOfRangeException {
        if (index < 0 || index >= taskList.size()) {
            throw new TaskOutOfRangeException();
        } else {
            taskList.remove(index);
        }
    }

    // function to ask the user for an input
    public static void ask() {
        /* if "bye", loop terminates
         * if "list", shows previous inputs as a numbered list
         * if "mark"/"unmark", mark/unmark task accordingly
         * if "deadline"/"todo"/"event", add task accordingly
         * if "delete", deletes task accordingly
         */
        System.out.print("Yoda. Do or do not what shall I help you with? > ");
        String userInput = SCANNER.nextLine();
        while (!userInput.equals("bye")) {
            String[] keywordsArray = splitInput(userInput);

            switch (keywordsArray[0]) {
                case "list":
                    printTaskList(inputList);
                    break;
                case "mark":
                case "unmark":
                    try {
                        int itemId = Integer.parseInt(keywordsArray[1]) - 1;
                        if (itemId >= inputList.size()) {
                            throw new TaskOutOfRangeException();
                        }
                        boolean isMark = keywordsArray[0].equals("mark");
                        markTaskList(inputList, itemId, isMark);
                    } catch (NumberFormatException e) {
                        System.out.println("Input is invalid! Try to mark/unmark again.");
                    } catch (TaskOutOfRangeException e) {
                        System.out.println("Task is out of range!");
                        System.out.println("You have " + inputList.size() + " tasks.");
                    }
                    break;
                case "todo":
                case "deadline":
                case "event":
                    addTaskList(inputList, keywordsArray);
                    break;
                case "delete":
                    try {
                        int itemId = Integer.parseInt(keywordsArray[1]) - 1;
                        String TaskCache = String.valueOf(inputList.get(itemId));
                        // confirmation message
                        System.out.println("This task will be PERMANENTLY deleted:");
                        System.out.println(TaskCache);
                        System.out.print("Are you sure? (Y/n) > ");
                        userInput = SCANNER.nextLine();

                        if (userInput.equals("Y")) {
                            deleteTaskList(inputList, itemId);
                            System.out.println("Successfully deleted!");
                        } else {
                            System.out.println("Delete command aborted...");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Input is invalid! Try to delete again.");
                    } catch (TaskOutOfRangeException | IndexOutOfBoundsException e) {
                        System.out.println("Task is out of range!");
                        System.out.println("You have " + inputList.size() + " tasks.");
                    }
                    break;
                default:
                    System.out.println("Your instruction is invalid.");
            }

            System.out.print("Yoda. Do or do not what shall I help you with? > ");
            userInput = SCANNER.nextLine();
        }
    }

    // main function that runs all other sub-functions
    public static void main(String[] args) {
        System.out.println("------------- YODA AWAKENS -------------");
        System.out.println("Greetings youngling, Yoda is my name");
        ask();

        System.out.print("\n");
        System.out.println("Do or do not, I shall say goodbye.");
        System.out.println("------------- PROGRAM TERMINATED -------------");
    }
}
