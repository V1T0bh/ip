import java.io.IOException;
import java.nio.file.*;

public class Yoda {
    static TaskList inputList = new TaskList();

    // main function that runs all other sub-functions
    public static void main(String[] args) {
        try {
            Storage userData = new Storage("data/user.txt");
            Ui.start(userData.isNewFile());

            Ui.run();
            userData.arrayToFile();

            Ui.end();
        } catch (IOException e) {
            Ui.showError("There were issues with the file.", e);
        } catch (NumberFormatException e) {
            Ui.showError("Number input is invalid! Try again.", e);
        } catch (TaskOutOfRangeException | IndexOutOfBoundsException e) {
            Ui.showError("Task is out of range!\n" +
                    "You have " + inputList.size() + " tasks.", e);
        } catch (Exception e) {
            Ui.showError("Something went wrong!", e);
        }
    }
}
