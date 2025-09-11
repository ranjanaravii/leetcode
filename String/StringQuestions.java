package String;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringQuestions {
    public static String sortVowels(String s) {
        String vowels = "aeiouAEIOU";
        List<Character> vowelList = new ArrayList<>();
        for (char ch : s.toCharArray()) {
            if (vowels.indexOf(ch) != -1) {
                vowelList.add(ch);
            }
        }
        Collections.sort(vowelList);
        int idx = 0;
        StringBuilder result = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (vowels.indexOf(ch) != -1) {
                result.append(vowelList.get(idx++));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(sortVowels("lEetcOde"));
    }
}
