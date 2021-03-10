//package com.tuyano.gradle;
//
//import java.util.Arrays;
//import java.util.Random;
//import java.util.Scanner;
//
//
//class Node {
//    private int data;
//    Node next;
//
//    public Node() {
//        data = 0;
//        next = null;
//    }
//
//    public Node(int data) {
//        this.data = data;
//        this.next = null;
//    }
//
//    public int getData() {
//        return data;
//    }
//}
//
//class LinkedList {
//    private Node first;
//
//
//    public LinkedList() {
//        first = null;
//    }
//
//    public LinkedList(Node first) {
//        this.first = first;
//    }
//
//    public void insertData(int data) {
//        Node newNode = new Node(data);
//        if (first == null) first = newNode;
//        else {
//            Node tempNode = first;
//            while (tempNode.next != null)
//                tempNode = tempNode.next;
//            tempNode.next = newNode;
//        }
//    }
//
//    public void show() {
//        Node t = this.first;
//        int n = 0;
//        while (t != null) {
//            //System.out.println(t.getData());
//            System.out.println(t.getData());
//            t = t.next;
//        }
//        System.out.println("");
//    }
//
//    public void setFirst(Node a) {
//        this.first = a;
//    }
//
//    public Node FIRST() {
//        return this.first;
//    }
//
//    public int NUM() {
//        Node temp=this.first;
//        int num=1;
//        while(temp.next!=null){
//            temp=temp.next;
//            num++;
//        }
//        return num;
//    }
//}
//
//public class LinkEX {
//    static void init(LinkedList[]ls) {
//        Scanner scan = new Scanner(System.in);
//        Random rd = new Random();
//        System.out.println("몇 개의 노드를 받으십니까?");
//        int n = scan.nextInt();
//        ls[0]=new LinkedList();
//        for (int i = 0; i < n; i++) {
//            ls[0].insertData(rd.nextInt(99));
//        }
//    }
//    static void show(LinkedList[]ls){
//        for (int i = 1; i <ls.length; i++ ) {
//            if(ls[i]!=null) {
//                System.out.print("ls[" + i + "] = ");
//                ls[i].show();
//            }
//        }
//    }
//    static int split(LinkedList[] lst) {
//        LinkedList temp=new LinkedList();
//        temp=lst[0];
//        lst[1]=temp;
//
//        int j = 1;
//        int n;
//        int idx=0;
//        int []cnt=new int[256];
//        cnt[0]=lst[j].NUM();
//        cnt[1]=lst[j].NUM();
//        int flag=0;
//
//        while (flag!=1) {
//
//            n = lst[j].NUM() / 2;
//            Node b = lst[j].FIRST();
//            if(lst[j].NUM()!=1) {
//                if (lst[j].NUM() % 2 == 0) n = n - 1;
//                if (lst[j].NUM() > 2) {
//                    for (int i = 0; i < n; i++) b = b.next;
//                }
//                lst[2 * j + 1] = new LinkedList();
//                lst[2 * j + 1].setFirst(b.next);
//                b.next = null;
//                lst[2 * j] = new LinkedList();
//                lst[2 * j].setFirst(lst[j].FIRST());
//                cnt[2 * j] = lst[2 * j].NUM();
//                cnt[2 * j + 1] = lst[2 * j + 1].NUM();
//                lst[j]=null;
//                cnt[j] = 0;
//            }
//            //lst[j]=null;
//            j = j + 1;
//            if(check(cnt)) flag=1;
//
//        }
//        return cnt[0];
//    }
//    static boolean check(int[]cnt){
//        for(int i=1;i<cnt.length;i++){
//            if(cnt[i]>1) return false;
//        }
//        return true;
//    }
//    static void merge(LinkedList[] ls,int cnt){
//        int[]P=new int[cnt+1];
//        P[0]=cnt;
//        int j=0;
//        LinkedList temp=new LinkedList();
//        for (int i = 1; i <ls.length; i++ ) {
//            if(ls[i]!=null) {
//                P[j]=i;
//                j++;
//            }
//        }
//       for(int i=0;i<P.length-1;i++) {
//           if (ls[P[i]].FIRST().getData() > ls[P[i+1]].FIRST().getData()) {
//               temp=ls[P[i]];
//               ls[P[i]]=ls[P[i+1]];
//               ls[P[i+1]]=temp;
//               i=-1;
//           }
//       }
//    }
//     public static void main(String[] args) {
////        LinkedList list = new LinkedList();
////        init(list);
//        //list.show();
//        LinkedList[] ls = new LinkedList[40];
//        init(ls);
//        ls[0].show();
//        int cnt=split(ls);
//        show(ls);
//       // merge(ls,cnt);
//        //show(ls);
//
//    }
//}