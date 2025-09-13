package String;

import java.util.*;

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


    /**********************************************************************************************/

    public boolean doesAliceWin(String s) {
        long count = s.chars()
                .filter(c -> "aeiou".indexOf(c) != -1)
                .count();
        return count > 0;
    }

    /**********************************************************************************************/

    public int maxFreqSum(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        int maxFreqVowel = 0, maxfreqConsonants = 0;
        for (char ch : s.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
            if ("aeiou".indexOf(ch) != -1) {
                maxfreqConsonants = Math.max(maxfreqConsonants, freq.get(ch));
            } else {
                maxFreqVowel = Math.max(maxFreqVowel, freq.get(ch));
            }
        }
        return maxFreqVowel + maxfreqConsonants;
    }

    public static void main(String[] args) {
        System.out.println(sortVowels("lEetcOde"));
    }
}
