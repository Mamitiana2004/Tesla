package model;

import data.Data;
import model.User;

public class Poids {
    User users;

    public Poids(User u){
        users=u;
    }

    public int getPoidTotal(){
        int val=0;
        Data user=new Data("User");
        Data passager=new Data("Passager", users.getNom());
        String[] passagerList=null;
        passagerList=passager.getList("passager", ";");
        if(passagerList!=null){
            if(passagerList.length>1){
                for (String pasId : passagerList) {
                    String[] userData=user.getList(pasId, ";");
                    val+= Integer.parseInt(userData[2]);
                }
            }
        }
        else{
            String passa=passager.getString("passager");
            if(!passa.equalsIgnoreCase("null")){
                val+=Integer.parseInt(user.getList(passa, ";")[2]);    
            }
        }
        val+=users.getPoid();
        return val;
    }

    public double getPourcentage(){
        Data poidData=new Data("Poid");
        int poid=getPoidTotal();
        int min=poid/100;
        int max=min+1;
        int minT=min*100;
        int maxT=max*100;
        if(poid==minT){
            minT=(min-1)*100;
            maxT=(max-1)*100;
        }
        return poidData.getDouble(minT+"-"+maxT);
    }
}
