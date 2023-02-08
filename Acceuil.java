package window;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import data.Data;
import plateform.TableauBord;
import plateform.Voiture;
import model.*;

public class Acceuil {
    
    Data configUser;
    Data passager;
    boolean distanceModif=false;
    boolean distanceVolant=false;
    boolean startMoteur=false;

    public Acceuil(ArrayList<User> utilisateur){

        configUser=new Data("Config",utilisateur.get(0).getNom());
        passager=new Data("Passager",utilisateur.get(0).getNom());

        JFrame f=new JFrame("Tesla");
        f.setSize(1200,700);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLayout(null);

        JLabel conducteurLabel=new JLabel("Conducteur :"+utilisateur.get(0).getNom());
        conducteurLabel.setBounds(20,20,200,40);
        f.add(conducteurLabel);

        String passagerLabel="";
        for (int i = 0; i < utilisateur.size(); i++) {
            if(i>0){
                passagerLabel+=" "+utilisateur.get(i).getNom()+" ";
            }
        }

        Poids poids=new Poids(utilisateur.get(0));
        configUser.update("poid_total", poids.getPoidTotal()+"");

        JLabel passager=new JLabel("Passager :"+passagerLabel);
        passager.setBounds(20,40,500,40);
        f.add(passager);

        JLabel poidLabel=new JLabel("Poid total :"+poids.getPoidTotal()+" kg");
        poidLabel.setBounds(20,60,500,40);
        f.add(poidLabel);

        Voiture voiture=new Voiture(utilisateur.get(0));
        voiture.setBounds(20,100,500,300);
        f.add(voiture);

        JButton modifVolant=new JButton("Modifier la hauteur du volant");
        modifVolant.setBounds(20,430,300,40);
        f.add(modifVolant);

        
        JTextField hauteur=new JTextField();
        hauteur.setBounds(20,430,150,40);

        modifVolant.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!distanceVolant){
                    distanceVolant=true;
                    System.out.println("Modifier");
                    f.add(hauteur);
                    modifVolant.setText("Modifier");
                    modifVolant.setBounds(180, 430, 150, 40);
                }
                else{
                    try {
                        int es=Integer.parseInt(hauteur.getText());
                        voiture.animerVolant(es);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }            
        });

        JButton modifDistance=new JButton("Modifier la distance de la chaise");
        modifDistance.setBounds(20, 480, 300, 40);
        f.add(modifDistance);

        JTextField distanceField=new JTextField();
        distanceField.setBounds(20,480,150,40);
        
        modifDistance.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!distanceModif){
                    distanceModif=true;
                    System.out.println("Modifier");
                    f.add(distanceField);
                    modifDistance.setText("Modifier");
                    modifDistance.setBounds(180, 480, 150, 40);
                }
                else{
                    try {
                        int d=Integer.parseInt(distanceField.getText());
                        voiture.animerChaise(d);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }            
        });


        JButton climButton=new JButton("Regler la clim");
        climButton.setBounds(20,530,300,40);
        f.add(climButton);

        climButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                new Clim(utilisateur.get(0),voiture);
            }
        });

        JButton startButton=new JButton("Demarer le moteur");
        startButton.setBounds(20,580,300,40);
        f.add(startButton);


        JButton accelerer=new JButton("Accelerer");
        accelerer.setBounds(910,480,150,40);
        accelerer.setEnabled(false);

        RapportVitesse rapport=new RapportVitesse(utilisateur.get(0));

        JButton freiner=new JButton("Freiner");
        freiner.setBounds(750,480,150,40);
        freiner.setEnabled(false);

        

        TableauBord tableauBord=new TableauBord(utilisateur.get(0));
        tableauBord.setBounds(750,100,400,300);
        f.add(tableauBord);
        
        f.add(freiner);
        f.add(accelerer);

        startButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!startMoteur){
                    startButton.setText("Arreter le moteur");
                    accelerer.setEnabled(true);
                    freiner.setEnabled(true);
                    startMoteur=true;
                    tableauBord.setColor(Color.RED);
                    tableauBord.setStart(true);
                    tableauBord.repaint();
                }   
                else{
                    startButton.setText("Demarrer le moteur");
                    accelerer.setEnabled(false);
                    freiner.setEnabled(false);
                    startMoteur=false;
                    tableauBord.setStart(false);
                    tableauBord.setColor(Color.DARK_GRAY);
                    tableauBord.repaint();
                    configUser.update("vitesse_actuelle", "0");
                }
            }
        });

        accelerer.addMouseListener(new MouseAdapter(){

            @Override
            public void mousePressed(MouseEvent e) {
                if(accelerer.isEnabled()){
                    if(tableauBord.getSecondeChange()>1){
                        rapport.doRapport(tableauBord.getSecondeChange());
                        tableauBord.setSecondeChange(0);
                    }
                    System.out.println("Accelerer");
                    int vitesse=configUser.getInt("vitesse_actuelle")+10;
                    configUser.update("vitesse_actuelle", vitesse+"");
                }
            }
        });

        freiner.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                if(freiner.isEnabled()){
                    if(tableauBord.getSecondeChange()>1){
                        rapport.doRapport(tableauBord.getSecondeChange());
                        tableauBord.setSecondeChange(0);
                    }
                    System.out.println("Freiner");
                    int vitesse=configUser.getInt("vitesse_actuelle")-5;
                    if(vitesse>0){
                        configUser.update("vitesse_actuelle", vitesse+"");
                    }
                    else{
                        configUser.update("vitesse_actuelle", 0+"");
                    }
                }
            }
        });


        

        


    }

}
