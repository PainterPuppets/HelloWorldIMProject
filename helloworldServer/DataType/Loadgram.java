package helloworldServer.DataType;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by painter on 17-1-5.
 */
@XmlRootElement
public class Loadgram {
    public int type;
    public UserInfo info;

    public Loadgram(){
        super();
    }
    public Loadgram(int type, UserInfo userInfo){
        this.type = type;
        this.info = userInfo;
    }
}
