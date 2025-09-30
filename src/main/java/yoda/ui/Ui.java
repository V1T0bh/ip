package yoda.ui;

import yoda.parser.Parser;
import yoda.exception.TaskOutOfRangeException;
import yoda.Yoda;

import java.util.Scanner;

public class Ui {
    static final Scanner SCANNER = new Scanner(System.in); //  used for user input

    // outputs text when program starts
    public static void start(boolean isNewFile) {
        System.out.println("------------- YODA AWAKENS -------------");

        if (isNewFile){
            System.out.println("Greetings youngling, yoda.Yoda is my name");
        } else {
            System.out.println("Welcome back youngling! It is a pleasure to see you again");
        }
    }

    public static void ask() {
        System.out.print("Do or do not what shall I help you with? > ");
    }

    // function to ask the user for an input
    // modifies contents of yoda.task.Task list depending on user input
    public static void run() throws TaskOutOfRangeException {
        /* if "bye", loop terminates
         * if "list", shows previous inputs as a numbered list
         * if "mark"/"unmark", mark/unmark task accordingly
         * if "deadline"/"todo"/"event", add task accordingly
         * if "delete", deletes task accordingly
         */
        int itemId;

        ask();

        String userInput = SCANNER.nextLine();
        while (!userInput.equals("bye")) {
            String[] keywordsArray = Parser.split(userInput);

            switch (keywordsArray[0]) {
                case "list":
                    System.out.print(Yoda.inputList);
                    break;
                case "mark":
                case "unmark":
                    itemId = Integer.parseInt(keywordsArray[1]) - 1;
                    if (itemId >= Yoda.inputList.size()) {
                        throw new TaskOutOfRangeException();
                    }
                    boolean isMark = keywordsArray[0].equals("mark");
                    Yoda.inputList.mark(itemId, isMark);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    Yoda.inputList.add(keywordsArray, true);
                    break;
                case "delete":
                    itemId = Integer.parseInt(keywordsArray[1]) - 1;
                    String TaskCache = String.valueOf(Yoda.inputList.get(itemId));
                    // confirmation message
                    System.out.println("This task will be PERMANENTLY deleted:");
                    System.out.println(TaskCache);
                    System.out.print("Are you sure? (Y/n) > ");
                    userInput = SCANNER.nextLine();

                    if (userInput.equals("Y")) {
                        Yoda.inputList.delete(itemId);
                        System.out.println("Successfully deleted!");
                    } else {
                        System.out.println("Delete command aborted...");
                    }
                    break;
                default:
                    System.out.println("Your instruction is invalid.");
            }

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
