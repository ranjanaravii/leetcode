package BinarySeach;

public class BinarySearch {

    public int shipWithinDays(int[] weights, int days) {
        int high = 0;
        int low = 0;
        for (int weight : weights) {
            high += weight;
            low = Math.max(low, weight);
        }
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (canShip(weights, days, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private boolean canShip(int[] weights, int days, int capacity) {
        int currentLoad = 0;
        int dayCount = 1;

        for (int weight : weights) {
            if (weight + currentLoad > capacity) {
                dayCount += 1;
                currentLoad = 0;
            }
            currentLoad += weight;
        }
        return dayCount <= days;
    }


    public static void main(String[] args) {

        BinarySearch bs = new BinarySearch();
        System.out.println(bs.shipWithinDays(new int[]{1,2,3,4,5,6,7,8,9,10}, 5));
    }
}

class Main {
    public static void main(String args[]) {
        System.out.println(" Main Method");
    }
    public static void main(int[] args){
        System.out.println("Overloaded Integer array Main Method");
    }
    public static void main(char[] args){
        System.out.println("Overloaded Character array Main Method");
    }
    public static void main(double[] args){
        System.out.println("Overloaded Double array Main Method");
    }
    public static void main(float args){
        System.out.println("Overloaded float Main Method");
    }
}

