package data;

import java.util.Date;
import java.util.HashMap;

public class Data{

    private DataFile dataFile;
    private DataRead dataRead;
    private DataWrite dataWrite;

    public Data(String fileName,String folderName){
        dataFile=new DataFile(fileName,folderName);
        dataRead=new DataRead(dataFile);
        dataWrite=new DataWrite(dataFile);
    }

    public Data(String fileName){
        dataFile=new DataFile(fileName);
        dataRead=new DataRead(dataFile);
        dataWrite=new DataWrite(dataFile);
    }

    public String getString(String property){
        return dataRead.getString(property);
    }

    public int getInt(String property){
        return dataRead.getInt(property);
    }

    public double getDouble(String property){
        return dataRead.getDouble(property);
    }

    public float getFloat(String property){
        return dataRead.getFloat(property);
    }

    public Date getDate(String property){
        return dataRead.getDate(property);
    }

    public String[] getList(String property,String regex){
        
        String [] list=dataRead.getList(property,regex);
        return list;
    }

    public void clear(){
        dataWrite.clear();
    }

    public HashMap<String,String> getAll(){
        return dataRead.getAll();
    } 

    public int count(){
        return getAll().size();
    }

    public String getProperty(String data){
        return dataRead.getProperty(data);
    }

    public String getProperty(String[] list){
        return dataRead.getProperty(list);
    }

    public String getProperty(String data,String regex){
        return dataRead.getProperty(data, regex);
    }


    public void create(){
        dataWrite.create();
    }

    public void insert(String property,String data){
        dataWrite.insert(property,data);
    }

    public void delete(String property){
        dataWrite.delete(property);
    }

    public void update(String property,String data){
        dataWrite.update(property,data);
    }

    

}