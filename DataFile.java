package data;

import java.io.File;

public class DataFile {
    
    private static final File origin=new File("");
    private boolean inOtherFolder=false;
    String folderName;
    String fileName;
    File file;
    boolean exist=false;

    public String getFolderName() {
        return folderName;
    }
    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }
    
    public boolean isExist() {
        return exist;
    }
    public void setExist(boolean exist) {
        this.exist = exist;
    }


    public void createFolder(String folderName){
        File file=new File(origin.getAbsolutePath()+"/res/"+folderName);
        if(file.mkdir()){
            System.out.println("---------------------\nFolder created\n---------------------");
        }
    }

    public void createFile(String fileName){
        File dataFichier=null;
        if(inOtherFolder){
            dataFichier=new File(origin.getAbsolutePath()+"/res/"+folderName+"/"+fileName);
        }
        else{
            dataFichier=new File(origin.getAbsolutePath()+"/res/"+fileName);
        }
        try {
            if(dataFichier.createNewFile()){
                file=dataFichier;
                exist=true;
                System.out.println("---------------------\nData created\n---------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void findFile(){
        File directory=null;
        if(inOtherFolder){
            directory=new File(origin.getAbsolutePath()+"/res/"+folderName+"/"+fileName);
        }
        else{
            directory=new File(origin.getAbsolutePath()+"/res/"+fileName);
        }

        //
        if(directory!=null){
            if(directory.exists()){
                exist=true;
                file=directory;
            }
            else{
                exist=false;
            }
        }
    }


    public DataFile(String fileName,String folderName){
        this.fileName=fileName;
        this.folderName=folderName;
        inOtherFolder=true;
        findFile();
        createFolder(folderName);
    }

    public DataFile(String fileName){
        this.fileName=fileName;
        inOtherFolder=false;
        findFile();
    }

    public static void main(String[] args) {
    }


}
