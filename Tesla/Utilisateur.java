package user;

import data.config.Data;
import piece.Chaise;

public class Utilisateur {

    final Data data=new Data("Utilisateur");

    public Utilisateur(int id){
        this.id=id;
    }

    public Utilisateur(String nom,int taille){
        id=0;
        this.nom=nom;
        this.taille=taille;
    }

    int id;
    
    private String nom;

    private int taille;

    public String getNom() {
        return nom;
    }
    public int getTaille() {
        return taille;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setTaille(int taille) {
        this.taille = taille;
    }

    @Override
    public String toString() {
        return nom+";"+taille;
    }
    

    public void add(){
        data.insert(data.count()+1+"",toString());
    }

    public void initAllConfig(){
        Data config=new Data("Config",nom);
        config.create();
        config.insert("chaise_distance", Chaise.distance(taille)+"");
        config.insert("vitesse_actuelle", "0");

        Data climatisation=new Data("Climatisation",nom);
        climatisation.create();
        for (int i = 0; i < 24; i++) {
            climatisation.insert(i+"","24.0");
        }

        Data dataBatterie=new Data("Batterie",nom);
        dataBatterie.create();
        dataBatterie.insert("puissance", "12000");

        

    }

}
