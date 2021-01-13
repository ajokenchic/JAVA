package org.hyperledger.fabric.chaincode;

import java.util.Random;


public class Bug {
    static int[] Travel(int x,int y,int size) {//public,static 확인.->lesson:static을 안붙이면 main에서 클래스로 객체선언해서 사용가능.
        Random rand=new Random();
        int travel;
        int a=x;
        int b=y;
        travel=rand.nextInt(7);
        if(travel==0) y--;//
        else if(travel==1) {
            x++;
            y--;
        }
        else if(travel==2)x++;
        else if(travel==3) {
            x++;
            y++;
        }
        else if(travel==4)y++;
        else if(travel==5) {
            x--;
            y++;
        }
        else if(travel==6)x--;
        else if(travel==7){
            x--;
            y--;
        }
        if (x > size - 1 || x < 0) {
            x = a;
            y = b;
        }
        if (y > size - 1 || y < 0){
            x = a;
            y = b;
        }
        int point[]=new int[2];
        point[0]=x;
        point[1]=y;
        return point;
    }
    static int Check(int a[][]){
        for(int i=0;i<a.length;i++){
            for(int j=0;j< a[0].length;j++){
                if(a[i][j]!=0) continue;
                else return 0;
            }
        }
        return 1;
    }

    static void Print(int a[][]) {
        for(int i=0;i<a.length;i++){
            for(int j=0;j< a[0].length;j++){
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        //Bug b=new Bug(); static을 안쓸 때,
        Random rand=new Random();
        int size=10;
        int cnt=0;
        int position[][]=new int[size][size];//자동으로 0으로 초기화
        int x=rand.nextInt(size-1);
        int y=rand.nextInt(size-1);
        while(Check(position)==0){
            position[x][y]++;
            x=Travel(x,y,size)[0];
            y=Travel(x,y,size)[1];
            cnt++;
        }
        Print(position);
        System.out.println("벌레가 총 이동한 경로의 수: "+cnt);
    }
}
