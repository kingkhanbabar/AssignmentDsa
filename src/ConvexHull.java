import java.util.*;

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Cross product
    static int cross(Point O, Point A, Point B) {
        return (A.x - O.x) * (B.y - O.y) -
                (A.y - O.y) * (B.x - O.x);
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }
}

public class ConvexHull {

    public static List<Point> getConvexHull(Point[] points) {

        int n = points.length;
        if (n < 3) {
            return Arrays.asList(points);
        }

        // Step 1: Sort points by x, then y
        Arrays.sort(points, (p1, p2) -> {
            if (p1.x == p2.x)
                return p1.y - p2.y;
            return p1.x - p2.x;
        });

        List<Point> hull = new ArrayList<>();

        // Step 2: Build lower hull
        for (int i = 0; i < n; i++) {
            while (hull.size() >= 2 &&
                    Point.cross(hull.get(hull.size()-2),
                            hull.get(hull.size()-1),
                            points[i]) <= 0) {
                hull.remove(hull.size()-1);
            }
            hull.add(points[i]);
        }

        // Step 3: Build upper hull
        int lowerSize = hull.size();

        for (int i = n - 2; i >= 0; i--) {
            while (hull.size() > lowerSize &&
                    Point.cross(hull.get(hull.size()-2),
                            hull.get(hull.size()-1),
                            points[i]) <= 0) {
                hull.remove(hull.size()-1);
            }
            hull.add(points[i]);
        }

        // Remove duplicate last point
        hull.remove(hull.size() - 1);

        return hull;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of points: ");
        int n = sc.nextInt();

        Point[] points = new Point[n];

        System.out.println("Enter x and y coordinates:");
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            points[i] = new Point(x, y);
        }

        List<Point> hull = getConvexHull(points);

        System.out.println("\nVertices of Convex Hull:");
        for (Point p : hull) {
            System.out.print(p + " ");
        }
    }
}




//import java.util.*;
//class Point {
//    int x, y;
//
//    Point(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }
//
//
//    boolean isLE180(Point b, Point c) {
//        int cross = (b.x - x) * (c.y - y) -
//                (b.y - y) * (c.x - x);
//        return cross <= 0;   // right turn
//    }
//
//    public String toString() {
//        return "(" + x + "," + y + ")";
//    }
//}
//
//
//class Node {
//    Point data;
//    Node next, prev;
//
//    Node(Point p) {
//        data = p;
//    }
//}
//
//
//class DCLL {
//    Node head = null;
//    int size = 0;
//
//    void create(Point[] arr, int n) {
//        if (n == 0) return;
//
//        Node last = null;
//
//        for (int i = 0; i < n; i++) {
//            Node temp = new Node(arr[i]);
//
//            if (head == null) {
//                head = temp;
//                head.next = head;
//                head.prev = head;
//                last = head;
//            } else {
//                temp.prev = last;
//                temp.next = head;
//                last.next = temp;
//                head.prev = temp;
//                last = temp;
//            }
//            size++;
//        }
//    }
//
//    Node getFirst() {
//        return head;
//    }
//
//    void del(Node x) {
//        if (x == null || head == null || size <= 2)
//            return;
//
//        x.prev.next = x.next;
//        x.next.prev = x.prev;
//
//        if (x == head)
//            head = x.next;
//
//        size--;
//    }
//
//    int getSize() {
//        return size;
//    }
//
//    void display() {
//        if (head == null) return;
//
//        Node p = head;
//        do {
//            System.out.print(p.data + " ");
//            p = p.next;
//        } while (p != head);
//
//        System.out.println();
//    }
//}
//
//
//public class ConvexHullDCLL {
//
//    static void getConvexHull(Point[] a, int n) {
//
//        if (n < 3) {
//            System.out.println("Convex hull not possible");
//            return;
//        }
//
//
//        Arrays.sort(a, (p1, p2) -> {
//            if (p1.x == p2.x)
//                return p1.y - p2.y;
//            return p1.x - p2.x;
//        });
//
//        DCLL lst = new DCLL();
//        lst.create(a, n);
//
//        Node x = lst.getFirst();
//        Node xr = x.next;
//        Node xrr = xr.next;
//
//        boolean changed = true;
//
//        // Keep checking until no deletions happen
//        while (changed) {
//            changed = false;
//            x = lst.getFirst();
//            xr = x.next;
//            xrr = xr.next;
//
//            int count = lst.getSize();
//
//            for (int i = 0; i < count && lst.getSize() > 2; i++) {
//
//                xrr = xr.next;
//
//                if (x.data.isLE180(xr.data, xrr.data)) {
//
//                    lst.del(xr);
//                    changed = true;
//
//                    x = x.prev;
//                    xr = x.next;
//
//                } else {
//                    x = xr;
//                    xr = xrr;
//                }
//            }
//        }
//
//        System.out.println("\nVertices of Convex HULL:");
//        lst.display();
//    }
//
//
//    public static void main(String[] args) {
//
//        Scanner sc = new Scanner(System.in);
//
//        System.out.print("Enter number of points: ");
//        int n = sc.nextInt();
//
//        if (n < 3) {
//            System.out.println("Convex hull not possible");
//            return;
//        }
//
//        Point[] points = new Point[n];
//
//        System.out.println("Enter x and y coordinates:");
//
//        for (int i = 0; i < n; i++) {
//            int x = sc.nextInt();
//            int y = sc.nextInt();
//            points[i] = new Point(x, y);
//        }
//
//        getConvexHull(points, n);
//    }
//
//}
