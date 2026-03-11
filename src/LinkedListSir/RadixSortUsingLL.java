package LinkedListSir;

public class RadixSortUsingLL {


    static int max(int[] a, int n) {
        int m = a[0];
        for (int i = 1; i < n; i++) {
            if (a[i] > m)
                m = a[i];
        }
        return m;
    }


    static int countDigits(int m) {
        int d = 0;
        while (m > 0) {
            d++;
            m = m / 10;
        }
        return d;
    }


    static void radixSort(int[] a, int n) {

        int m = max(a, n);
        int d = countDigits(m);  // number of digits


        LinkedListCreate[] L = new LinkedListCreate[10];

        for (int i = 0; i < 10; i++)
            L[i] = new LinkedListCreate();

        int divisor = 1;  // 1, 10, 100, 1000...


        for (int pass = 1; pass <= d; pass++) {


            for (int i = 0; i < n; i++) {
                int digit = (a[i] / divisor) % 10;
                L[digit].insert(a[i], 0);  // insert at head
            }


            int index = 0;
            for (int j = 0; j < 10; j++) {
                while (!L[j].isEmpty()) {
                    a[index] = L[j].del(L[j].size() - 1); // delete from end
                    index++;
                }
            }

            divisor *= 10;  // move to next digit
        }
    }


    public static void main(String[] args) {

        int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
        int n = arr.length;

        radixSort(arr, n);

        System.out.println("Sorted Array:");
        for (int x : arr)
            System.out.print(x + " ");
    }
}