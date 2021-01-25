package com.tuyano.gradle;

import java.util.Random;

class ChainNode{
    private int data;
    ChainNode next;

    public ChainNode(){
        this.data=0;
        this.next=null;
    };
    public ChainNode(int data){
        this.data=data;
        this.next=null;
    };
    public int GetData(){
        return this.data;
    }
}
class Chain{
    private ChainNode first;

    public Chain(){
        this.first=null;
    }
    public Chain(ChainNode first){
        this.first=first;
    }

    public  void Insert(int data){
        ChainNode newNode=new ChainNode(data);
        if(first==null)first=newNode;
        else{
            ChainNode temp=first;
            while(temp.next!=null)
                temp=temp.next;
            temp.next=newNode;
        }
    }
    public void Show(){
        ChainNode temp=first;
        int n=0;
        while(temp!=null){
            System.out.print("["+temp.GetData()+"] -> ");
            temp=temp.next;
        }
        System.out.println("null");
    }
    public int Num(){
        if(this.first!=null){
        ChainNode temp=this.first;
        int num=1;
        while(temp.next!=null){
            temp=temp.next;
            num++;
        }
            return num;
        }
       else return 0;
    }
    public void SetFirst(ChainNode first){
        this.first=first;
    }
    public ChainNode GetFirst(){
        return this.first;
    }
    public Chain Split(Chain Former){
        ChainNode temp=this.first;
        int num=1;
        while(temp.next!=null){
            temp=temp.next;
            num++;
        }
        temp=this.first;
        int n=num/2;
        if(num%2==0) n=n-1;
        if(num>2){
            for(int i=0;i<n;i++) temp=temp.next;
        }
        Chain Latter=new Chain();

        Latter.SetFirst(temp.next);
        temp.next=null;
        Former.SetFirst(this.first);
        return Latter;
    }
    public Chain Merge(Chain CPR){
        ChainNode temp=this.first;
        Chain Merged =new Chain();
        int flag=0;
        while(flag==0) {
            if (temp.GetData() < CPR.first.GetData()) {
                Merged.Insert(temp.GetData());
                if (temp.next != null) temp = temp.next;
                else {
                    while(CPR.first!=null) {
                        Merged.Insert(CPR.first.GetData());
                        CPR.first=CPR.first.next;
                    }
                    flag = 1;
                }
            } else {
                Merged.Insert(CPR.first.GetData());

                if (CPR.first.next != null) CPR.first = CPR.first.next;
                else {
                    while(temp!=null) {
                        Merged.Insert(temp.GetData());
                        temp= temp.next;
                    }
                    flag = 1;
                }
            }
        }
        return Merged;
    }
}
public class SimpleSplitEX {
    static void Insert(Chain link, int cnt) {
        int n = 0;
        Random R = new Random();
        while (n != cnt) {
            link.Insert(R.nextInt(98) + 1);
            n++;
        }
    }
    static int[] Split(Chain link,Chain[] Splited) {
        Splited[0]=new Chain();
        Splited[1]=new Chain();
        Splited[0] = link;
        Splited[1] = link;
        int idx = 1;
        int flag=0;
        int []cnt=new int[Splited.length];
        cnt[0]=Splited[0].Num();
        cnt[1]=Splited[1].Num();

        while(flag!=1) {
            Splited[2 * idx] = new Chain();
            Splited[2 * idx + 1] = new Chain();
            Splited[2 * idx + 1] = Splited[idx].Split(Splited[2 * idx]);
            cnt[2*idx]=Splited[2*idx].Num();
            cnt[2*idx+1]=Splited[2*idx+1].Num();
            cnt[idx]=0;
            Splited[idx]=null;
            if(!check(cnt)) flag=1;
            idx++;
        }
        return cnt;
    }
    static void Merge(Chain[] Splited,int[] cnt){
        int findnull;
        for(int i=cnt.length-1;i>2;i--){
            if(cnt[i]==0) continue;
            else{
                for(findnull=i;findnull>0;findnull--){
                    if(cnt[findnull]==0) {
                        Splited[findnull]=Splited[i].Merge(Splited[i-1]);
                        Splited[i]=null;
                        Splited[i-1]=null;
                        cnt[i]=0;
                        cnt[i-1]=0;
                        cnt[findnull]=Splited[findnull].Num();
                        break;
                    }
                }
            }
            if(cnt[findnull]==cnt[0]) break;
        }
    }
    static boolean check(int[] cnt){
        for(int i=1;i<cnt.length;i++){
            if(cnt[i]>1) return true;
        }
        return false;
    }
    static void show(Chain[] Splited,int[] cnt){
        for(int i=1;i<Splited.length;i++) {
            if(cnt[i]>0) {
                System.out.print("Splited<"+i+"> :");
                Splited[i].Show();
            }
        }
    }
    public static void main(String[] args) {
        Chain link=new Chain();
        Insert(link,12);
        link.Show();
        Chain[] Splited=new Chain[link.Num()*3];
        int[] Count=new int[Splited.length];
        Count=Split(link,Splited);
        show(Splited,Count);
        Merge(Splited,Count);
        show(Splited,Count);
    }
}
