package plateform;

import java.awt.Graphics;
import java.awt.*;

import javax.swing.JPanel;
import util.*;
import data.config.Data;
import user.Utilisateur;

public class Voiture extends JPanel{

    Utilisateur utilisateur;
    Data data;
    Data climatisation;
    int distance;
    boolean animerChaise=false;
    int distanceFinal;

    public Voiture(Utilisateur u){
        utilisateur=u;
        data=new Data("Config",utilisateur.getNom());
        climatisation=new Data("Climatisation",utilisateur.getNom());
        distance=data.getInt("chaise_distance");
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0,0, 1000,1000);
        g.setColor(new Color(200,100,0));
        g.drawString("Temperature :"+climatisation.getString(Util.getHeureNow()+"")+" C", 20, 20);
        g.drawString("Distance de "+distance+"cm du volant", 20, 50);

        //volant
        g.setColor(Color.BLACK);
        g.fillRect(0, 150,20, 1000);


        

        //chaise
        g.fillRect(150+(distance), 100, 40, 1000);
        g.fillRect(50+(distance), 260, 120, 60);

        if(animerChaise){
            animation(g);
        }

    }
    
    public void animerChaise(int newDistance){
        animerChaise=true;
        distanceFinal=newDistance;
        data.update("chaise_distance", distanceFinal+"");
        System.out.println("Deplacement de la chaise de "+distance+"cm a "+newDistance+"cm du volant");
        repaint();
    }


    public void animation(Graphics g){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5);
                    if(distance<distanceFinal){
                        distance+=1;
                        repaint();
                    }
                    else if (distance>distanceFinal){
                        distance-=1;
                        repaint();
                    }
                    else if(distance==distanceFinal){
                        animerChaise=false;
                        repaint();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
