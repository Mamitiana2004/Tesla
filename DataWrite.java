package data;

import java.io.FileWriter;
import java.util.Scanner;

import exception.DataException;

public class DataWrite {
    
    private DataFile datafile;

    public DataWrite(DataFile data){
        datafile=data;
    }

    public void clear(){
        try {
            FileWriter fileWriter=new FileWriter(datafile.getFile());
            fileWriter.write("");
            fileWriter.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void create(){
        if(!datafile.isExist()){
            datafile.createFile(datafile.getFileName());
        }
        else{
            try {
                throw new DataException(datafile.getFileName()+" already exist in this project");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void insert(String property,String data){
        String fileText="";
        boolean canInsert=true;
        if(datafile.isExist()){
            try {
                Scanner scan=new Scanner(datafile.getFile());
                while (scan.hasNext()) {
                    String line=scan.next();
                    if (!line.split("=")[0].equalsIgnoreCase(property)) {
                        fileText+=line+"\n";
                    }
                    else{
                        canInsert=false;
                    }
                    
                }
                if(canInsert){    
                    fileText+=property+"="+data;
                    FileWriter fileWriter=new FileWriter(datafile.getFile());
                    fileWriter.write(fileText);
                    fileWriter.close();
                    System.out.println("INSERT OK");
                }
                else{
                    try {
                        throw new DataException(property+" is already in the file \""+datafile.getFileName()+"\"");
                    } catch (DataException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(String property){
        String fileText="";
        boolean canDelete=false;
        if(datafile.isExist()){
            try {
                Scanner scan=new Scanner(datafile.getFile());
                while (scan.hasNext()) {
                    String line=scan.next();
                    if(line.split("=")[0].equalsIgnoreCase(property)){
                        canDelete=true;
                    }
                    else{
                        fileText+=line+"\n";
                    }
                }
                if(canDelete){
                    FileWriter fileWriter=new FileWriter(datafile.getFile());
                    fileWriter.write(fileText);
                    fileWriter.close();
                    System.out.println("DELETE OK");
                }
                else{
                    try {
                        throw new DataException(datafile.getFileName()+" doesn't have the property '"+property+"'");
                    } catch (DataException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update(String property,String data){
        String fileText="";
        boolean canUpdate=false;
        if(datafile.isExist()){
            try {
                Scanner scan=new Scanner(datafile.getFile());
                while (scan.hasNext()) {
                    String line=scan.next();
                    if(line.split("=")[0].equalsIgnoreCase(property)){
                        canUpdate=true;
                        fileText+=line.split("=")[0]+"="+data+"\n";
                    }
                    else{
                        fileText+=line+"\n";
                    }
                }
                if(canUpdate){
                    FileWriter fileWriter=new FileWriter(datafile.getFile());
                    fileWriter.write(fileText);
                    fileWriter.close();
                    System.out.println("UPDATE OK");
                }
                else{
                    try {
                        throw new DataException(datafile.getFileName()+" doesn't have the property '"+property+"'");
                    } catch (DataException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
