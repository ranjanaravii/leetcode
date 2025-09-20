package Heap;

public class MinHeap {
    public static int left(int i) {
        return 2 * i;
    }

    public static int right(int i) {
        return 2 * i + 1;
    }

    public static int parent(int i) {
        return i / 2;
    }

    public static void minHeapify(int[] a, int heapSize, int i) {
        int l = left(i);
        int r = right(i);
        int smallest = i;

        if (l < heapSize && a[l] < a[i]) {
            smallest = l;
        }

        if (r < heapSize && a[r] < a[smallest]) {
            smallest = r;
        }

        if (smallest != i) {
            // swap
            int temp = a[i];
            a[i] = a[smallest];
            a[smallest] = temp;

            minHeapify(a, heapSize, smallest);
        }
    }

    public static void buildMinHeap(int[] a) {
        int heapSize = a.length;
        for (int i = heapSize / 2; i > 0; i--) {
            minHeapify(a, heapSize, i);
        }
    }

    public static void main(String[] args) {
        // root at index 1, index 0 unused
        int[] a = {0, 0, 5, 20, 6, 12, 65, 1, 4, 9, 3, 89, 22, 25, 28, 10};

        buildMinHeap(a);

        System.out.print("Min Heap: ");
        for (int i = 1; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

}
