package data.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import data.exception.DataException;

public class Data {

    final File origine=new File("");
    
    public Data(String dataName,String directory){
        fileName=dataName;
        this.repository=directory;
        File file=new File(origine.getAbsoluteFile()+"/res/"+directory);
        if(file.mkdir()){
            System.out.println("Repository create");
        }
        exist=false;
        findFile();
    }

    public Data(String dataName){
        fileName=dataName;
        this.repository=null;
        exist=false;
        findFile();
    }

    private String repository;
    private String fileName;
    private File dataFile;
    private boolean exist;

    
    public String getFileName() {
        return fileName;
    }
    public File getDataFile() {
        return dataFile;
    }
    public boolean isExist() {
        return exist;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public void setDataFile(File dataFile) {
        this.dataFile = dataFile;
    }
    public void setExist(boolean exist) {
        this.exist = exist;
    }


    private void findFile(){
        File directory=null;
        if(this.repository!=null){
            directory=new File(origine.getAbsolutePath()+"/res/"+repository+"/"+fileName);
        }
        else{
            directory=new File(origine.getAbsolutePath()+"/res/"+fileName);
        }
        if(directory!=null){
            if(directory.exists()){
                exist=true;
                dataFile=directory;
            }
            else{
                exist=false;
            }
        }
    }


    public String getString(String property){
        String value="";
        try {
            boolean trouve=false;
            if(exist){
                Scanner scan=new Scanner(dataFile);
                while (scan.hasNext()) {
                    String line=scan.next();
                    if(line.split("=")[0].compareTo(property)==0){
                        value=line.split("=")[1];
                        trouve=true;
                        break;
                    }
                }
                if(trouve){
                    return value;
                }
                else{
                    try {
                        throw new DataException("the property '"+property+"' doesn't exist in the file "+fileName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            else{
                try {
                    throw new DataException("The file '"+fileName+"' is not found in the repository : res/");
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

    public Date getDate(String property){
        try {
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            return format.parse(getString(property));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] getList(String property,String regex){
        return getString(property).split(regex);
    }

    public HashMap<String,String> getAll(){
        HashMap<String,String> val=new HashMap<>();
        try {
            boolean trouve=false;
            if(exist){
                Scanner scan=new Scanner(dataFile);
                System.out.println("Find all properties ====> \n");
                while (scan.hasNext()) {
                    String line=scan.next();
                    val.put(line.split("=")[0], line.split("=")[1]);
                }
            }
            else{
                try {
                    throw new DataException("The file '"+fileName+"' is not found in the repository : res/");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n\n");
        return val;
    } 

    public int count(){
        return getAll().size();
    }

    public String getProperty(String data){
        String value="";
        try {
            boolean trouve=false;
            if(exist){
                Scanner scan=new Scanner(dataFile);
                System.out.println("Find the property of the data ====> "+data+"\n");
                while (scan.hasNext()) {
                    String line=scan.next();
                    System.out.println("data => "+line.split("=")[1]);
                    if(line.split("=")[1].compareTo(data)==0){
                        value=line.split("=")[0];
                        trouve=true;
                        break;
                    }
                }
                if(trouve){
                    return value;
                }
                else{
                    try {
                        throw new DataException("the property of the data '"+data+"' doesn't exist in the file "+fileName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            else{
                try {
                    throw new DataException("The file '"+fileName+"' is not found in the repository : res/");
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
            boolean trouve=false;
            if(exist){
                Scanner scan=new Scanner(dataFile);
                System.out.println("Find the property of the data ====> "+data+"\n");
                while (scan.hasNext()) {
                    String line=scan.next();
                    System.out.println("data => "+line.split("=")[1]);
                    if(line.split("=")[1].compareTo(data)==0){
                        value=line.split("=")[0];
                        trouve=true;
                        break;
                    }
                }
                if(trouve){
                    return value;
                }
                else{
                    try {
                        throw new DataException("the property of the data '"+data+"' doesn't exist in the file "+fileName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            else{
                try {
                    throw new DataException("The file '"+fileName+"' is not found in the repository : res/");
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
            boolean trouve=false;
            if(exist){
                Scanner scan=new Scanner(dataFile);
                System.out.println("Find the property of the data ====> "+data+"\n");
                while (scan.hasNext()) {
                    String line=scan.next();
                    System.out.println("data => "+line.split("=")[1]);
                    String[] listData=getList(line.split("=")[0], regex);
                    for (String donnee : listData) {
                        if(donnee.compareTo(data)==0){
                            value=line.split("=")[0];
                            trouve=true;
                            break;
                        }
                    }
                }
                if(trouve){
                    return value;
                }
                else{
                    try {
                        throw new DataException("the property of the data '"+data+"' doesn't exist in the file "+fileName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            else{
                try {
                    throw new DataException("The file '"+fileName+"' is not found in the repository : res/");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void create(){
        if(!exist){
            File directory=null;
            if(this.repository!=null){
                directory=new File(origine.getAbsolutePath()+"/res/"+repository+"/"+fileName);
            }
            else{
                directory=new File(origine.getAbsolutePath()+"/res/"+fileName);
            }
            if(directory!=null){
                try {
                    directory.createNewFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dataFile=directory;
                exist=true;
                System.out.println("DATA CREATE OK");
            }
        }
        else{
            try {
                throw new DataException(fileName+" already exist in this project");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void insert(String property,String data){
        String fileText="";
        boolean canInsert=true;
        if(exist){
            try {
                Scanner scan=new Scanner(dataFile);
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
                    FileWriter fileWriter=new FileWriter(dataFile);
                    fileWriter.write(fileText);
                    fileWriter.close();
                    System.out.println("INSERT OK");
                }
                else{
                    try {
                        throw new DataException(property+" is already in the file \""+dataFile.getName()+"\"");
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
        if(exist){
            try {
                Scanner scan=new Scanner(dataFile);
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
                    FileWriter fileWriter=new FileWriter(dataFile);
                    fileWriter.write(fileText);
                    fileWriter.close();
                    System.out.println("DELETE OK");
                }
                else{
                    try {
                        throw new DataException(dataFile.getName()+" doesn't have the property '"+property+"'");
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
        if(exist){
            try {
                Scanner scan=new Scanner(dataFile);
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
                    FileWriter fileWriter=new FileWriter(dataFile);
                    fileWriter.write(fileText);
                    fileWriter.close();
                    System.out.println("UPDATE OK");
                }
                else{
                    try {
                        throw new DataException(dataFile.getName()+" doesn't have the property '"+property+"'");
                    } catch (DataException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    
    public static void main(String[] args) {
        Data data=new Data("Utilisateur");
        String l="Lita";
        System.out.println(data.getProperty(l,";"));
    }



}
