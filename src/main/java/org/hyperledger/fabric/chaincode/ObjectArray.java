package org.hyperledger.fabric.chaincode;

import java.util.Scanner;
import java.util.Random;
import java.util.Stack;

class Person {
    private String name;
    private int age;

    public Person() {}
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public void show() {
        System.out.println("이름: "+name+", 나이: "+age);
    }
}
class Student extends Person {
    private String school;
    private int grade;

    public Student() {}
    public Student(String name,int age,String school, int grade) {
        super(name,age);
        this.school = school;
        this.grade = grade;
    }

    public void show() {
        super.show();
        System.out.println("학교: "+school+",학년: "+grade);
    }
}
class workStudent extends Student{
    private String job;
    private int salary;

    public workStudent(){}
    public workStudent(String name,int age,String school, int grade,String job,int salary){
        super(name, age, school, grade);
        this.job=job;
        this.salary=salary;
    }
    public void show(){
        super.show();
        System.out.println("직업: "+job+",연봉: "+salary+" 만");
    }
}

public class ObjectArray {
    static void initStudent(workStudent[] students){
        for(int i=0;i<students.length;i++){
            Scanner scan=new Scanner(System.in);
            Random rand=new Random();
            System.out.println("이름과 학교,직업 입력하세요.");
            students[i]=new workStudent(scan.next(), 20+rand.nextInt(10),scan.next(),1+rand.nextInt(3),scan.next(),2000+rand.nextInt(2000));
            students[i].show();
            System.out.println("---------------------------------");
        }

    }
    static void show(workStudent[] students){
        for(int i=0;i<students.length;i++)students[i].show();
    }
    static workStudent[] stack(workStudent[] students){
        workStudent[] ss=new workStudent[students.length];
        Stack<workStudent> stack=new Stack<>();

        for(int i=0;i<students.length;i++) stack.push(students[i]);
        for(int i=0;i< students.length;i++) ss[i]=stack.pop();

        return ss;
    }

    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        System.out.println("학생 수 입력: ");
        workStudent[]student=new workStudent[scan.nextInt()];
        initStudent(student);
        show(student);
        System.out.println("---------------------------------");
        show(stack(student));
        scan.close();
    }
}


