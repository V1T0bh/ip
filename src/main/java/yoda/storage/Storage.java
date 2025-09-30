package yoda.storage;

import yoda.parser.Parser;
import yoda.Yoda;
import yoda.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    private final File userFile;
    private final boolean isNew;

    // returns whether a file was created
    // true, if new file
    // false, otherwise
    public boolean isNewFile() {
        return isNew;
    }

    public Storage(String path) throws IOException {
        // creates dir based on path if it does not exist
        // does nothing if it exists
        Path dir  = Paths.get(path);
        Files.createDirectories(dir.getParent());

        userFile = new File(path);
        isNew = userFile.createNewFile();
    }

    // function extracts and processes all tasks to tasks array from file f
    // returns a yoda.task.Task[] array, filled with extracted tasks
    public void fileToArray() throws FileNotFoundException {
        Scanner fileScanner = new Scanner(userFile);

        while (fileScanner.hasNext()) {
            String fileInput = fileScanner.nextLine();
            String[] keywordsArray = Parser.split(fileInput);

            Yoda.inputList.add(keywordsArray, false);
        }
    }

    // function uses task array and writes it to file f
    public void arrayToFile() throws IOException {
        FileWriter fWrite = new FileWriter(userFile);

        for (Task task : Yoda.inputList.getTasks()) {
            fWrite.write(task.toCommand());
            fWrite.write("\n");
        }
        fWrite.close();
    }
}
