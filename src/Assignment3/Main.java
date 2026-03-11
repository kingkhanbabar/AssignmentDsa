package Assignment3;
import java.util.Scanner;

class LTM{

    int n;
    double a[];

    LTM(int n){
        this.n = n;
        a = new double[n*(n+1)/2];
    }

    int map(int i,int j){
        return (i*(i+1))/2 + j;
    }

    void read(){

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter non-zero elements of Lower Triangular Matrix");

        for(int i=0;i<n;i++)
            for(int j=0;j<=i;j++)
                a[map(i,j)] = sc.nextDouble();
    }

    void print(){

        for(int i=0;i<n;i++){

            for(int j=0;j<n;j++){

                if(i>=j)
                    System.out.print(a[map(i,j)]+"\t");
                else
                    System.out.print("0\t");
            }

            System.out.println();
        }
    }

    double[][] multiply(UTM B){

        double C[][] = new double[n][n];

        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                for(int k=0;k<=Math.min(i,j);k++)
                    C[i][j]+=a[map(i,k)]*B.a[B.map(k,j)];

        return C;
    }
}


class UTM{

    int n;
    double a[];

    UTM(int n){
        this.n = n;
        a = new double[n*(n+1)/2];
    }

    int map(int i,int j){
        return n*i + j - (i*(i+1))/2;
    }

    void read(){

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter non-zero elements of Upper Triangular Matrix");

        for(int i=0;i<n;i++)
            for(int j=i;j<n;j++)
                a[map(i,j)] = sc.nextDouble();
    }

    void print(){

        for(int i=0;i<n;i++){

            for(int j=0;j<n;j++){

                if(j>=i)
                    System.out.print(a[map(i,j)]+"\t");
                else
                    System.out.print("0\t");
            }

            System.out.println();
        }
    }

    double[][] multiply(LTM B){

        double C[][] = new double[n][n];

        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                for(int k=0;k<n;k++)
                    if(j>=k && k>=i)
                        C[i][j]+=a[map(i,k)]*B.a[B.map(k,j)];

        return C;
    }
}


public class Main{

    void print(double C[][]){

        for(int i=0;i<C.length;i++){

            for(int j=0;j<C.length;j++)
                System.out.print(C[i][j]+"\t");

            System.out.println();
        }
    }

    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter order of matrix: ");
        int n = sc.nextInt();

        LTM L = new LTM(n);
        UTM U = new UTM(n);

        L.read();
        U.read();

        System.out.println("\nLower Triangular Matrix");
        L.print();

        System.out.println("\nUpper Triangular Matrix");
        U.print();

        Main m = new Main();

        System.out.println("\nLTM × UTM");
        m.print(L.multiply(U));

        System.out.println("\nUTM × LTM");
        m.print(U.multiply(L));
    }
}
