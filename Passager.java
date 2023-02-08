package plateform;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import data.Data;
import model.User;
import window.Acceuil;

public class Passager extends JPanel{
    
    ArrayList<User> list;

    public ArrayList<User> getList() {
        return list;
    }
    public void setList(ArrayList<User> list) {
        this.list = list;
    }

    JButton btn;


    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, 400,400);
        g.setColor(Color.BLACK);
        btn.setEnabled(false);
        btn.setBounds(20,200,160,40);
        if(list.size()>0){        
            int passager=list.size()-1;
            g.drawString("Chauffeur :"+list.get(0).getNom(),20, 20);
            g.drawString("Passager :("+passager+"/4)",20, 60);
            for (int i = 0; i < list.size(); i++) {
                if(i>0){
                    g.drawString(list.get(i).getNom(), 20, 60+(20*i));
                }
            }
            btn.setEnabled(true);
        }
    }

    public Passager(JFrame f,ArrayList<User> listUser){
        list=listUser;
        btn=new JButton("Entrer dans la voiture");
        add(btn);
        setLayout(null);
        btn.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(btn.isEnabled()){
                    f.dispose();
                    list.get(0).addPassager(list);
                    Data rapport=new Data("RapportVitesse", list.get(0).getNom());
                    rapport.clear();
                    rapport.insert("Date", new Date().toString());
                    new Acceuil(list);
                }
            }
        });
    }

}
