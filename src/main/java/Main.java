import javax.naming.InsufficientResourcesException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class Main {
    // CONSTANTS
    static final Scanner SCANNER = new Scanner(System.in); //  used for user input
    static final int MAX_TASKS = 100; // max no. of tasks

    private static int tasksArrLength;

    // function to split the input to array
    // [TASK TYPE, TASK LABEL, TASK START, TASK END], if exists
    private static String[] splitInput(String userInput) {
        final int NO_OF_KEYWORDS = 5;
        String[] finalArray = {"", "", "", "", ""};
        String[] splitArray = userInput.split(" ");

        // TYPE is first word
        finalArray[0] = splitArray[0];

        if (splitArray.length == 1) {
            return finalArray;
        }

        // LABEL, START, END
        int i = 1;
        for (int j = 1; j < NO_OF_KEYWORDS; j++) {
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
    private static void printTaskList(Task[] taskList, int count) {
        if (count == 0) {
            System.out.println("This list of yours looks empty...");
        } else {
            System.out.println("You have " + count + " tasks:");
            for (int i = 0; i < count; i++) {
                System.out.print((i + 1) + ". ");
                System.out.println(taskList[i]);
            }
        }
    }

    // function that marks a task in a task list, depending on index
    // prints a success message if task is found
    private static void markTaskList(Task[] taskList, int count, int index, boolean isMark) {
        if (index < 0 & index >= count) {
            System.out.println("Funny. This ID matches no task of yours.");
        } else {
            taskList[index].setMark(isMark);
        }
    }

    // function that adds a task to a task list
    // prints either a success or error message
    // returns the new value of count
    private static int addTaskList(Task[] taskList, String[] keywordsArray, int count, boolean show) {
        try {
            int lastIndex = 0;
            Task newTask = new Task();
            if (keywordsArray[1].isEmpty()) {
                throw new InsufficientResourcesException();
            }
            switch (keywordsArray[0]) {
                case "todo":
                    newTask = new Todo(keywordsArray[1]);
                    lastIndex = 2;
                    break;
                case "deadline":
                    newTask = new Deadline(keywordsArray[1], keywordsArray[2]);
                    lastIndex = 3;
                    break;
                case "event":
                    newTask = new Event(keywordsArray[1], keywordsArray[2], keywordsArray[3]);
                    lastIndex = 4;
                    break;
            }
            taskList[count] = newTask;
            if (show) {
                // expected: entry through user input
                System.out.println("Successfully added: ");
                System.out.println(taskList[count]);
            } else {
                // expected: entry through file input
                if (keywordsArray[lastIndex].equals("X")) {
                    taskList[count].setMarkSilent(true);
                }
            }
            count += 1;
        } catch (InsufficientResourcesException e) {
            System.out.print("SHEESHHH!! ");
            System.out.println("Your command has not enough arguments!");
            System.out.println("Your task was not added.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("My condolences, it seems you have too much on your plate.");
        }
        return count;
    }

    // function to ask the user for an input
    private static Task[] ask(Task[] inputList) {
        /* if "bye", loop terminates
         * if "list", shows previous inputs as a numbered list
         * if "mark"/"unmark", mark/unmark task accordingly
         * if "deadline"/"todo"/"event", add task accordingly
         */
        int count = tasksArrLength;

        System.out.print("Yoda. Do or do not what shall I help you with? > ");
        String userInput = SCANNER.nextLine();
        while (!userInput.equals("bye")) {
            String[] keywordsArray = splitInput(userInput);

            switch (keywordsArray[0]) {
                case "list":
                    printTaskList(inputList, count);
                    break;
                case "mark":
                case "unmark":
                    try {
                        int itemId = Integer.parseInt(keywordsArray[1]) - 1;
                        if (itemId >= count) {
                            throw new TaskOutOfRangeException();
                        }
                        boolean isMark = keywordsArray[0].equals("mark");
                        markTaskList(inputList, count, itemId, isMark);
                    } catch (NumberFormatException e) {
                        System.out.println("Input is invalid! Try to mark/unmark again.");
                    } catch (TaskOutOfRangeException e) {
                        System.out.println("Task is out of range!");
                        System.out.println("You have " + count + " tasks.");
                    }
                    break;
                case "todo":
                case "deadline":
                case "event":
                    count = addTaskList(inputList, keywordsArray, count, true);
                    break;
                default:
                    System.out.println("Your instruction is invalid.");
            }

            System.out.print("Yoda. Do or do not what shall I help you with? > ");
            userInput = SCANNER.nextLine();
        }

        return inputList;
    }

    // function extracts and processes all tasks to tasks array from file f
    // returns a Task[] array, filled with extracted tasks
    private static Task[] fileToArray(File f) throws FileNotFoundException {
        Task[] tempArray = new Task[MAX_TASKS];
        Scanner fileScanner = new Scanner(f);

        while (fileScanner.hasNext()) {
            String fileInput = fileScanner.nextLine();
            String[] keywordsArray = splitInput(fileInput);

            tasksArrLength = addTaskList(tempArray, keywordsArray, tasksArrLength, false);
        }

        return tempArray;
    }

    // function takes a tasks array and writes it to file f
    private static void arrayToFile(Task[] tasksArr, File f) throws IOException {
        /*FileWriter fWrite = new FileWriter(f);



        //fWrite.write("Files in Java might be tricky, but it is fun enough!");
        fWrite.close();  // must close manually
        System.out.println("Successfully wrote to the file.");*/

        System.out.println("<Array converted to file. Success!>");
    }


    // main function that runs all other sub-functions
    public static void main(String[] args) throws IOException {
        try {
            System.out.println("------------- YODA AWAKENS -------------");


            Task[] tasksArrayFromFile;

            // creates dir "data/" if it does not exist
            // does nothing if it exists
            Path dir  = Paths.get("data");
            Files.createDirectories(dir);

            File userFile = new File("data/user.txt");
            tasksArrLength = 0;

            if (userFile.createNewFile()) {
                System.out.println("Greetings youngling, Yoda is my name");
                tasksArrayFromFile = new Task[MAX_TASKS];
            } else {
                System.out.println("Welcome back youngling! It is a pleasure to see you again");
                tasksArrayFromFile = fileToArray(userFile);
            }

            Task[] newtasksArray = ask(tasksArrayFromFile);
            arrayToFile(tasksArrayFromFile, userFile);

            System.out.print("\n");
            System.out.println("Do or do not, I shall say goodbye.");
            System.out.println("------------- PROGRAM TERMINATED -------------");
        } catch (IOException e) {
            System.out.println("There were issues with the file.");
            System.out.print("ERROR MESSAGE: ");
            System.out.println(e);
        } catch (Exception e) {
            System.out.println("Something went wrong!");
            System.out.print("ERROR MESSAGE: ");
            System.out.println(e);
        }
    }
}
