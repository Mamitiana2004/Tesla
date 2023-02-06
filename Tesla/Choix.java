package window;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import plateform.ListeUtilisateur;
import window.AddUser;

import java.awt.*;
import java.awt.event.*;
public class Choix {
    
    ListeUtilisateur listeUtilisateur;

    public Choix(){
        JFrame f=new JFrame("Choix de l'utilisateur");
        listeUtilisateur=new ListeUtilisateur(f);
        GridLayout grid=new GridLayout(1,2);
        f.setLayout(grid);
        f.add(listeUtilisateur);
        f.setSize(600,600);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel=new JPanel(null);
        f.add(panel);
        JButton addUser=new JButton("New User");
        addUser.setBounds(40, 40, 100, 40);
        panel.add(addUser);

        addUser.addMouseListener(new MouseAdapter(){
            
            public void mouseClicked(MouseEvent e) {
                f.dispose();
                new AddUser();    
            }
        });
    }

}
