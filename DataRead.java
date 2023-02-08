package data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import data.DataFile;
import exception.DataException;

public class DataRead {
    
    private DataFile dataFile;

    public DataRead(DataFile data){
        dataFile=data;
    }

    public String getString(String property){
        String value="";
        boolean find=false;
        try {
            if(dataFile.isExist()){
                Scanner scan=new Scanner(dataFile.getFile());
                while (scan.hasNext()) {
                    String line=scan.next();
                    if(line.split("=")[0].compareTo(property)==0){
                        value=line.split("=")[1];
                        find=true;
                        break;
                    }
                }
                if(find){
                    return value;
                }
                else{
                    try {
                        throw new DataException("the property '"+property+"' doesn't exist in the file "+dataFile.getFileName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            else{
                try {
                    throw new DataException("The file '"+dataFile.getFileName()+"' is not found in the repository : res/");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getInt(String property){
        return Integer.parseInt(getString(property));
    }

    public double getDouble(String property){
        return Double.parseDouble(getString(property));
    }

    public float getFloat(String property){
        return Float.parseFloat(getString(property));
    }

    public String[] getList(String property,String regex){
        String[] f= getString(property).split(regex);
        if(f.length>1){
            return f;
        }
        else{
            return null;
        }
    }

    public Date getDate(String property){
        try {
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            return format.parse(getString(property));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public HashMap<String,String> getAll(){
        HashMap<String,String> val=new HashMap<>();
        try {
            boolean find=false;
            if(dataFile.isExist()){
                Scanner scan=new Scanner(dataFile.getFile());
                while (scan.hasNext()) {
                    String line=scan.next();
                    val.put(line.split("=")[0], line.split("=")[1]);
                }
            }
            else{
                try {
                    throw new DataException("The file '"+dataFile.getFileName()+"' is not found in the repository : res/");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return val;
    } 

    public int count(){
        return getAll().size();
    }

    public String getProperty(String data){
        String value="";
        try {
            boolean find=false;
            if(dataFile.isExist()){
                Scanner scan=new Scanner(dataFile.getFile());
                while (scan.hasNext()) {
                    String line=scan.next();
                    if(line.split("=")[1].compareTo(data)==0){
                        value=line.split("=")[0];
                        find=true;
                        break;
                    }
                }
                if(find){
                    return value;
                }
                else{
                    try {
                        throw new DataException("the property of the data '"+data+"' doesn't exist in the file "+dataFile.getFileName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            else{
                try {
                    throw new DataException("The file '"+dataFile.getFileName()+"' is not found in the repository : res/");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getProperty(String[] list){
        String data="";
        for (int i = 0; i < list.length; i++) {
            if(i<list.length-1){
                data+=list[i]+";";
            }
            else{
                data+=list[i];
            }
        }
        String value="";
        try {
            boolean find=false;
            if(dataFile.isExist()){
                Scanner scan=new Scanner(dataFile.getFile());
                while (scan.hasNext()) {
                    String line=scan.next();
                    if(line.split("=")[1].compareTo(data)==0){
                        value=line.split("=")[0];
                        find=true;
                        break;
                    }
                }
                if(find){
                    return value;
                }
                else{
                    try {
                        throw new DataException("the property of the data '"+data+"' doesn't exist in the file "+dataFile.getFileName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            else{
                try {
                    throw new DataException("The file '"+dataFile.getFileName()+"' is not found in the repository : res/");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getProperty(String data,String regex){
        String value="";
        try {
            boolean find=false;
            if(dataFile.isExist()){
                Scanner scan=new Scanner(dataFile.getFile());
                while (scan.hasNext()) {
                    String line=scan.next();
                    String[] listData=getList(line.split("=")[0], regex);
                    for (String donnee : listData) {
                        if(donnee.compareTo(data)==0){
                            value=line.split("=")[0];
                            find=true;
                            break;
                        }
                    }
                }
                if(find){
                    return value;
                }
                else{
                    try {
                        throw new DataException("the property of the data '"+data+"' doesn't exist in the file "+dataFile.getFileName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            else{
                try {
                    throw new DataException("The file '"+dataFile.getFileName()+"' is not found in the repository : res/");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
