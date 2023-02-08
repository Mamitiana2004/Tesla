package model;

import java.util.ArrayList;
import java.util.Date;

import data.Data;
import piece.Volant;

public class User {
    
    private String nom;
    private int taille;
    private int poid;
    private final Data data=new Data("User");

    public User(String nom,int taille,int poid){
       this.nom=nom;
       this.taille=taille; 
       this.poid=poid;
    }
    
    public int getPoid() {
        return poid;
    }
    public void setPoid(int poid) {
        this.poid = poid;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getTaille() {
        return taille;
    }
    public void setTaille(int taille) {
        this.taille = taille;
    }

    @Override
    public String toString() {
        return nom+";"+taille+";"+poid;
    }

    public void add(){
        data.insert(data.count()+1+"",toString());
    }

    public void initAllConfig(){
        Data config=new Data("Config",nom);
        config.create();
        config.insert("chaise_distance", Chaise.distance(taille)+"");
        config.insert("volant_hauteur", Volant.distance(taille)+"");
        config.insert("vitesse_actuelle", "0");
        config.insert("poid_total", "0");

        Data climatisation=new Data("Climatisation",nom);
        climatisation.create();
        for (int i = 0; i < 23; i++) {
            int j=i+1;
            climatisation.insert(i+"-"+j,"24.0");
        }


        Data passager=new Data("Passager",nom);
        passager.create();
        passager.insert("passager", "null");

        Data rapport=new Data("RapportVitesse",nom);
        rapport.create();
        rapport.insert("Date",new Date().toString());

    }

    public void addPassager(ArrayList<User> list){
        Data passager=new Data("Passager",nom);
        String listP="";
        if(list.size()>1){
            for (int i = 0; i < list.size(); i++) {
                if(i>0){
                    if(i<list.size()-1){
                        listP+=data.getProperty(list.get(i).toString())+";";
                    }
                    else{
                        listP+=data.getProperty(list.get(i).toString());
                    }
                }
            }
        }
        else{
            listP="null";
        }
        passager.update("passager", listP);
    }


    


}
