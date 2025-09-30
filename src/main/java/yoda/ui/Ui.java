package yoda.ui;

import yoda.parser.Parser;
import yoda.exception.TaskOutOfRangeException;
import yoda.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

import static yoda.Yoda.inputList;
import static yoda.parser.Parser.keywordsList;

/**
 * The {@code Ui} class handles all user interaction for the Yoda application.
 *
 * <p>
 * It (attempts to) boasts the vocabulary of a Star Wars character. Giving
 * the user an immersive experience.
 * </p>
 */
public class Ui {
    static final Scanner SCANNER = new Scanner(System.in); //  used for user input

    /**
     * Prints a horizontal divider line to separate sections of console output.
     */
    public static void divider(){
        System.out.println("----------------------------------------------");
    }

    // outputs text when program starts
    /**
     * Prints the startup banner and a greeting that depends on whether the data file is new.
     *
     * @param isNewFile true if this is the first time the data file is created; false otherwise.
     */
    public static void start(boolean isNewFile) {
        divider();
        System.out.println("""
                ___  _ ____  ____  ____\s
                \\  \\///  _ \\/  _ \\/  _ \\
                 \\  / | / \\|| | \\|| / \\|
                 / /  | \\_/|| |_/|| |-||
                /_/   \\____/\\____/\\_/ \\|
                                       \s""");
        System.out.println("------------------- AWAKENS ------------------");

        if (isNewFile){
            System.out.println("Greetings youngling, Yoda is my name");
        } else {
            System.out.println("Welcome back youngling! It is a pleasure to see you again");
        }
    }

    /**
     * Prints the iconic Yoda quote while asking the user for the next command.
     */
    public static void ask() {
        System.out.print("Do or do not what shall I help you with? > ");
    }

    /**
     * Processes the most recently parsed command stored in {@code keywordsList}.
     * <p>
     * Commands include:
     * </p>
     * <ul>
     *   <li>{@code list} — prints the task list.</li>
     *   <li>{@code mark} / {@code unmark} — marks/unmarks a task.</li>
     *   <li>{@code todo}, {@code deadline}, {@code event} — adds a task based on user input.</li>
     *   <li>{@code delete} — deletes the specified task after confirmation.</li>
     *   <li>{@code find} — shows tasks whose text contains the keyword.</li>
     * </ul>
     *
     *
     * @throws TaskOutOfRangeException if a task index is out of bounds for the current {@code TaskList}.
     * @throws NumberFormatException   if a numeric argument cannot be parsed (e.g., invalid task index).
     */
    public static void processCommand() throws TaskOutOfRangeException {
        int itemId;
        String userInput;
        ArrayList<Task> filteredList;

        switch (keywordsList.get(0)) {
            case "list":
                System.out.print(inputList);
                break;
            case "mark":
            case "unmark":
                itemId = Integer.parseInt(keywordsList.get(1)) - 1;
                if (itemId >= inputList.size()) {
                    throw new TaskOutOfRangeException();
                }
                boolean isMark = keywordsList.get(0).equals("mark");
                inputList.mark(itemId, isMark);
                break;
            case "todo":
            case "deadline":
            case "event":
                inputList.add(true);
                break;
            case "delete":
                itemId = Integer.parseInt(keywordsList.get(1)) - 1;
                // confirmation message
                System.out.println("This task will be PERMANENTLY deleted:");
                System.out.println(inputList.get(itemId));
                System.out.print("Are you sure? (Y/n) > ");
                userInput = SCANNER.nextLine();

                if (userInput.equals("Y")) {
                    inputList.delete(itemId);
                    System.out.println("Successfully deleted!");
                } else {
                    System.out.println("Delete command aborted...");
                }
                break;
            case "find":
                filteredList = inputList.filterTasks(keywordsList.get(1));

                if (filteredList.isEmpty()) {
                    System.out.println("*cough* All I found was dust. *cough*");
                } else {
                    System.out.println("Ahh. I found these in your pocket:");
                    for (Task task : filteredList) {
                        System.out.println(task);
                    }
                }

                break;
            default:
                System.out.println("Your instruction is invalid.");
        }
    }

    // function to ask the user for an input
    // modifies contents of yoda.task.Task list depending on user input
    /**
     * Runs the interactive command loop (REPL) until the user enters {@code bye}.
     * <p>
     * For each iteration:
     * </p>
     * <ol>
     *   <li>Prompts the user for input.</li>
     *   <li>Parses input using {@code Parser.split(String)}.</li>
     *   <li>Executes the command via {@link #processCommand()}.</li>
     *   <li>Displays errors consistently using {@link #showError(String, Exception)}.</li>
     * </ol>
     * <p>
     * Valid commands include: {@code list}, {@code mark}, {@code unmark}, {@code todo}, {@code deadline},
     * {@code event}, {@code delete}, {@code find}, and {@code bye}.
     * </p>
     */
    public static void run() {
        /* if "bye", loop terminates
         * if "list", shows previous inputs as a numbered list
         * if "mark"/"unmark", mark/unmark task accordingly
         * if "deadline"/"todo"/"event", add task accordingly
         * if "delete", deletes task accordingly
         */

        ask();

        String userInput = SCANNER.nextLine();
        while (!userInput.equals("bye")) {
            Parser.split(userInput);

            divider();
            try {
                processCommand();
            } catch (NumberFormatException e) {
                showError("Number input is invalid! Try again.", e);
            } catch (TaskOutOfRangeException | IndexOutOfBoundsException e) {
                showError("Task is out of range!\n" +
                        "You have " + inputList.size() + " tasks.", e);
            } catch (Exception e) {
                showError("Something went wrong!", e);
            }
            divider();

            ask();
            userInput = SCANNER.nextLine();
        }

    }

    /**
     * Prints a formatted error with a custom heading and the exception message.
     *
     * @param header human-readable error heading to display.
     * @param e      the thrown exception whose message will be shown.
     */
    public static void showError(String header, Exception e) {
        System.out.println(header);
        System.out.print("ERROR MESSAGE: ");
        System.out.println(e.getMessage());
    }

    /**
     * Prints the farewell message along with the iconic Yoda line one last time and a termination banner.
     * Intended to be called when the application is about to exit.
     */
    public static void end() {
        System.out.print("\n");
        System.out.println("Do or do not, I shall say goodbye.");
        System.out.println("------------- PROGRAM TERMINATED -------------");
    }

}
