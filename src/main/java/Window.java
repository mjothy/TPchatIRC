import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: mario
 * Date: 21/10/13
 * Time: 10:17
 * To change this template use File | Settings | File Templates.
 */
public class Window extends JFrame{
    JTextArea textArea;
    SalleChat chat;
    ListUsers users;
    JPanel global;
    JButton send;
    JButton disconnect;
    Chatable chatable;
    User user;

    public Window(Chatable chatable, User user){
        this.chatable = chatable;
        this.user= user;
        this.setTitle("chat");
        this.setSize(700, 600);
        this.setLocationRelativeTo(null);
        global = new JPanel();

        chat = new SalleChat();
        chat.setPreferredSize(new Dimension(400,300));
        chat.setEditable(false);
        chat.setLineWrap(true);
        global.add(chat);

        users = new ListUsers();
        users.setPreferredSize(new Dimension(200,300));
        global.add(users);

        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(600, 200));
        global.add(textArea);

        send = new JButton("Send");
        send.setPreferredSize(new Dimension(150,50));
        send.addActionListener(new SendListener());
        global.add(send);

        disconnect = new JButton("Disconnect");
        disconnect.setPreferredSize(new Dimension(150,50));
        disconnect.addActionListener(new DisconnectListener(this));
        global.add(disconnect);

        this.setContentPane(global);
        this.setVisible(true);
        TimerPerso timerPerso = new TimerPerso(chat, users);

        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(timerPerso, 0, 1000);
    }

    class SendListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try {
                chatable.sendMessage(new Message(textArea.getText(),user));
                textArea.setText("");
            } catch (RemoteException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    class DisconnectListener implements ActionListener{
        private Window window;
        public DisconnectListener(Window window){
            super();
            this.window = window;
        }
        public void actionPerformed(ActionEvent e){
            try {
                chatable.disconnect(user);
                window.setVisible(false);

            } catch (RemoteException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    public class SalleChat extends JTextArea{
        public void refresh(){
            try {
                this.setText("");
                ArrayList<Message> messages = chatable.getHistorique();
                int j = 1;
                int bound = messages.size()>11?messages.size()-11:0;
                for(int i=bound; i<messages.size(); i++){
                    Message message = messages.get(i);
                    this.append(message.getAuthor().name+" <"+message.getDate()+"> "+": "+message.getContenu()+"\n\r");
                    j++;
                }
            } catch (RemoteException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    public class ListUsers extends  JPanel{
        public void paintComponent(Graphics g){
            g.setColor(Color.WHITE);
            g.clearRect(0,0,200,300);
            g.setColor(Color.black);

            try {
                ArrayList<User> users = chatable.getUserList();
                for(int i=0; i<users.size(); i++){
                    g.drawString(users.get(i).name, 5, (i+1)*20);
                }
            } catch (RemoteException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
}
