package helloworld.DataType;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by painter on 17-1-4.
 */
@XmlRootElement
public class RecLogingram{
    public int type;
    public int Uid;
    public String Nickname;
    public String Introduction;

    public RecLogingram(){
        super();
    }
    public RecLogingram(int type,int uid,String nickname,String introduction){
        this.Uid = uid;
        this.Nickname = nickname;
        this.Introduction = introduction;
    }
}
