package org.hyperledger.fabric.chaincode;

import java.util.Scanner;

public class Hello2030 {

    public static int sum(int n,int m) {
        return n+m;
    }

    public static void main(String[] args) {
        int n=2030;
        int i=20;
        System.out.println("헬로"+n);
        System.out.println("sum is "+sum(i,10));
        System.out.println("이름,도시,나이,체중,독신 여부를 빈칸으로 분리하여 입력.");
        Scanner scanner=new Scanner((System.in));
        String name=scanner.next(); //"kim"
        System.out.println("name = " + name);
        String city=scanner.next(); //"seoul"
        System.out.println("city = " + city);
        int age=scanner.nextInt(); // 20
        System.out.println("age = " + age);
        double weight=scanner.nextDouble();//65.1
        System.out.println("weight = " + weight);
        boolean inSingle=scanner.nextBoolean();//true
        System.out.println("inSingle = " + inSingle);
        scanner.close();
    }




}
