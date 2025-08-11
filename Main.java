import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        printArray(array);
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    static class Result {
        public static List<Integer> getValueAtIndices(List<Integer> query) {
            int maxIndex = Collections.max(query);
            List<Integer> sequence = new ArrayList<>();
            int num = 1;

            while (sequence.size() <= maxIndex) {
                int ones = Integer.bitCount(num);
                for (int i = 0; i < ones && sequence.size() <= maxIndex; i++) {
                    sequence.add(num);
                }
                num++;
            }

            List<Integer> result = new ArrayList<>();
            for (int idx : query) {
                result.add(sequence.get(idx));
            }
            return result;
        }
    }
}
