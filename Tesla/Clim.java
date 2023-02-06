package window;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.*;
import data.config.Data;
import plateform.Voiture;
import user.Utilisateur;

public class Clim {

    Utilisateur utilisateur; 
    Data data;


    public Clim(Utilisateur u,Voiture voiture){
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

        JLabel heureLabel=new JLabel("Heure :");
        heureLabel.setBounds(20,70,100,40);
        f.add(heureLabel);

        JTextField heureField=new JTextField();
        heureField.setBounds(130,70,100,40);
        f.add(heureField);

        JLabel temperatureLabel=new JLabel("Temperature :");
        temperatureLabel.setBounds(20,120,100,40);
        f.add(temperatureLabel);

        JTextField temperatureField=new JTextField();
        temperatureField.setBounds(130,120,100,40);
        f.add(temperatureField);

        
        JButton enter=new JButton("Confirmer");
        enter.setBounds(20,170,100,40);
        f.add(enter);

        JLabel error=new JLabel();
        error.setBounds(20,220,1000,40);
        f.add(error);

        enter.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(heureField.getText().equalsIgnoreCase("") || temperatureField.getText().equalsIgnoreCase("")){
                    error.setText("Champ requis");
                }
                else{
                    try {
                        int heure=Integer.parseInt(heureField.getText());
                        double temperature=Double.parseDouble(temperatureField.getText());
                        if(heure<24 && heure>0){
                            data.update(heure+"",temperature+"");
                            voiture.repaint();
                            f.dispose();
                        }
                        else{
                            error.setText("Heure "+heure+" non existant");
                        }
                    } catch (Exception ex) {
                        error.setText("La valeur doit etre numerique");
                    }
                }
            }
        });


    }

    

}
