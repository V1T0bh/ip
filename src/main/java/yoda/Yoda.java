package yoda;

import yoda.storage.Storage;
import yoda.task.TaskList;
import yoda.ui.Ui;

import java.io.IOException;

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
