import java.util.Scanner;

public class Yoda {
    // function to output a greeting and a question to ask the user for a query
    public static void greet() {
        System.out.println("Greetings youngling, Yoda is my name");
    }

    public static void ask() {
        final Scanner SCANNER = new Scanner(System.in);
        System.out.println("What help shall I do you for?");
        String userInput =  SCANNER.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(userInput);
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
