
package TwoPointer;

import java.util.Arrays;

public class BoatToSavePeople {

    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0, right = people.length - 1;
        int boats = 0;
        while (left <= right) {
            boats++;
            if (people[left] + people[right] <= limit) {
                left++;
            }
            right--;
        }
        return boats;
    }
}
