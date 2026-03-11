package Assignment2Very;

import java.util.Scanner;

class Matrix {

    int m, n;
    double[][] a;

    Matrix(int m, int n) {
        this.m = m;
        this.n = n;
        a = new double[m][n];
    }

    void read() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter matrix of order " + m + " x " + n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = sc.nextDouble();
            }
        }
    }

    void print() {
        System.out.println("The matrix is:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(a[i][j] + "\t");
            }
            System.out.println();
        }
    }

    Matrix add(Matrix B) {

        if (m != B.m || n != B.n)
            throw new RuntimeException("Addition failed");

        Matrix C = new Matrix(m, n);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                C.a[i][j] = a[i][j] + B.a[i][j];
            }
        }

        return C;
    }

    Matrix multiply(Matrix B) {

        if (n != B.m)
            throw new RuntimeException("Multiplication not possible");

        Matrix C = new Matrix(m, B.n);

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < B.n; j++) {

                C.a[i][j] = 0;

                for (int k = 0; k < n; k++) {

                    C.a[i][j] += a[i][k] * B.a[k][j];

                }
            }
        }

        return C;
    }

    double det() {

        if (m != n)
            throw new RuntimeException("Matrix must be square");

        // copy matrix so original is not destroyed
        double[][] temp = new double[m][n];

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                temp[i][j] = a[i][j];

        for (int k = 0; k <= m - 2; k++) {

            double x = temp[k][k];

            for (int i = k + 1; i <= m - 1; i++) {

                double y = temp[i][k];

                for (int j = 0; j < n; j++) {

                    temp[i][j] = temp[i][j] - temp[k][j] * y / x;
                }
            }
        }

        double d = 1;

        for (int i = 0; i < n; i++) {
            d = d * temp[i][i];
        }

        return d;
    }

    Matrix inverse() {

        if (m != n)
            throw new RuntimeException("Inverse not possible");

        Matrix B = new Matrix(m, n);

        // Identity matrix
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == j)
                    B.a[i][j] = 1;
                else
                    B.a[i][j] = 0;
            }
        }

        // copy matrix
        double[][] temp = new double[m][n];

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                temp[i][j] = a[i][j];

        // Forward elimination
        for (int k = 0; k <= m - 2; k++) {

            double x = temp[k][k];

            for (int i = k + 1; i <= m - 1; i++) {

                double y = temp[i][k];

                for (int j = 0; j < n; j++) {

                    temp[i][j] = temp[i][j] - temp[k][j] * y / x;
                    B.a[i][j] = B.a[i][j] - B.a[k][j] * y / x;
                }
            }
        }

        // Backward elimination
        for (int k = m - 1; k > 0; k--) {

            double x = temp[k][k];

            for (int i = 0; i < k; i++) {

                double y = temp[i][k];

                for (int j = 0; j < n; j++) {

                    temp[i][j] = temp[i][j] - temp[k][j] * y / x;
                    B.a[i][j] = B.a[i][j] - B.a[k][j] * y / x;
                }
            }
        }

        // Make diagonal 1
        for (int i = 0; i < m; i++) {

            double x = temp[i][i];

            for (int j = 0; j < n; j++) {

                temp[i][j] = temp[i][j] / x;
                B.a[i][j] = B.a[i][j] / x;
            }
        }

        return B;
    }
}

public class Assignment2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter size of square matrix:");
        int n = sc.nextInt();

        Matrix A = new Matrix(n, n);
        Matrix B = new Matrix(n, n);

        System.out.println("Enter first matrix:");
        A.read();

        System.out.println("Enter second matrix:");
        B.read();

        System.out.println("First Matrix:");
        A.print();

        System.out.println("Second Matrix:");
        B.print();

        Matrix C = A.add(B);
        System.out.println("Addition Result:");
        C.print();

        Matrix D = A.multiply(B);
        System.out.println("Multiplication Result:");
        D.print();

        double d = A.det();

        System.out.println("Determinant of First Matrix = " + d);

        if (d == 0) {
            System.out.println("Inverse does not exist (Determinant = 0)");
        } else {

            Matrix inv = A.inverse();

            System.out.println("Inverse of First Matrix:");
            inv.print();
        }
    }
}
