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

/**
 * Handles persistence of tasks to and from the file system.
 * <p>
 * The {@code Storage} class ensures the data file and its parent directories exist.
 * It loads the tasks from the save file into {@link yoda.task.TaskList}.
 * Finally, it will write tasks back to the file in a command-like format.
 * </p>
 */
public class Storage {
    private final File userFile;
    private final boolean isNew;

    /**
     * Returns whether the underlying file was created for the first time.
     *
     * @return {@code true} if the file did not previously exist and was created;
     *         {@code false} if the file already existed.
     */
    public boolean isNewFile() {
        return isNew;
    }

    /**
     * Constructs a {@code Storage} instance pointing to the given path.
     * <p>
     * If necessary, parent directories are created automatically. If the file
     * does not exist, it will be created.
     * </p>
     *
     * @param path the path to the file used for persistence (e.g. {@code data/user.txt}).
     * @throws IOException if directories or file cannot be created.
     */
    public Storage(String path) throws IOException {
        // creates dir based on path if it does not exist
        // does nothing if it exists
        Path dir  = Paths.get(path);
        Files.createDirectories(dir.getParent());

        userFile = new File(path);
        isNew = userFile.createNewFile();
    }

    /**
     * Loads tasks from the file into {@link Yoda#inputList}.
     * <p>
     * Each line of the file is parsed into keywords via {@link Parser#split(String)},
     * and then delegated to {@link yoda.task.TaskList#add(boolean)} with
     * {@code show = false} to add silently.
     * </p>
     *
     * @throws FileNotFoundException if the underlying file cannot be read.
     */
    public void fileToArray() throws FileNotFoundException {
        Scanner fileScanner = new Scanner(userFile);

        while (fileScanner.hasNext()) {
            String fileInput = fileScanner.nextLine();
            Parser.split(fileInput);

            Yoda.inputList.add(false);
        }
    }

    /**
     * Writes all tasks from {@link Yoda#inputList} into the file.
     * <p>
     * Each task is converted to a command string via {@link Task#toCommand()} and
     * written as a separate line.
     * </p>
     *
     * @throws IOException if the file cannot be opened or written to.
     */
    public void arrayToFile() throws IOException {
        FileWriter fWrite = new FileWriter(userFile);

        for (Task task : Yoda.inputList.getTasks()) {
            fWrite.write(task.toCommand());
            fWrite.write("\n");
        }
        fWrite.close();
    }
}
