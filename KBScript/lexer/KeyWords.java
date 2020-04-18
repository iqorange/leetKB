package KBScript.lexer;

import java.util.Arrays;
import java.util.HashSet;

// 校验keywords
public class KeyWords {
    private static String[] keyWords = {
            "var",
            "if",
            "else",
            "for",
            "while",
            "break",
            "func",
            "return"
    };

    private static HashSet<String> stringSet = new HashSet<>(Arrays.asList(keyWords));

    public static boolean isKeyWord(String word){
        return stringSet.contains(word);
    }
}
