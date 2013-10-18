import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: mario
 * Date: 18/10/13
 * Time: 14:15
 * To change this template use File | Settings | File Templates.
 */
public class Message implements Serializable{
    private String contenu;
    private User author;
    private Date date;

    public Message(String contenu, User author) {
        this.contenu = contenu;
        this.author = author;
        date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public User getAuthor() {
        return author;
    }
}
