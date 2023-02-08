package plateform;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

import data.Data;
import model.User;
import plateform.Passager;

public class ListUser extends JScrollPane{
    
    private Data data;
    private JButton[] listButtons;
    private User[] listUsers;
    private int nbrUser=5;
    private int choisi=0;
    private ArrayList<User> userList;
    
    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    public ListUser(JFrame f,Passager passager){
        userList=new ArrayList<>();
        JPanel panel=new JPanel(null);
        data=new Data("User");
        listButtons=new JButton[data.count()];
        listUsers=new User[data.count()];
        for (int i = 0; i < listButtons.length; i++) {
            String[] userData=data.getList(i+1+"", ";");
            listUsers[i]=new User(userData[0],Integer.parseInt(userData[1]),Integer.parseInt(userData[2]));
            listButtons[i]=new JButton(userData[0]+" "+userData[2]+" kg");
            listButtons[i].setEnabled(true);
            listButtons[i].setBounds(40,i*50,200,40);
            panel.add(listButtons[i]);
            JButton btn=listButtons[i];
            User user=listUsers[i];
            listButtons[i].addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(btn.isEnabled()){
                        btn.setEnabled(false);
                        choisi+=1;
                        passager.getList().add(user);
                        enableBtn();
                    }
                    else{
                        choisi-=1;
                        btn.setEnabled(true);
                        passager.getList().remove(user);
                        enableBtn();
                    }
                    System.out.println(passager.getList().size());
                    passager.repaint();
                    //f.dispose();
                    //new window.Acceuil(new User(userData[0],Integer.parseInt(userData[1]),Integer.parseInt(userData[2])));
                }
            });
        }
        this.setViewportView(panel);
    }

    public void enableBtn(){
        if(choisi>=5){
            for (JButton jButton : listButtons) {
                jButton.setEnabled(false);
            }
        }
    }

}
