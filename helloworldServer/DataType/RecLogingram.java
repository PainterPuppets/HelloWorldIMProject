package helloworldServer.DataType;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by painter on 17-1-4.
 */
@XmlRootElement
public class RecLogingram {
    public int type;
    public UserInfo userInfo;

    public RecLogingram(){
        super();
    }
    public RecLogingram(int type){
        this.type = type;
    }

    public RecLogingram(int type,UserInfo userInfo){
        this.type = type;
        this.userInfo = userInfo;
    }
}

