/**
 * Created with IntelliJ IDEA.
 * User: mario
 * Date: 18/10/13
 * Time: 14:04
 * To change this template use File | Settings | File Templates.
 */
import java.rmi.*;
import java.util.ArrayList;


public interface Chatable extends Remote{
    public void connect(User user) throws RemoteException;
    public void disconnect(User user) throws RemoteException;
    public ArrayList<User> getUserList() throws RemoteException;
    public ArrayList<Message> getHistorique() throws RemoteException;
    public void sendMessage(Message message)throws RemoteException;
}
