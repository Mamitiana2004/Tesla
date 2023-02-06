package window;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.*;
import data.config.Data;
import plateform.TableauBord;
import plateform.Voiture;
import user.Utilisateur;

public class Acceuil {
    
    Data configUser;
    boolean distanceModif=false;
    boolean startMoteur=false;

    public Acceuil(Utilisateur utilisateur){

        configUser=new Data("Config",utilisateur.getNom());
        

        JFrame f=new JFrame("Tesla");
        f.setSize(1200,700);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLayout(null);

        JLabel conducteurLabel=new JLabel("Conducteur :"+utilisateur.getNom());
        conducteurLabel.setBounds(20,20,200,40);
        f.add(conducteurLabel);


        Voiture voiture=new Voiture(utilisateur);
        voiture.setBounds(20,100,500,300);
        f.add(voiture);

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
                new Clim(utilisateur,voiture);
            }
        });

        JButton startButton=new JButton("Demarer le moteur");
        startButton.setBounds(20,580,300,40);
        f.add(startButton);


        JButton accelerer=new JButton("Accelerer");
        accelerer.setBounds(910,480,150,40);
        accelerer.setEnabled(false);

        JButton freiner=new JButton("Freiner");
        freiner.setBounds(750,480,150,40);
        freiner.setEnabled(false);

        TableauBord tableauBord=new TableauBord(utilisateur);
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
            public void mouseClicked(MouseEvent e) {
                if(accelerer.isEnabled()){
                    System.out.println("Acceler");
                    int vitesse=configUser.getInt("vitesse_actuelle")+5;
                    configUser.update("vitesse_actuelle", vitesse+"");
                }
            }
        });

        freiner.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(freiner.isEnabled()){
                    System.out.println("Freiner");
                    int vitesse=configUser.getInt("vitesse_actuelle")-5;
                    configUser.update("vitesse_actuelle", vitesse+"");
                }
            }
        });


        

        


    }

}
