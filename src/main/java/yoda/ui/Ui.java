package yoda.ui;

import yoda.parser.Parser;
import yoda.exception.TaskOutOfRangeException;

import java.util.Scanner;

import static yoda.Yoda.inputList;
import static yoda.parser.Parser.keywordsList;

public class Ui {
    static final Scanner SCANNER = new Scanner(System.in); //  used for user input

    // prints a divider line
    public static void divider(){
        System.out.println("-----------------------------------");
    }

    // outputs text when program starts
    public static void start(boolean isNewFile) {
        System.out.println("-----------------------------------");
        System.out.println("""
                ___  _ ____  ____  ____\s
                \\  \\///  _ \\/  _ \\/  _ \\
                 \\  / | / \\|| | \\|| / \\|
                 / /  | \\_/|| |_/|| |-||
                /_/   \\____/\\____/\\_/ \\|
                                       \s""");
        System.out.println("-------------- AWAKENS -------------");

        if (isNewFile){
            System.out.println("Greetings youngling, Yoda is my name");
        } else {
            System.out.println("Welcome back youngling! It is a pleasure to see you again");
        }
    }

    public static void ask() {
        System.out.print("Do or do not what shall I help you with? > ");
    }

    public static void processCommand() throws TaskOutOfRangeException {
        int itemId;
        String userInput;

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
            default:
                System.out.println("Your instruction is invalid.");
        }
    }

    // function to ask the user for an input
    // modifies contents of yoda.task.Task list depending on user input
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

    public static void showError(String header, Exception e) {
        System.out.println(header);
        System.out.print("ERROR MESSAGE: ");
        System.out.println(e.getMessage());
    }

    public static void end() {
        System.out.print("\n");
        System.out.println("Do or do not, I shall say goodbye.");
        System.out.println("------------- PROGRAM TERMINATED -------------");
    }

}
