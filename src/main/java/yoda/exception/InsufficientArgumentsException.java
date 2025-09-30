package yoda.exception;

/**
 * Thrown to indicate that a user command did not contain enough arguments.
 * <p>
 * For example:
 * </p>
 * <ul>
 *   <li>{@code todo} with no description</li>
 *   <li>{@code deadline} without a label or missing the {@code /by} argument</li>
 *   <li>{@code event} without a start or end time</li>
 * </ul>
 *
 */
public class InsufficientArgumentsException extends YodaException {
}
