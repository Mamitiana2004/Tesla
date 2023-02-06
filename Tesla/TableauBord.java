package plateform;

import java.awt.*;

import javax.swing.JPanel;

import data.config.Data;
import user.Utilisateur;
import util.Autonomie;

/**
 * TableauBord
 */
public class TableauBord extends JPanel{

    Utilisateur utilisateur;
    Color color;

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

    public TableauBord(Utilisateur u){
        utilisateur=u;
        dataBatterie=new Data("Batterie",utilisateur.getNom());
        config=new Data("Config",utilisateur.getNom());
        color=Color.DARK_GRAY;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, 1000,1000);

        g.setColor(color);

        g.drawString("Puissance de la batterie restant :"+dataBatterie.getDouble("puissance"),20, 20);
        g.drawString("Vitesse :"+config.getDouble("vitesse_actuelle")+" km/h", 20, 50);
        g.drawString("Autonomie :"+autonomie+" s",  20, 80);


        if(start){
            demarrer();
        }

    }



    public void demarrer(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int vitesse=config.getInt("vitesse_actuelle");
                    double puissance=dataBatterie.getDouble("puissance");
                    if(puissance<=0){
                        vitesse=0;
                        config.update("vitesse_actuelle", "0");
                        dataBatterie.update("puissance", "0");
                    }
                    Thread.sleep(1000);
                    dataBatterie.update("puissance",Autonomie.puissanceConsommer(puissance, vitesse)+"");
                    if(vitesse!=0){
                        autonomie=Autonomie.nbrSeconde(puissance, vitesse);
                    }
                    else{
                        autonomie=0;
                    }
                    repaint();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    
}