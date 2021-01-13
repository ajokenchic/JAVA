package org.hyperledger.fabric.chaincode;

import java.util.Scanner;
import java.util.Random;
import java.util.Stack;

class Student {
    private String name;
    private int code;
    private int age;

    public Student() {}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}

public class ObjectArray {
    static void initStudent(Student[] s) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("총 학생 수: " + s.length);
        for (int i = 0; i < s.length; i++) {
            s[i] = new Student();
            s[i].setAge(20 + rand.nextInt(10));
            s[i].setCode(202100000 + rand.nextInt(99999));
            System.out.print("학생 이름 입력: ");
            s[i].setName(scan.next());
            System.out.println((i + 1) + "번째 학생: " + s[i].getAge() + "세 - " + s[i].getCode() + " - " + s[i].getName());
        }
        scan.close();
    }
    static void showStudent(Student[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            System.out.println("나이: " + a[i].getAge() + "세 , 학번: " + a[i].getCode() + ", 이름:" + a[i].getName());
        }
    }
    static Student[] stackStudent(Student[] a) {
        Student[] temp = new Student[a.length];
        Stack<Student> ss = new Stack<>();
        for (int i = 0; i < a.length; i++) ss.push(a[i]);
        for (int i = 0; i < a.length; i++) temp[i] = ss.pop();
        return temp;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("학생 수 입력: ->");
        Student[] s = new Student[scan.nextInt()];
        initStudent(s);
        showStudent(s);
        showStudent(stackStudent(s));
    }
}


