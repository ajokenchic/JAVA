package ledgerProject;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class FileIO {
    public static void main(String[] args) {
        FileReader fr=null;
        String str="";
        try {
            fr=new FileReader("/home/mark/바탕화면/streamer.txt");
            int data = 0;
            while ((data = fr.read()) != -1) {
                str = str + (char) data;
            }
            fr.close();
        } catch (IOException e) {
            System.out.println("출력 오류");
        }
        String[] ac=str.split("&");//계정 구분자 &
        for(int i=0;i<ac.length;i++){
            String[] field=ac[i].split("@");//필드 구분자 @
            System.out.println(field[0]+"  "+field[1]);
            String[] list=field[4].split("!");//리스트 구분자 !
            for (int j=0;j< list.length;j++) {
                String[] comp=list[j].split(";");//요소 구분자 ;
                System.out.println("           "+comp[0]+"  "+comp[1]+"  "+comp[2]+"       "+comp[3]);
            }
            System.out.println("                 prev.Bal: "+field[2]+"  new Bal  "+field[3]);
        }

    }
}


