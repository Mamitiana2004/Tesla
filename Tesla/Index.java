package window;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import window.Choix;

public class Index{
    
    JButton enterBtn;
    JLabel title;

    public Index(){
        JFrame f=new JFrame("Tesla");
        f.setSize(400, 400);
        f.setVisible(true);
        f.setLayout(null);
        title=new JLabel("Tesla");
        title.setBounds(20, 20, 100, 40);
        enterBtn=new JButton("Entrer");
        enterBtn.setBounds(20, 100, 150, 40);
        f.add(title);
        f.add(enterBtn);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        enterBtn.addMouseListener(new MouseAdapter(){
            
            public void mouseClicked(MouseEvent e) {
                f.dispose();
                new Choix();
            }

        });
    }


}
