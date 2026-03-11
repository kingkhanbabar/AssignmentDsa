class ArrayDs {
    int[] a;
    int size;
    int length;

    ArrayDs(int length){
        this.length = length;
        a = new int[length];
        size = 0;
    }

    boolean isEmpty(){
        return size == 0;
    }

    int size(){
        return size;
    }

    void insert(int x, int index){
        if(size == length) throw new RuntimeException("Array is full");
        if(index < 0 || index > size) throw new RuntimeException("Invalid Index");

        for(int i = size - 1; i >= index; i--){
            a[i+1] = a[i];
        }
        a[index] = x;
        size++;
    }

    void display(){
        System.out.print("Array: ");
        for(int i = 0; i < size; i++){
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    void series(int n){
        int evenIndex = 0;
        int oddIndex = 0;

        for(int i = 0; i < n; i++){
            if(i % 2 == 0){
                insert(i, evenIndex);
                evenIndex++;
                oddIndex++;
            } else {
                insert(i, oddIndex);
                oddIndex++;
            }
        }
    }
}

public class Assignment1 {
    public static void main(String[] args){
        ArrayDs s = new ArrayDs(20);
        s.series(10);
        s.display();
    }
}