import java.util.Scanner;

public class Yoda {
    // function to output a greeting
    public static void greet() {
        System.out.println("Greetings youngling, Yoda is my name");
    }

    // function to ask the user for an input
    /* if "bye", breaks the loop and terminates function
    * if "list", shows previous inputs as a numbered list
    * otherwise, adds to a list */
    public static void ask() {
        final Scanner SCANNER = new Scanner(System.in);
        String[] inputList = new String[100];
        int count = 0;

        System.out.println("What help shall I do you for?");

        String userInput =  SCANNER.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                for (int i = 0; i < count; i++) {
                    System.out.print((i+1) + ". ");
                    System.out.println(inputList[i]);
                }
            } else {
                System.out.print("added: ");
                System.out.println(userInput);
                inputList[count] = userInput;
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
