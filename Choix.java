package window;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.User;

import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;
import plateform.ListUser;
import plateform.Passager;

public class Choix {
    
    private ListUser listUser;
    private ArrayList<User> users;
    public Choix(){
        JFrame f=new JFrame("User choice");
        users=new ArrayList<>();
        Passager passager=new Passager(f,users);
        passager.setBounds(20,100,200,300);
        listUser=new ListUser(f,passager);
        GridLayout grid=new GridLayout(1,2);
        f.setLayout(grid);
        f.setSize(600,600);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.add(listUser);
        JPanel panel=new JPanel(null);
        f.add(panel);
        panel.add(passager);
        JButton add=new JButton("Add new user");
        add.setBounds(20,20,200,40);
        panel.add(add);
        add.addMouseListener(new MouseAdapter(){
            
            public void mouseClicked(MouseEvent e) {
                f.dispose();
                new window.AddUser();    
            }
        });
        
    }

}
