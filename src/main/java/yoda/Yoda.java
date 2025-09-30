package yoda;

import yoda.storage.Storage;
import yoda.task.TaskList;
import yoda.ui.Ui;

import java.io.IOException;

/**
 * The {@code Yoda} class is the entry point of the application.
 *
 * <p>
 * It wires together the core components ({@link yoda.task.TaskList}, {@link yoda.storage.Storage}, and {@link yoda.ui.Ui)
 * and drives the high-level lifecycle of the program.
 * </p>
 * Yoda chatbot utilizes the following packages:
 * <ul>
 *     <li>{@link yoda.task.TaskList}: For storing tasks throughout the execution of Yoda.</li>
 *     <li>{@link yoda.storage.Storage}: For opening and saving of tasks from and to file.</li>
 *     <li>{@link yoda.ui.Ui}: For handling all user interaction for the Yoda application.</li>
 *     <li>{@link yoda.parser.Parser}: For parsing raw user input into a keywords array.</li>
 * </ul>
 * </p>
*/
public class Yoda {
    public static TaskList inputList = new TaskList();

    // main function that runs all other sub-functions
    public static void main(String[] args) {
        try {
            Storage userData = new Storage("data/user.txt");
            Ui.start(userData.isNewFile());
            userData.fileToArray();

            Ui.run();
            userData.arrayToFile();

            Ui.end();
        } catch (IOException e) {
            Ui.showError("There were issues with the file.", e);
        }
    }
}
