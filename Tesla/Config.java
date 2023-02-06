package user;

import data.config.Data;
import user.Climatisation;
import user.Utilisateur;

public class Config {
    public Config(String name){
        
    }


    Data data;
    Utilisateur utilisateur;
    Climatisation climatisation;


    public Climatisation getClimatisation() {
        return climatisation;
    }
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    public Data getData() {
        return data;
    }



    public void setClimatisation(Climatisation climatisation) {
        this.climatisation = climatisation;
    }
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    public void setData(Data data) {
        this.data = data;
    }

}
