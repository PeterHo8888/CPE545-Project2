package server.services;
import java.io.Serializable;

public class Hello implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    
    public Hello(int id) {
        super();
        this.id = id;
    }
    
    public int get_id() {
    	System.out.println("yes");
    	return id;
	}
}
