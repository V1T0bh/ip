package yoda.exception;

/**
 * Base class for all custom exceptions in the Yoda application.
 * <p>
 * All exceptions from Yoda application should extend {@code YodaException} to provide a
 * common type that can be caught when handling Yoda-specific errors.
 * </p>
 *
 * <h2>Subclasses</h2>
 * <ul>
 *   <li>{@link InsufficientArgumentsException} – thrown when a command does not have enough arguments.</li>
 *   <li>{@link InvalidCommandException} – thrown when the user provides an unrecognized command keyword.</li>
 *   <li>{@link TaskOutOfRangeException} – thrown when an invalid task index is referenced.</li>
 * </ul>
 */
public class YodaException extends Exception {
}
