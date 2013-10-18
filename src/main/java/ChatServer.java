/**
 * Created with IntelliJ IDEA.
 * User: mario
 * Date: 18/10/13
 * Time: 14:11
 * To change this template use File | Settings | File Templates.
 */
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 */
public class ChatServer extends UnicastRemoteObject implements Chatable{
    private String nomSalle;
    private ArrayList<User> userList;
    private ArrayList<Message> historique;

    public ChatServer(String nomSalle) throws RemoteException {
        this.nomSalle = nomSalle;
        userList = new ArrayList<User>();
        historique = new ArrayList<Message>();
    }

    public void connect(User user) throws RemoteException{
        this.userList.add(user);
    }

    public void disconnect(User user) throws  RemoteException{
        User toDelete = null;
        for(User connected : this.getUserList()){
            if(connected.name.equals(user.name)){
                toDelete = connected;
            }
        }
        if(toDelete!=null){
            this.getUserList().remove(toDelete);
        }
    }

    public ArrayList<User> getUserList() throws  RemoteException{
        return userList;
    }

    public ArrayList<Message> getHistorique() throws  RemoteException{
        return historique;
    }

    public void sendMessage(Message message) throws  RemoteException{
        this.historique.add(message);
    }

    public static void main(String[] args) {
        int port = 9000;
        String URL;
        try{
            Registry registry = LocateRegistry.createRegistry(port);
            Chatable chatServer = new ChatServer("Rleyh\'");
            URL = "//"+ InetAddress.getLocalHost().getHostAddress()+":"+port+"/monserveur";
            System.out.println(URL);
            Naming.rebind(URL, chatServer);
        } catch (RemoteException e) {
            System.out.println("Reseau cassé");
            return;
        } catch (java.net.UnknownHostException e) {
            System.out.println("Host invalide");
            return;
        } catch (MalformedURLException e) {
            System.out.println("URL cassée");
            return;
        }
    }
}
