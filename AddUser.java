package window;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import data.Data;
import model.User;
import window.Choix;

import java.awt.event.*;
public class AddUser {
    
    public AddUser(){

        JFrame f=new JFrame("Add user");
        f.setSize(400,400);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title=new JLabel("Nouvel utilisateur");
        title.setBounds(20, 20, 100, 40);
        f.add(title);

        JLabel nomLabel=new JLabel("Nom :");
        nomLabel.setBounds(20,70,150,40);
        f.add(nomLabel);

        JTextField nomField=new JTextField();
        nomField.setBounds(200,70,100,30);
        f.add(nomField);

        JLabel poidLabel=new JLabel("poid (kg):");
        poidLabel.setBounds(20,120,150,40);
        f.add(poidLabel);

        JTextField poidField=new JTextField();
        poidField.setBounds(200,120,100,30);
        f.add(poidField);

        JLabel tailleLabel=new JLabel("Taille (cm):");
        tailleLabel.setBounds(20,170,150,40);
        f.add(tailleLabel);

        JTextField tailleField=new JTextField();
        tailleField.setBounds(200,170,100,30);
        f.add(tailleField);


        JButton enter=new JButton("Ok");
        enter.setBounds(20,220,100,40);
        f.add(enter);

        JLabel errorLabel=new JLabel();
        errorLabel.setBounds(20,220,1000,40);
        f.add(errorLabel);
        
        enter.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                if(nomField.getText().equalsIgnoreCase("") || tailleField.getText().equalsIgnoreCase("")){
                    errorLabel.setText("Remplisser le formulaire");
                } 
                else{
                    errorLabel.setText("");
                    try {
                        int taille=Integer.parseInt(tailleField.getText());
                        int poid=Integer.parseInt(poidField.getText());
                        User utilisateur=new User(nomField.getText(), taille,poid);
                        utilisateur.add();
                        utilisateur.initAllConfig();
                        f.dispose();
                        new Choix();
                    } catch (Exception ex1) {
                        errorLabel.setText("Taille doit etre un chiffre");
                    }
                }
            }
        });

    }

}
