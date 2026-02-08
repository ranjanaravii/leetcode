package HackerRank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    private static int minimizeCost(List<Integer> numsPeople, List<Integer> x, List<Integer> y) {
        int n = numsPeople.size();
        int bestX = weightedMedian(numsPeople, x);
        int bestY = weightedMedian(numsPeople, y);
        int totalCost = 0;
        for (int i = 0; i < n; i ++) {
            totalCost += numsPeople.get(i) * (Math.abs(x.get(i) - bestX) + Math.abs(y.get(i) - bestY));
        }
        return totalCost;
    }

    private static int weightedMedian(List<Integer> numsPeople, List<Integer> coordinate) {
        int n = numsPeople.size();
        List<int[]> list = new ArrayList<>();
        int totalPopulation = 0;
        for (int i = 0; i < n; i++) {
            list.add(new int[]{coordinate.get(i), numsPeople.get(i)});
            totalPopulation += numsPeople.get(i);
        }
        list.sort(Comparator.comparingInt(value -> value[0]));
        int prefix = 0;
        for (int[] city: list) {
            prefix += city[1];
            if (2 * prefix >= totalPopulation) {
                return city[0];
            }
        }
        return list.get(n - 1)[0];
    }

    public static void main(String[] args) {
        List<Integer> numsPeople = Arrays.asList(1, 2);
        List<Integer> x = Arrays.asList(1, 3);
        List<Integer> y = Arrays.asList(1, 3);

        System.out.println(minimizeCost(numsPeople, x, y)); // Output: 4
    }
}
