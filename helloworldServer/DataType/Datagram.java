package helloworldServer.DataType;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by painter on 17-1-4.
 */
@XmlRootElement
public class Datagram {
    public int Port;
    public String Content;

    public Datagram(){
        super();
    }
    public Datagram(int port, String content){
        this.Port = port;
        this.Content = content;
    }
}
