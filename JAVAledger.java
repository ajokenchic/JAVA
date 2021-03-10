package com.tuyano.gradle;


import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class JAVAledger {
    public static void main(String[] args) {
        FileInputStream fn = null;
        FileReader fr=null;
        TreeMap<Integer, Ledger> lmap = new TreeMap<Integer, Ledger>();

        String str = "";
        //계정과목 맵 설정.
        try {
            fn = new FileInputStream("/home/mark/바탕화면/ledger.txt");
            int data = 0;
            while ((data = fn.read()) != -1) {
                str = str + (char) data;
            }
            fn.close();
        } catch (IOException e) {
            System.out.println("출력 오류");
        }
        String str2=str;

        String[] m=str.split("MONTH\n");
        String[] line=m[m.length-1].split("\n");
        int idx = 0;
        while(idx!= line.length){
            StringTokenizer st=new StringTokenizer(line[idx],",");
            int acnum=new Integer(st.nextToken());
            Ledger L=new Ledger();
            L.acctname=st.nextToken();
            L.prevBal=new Double(st.nextToken());
            L.transac=new ArrayList<Journal>();
            lmap.put(acnum,L);
            idx++;
        }

        str=null;
        //저널 반영.
        try {
            fr= new FileReader("/home/mark/바탕화면/journal.txt");
            BufferedReader reader=new BufferedReader(fr);
            String data;
            while((data=reader.readLine())!=null){
                StringTokenizer st=new StringTokenizer(data,",");
                int acct=new Integer(st.nextToken());
                int check=new Integer(st.nextToken());
                String date=st.nextToken();
                String desc=st.nextToken();
                double dorc=new Double(st.nextToken());
                Journal j=new Journal(check,date,desc,dorc);
                lmap.get(acct).transac.add(j);
            }
            fr.close();
        } catch (IOException e) {
            System.out.println("출력 오류");
        }
        //월말 분개.
        Iterator<Integer>it=lmap.keySet().iterator();
        while(it.hasNext()){
            Ledger l=lmap.get(it.next());
            if(l.transac.size()!=0){
                double sum=0;
                for(int i=0;i<l.transac.size();i++){
                    sum+=l.transac.get(i).DorC;
                }
                l.newBal=l.prevBal+sum;
            }
        }
        it=lmap.keySet().iterator();
        String str3="MONTH\n";
        String lstr="";
        while (it.hasNext()){
            int acct=it.next();
            Ledger l = lmap.get(acct);
            if(lmap.get(acct).transac.size()!=0) {
                lstr=lstr+acct+"@"+l.acctname+"@"+String.format("%.2f",l.prevBal)+"@"+String.format("%.2f",l.newBal)+"@";
                System.out.println(acct + "  " + l.acctname);
                for (int i = 0; i < l.transac.size(); i++) {
                    lstr=lstr+l.transac.get(i).check+";"+l.transac.get(i).date+";"+l.transac.get(i).desc+";"+String.format("%.2f",l.transac.get(i).DorC)+"!";
                    System.out.println("       " + l.transac.get(i).check + "  " + l.transac.get(i).date + "  " + l.transac.get(i).desc + "  " + l.transac.get(i).DorC);
                }
                lstr=lstr+"&";
                System.out.println("                   Prev.bal : " + String.format("%.2f",l.prevBal) + "       New bal: " + String.format("%.2f",l.newBal));
                str3=str3+acct+","+l.acctname+","+String.format("%.2f",l.newBal)+"\n";
            }
            else str3=str3+acct+","+l.acctname+","+String.format("%.2f",l.prevBal)+"\n";
        }

        FileWriter fw=null;
        try {
            fw=new FileWriter("/home/mark/바탕화면/FinalLedger.txt");
            fw.write(str2+str3);
            fw.close();
        }catch (IOException e){
            System.out.println("최종원장 생성불가");
        }
        try {
            fw=new FileWriter("/home/mark/바탕화면/streamer.txt");
            fw.write(lstr);
            fw.close();
        }catch (IOException e){
            System.out.println("최종원장 생성불가");
        }



    }
}

class Ledger {
    String acctname;
    double prevBal;
    double newBal;
    ArrayList<Journal> transac;

    public Ledger() {
    }
    public Ledger(String acctname, double prevBal,double newBal, ArrayList<Journal> transac) {
        this.acctname = acctname;
        this.prevBal = prevBal;
        this.newBal = newBal;
        this.transac = transac;
    }
}

class Journal {

    int check;
    String date;
    String desc;
    double DorC;

    public Journal() {
    }
    public Journal(int check,String date, String desc, double dorC) {

        this.check = check;
        this.date=date;
        this.desc = desc;
        DorC = dorC;
    }
}




