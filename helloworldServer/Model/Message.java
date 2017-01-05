package helloworldServer.Model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by painter on 17-1-4.
 */
@XmlRootElement
public class Message {
    public int sender;//发信人
    public int recipient;//收信人
    public String  content;//内容
    public Date senddate;//发信时间



    public Message(){
        super();
    }

    public Message(int sender,int recipient,String content){
        this.content = content;
        this.sender = sender;
        this.recipient = recipient;
        this.senddate = new Date();
    }
}
