package yoda.parser;

import java.util.ArrayList;

/**
 * Splits raw user input into structured keywords for command handling.
 * <p>
 * The {@code Parser} fills a shared {@link #keywordsList} with up to five fields:
 * </p>
 * <ol>
 *   <li><b>index 0</b> – command type (e.g., {@code todo}, {@code deadline}, {@code event}, {@code mark}, {@code list})</li>
 *   <li><b>index 1</b> – task label/description</li>
 *   <li><b>index 2</b> – start value (e.g., {@code /from} for events)</li>
 *   <li><b>index 3</b> – end value (e.g., {@code /to} or {@code /by})</li>
 *   <li><b>index 4</b> – Mark flag</li>
 * </ol>
 *
 * <p>
 * Delimiters such as {@code /by}, {@code /from}, {@code /to}, and {@code /mark} are
 * used as boundaries for each keyword. Commands are parsed as per these examples:
 * </p>
 *
 * <pre>{@code
 * "deadline submit report /by Friday /mark X"
 * // -> ["deadline", "submit report", "", "Friday", "X"]
 *
 * "event project meeting /from Mon 2pm /to Mon 4pm"
 * // -> ["event", "project meeting", "Mon 2pm", "Mon 4pm", ""]
 * }</pre>
 */
public class Parser {
    public static final ArrayList<String> keywordsList = new ArrayList<>();

    // strips the string at index
    private static void strip (int index){
        keywordsList.set(index, keywordsList.get(index).strip());
    }

    // takes an index and content and concats the string (at that index) with the content
    private static void append(int index, String content) {
        keywordsList.set(index, keywordsList.get(index) + content);
    }

    // function to split the input to array
    // [TASK TYPE, TASK LABEL, TASK START, TASK END], if exists
    public static void split(String userInput) {
        keywordsList.clear();

        final int NO_OF_KEYWORDS = 5;
        String[] splitArray = userInput.split(" ");

        // TYPE is first word
        keywordsList.add(splitArray[0]);

        if (splitArray.length == 1) {
            return;
        }

        // LABEL, START, END
        int i = 1;
        for (int j = 1; j < NO_OF_KEYWORDS; j++) {
            boolean hasExceedLength = i >= splitArray.length;
            boolean wordContainsSlash = hasExceedLength || splitArray[i].contains("/");

            keywordsList.add("");
            while (!(hasExceedLength || wordContainsSlash)) {
                append(j, splitArray[i] + " ");
                i += 1;

                hasExceedLength = i >= splitArray.length;
                wordContainsSlash = hasExceedLength || splitArray[i].contains("/");
            }
            strip(j);

            // skips the word containing "/" (e.g. /by)
            i += 1;
        }
    }
    
    
}
