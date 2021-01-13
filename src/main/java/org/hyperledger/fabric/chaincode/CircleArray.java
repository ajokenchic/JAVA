package org.hyperledger.fabric.chaincode;

class Circle {
    int radius;
    public Circle(int radius){
        this.radius=radius;
    }
    public double getArea(){
        return 3.14*radius*radius;
    }
}
public class CircleArray{
    public static void main(String[] args) {
        Circle[] c;//Circle배열에 대한 레퍼런스 c선언.
        c=new Circle[5];

        for(int i=0;i<c.length;i++) c[i]=new Circle(i); //배열의 개수만큼 i번째 원소 객체 생성.
        for(int i=0;i<c.length;i++) System.out.println((int)(c[i].getArea())+" ");
        //배열의 모든 Circle객체의 면적 출력.
    }
}
