package org.hyperledger.fabric.chaincode;

import java.util.Scanner;


public class Arraysort {//1.입력(맨 앞에 사이즈) 2.미니멈 3.교체

    static void Insert(int[] a) {
        Scanner scanner = new Scanner((System.in));
        int i = 0;
        int flag = 0;
        while (flag == 0) {
            i++;
            a[i] = scanner.nextInt();
            if (a[i] <= 0) flag = 1;
        }
        a[0] = i-1;
    }

    static void Print(int a[]) {
        System.out.print("[");
        for (int i = 1; i <= a[0]; i++) {
            if (i == a[0]) System.out.print(a[i]);
            else System.out.print(a[i] + ",");
        }
        System.out.print("]");
    }

    static void makeHeap(int[] a) {
        int n = a[0];
        for (int I = n / 2 + 1; I <= n; I++) {
            int j = I;
            while (j >1) {
                //System.out.println("j = " + j); 디버그
                if (a[j] > a[j / 2]) {
                    //System.out.println("j = " +j+":"+ a[j] + ","+ j/2 +" : " +a[j/2]); 디버그
                    int temp = a[j / 2];
                    a[j / 2] = a[j];
                    a[j] = temp;
                    j=I;//--------------------┐
                    continue;//치환이 일어나면 리프와 다시 비교해주게-오류 입력=[12, 3, 5,8, 12, 9, 4, 6,7,11,10,12]
                }
                j = j / 2;//---------------------힙형식 만들어 주기.
            }
        }
    }

    static void sortHeap(int[] a, int[] r) {
        int Order = a[0];
        r[0] = Order;
        int[] temp = new int[a[0]];
        temp = a;
        while (Order != 0) {
            r[Order] = deleteMax(temp);
            Order--;//제일 큰 수를 Result 배열 맨 끝으로 보내고.
        }
    }

    static int deleteMax(int[] a) {

        int max = a[1];
        int n = a[0];
        a[0]--;
        if(a[0]==0) return a[2];
        int x = a[n];
        int l = 1;//l is level
        int flag = 0;
        while (flag == 0) {
            if (a[2 * l]>a[2 * l + 1]) {
                if (a[2 * l] >x) {
                    a[l] = a[2 * l];
                    a[2 * l] = x;
                }
                else  a[l] = x;
                l = 2 * l;
            }
            else {
                if (a[2 * l + 1] >x) {
                    a[l] = a[2 * l + 1];
                    a[2 * l + 1] = x;
                }
                else  a[l] = x;
                l = 2 * l + 1;
            }
            if (a[0] < 2 * l || a[0] < 2 * l + 1) flag = 1;
        }
        return max;
        //for (int i = 1; i < a[0]; i++) a[i] = a[i + 1];//제일 큰 수 삭제.

    }


    public static void main(String[] args) {
        //int[] Arr = new int[]{12,12, 3, 5,8, 12, 9, 4, 6,7,11,10,12};
        int[] Arr=new int[256];
        Insert(Arr);
        int[] result = new int[Arr.length];
        Print(Arr);
        makeHeap(Arr);
        Print(Arr);
        sortHeap(Arr, result);
        Print(result);
    }
}
