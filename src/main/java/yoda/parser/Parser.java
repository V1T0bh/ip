package yoda.parser;

import java.util.ArrayList;

// parses command to keywords
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
