package org.hyperledger.fabric.chaincode;

import java.util.Random;

// [1] parameter를 보내는 버젼.
//public class Matrix {
//    public static int[][] Init(int[][] a, int r, int c) {
//        Random random = new Random();
//        for (int i = 0; i < r; i++) {
//            for (int j = 0; j < c; j++) a[i][j] = random.nextInt(100);
//        }
//        return a;
//    }
//
//    public static void Print(int[][] a, int r, int c) {
//        for (int i = 0; i < r; i++) {
//            for (int j = 0; j < c; j++) System.out.print(a[i][j] + " ");
//            System.out.println();
//        }
//        System.out.println("----------------------------------------");
//    }
//
//    public static int[][] Add(int[][] a, int[][] b, int r, int c) {
//        int add[][] = new int[r][c];
//        for (int i = 0; i < r; i++) {
//            for (int j = 0; j < c; j++) add[i][j] = a[i][j] + b[i][j];
//        }
//        return add;
//    }
//
//    public static int[][] Multi(int[][] a, int[][] b, int rowa, int cola, int colb) {
//        int multi[][] = new int[rowa][colb];
//        int mul;
//        for (int i = 0; i < rowa; i++) {
//            for (int j = 0; j < colb; j++) {
//                mul = 0;
//                for (int k = 0; k < cola; k++) {
//                    mul += (a[i][k] * b[k][j]);
//                }
//                multi[i][j] = mul;
//            }
//        }
//        return multi;
//    }
//
//    public static void main(String[] args) {
//        int rowa = 3;
//        int cola = 4;
//        int colb = 5;
//        int a[][] = new int[rowa][cola];
//        int b[][] = new int[rowa][cola];
//        int c[][] = new int[cola][colb];
//        Init(a, rowa, cola);
//        Init(b, rowa, cola);
//        Init(c, cola, colb);
//        Print(a, rowa, cola);
//        Print(b, rowa, cola);
//        Print(c, cola, colb);
//        Print(Add(a, b, rowa, cola), rowa, cola);
//        Print(Multi(a, c, rowa, cola, colb), rowa, colb);
//
//    }
//}

// [2] length를 사용하는 버젼.
public class Matrix {
    public static int[][] Init(int[][] a) {
        Random random = new Random();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) a[i][j] = random.nextInt(10);
        }
        return a;
    }

    public static void Print(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) System.out.print(a[i][j] + " ");
            System.out.println();
        }
        System.out.println("----------------------------------------");
    }

    public static int[][] Add(int[][] a, int[][] b) {
        int add[][] = new int[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) add[i][j] = a[i][j] + b[i][j];
        }
        return add;
    }

    public static int[][] Multi(int[][] a, int[][] b) {
        int multi[][] = new int[a.length][b[0].length];
        int mul;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                mul = 0;
                for (int k = 0; k < a[0].length; k++) {
                    mul += (a[i][k] * b[k][j]);
                }
                multi[i][j] = mul;
            }
        }
        return multi;
    }

    public static <delete> void main(String[] args) {
        int a[][] = new int[3][4];
        int b[][] = new int[3][4];
        int c[][] = new int[4][6];
        Init(a);
        Init(b);
        Init(c);
        Print(a);
        Print(b);
        Print(c);
        Print(Add(a, b));
        Print(Multi(a, c));
    }
}