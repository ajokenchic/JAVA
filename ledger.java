package com.tuyano.gradle;


import java.io.*;
import java.util.*;


public class JAVAledger {
    public static void main(String[] args) {
      //파일에 저널 기록
      Scanner scanner = new Scanner(System.in);

      FileWriter fw = null;

      try {
        fw = new FileWriter("/home/mark/바탕화면/journal.txt");
        while (true) {
            System.out.println("Acct.No,Check No.,Date,Description,Debit/credit");
            String line = scanner.nextLine();
            if(line.length()==0)break;
            fw.write(line, 0, line.length());
            fw.write("\n", 0, 1);
        }
        fw.close();
      } catch (IOException e) {
        System.out.println("Error");
      }
      scanner.close();
        FileInputStream fn = null;
        FileReader fr=null;
        HashMap<Integer, Ledger> lmap = new HashMap<Integer, Ledger>();

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
        String[] line=str.split("\n");
        int idx = 0;
        while(idx!= line.length){
            StringTokenizer st=new StringTokenizer(line[idx],",");
            int acnum=new Integer(st.nextToken());
            Ledger L=new Ledger();
            L.acctname=st.nextToken();
            L.prevBal=new Float(st.nextToken());
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
                float dorc=new Float(st.nextToken());
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
                float sum=0;
                for(int i=0;i<l.transac.size();i++){
                    sum+=l.transac.get(i).DorC;
                }
                l.newBal=l.prevBal+sum;
            }
        }
        it=lmap.keySet().iterator();
        while (it.hasNext()){
            int acct=it.next();
            if(lmap.get(acct).transac.size()!=0) {
                Ledger l = lmap.get(acct);
                System.out.println(acct + "  " + l.acctname);
                for (int i = 0; i < l.transac.size(); i++) {
                    System.out.println("       " + l.transac.get(i).check + "  " + l.transac.get(i).date + "  " + l.transac.get(i).desc + "  " + l.transac.get(i).DorC);
                }
                System.out.println("                   Prev.bal : " + l.prevBal + "       New bal: " + l.newBal);
            }
        }


    }
}

class Ledger {
    String acctname;
    float prevBal;
    float newBal;
    ArrayList<Journal> transac;

    public Ledger() {
    }
    public Ledger(String acctname, float prevBal, float newBal, ArrayList<Journal> transac) {
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
    float DorC;

    public Journal() {
    }
    public Journal(int check,String date, String desc, float dorC) {

        this.check = check;
        this.date=date;
        this.desc = desc;
        DorC = dorC;
    }
}
