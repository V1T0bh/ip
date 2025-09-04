// follows coding standards CHECKED
import java.util.Scanner;

public class Yoda {
    // CONSTANTS
    static final Scanner SCANNER = new Scanner(System.in); //  used for user input
    static final int MAX_TASKS = 100; // max no. of tasks

    /*
        Task class
        Attributes: type, label, isDone
        methods: print, mark, unmark
     */
    public static class Task {
        protected String label;
        protected boolean isDone;

        public Task(){
            setLabel("");
        }

        public Task(String inputLabel) {
            setLabel(inputLabel);
        }

        public void setLabel(String inputLabel) {
            this.label = inputLabel;
        }

        protected String taskString(){
            String mark = (isDone) ? "X" : " ";
            return "["+mark+"] " + label;
        }

        @Override
        public String toString() {
            String mark = (isDone) ? "X" : " ";
            return "["+mark+"] " + label;
        }

        public void setMark(boolean inputMark){
            this.isDone = inputMark;
            if (inputMark) {
                System.out.println("Affirmative! Marked have been the task:");
            } else {
                System.out.println("Oh no, I shall unmark thy task:");
            }
            System.out.println(this);
        }
    }

    public static class Todo extends Task {
        protected boolean isDone;

        public Todo(String inputLabel) {
            super(inputLabel);
        }

        @Override
        public String toString() {
            return "[T]"+ this.taskString();
        }
    }

    public static class Deadline extends Todo {
        protected String end;

        public Deadline(String inputLabel, String inputEnd) {
            super(inputLabel);
            this.end = inputEnd;
        }

        @Override
        public String toString() {
            return "[D]"+ this.taskString() + " (by: " + this.end + ")";
        }

    }

    public static class Event extends Deadline {
        protected String start;

        public Event(String inputLabel, String inputFrom, String inputEnd){
            super(inputLabel, inputEnd);
            this.start = inputFrom;
        }

        @Override
        public String toString() {
            return "[E]"+ this.taskString() + " (from: " + this.start + " to: " + this.end + ")";
        }
    }


    // function to split the input to array
    // [TASK TYPE, TASK LABEL, TASK START, TASK END], if exists
    public static String[] splitInput(String userInput){
        String[] finalArray = {"","","",""};
        String[] splitArray = userInput.split(" ");

        // TYPE is first word
        finalArray[0] = splitArray[0];

        if (splitArray.length == 1) {
            return finalArray;
        }

        // LABEL, START, END
        int i = 1;
        for (int j = 1; j < 4; j ++) {
            boolean hasExceedLength = i >= splitArray.length;
            boolean wordContainsSlash = (!hasExceedLength) ? splitArray[i].contains("/") : true;
            while (!(hasExceedLength || wordContainsSlash)) {
                finalArray[j] += splitArray[i] + " ";
                i += 1;

                hasExceedLength = i >= splitArray.length;
                wordContainsSlash = (!hasExceedLength) ? splitArray[i].contains("/") : true;
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
    public static void printTaskList(Task taskList[], int count){
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
    public static void markTaskList(Task taskList[], int count, int index, boolean isMark){
        if (index < 0 & index >= count){
            System.out.println("Funny. This ID matches no task of yours.");
        } else {
            taskList[index].setMark(isMark);
        }
    }

    // function that adds a task to a task list
    // prints either a success or error message
    // returns the new value of count
    public static int addTaskList(Task[] taskList, String[] keywordsArray, int count){
        if (count >= MAX_TASKS){
            System.out.println("My condolences, it seems you have too much on your plate.");
            System.out.println("Your task was not added.");
        } else {
            Task newTask = new Task();
            switch (keywordsArray[0]){
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
            taskList[count] = newTask;
            System.out.println("Successfully added: ");
            System.out.println(taskList[count]);
            count += 1;
        }
        return count;
    }

    // function to ask the user for an input
    public static void ask() {
        // Initialize input variable, empty task list and item count
        Task[] inputList = new Task[MAX_TASKS];
        int count = 0;

        /* if "bye", loop terminates
         * if "list", shows previous inputs as a numbered list
         * if "mark"/"unmark", mark/unmark task accordingly
         * if "deadline"/"todo"/"event", add task accordingly
         */
        System.out.print("Yoda. Do or do not what shall I help you with? > ");
        String userInput =  SCANNER.nextLine();
        while (!userInput.equals("bye")) {
            String[] keywordsArray = splitInput(userInput);

            switch (keywordsArray[0]){
                case "list":
                    printTaskList(inputList, count);
                    break;
                case "mark":
                case "unmark":
                    int itemId = Integer.parseInt(keywordsArray[1]) - 1;
                    boolean isMark = keywordsArray[0].equals("mark");
                    markTaskList(inputList, count, itemId, isMark);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    count = addTaskList(inputList, keywordsArray, count);
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
