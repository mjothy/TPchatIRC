import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: mario
 * Date: 18/10/13
 * Time: 16:26
 * To change this template use File | Settings | File Templates.
 */
public class TimerPerso extends TimerTask {
    public Window.SalleChat salleChat;
    public Window.ListUsers users;

    public TimerPerso(Window.SalleChat salleChat, Window.ListUsers users){
        super();
        this.salleChat=salleChat;
        this.users = users;
    }

    public void run() {
        salleChat.refresh();
        users.repaint();
    }
}
