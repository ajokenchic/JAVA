package org.hyperledger.fabric.chaincode;



//public class The99dan {
//    public static void main(String[] args) {
//
//        int intArray[]=new int[81];
//        int m=0;
//
//        for(int i=1;i<=9;i++){
//            for(int j=1;j<=9;j++) {
//                intArray[m]=i*j;
//                System.out.println(i+" X "+j+"="+intArray[m]);
//                m++;
//            }
//        }
//    }
//}
//2차원배열로 만들기
public class The99dan {
    public static void main(String[] args) {

        int intArray[][]=new int[9][9];

        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++) {
                intArray[i][j]=(i+1)*(j+1);
                System.out.println((i+1)+" X "+(j+1)+"="+intArray[i][j]);
            }
        }
    }
}