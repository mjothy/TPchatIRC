import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: mario
 * Date: 18/10/13
 * Time: 14:14
 * To change this template use File | Settings | File Templates.
 */
public class User implements Serializable{
    public String name;

    public User(String name) {
        this.name = name;
    }

    public Message write(String contenu){
        return new Message(contenu, this);
    }
}
