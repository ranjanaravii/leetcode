package Interview;

import java.util.*;

public class Solution {

    private static List<List<String>> groupTransaction(List<String> input) {

        List<String> featureCodes = List.of("TRANSPORT", "FOOD", "MART", "EXPRESS");

        // Precompute sorted feature keys
        Set<String> validSortedKeys = new HashSet<>();
        for (String feature : featureCodes) {
            validSortedKeys.add(sortString(feature));
        }

        Map<String, List<String>> map = new HashMap<>();

        for (String transaction : input) {

            String[] parts = transaction.split("_");
            if (parts.length != 2) continue;

            String part1 = parts[0];
            String part2 = parts[1];

            String feature = null;

            // If first part is digits → feature is second
            if (isAllDigit(part1)) {
                feature = part2;
            }
            // If second part is digits → feature is first
            else if (isAllDigit(part2)) {
                feature = part1;
            }
            else {
                continue;
            }

            String sortedFeature = sortString(feature);

            if (!validSortedKeys.contains(sortedFeature)) {
                continue;
            }

            map.computeIfAbsent(sortedFeature, k -> new ArrayList<>())
                    .add(transaction);
        }

        return new ArrayList<>(map.values());
    }

    static String sortString(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

    static boolean isAllDigit(String s) {
        for (char ch : s.toCharArray()) {
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        List<String> input = List.of(
                "TRANSPORT_132",
                "FOOD_123",
                "123_FODO",
                "132_TRANSPORT",
                "FODO_132",
                "BIKE_123"
        );

        List<List<String>> res = groupTransaction(input);
        System.out.println(res);
    }
}