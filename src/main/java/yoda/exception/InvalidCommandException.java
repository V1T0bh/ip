package yoda.exception;

/**
 * Thrown to indicate that a user command was not a valid one.
 * <p>
 * For example:
 * </p>
 * <ul>
 *   <li>{@code todoi read book} </li>
 *   <li>{@code clear}</li>
 *   <li>{@code calendar}</li>
 * </ul>
 *
 */
public class InvalidCommandException extends YodaException {
    public InvalidCommandException(String message) {
        super();
    }
}
