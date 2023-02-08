package window;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.*;

public class Index {
    
    private JFrame f;
    private JButton enterBtn;
    private JLabel title;

    public Index(){
        f=new JFrame("TESLA");
        f.setSize(400, 400);
        f.setVisible(true);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        title=new JLabel("Tesla");
        title.setBounds(20, 20, 100, 40);
        enterBtn=new JButton("Entrer");
        enterBtn.setBounds(20, 100, 150, 40);

        f.add(title);
        f.add(enterBtn);

        enterBtn.addMouseListener(new MouseAdapter(){
            
            public void mouseClicked(MouseEvent e) {
                f.dispose();
                new Choix();
            }

        });
    }

}
