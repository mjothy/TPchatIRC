/**
 * Created with IntelliJ IDEA.
 * User: mario
 * Date: 18/10/13
 * Time: 14:37
 * To change this template use File | Settings | File Templates.
 */
import java.net.MalformedURLException;
import java.rmi.*;
import java.util.Scanner;
import java.util.Timer;

public class ChatClient {

    public static void main(String[] args) {

        try{



            Chatable chatServer = (Chatable) Naming.lookup("//127.0.1.1:9000/monserveur");
            Scanner in = new Scanner(System.in);
            String options = "";
            String option = "";
            Message last = null;
            User user = null;


            do{
                System.out.println("Insert command :");
                options = in.nextLine();

                option = options.split(" ")[0];

                if (option.equals("connect") && user == null) {
                    user = new User(options.substring(8));
                    if(user.name != null || !user.name.equals("")){
                        boolean exists = true;
                        for(User connected: chatServer.getUserList()){
                            if(connected.name.equals(user.name)){
                                System.out.println("Utilisateur déja existant");
                                exists = false;
                                break;
                            }
                        }
                        if(exists){
                            chatServer.connect(user);
                            System.out.println("Connecté sous le pseudo : "+user.name);
                            Window window = new Window(chatServer, user);
                        };

                    }else{
                        System.out.println("pseudo invalide");
                    }
                }

                else if(option.equals("bye") && user !=null) {
                    chatServer.disconnect(user);
                }

                else {
                    System.out.println("Try again, with one of the keywords : connect ; send ; bye ; who");
                }

            } while(!option.equals("bye"));

        } catch (RemoteException e) {
            e.printStackTrace();
            return;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return;
        } catch (NotBoundException e) {
            e.printStackTrace();
            return;
        }
    }
}
