package plateform;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.*;
import data.config.Data;
import user.Utilisateur;
import window.Acceuil;

public class ListeUtilisateur extends JScrollPane{
    
    Data data;
    JButton[] utilisateurButtons;
    Utilisateur[] utilisateurs;

    public ListeUtilisateur(JFrame f){
        JPanel panel=new JPanel();
        panel.setLayout(null);
        data=new Data("Utilisateur");
        utilisateurButtons=new JButton[data.count()];
        utilisateurs=new Utilisateur[data.count()];
        for (int i = 0; i < utilisateurButtons.length; i++) {
            String[] utilisateurData=data.getList(i+1+"",";");
            utilisateurs[i]=new Utilisateur(utilisateurData[0],Integer.parseInt(utilisateurData[1]));
            utilisateurButtons[i]=new JButton(utilisateurs[i].getNom());
            utilisateurButtons[i].setBounds(40, i*50, 100, 40);
            panel.add(utilisateurButtons[i]);

            utilisateurButtons[i].addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e) {
                    f.dispose();    
                    new Acceuil(new Utilisateur(utilisateurData[0], Integer.parseInt(utilisateurData[1])));
                }
            });

        }
        this.setViewportView(panel);
    }
    
}
