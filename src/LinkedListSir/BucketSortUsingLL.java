package LinkedListSir;

public class BucketSortUsingLL {

    // -------- Find Maximum --------
    static int max(int[] a, int n) {
        int m = a[0];
        for (int i = 1; i < n; i++) {
            if (a[i] > m) {
                m = a[i];
            }
        }
        return m;
    }

    // -------- Bucket Sort --------
    static void bucketSort(int[] a, int n) {

        int m = max(a, n);

        // Create m+1 linked lists (buckets)
        LinkedListCreate[] L = new LinkedListCreate[m + 1];

        for (int i = 0; i <= m; i++) {
            L[i] = new LinkedListCreate();
        }

        // Insert elements into buckets
        for (int i = 0; i < n; i++) {
            L[a[i]].insert(a[i], 0);   // insert at head
        }

        // Collect elements back to array
        int i = 0;
        for (int j = 0; j <= m; j++) {
            while (!L[j].isEmpty()) {
                a[i] = L[j].del(L[j].size() - 1);  // delete from end
                i++;
            }
        }
    }

    // -------- Main --------
    public static void main(String[] args) {

        int[] a = {10,17,8,804,0};
        int n = a.length;

        bucketSort(a, n);

        System.out.println("Sorted Array:");
        for (int x : a) {
            System.out.print(x + " ");
        }
    }
}