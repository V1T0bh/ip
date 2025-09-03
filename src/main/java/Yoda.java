// follows coding standards CHECKED
import java.util.Scanner;

public class Yoda {
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
        final Scanner SCANNER = new Scanner(System.in);
        Task[] inputList = new Task[100];
        int count = 0;

        System.out.println("What help shall I do you for?");

        /* if "bye", loop terminates
         * if "list", shows previous inputs as a numbered list
         * otherwise, adds to a list */
        String userInput =  SCANNER.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                if (count == 0) {
                    System.out.println("This list of yours looks empty...");
                }
                for (int i = 0; i < count; i++) {
                    System.out.print((i+1) + ". ");
                    inputList[i].print();
                }
            } else if (userInput.split(" ")[0].equals("mark")) {
                int itemId = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (itemId < 0 & itemId >= count){
                    System.out.println("Funny. This ID matches no task of yours.");
                } else {
                    inputList[itemId].setMark(true);
                }
            } else if (userInput.split(" ")[0].equals("unmark")) {
                int itemId = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (itemId < 0 & itemId >= count){
                    System.out.println("Funny. This ID matches no task of yours.");
                } else {
                    inputList[itemId].setMark(false);
                }
            } else {
                System.out.print("added: ");
                System.out.println(userInput);
                inputList[count] = new Task(userInput);
                count += 1;
            }
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
