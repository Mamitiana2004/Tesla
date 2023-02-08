package plateform;

import java.awt.*;

import javax.swing.JPanel;

import data.Data;
import model.*;
import util.Autonomie;

/**
 * TableauBord
 */
public class TableauBord extends JPanel{

    User utilisateur;
    Color color;
    int secondeChange=0;

    public int getSecondeChange() {
        return secondeChange;
    }
    public void setSecondeChange(int secondeChange) {
        this.secondeChange = secondeChange;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return color;
    }

    boolean start=false;

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    Data dataBatterie;
    Data config;
    long autonomie=0;
    float km=0;
    Poids poids;

    public TableauBord(User u){
        utilisateur=u;
        dataBatterie=new Data("Batterie");
        config=new Data("Config",utilisateur.getNom());
        color=Color.DARK_GRAY;
        poids=new Poids(utilisateur);
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, 1000,1000);

        g.setColor(color);

        g.drawString("Puissance de la batterie restant :"+dataBatterie.getDouble("puissance")+"w",20, 20);
        g.drawString("Vitesse :"+config.getDouble("vitesse_actuelle")+" km/h", 20, 50);
        g.drawString("Reste de seconde :"+autonomie+"s",  20, 80);
        g.drawString("Reste kilometre :"+km+" km", 20, 110);
        g.drawString("Consomation :"+Autonomie.consomationSansP(dataBatterie.getDouble("puissance"), config.getInt("vitesse_actuelle"))+"w/s  (x"+poids.getPourcentage()+")======> "+Autonomie.consomation(dataBatterie.getDouble("puissance"), config.getInt("vitesse_actuelle"),poids)+"w/s", 20, 140);


        if(start){
            demarrer();
        }

    }



    public void demarrer(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    secondeChange++;
                    int vitesse=config.getInt("vitesse_actuelle");
                    double puissance=dataBatterie.getDouble("puissance");
                    if(puissance<=0){
                        vitesse=0;
                        config.update("vitesse_actuelle", "0");
                        dataBatterie.update("puissance", "0");
                    }
                    Thread.sleep(1000);
                    dataBatterie.update("puissance",Autonomie.puissanceConsommer(puissance, vitesse,poids)+"");
                    if(vitesse!=0){
                        autonomie=Autonomie.nbrSeconde(puissance, vitesse,poids);
                        km=Autonomie.resteKilometre(puissance, vitesse,poids);
                    }
                    else{
                        autonomie=0;
                        km=0;
                    }
                    repaint();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    
}