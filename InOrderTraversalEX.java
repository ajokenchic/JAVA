package com.tuyano.gradle;

import java.util.Scanner;

class Node{
    private int data;
    Node left;
    Node right;

    public Node(){
        data=0;
        left=null;
        right=null;
    }
    public Node(int data){
        this.data=data;
        left=null;
        right=null;
    }

    public void setData(int data) {
        this.data = data;
    }
    public int getData() {
        return data;
    }
}
class Tree{
    private Node root;

    public Tree(){
        root=null;
    }
    public void Insert(int data){
        Node newNode=new Node(data);
        int flag=0;
        Node temp = root;
        if(root==null) root=newNode;
        else {
            while(flag==0) {
                if (data > temp.getData()) {
                    if (temp.right == null) {
                        temp.right = newNode;
                        flag=1;
                    }
                    else {
                        temp = temp.right;
                    }
                } else {
                    if (temp.left == null) {
                        temp.left = newNode;
                        flag=1;
                    }
                    else {
                        temp = temp.left;
                    }
                }
            }
        }
    }
    private void inorder(Node p){
        if (p != null) {
            inorder(p.left);
            System.out.print(p.getData()+ " > ");
            inorder(p.right);
        }
    }
    public void show(){
        Node t=root;
        inorder(t);
        System.out.println("End");
    }
    public Node InorderSucc(Node c){
        Node temp=c.right;
        if(c.right!=null) while(temp.left!=null) temp=temp.left;
        return temp;
    }
    public Node InorderPred(Node c){
        Node temp=c.left;
        if(c.left!=null)while(temp.right!=null)temp=temp.right;
        return temp;
    }
    public void Delete(int target){
        Node t=root;
        Node tp=null;
        while(t.getData()!=target){
            if(t.getData()>target){
                tp=t;
                t=t.left;
            }
            else if(t.getData()<target){
                tp=t;
                t=t.right;
            }
            if(t==null) {
               System.out.println("찾는 값이 트리에 없어요.");
                return;
            }
        }
        System.out.println(t.getData()+" 의 노드를 찾았습니다.");
        //1.자식이 없을 때.
        if(t.right==null&&t.left==null){
            if(tp!=null) {
                if (tp.right == t) tp.right = null;
                else tp.left = null;
            }
            else root=null;
        }
        //2.자식이 하나만 있을때.
        else if(t.left==null&&t.right!=null){
            if(tp!=null) {
                if (tp.right == t) {
                    Node temp = t.right;
                    tp.right = null;
                    tp.right = temp;
                } else {
                    Node temp = t.right;
                    tp.left = null;
                    tp.left = temp;
                }
            }
            else {
                Node temp = t.right;
                root=null;
                root=temp;
            }
        }
        else if(t.left!=null&&t.right==null){
            if(tp!=null) {
                if (tp.right == t) {
                    Node temp = t.left;
                    tp.right = null;
                    tp.right = temp;
                } else {
                    Node temp = t.left;
                    tp.left = null;
                    tp.left = temp;
                }
            }
            else{
                Node temp = t.left;
                root=null;
                root=temp;
            }
        }
        //3.자식이 둘이 있을 때.
        else{
            //(1)suc이 자식이 없을 때.
            if (InorderSucc(t).right == null) {
                t.setData(InorderSucc(t).getData());
                if(t.right.getData()==InorderSucc(t).getData()) t.right=null;
                else {
                    t = t.right;

                    Node succpre = null;
                    while (t.left != null) {
                        succpre = t;
                        t = t.left;
                    }
                    succpre.left = null;
                }
           }
            //(2)suc이 자식이 있을 때.
            else {
                t.setData(InorderSucc(t).getData());
                if(t.right.getData()==InorderSucc(t).getData()) t.right=t.right.right;
                else {
                    t = t.right;
                    Node succpre = null;
                    while (t.left != null) {
                        succpre = t;
                        t = t.left;
                    }
                    succpre.left = t.right;
                }
            }
        }
    }
    public void Split(){
        int RDepth=0;
        int LDepth=0;
        Node Tr=root;
        Node Tl=root;
        Node Trav=root;
        Node Troot=null;
        while(Tr.right!=null){
            Tr=Tr.right;
            RDepth++;
        }
        while (Tl.left!=null){
            Tl=Tl.left;
            LDepth++;
        }
        Troot=Trav;
        Node temp=null;
        int cnt=0;
        if(LDepth-RDepth<0){
            cnt=(RDepth-LDepth)/2;
            if((RDepth-LDepth)%2==1) cnt=cnt+1;
            for(int i=0;i<cnt;i++){
                temp=Trav;
                Trav=Trav.right;
            }
            temp.right=null;
            root=Trav;
            root.left=Troot;
        }
        else if(LDepth-RDepth>0) {
            cnt=(LDepth-RDepth)/2;
            if((LDepth-RDepth)%2==1) cnt=cnt+1;
            for (int i = 0; i < cnt; i++) {
                temp=Trav;
                Trav = Trav.left;
            }
            temp.left = null;
            root=Trav;
            root.right = Troot;
        }

    }
    public Node getRoot() {return root;}
}
public class InOrderTraversalEX {
    static void Insert(Tree Forest){
        Scanner scan=new Scanner(System.in);
        System.out.println("숫자를 입력하세요.(0을 입력하면 종료)");
        int data=1;
        while (data!=0){
            data=scan.nextInt();
            if(data!=0) Forest.Insert(data);
        }
    }
    public static void main(String[] args) {
        Tree Forest=new Tree();
        Insert(Forest);
        Forest.show();
        System.out.println("지울 숫자를 입력하세요.");
        Scanner scan = new Scanner(System.in);
        int data;
        while((data = scan.nextInt())!=0) {
            Forest.Delete(data);//오
            Forest.show();
        }
        Forest.Split();
        Forest.show();
    }
}
