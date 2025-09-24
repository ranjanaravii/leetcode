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

    public int maxFreqSum2(String s) {
        int[] freq = new int[26];
        boolean[] isVowel = new boolean[26];
        for (char v : "aeiou".toCharArray()) {
            isVowel[v - 'a'] = true;
        }

        int maxFreqVowel = 0, maxFreqConsonant = 0;

        for (char ch : s.toCharArray()) {
            int idx = ch - 'a';
            freq[idx]++;

            if (isVowel[idx]) {
                maxFreqVowel = Math.max(maxFreqVowel, freq[idx]);
            } else {
                maxFreqConsonant = Math.max(maxFreqConsonant, freq[idx]);
            }
        }

        return maxFreqVowel + maxFreqConsonant;
    }

    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";

        StringBuilder result = new StringBuilder();

        // Handle sign
        if ((numerator < 0) ^ (denominator < 0)) {
            result.append("-");
        }

        // Use long to prevent overflow (e.g., Integer.MIN_VALUE / -1)
        long dividend = Math.abs((long) numerator);
        long divisor = Math.abs((long) denominator);

        // Integer part
        result.append(dividend / divisor);
        long remainder = dividend % divisor;

        if (remainder == 0) {
            return result.toString();
        }

        result.append(".");

        // Map to store remainders and their positions in the result
        Map<Long, Integer> seen = new HashMap<>();

        while (remainder != 0) {
            if (seen.containsKey(remainder)) {
                int index = seen.get(remainder);
                result.insert(index, "(");
                result.append(")");
                break;
            }

            seen.put(remainder, result.length());
            remainder *= 10;
            result.append(remainder / divisor);
            remainder %= divisor;
        }

        return result.toString();
    }



    public static void main(String[] args) {
        System.out.println(sortVowels("lEetcOde"));
    }
}
