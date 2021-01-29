package com.tuyano.gradle;

import java.util.Scanner;

class Node{
    private int Jisu;
    private int Kyesu;
    Node Next;

    public Node(){
        Jisu=0;
        Kyesu=0;
        Next=null;
    }
    public Node(int _Kyesu,int _Jisu){
        Kyesu=_Kyesu;
        Jisu=_Jisu;
        Next=null;
    }
    public void setJisu(int Jisu){
        this.Jisu=Jisu;
    }
    public void setKyesu(int Kyesu){
        this.Kyesu=Kyesu;
    }
    public int getJisu(){
        return Jisu;
    }
    public int getKyesu(){
        return Kyesu;
    }
}
class CalChain{
    private Node first;

    public CalChain(){
        this.first=null;
    };

    public void Insert(int Kyesu,int Jisu){
        Node newNode=new Node(Kyesu,Jisu);
        if(first==null)first=newNode;
        else {
            if(newNode.getJisu()==first.getJisu()){
                first.setKyesu(newNode.getKyesu()+ first.getKyesu());
            }
            else{
                if(newNode.getJisu()> first.getJisu()){
                    newNode.Next=first;
                    first=newNode;
                }
                else{
                    Node temp=first;
                    int flag=0;
                    while(flag!=1) {
                        if(temp.Next==null){
                            temp.Next=newNode;
                            break;
                        }
                        if(temp.Next.getJisu()<=newNode.getJisu()){
                            flag=1;
                            break;
                        }
                        temp=temp.Next;//
                    }
                    if(flag==1) {
                        if (newNode.getJisu() == temp.Next.getJisu()) {
                            temp.Next.setKyesu(newNode.getKyesu() + temp.Next.getKyesu());
                        }
                        else {
                            Node a = temp.Next;
                            temp.Next = newNode;
                            newNode.Next = a;
                        }
                    }
                }
            }
        }
    }
    public CalChain Add(CalChain Former){
        CalChain Res=new CalChain();
        Node F=Former.first;
        Node L=first;
        while(L!=null){
            Res.Insert(L.getKyesu(), L.getJisu());
            L=L.Next;
        }
        while(F!=null) {
            Res.Insert(F.getKyesu(),F.getJisu());
            F=F.Next;
        }
        return Res;
    }
    public CalChain Multi(CalChain Former){
        CalChain Res=new CalChain();
        Node F=Former.first;
        for(int i=0;i<Former.Num();i++){
            Node L=first;
            while (L!=null){
                Res.Insert(L.getKyesu()*F.getKyesu(),L.getJisu()+F.getJisu());
                L=L.Next;
            }
            F=F.Next;
        }
        return Res;
    }
    public void Show(){
        Node temp=first;

        while(temp.Next!=null){
            if(temp.getKyesu()!=0) {
                System.out.print(temp.getKyesu() + " (x^" + temp.getJisu() + ") + ");
            }
            temp=temp.Next;
        }

        if(temp.getJisu()==0)System.out.println(temp.getKyesu());
        else System.out.println(temp.getKyesu()+" (x^"+temp.getJisu()+")");
    }
    public int Num(){
        Node temp=first;
        int num=1;
        while(temp.Next!=null){
            temp=temp.Next;
            num++;
        }
        return num;
    }
}
public class CalculatorEX {
    static void Insert(CalChain A){
        Scanner input=new Scanner(System.in);
        System.out.println("계수와 지수 순서로 입력하고 끝낼 때는 0 0 을 입력하세요.");
        int a;
        int b;
        int flag=0;
        while(flag==0) {
            a=input.nextInt();
            b=input.nextInt();
            if(a==0&&b==0) {
                flag=1;
                break;
            }
            A.Insert(a,b);
        }
    }
    public static void main(String[] args) {
        CalChain A=new CalChain();
        Insert(A);
        CalChain B=new CalChain();
        Insert(B);
        CalChain Add=new CalChain();
        Add=A.Add(B);
        CalChain Mul=new CalChain();
        Mul=A.Multi(B);

        A.Show();
        B.Show();
        Add.Show();
        Mul.Show();
    }
}
