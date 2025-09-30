package yoda.parser;

public class Parser {
    // function to split the input to array
    // [TASK TYPE, TASK LABEL, TASK START, TASK END], if exists
    public static String[] split(String userInput) {
        final int NO_OF_KEYWORDS = 5;
        String[] finalArray = {"", "", "", "", ""};
        String[] splitArray = userInput.split(" ");

        // TYPE is first word
        finalArray[0] = splitArray[0];

        if (splitArray.length == 1) {
            return finalArray;
        }

        // LABEL, START, END
        int i = 1;
        for (int j = 1; j < NO_OF_KEYWORDS; j++) {
            boolean hasExceedLength = i >= splitArray.length;
            boolean wordContainsSlash = hasExceedLength || splitArray[i].contains("/");
            while (!(hasExceedLength || wordContainsSlash)) {
                finalArray[j] += splitArray[i] + " ";
                i += 1;

                hasExceedLength = i >= splitArray.length;
                wordContainsSlash = hasExceedLength || splitArray[i].contains("/");
            }
            finalArray[j] = finalArray[j].strip();

            // skips the word containing "/" (e.g. /by)
            i += 1;
        }

        return finalArray;
    }
}
