package Interval.interview;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String str = "Welcome to programming";
        str = str.toLowerCase();
        Map<Character, Integer> freq = new HashMap<>();
        for (char ch: str.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }
        int maxCount = 0;
        String ans = "";
        for (Map.Entry<Character, Integer> entry: freq.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                ans = entry.getKey().toString();
            }
        }
        System.out.println("Max character: " + ans  + " Count : " + maxCount);
    }
}

