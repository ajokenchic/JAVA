package org.hyperledger.fabric.chaincode;

import java.util.Scanner;

class Node{
    private int data;
    Node next;

    public Node(){};
    public Node(int data) {
        this.data = data;
        next = null;
    }
    public int returnData(){ return data; }
}
class LinkedList{
    Node first;
    public LinkedList(){
        first = null;
    }
    public void insert(int data){
        Node New = new Node(data);
        New.next = first;
        first = New;
    }
    public void Show(){
        while(first.next!=null){
            System.out.println("["+first.returnData()+"]");
            first = first.next;
        }
    }

}
public class LinkedListEx {

    public static void Push(LinkedList L){
        L.insert(a);
        Scanner scan = new Scanner(System.in);
        for (int i = 1; i <= L.first.returnData(); i++) L.insert(scan.nextInt());
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("몇 개의 숫자를 받으십니까?");
        int a=scan.nextInt();
        LinkedList[] lst = new LinkedList[12];
        Push(lst[0],a);
        lst[0].Show();
    }
}

