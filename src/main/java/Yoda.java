// follows coding standards CHECKED
import java.util.Scanner;

public class Yoda {
    // CONSTANTS
    static final Scanner SCANNER = new Scanner(System.in); //  used for user input
    static final int MAX_TASKS = 100; // max no. of tasks

    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    /*
        Task class
        Attributes: type, label, isDone
        methods: print, mark, unmark
     */
    public static class Task {
        public TaskType type = TaskType.TODO;
        public String label = "";
        public boolean isDone = false;

        public Task(String inputLabel) {
            setLabel(inputLabel);
        }

        public void setLabel(String inputLabel) {
            this.label = inputLabel;
        }

        public void print() {
            String mark = (isDone) ? "X" : " ";
            System.out.print("["+mark+"] ");
            System.out.println(label);
        }

        public void setMark(boolean inputMark){
            this.isDone = inputMark;
            if (inputMark) {
                System.out.println("Affirmative! Marked have been the task:");
            } else {
                System.out.println("Oh no, I shall unmark thy task:");
            }
            print();
        }
    }


//    public static class Todo extends Task {
//
//    }
//
//    public static class Deadline extends Task {
//
//    }
//
//    public static class Event extends Task {
//
//    }

    // function to output a greeting
    public static void greet() {
        System.out.println("Greetings youngling, Yoda is my name");
    }

    // function to ask the user for an input
    public static void ask() {

        // Initialize input variable, empty task list and item count
        Task[] inputList = new Task[MAX_TASKS];
        int count = 0;



        /* if "bye", loop terminates
         * if "list", shows previous inputs as a numbered list
         * otherwise, adds to a list */
        System.out.print("Yoda. Do or do not what shall I help you with? > ");
        String userInput =  SCANNER.nextLine();
        while (!userInput.equals("bye")) {
            String firstWord = userInput.split(" ")[0];
            switch (firstWord){
                case "list":
                    if (count == 0) {
                        System.out.println("This list of yours looks empty...");
                    }
                    for (int i = 0; i < count; i++) {
                        System.out.print((i+1) + ". ");
                        inputList[i].print();
                    }
                    break;
                case "mark":
                case "unmark":
                    int itemId = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    if (itemId < 0 & itemId >= count){
                        System.out.println("Funny. This ID matches no task of yours.");
                    } else {
                        inputList[itemId].setMark(firstWord.equals("mark"));
                    }
                    break;
                default:
                    if (count >= MAX_TASKS){
                        System.out.println("My condolences, it seems you have too much on your plate.");
                        System.out.println("Your task was not added.");
                    } else {
                        System.out.print("added: ");
                        System.out.println(userInput);
                        inputList[count] = new Task(userInput);
                        count += 1;
                    }
            }
            System.out.print("Yoda. Do or do not what shall I help you with? > ");
            userInput = SCANNER.nextLine();
        }
    }

    // main function that runs all other sub-functions
    public static void main(String[] args) {
        greet();
        ask();

        System.out.print("\n");
        System.out.println("Do or do not, I shall say goodbye.");
    }
}
