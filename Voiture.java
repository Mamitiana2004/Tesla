package plateform;

import java.awt.Graphics;
import java.awt.*;

import javax.swing.JPanel;
import util.*;
import data.Data;
import model.*;

public class Voiture extends JPanel{

    User utilisateur;
    Data data;
    Data climatisation;
    int distance;
    boolean animerChaise=false;
    boolean animerVolant=false;
    int distanceFinal;
    int hauteurFinal;
    int hauteur;

    public Voiture(User u){
        utilisateur=u;
        data=new Data("Config",utilisateur.getNom());
        climatisation=new Data("Climatisation",utilisateur.getNom());
        distance=data.getInt("chaise_distance");
        distanceFinal=distance;
        hauteur=data.getInt("volant_hauteur");
        hauteurFinal=hauteur;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0,0, 1000,1000);
        g.setColor(new Color(200,100,0));
        int heurePlus=Helper.getHeureNow()+1;
        g.drawString("Temperature :"+climatisation.getString(Helper.getHeureNow()+"-"+heurePlus)+" C", 20, 20);
        g.drawString("Distance de "+distance+"cm du volant", 20, 50);

        //volant
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 200-hauteur,20, 100);


        

        //chaise
        g.setColor(Color.BLACK);
        g.fillRect(150+(distance), 100, 40, 1000);
        g.fillRect(50+(distance), 260, 120, 60);

        if(animerChaise){
            animation(g);
        }
        if(animerVolant){
            animation2(g);
        }

    }
    
    public void animerChaise(int newDistance){
        animerChaise=true;
        distanceFinal=newDistance;
        data.update("chaise_distance", distanceFinal+"");
        System.out.println("Deplacement de la chaise de "+distance+"cm a "+newDistance+"cm du volant");
        repaint();
    }

    public void animerVolant(int newHauteur){
        animerVolant=true;
        hauteurFinal=newHauteur;
        data.update("volant_hauteur", hauteurFinal+"");
        repaint();
    }

    public void animation2(Graphics g){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5);
                    if(hauteur<hauteurFinal){
                        hauteur+=1;
                        repaint();
                    }
                    else if (hauteur>hauteurFinal){
                        hauteur-=1;
                        repaint();
                    }
                    else if(hauteur==hauteurFinal){
                        animerVolant=false;
                        repaint();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
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

                    if(hauteur<hauteurFinal){
                        hauteur+=1;
                        repaint();
                    }
                    else if (hauteur>hauteurFinal){
                        hauteur-=1;
                        repaint();
                    }
                    else if(hauteur==hauteurFinal){
                        animerVolant=false;
                        repaint();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
