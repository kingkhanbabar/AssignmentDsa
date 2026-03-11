import java.util.Map;
import java.util.Scanner;
class Matrix{
    int m,n;
    double [][]a;
    Matrix(int m,int n){
        this.m=m;
        this.n=n;
        a= new double[m][n];
    }
    void read(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter matrix of order " + m + " x " + n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j]=sc.nextDouble();
            }
        }
    }
    void print(){
        System.out.println("The matrix is ");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(a[i][j]+"\t");
            }
            System.out.println();
        }
    }
   Matrix  add(Matrix B){
        if(m!=B.m || m!=B.n){
            throw new RuntimeException("Addition faikled");
        }
        Matrix C = new Matrix(m,n);
       for (int i = 0; i < m; i++) {
           for (int j = 0; j < n; j++) {
               C.a[i][j] = a[i][j] + B.a[i][j];
           }
       }
    return C;
   }
   Matrix multiply(Matrix B){
        if(n!=B.n) throw new RuntimeException("Multiplication failed");
        Matrix C = new Matrix(m,n);
       for (int i = 0; i < m; i++) {
           for (int j = 0; j < B.n; j++) {
               C.a[i][j]=0;
               for (int k = 0; k < n; k++) {
                   C.a[i][j] += a[i][k] * B.a[k][j];
               }

           }

       }
       return  C;
   }
   double det(){
        if(m!=n) throw new RuntimeException("Should be square");
       for (int k = 0; k <= m-2; k++) {
           double x = a[k][k];
           for (int i = k+1; i <= m-1; i++) {
               double y = a[i][k];
               for (int j = 0; j < n; j++) {
                   a[i][j] = a[i][j]-a[k][j]*y/x;
               }
           }
       }
       double d =1;
       for (int i = 0; i < n; i++) {
           d = d*a[i][i];

       }
       return d;
   }
   Matrix inverse(){
       if (m != n)
           throw new RuntimeException("Inverse not possible");
      // if(det()==0) throw  new RuntimeException("Determinant not zero");
        Matrix B = new Matrix(m,n);
       for (int i = 0; i < m; i++) {
           for (int j = 0; j < n; j++) {
               if (i == j)
                   B.a[i][j] = 1;
               else
                   B.a[i][j] = 0;
           }
       }
       for (int k = 0; k <=m-2; k++) {
           double x = a[k][k];
           for (int i = k+1; i <=m-1 ; i++) {
               double y = a[i][k];
               for (int j = 0; j < n; j++) {
                   a[i][j] = a[i][j] - a[k][j] * y / x;
                   B.a[i][j] = B.a[i][j] - B.a[k][j] * y / x;
               }
           }
       }
       for (int k = m-1; k >0 ; k--) {
           double x = a[k][k];

           for (int i = 0; i < k; i++) {
               double y = a[i][k];
               for (int j = 0; j <= n - 1; j++) {

                   a[i][j] = a[i][j] - a[k][j] * y / x;
                   B.a[i][j] = B.a[i][j] - B.a[k][j] * y / x;
               }

           }
       }
       for (int i = 0; i <= m - 1; i++) {

           double x = a[i][i];

           for (int j = 0; j <= n - 1; j++) {

               a[i][j] = a[i][j] / x;
               B.a[i][j] = B.a[i][j] / x;
           }
       }
        return  B;
   }
   
}
public class Assignment2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter size of square matrix:");
        int n = sc.nextInt();

        Matrix A = new Matrix(n, n);
        Matrix B = new Matrix(n, n);

        System.out.println("Enter first matrix (Diagonal Matrix):");
        A.read();

        System.out.println("Enter second matrix (Diagonal Matrix):");
        B.read();

        System.out.println("First Matrix:");
        A.print();

        System.out.println("Second Matrix:");
        B.print();

        // Addition
        Matrix C = A.add(B);
        System.out.println("Addition Result:");
        C.print();

        // Multiplication
        Matrix D = A.multiply(B);
        System.out.println("Multiplication Result:");
        D.print();

        // Determinant
        System.out.println("Determinant of First Matrix = " + A.det());

        // Inverse
        Matrix inv = A.inverse();
        double d = A.det();
        if(d==0){
            throw  new RuntimeException("zero ");
        }else {
            System.out.println("Inverse of First Matrix:");
            inv.print();
        }
    }
}
