package window;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.*;
import data.Data;
import plateform.Voiture;
import model.*;

public class Clim {

    User utilisateur; 
    Data data;


    public Clim(User u,Voiture voiture){
        utilisateur=u;
        data=new Data("Climatisation",utilisateur.getNom());

        JFrame f=new JFrame("Clim");
        f.setSize(400,400);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLayout(null);

        JLabel label=new JLabel("Reglage de la clim");
        label.setBounds(20,20,200,40);
        f.add(label);

        JLabel heureDebutLabel=new JLabel("Heure Debut:");
        heureDebutLabel.setBounds(20,70,100,40);
        f.add(heureDebutLabel);

        JTextField heureDebutField=new JTextField();
        heureDebutField.setBounds(130,70,100,40);
        f.add(heureDebutField);

        JLabel heureFinLabel=new JLabel("Heure Fin:");
        heureFinLabel.setBounds(20,120,100,40);
        f.add(heureFinLabel);

        JTextField heureFinField=new JTextField();
        heureFinField.setBounds(130,120,100,40);
        f.add(heureFinField);

        JLabel temperatureLabel=new JLabel("Temperature :");
        temperatureLabel.setBounds(20,170,100,40);
        f.add(temperatureLabel);

        JTextField temperatureField=new JTextField();
        temperatureField.setBounds(130,170,100,40);
        f.add(temperatureField);

        
        JButton enter=new JButton("Confirmer");
        enter.setBounds(20,220,100,40);
        f.add(enter);

        JLabel error=new JLabel();
        error.setBounds(20,270,1000,40);
        f.add(error);

        enter.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(heureDebutField.getText().equalsIgnoreCase("") || temperatureField.getText().equalsIgnoreCase("")){
                    error.setText("Champ requis");
                }
                else{
                    try {
                        int heureDebut=Integer.parseInt(heureDebutField.getText());
                        int heureFin=Integer.parseInt(heureFinField.getText());
                        double temperature=Double.parseDouble(temperatureField.getText());
                        if(heureDebut<24 && heureDebut>0){
                            if(heureDebut<heureFin){
                                for(int i=heureDebut;i<heureFin;i++){
                                    int heurePlus=i+1;
                                    data.update(i+"-"+heurePlus, temperature+"");
                                }
                                voiture.repaint();
                                f.dispose();
                            }
                            else{
                                error.setText("Heure fin ne doit pas etre inferieure a heure debut");
                            }
                        }
                        else{
                            error.setText("Heure non existant");
                        }
                    } catch (Exception ex) {
                        error.setText("La valeur doit etre numerique");
                    }
                }
            }
        });


    }

    

}
