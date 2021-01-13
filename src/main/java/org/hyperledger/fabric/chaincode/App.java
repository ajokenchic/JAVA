package org.hyperledger.fabric.chaincode;


public class App {
    public static void main(String[] args) {
        App app= new App();
        System.out.println("===App class=====");
        app.showMessage("Taro");
        app.showMessage("Hanako");
        System.out.println("====App class====");
    }
    public boolean showMessage(String name){
        try {
            System.out.println(this.getMessage(name));
        } catch (Exception e){
            System.out.println("[ERR: " + e.getMessage()+"]");
            return false;
        } finally {
            return true;
        }
    }
    public String getMessage(String name)throws Exception{
        if(name==null ||name==""){
            throw new Exception("no name!!");
        }
        return "Hi,"+name+". welcome to gradle world!";
    }
}

