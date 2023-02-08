package model;

import data.Data;
import model.User;

public class RapportVitesse {
    
    Data data;
    User user;

    public RapportVitesse(User u){
        user=u;
        data=new Data("RapportVitesse",user.getNom());
    }

    public void doRapport(int seconde){
        Data userData=new Data("Config",user.getNom());
        int vitesse=userData.getInt("vitesse_actuelle");
            if(vitesse!=0){
                int co=data.count();
                data.insert(co+1+"", "vitesse:"+vitesse+"->seconde:"+seconde+"");
            }
    }

}
