import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class Yoda {
    // CONSTANTS
    static final Scanner SCANNER = new Scanner(System.in); //  used for user input

    static TaskList inputList = new TaskList();

    // function to ask the user for an input
    // modifies contents of Task list depending on user input
    private static void ask() {
        /* if "bye", loop terminates
         * if "list", shows previous inputs as a numbered list
         * if "mark"/"unmark", mark/unmark task accordingly
         * if "deadline"/"todo"/"event", add task accordingly
         * if "delete", deletes task accordingly
         */
        System.out.print("Yoda. Do or do not what shall I help you with? > ");
        String userInput = SCANNER.nextLine();
        while (!userInput.equals("bye")) {
            String[] keywordsArray = Parser.split(userInput);

            switch (keywordsArray[0]) {
                case "list":
                    System.out.print(inputList);
                    break;
                case "mark":
                case "unmark":
                    try {
                        int itemId = Integer.parseInt(keywordsArray[1]) - 1;
                        if (itemId >= inputList.size()) {
                            throw new TaskOutOfRangeException();
                        }
                        boolean isMark = keywordsArray[0].equals("mark");
                        inputList.mark(itemId, isMark);
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
                    inputList.add(keywordsArray, true);
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
                            inputList.delete(itemId);
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

    // function extracts and processes all tasks to tasks array from file f
    // returns a Task[] array, filled with extracted tasks
    private static void fileToArray(File f) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(f);

        while (fileScanner.hasNext()) {
            String fileInput = fileScanner.nextLine();
            String[] keywordsArray = Parser.split(fileInput);

            inputList.add(keywordsArray, false);
        }
    }

    // function uses task array and writes it to file f
    private static void arrayToFile(File f) throws IOException {
        FileWriter fWrite = new FileWriter(f);

        for (Task task : inputList.getTasks()) {
            fWrite.write(task.toCommand());
            fWrite.write("\n");
        }
        fWrite.close();
    }


    // main function that runs all other sub-functions
    public static void main(String[] args) {
        try {
            System.out.println("------------- YODA AWAKENS -------------");



            // creates dir "data/" if it does not exist
            // does nothing if it exists
            Path dir  = Paths.get("data");
            Files.createDirectories(dir);

            File userFile = new File("data/user.txt");

            if (userFile.createNewFile()) {
                System.out.println("Greetings youngling, Yoda is my name");
            } else {
                System.out.println("Welcome back youngling! It is a pleasure to see you again");
                fileToArray(userFile);
            }

            ask();
            arrayToFile(userFile);

            System.out.print("\n");
            System.out.println("Do or do not, I shall say goodbye.");
            System.out.println("------------- PROGRAM TERMINATED -------------");
        } catch (IOException e) {
            System.out.println("There were issues with the file.");
            System.out.print("ERROR MESSAGE: ");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Something went wrong!");
            System.out.print("ERROR MESSAGE: ");
            System.out.println(e.getMessage() );
        }
    }
}
