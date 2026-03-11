package LinkedListSir;
import java.util.Scanner;

class Node {
    int data;
    Node link;

    Node(int data) {
        this.data = data;
        this.link = null;
    }
}

public class LinkedListCreate {

    Node first = null;

    public void create(int n) {
        Scanner sc = new Scanner(System.in);

        if (n <= 0) {
            System.out.println("List size must be greater than 0.");
            return;
        }

        System.out.println("Enter " + n + " elements:");

        Node cur = null;

        for (int i = 1; i <= n; i++) {

            int data = sc.nextInt();

            if (i == 1) {
                cur = first = new Node(data);
            } else {
                cur.link = new Node(data);
                cur = cur.link;
            }
        }
    }
    public boolean isEmpty() {
        if (first == null) {
            return true;
        } else {
            return false;
        }
    }
    public int size() {
        int size = 0;
        Node cur = first;

        while (cur != null) {
            size++;
            cur = cur.link;
        }

        return size;
    }
    public int indexOf(int x) {

        int index = 0;
        Node cur = first;

        while (cur != null && cur.data != x) {
            cur = cur.link;
            index++;
        }

        if (cur == null) {
            return -1;
        } else {
            return index;
        }
    }
    public int get(int index) {

        if (index < 0 || index >= size()) {
            throw new RuntimeException("Invalid index");
        }

        Node cur = first;

        for (int i = 0; i < index; i++) {
            cur = cur.link;
        }

        return cur.data;
    }
    public  void insert(int x,int index){
        if(index<0 || index>size()){
            throw new RuntimeException("Invalid index");

        }
        Node newNode = new Node(x);
        newNode.data=x;
        newNode.link=null;
        if(index==0){
            newNode.link=first;
            first= newNode;
        }
        else{
            Node prev = first;
            for (int i = 1; i <=index-1; i++) {
                prev= prev.link;
            }
            newNode.link= prev.link;
            prev.link= newNode;
        }

    }
public int del(int index){
        if(index<0 || index>=size()){
            throw  new RuntimeException("Invalid index bro");
        }
        int x;
        Node del;
        if(index==0){
            del= first;
            x= first.data;
            first= first.link;

        }
        else {
            Node prev = first;
            for (int i=1;i<index;i++){
                prev=prev.link;
            }
            del = prev.link;
            x= del.data;
            prev.link=del.link;
        }
        del.link= null;
        return x;
}

    public void display() {
        if (first == null) {
            System.out.println("List is empty.");
            return;
        }

        Node temp = first;
        while (temp != null) {
            System.out.print(temp.data);
            if (temp.link != null) {
                System.out.print(" -> ");
            }
            temp = temp.link;
        }
        System.out.println(" -> NULL");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedListCreate list = new LinkedListCreate();

        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        list.create(n);

        System.out.println("Linked List:");
        list.display();
        System.out.print("Enter value to insert: ");
        int value = sc.nextInt();

        System.out.print("Enter index to insert at: ");
        int index = sc.nextInt();

        list.insert(value, index);
        list.display();

        System.out.println(list.del(3));
    }
}