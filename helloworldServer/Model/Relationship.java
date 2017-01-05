package helloworldServer.Model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by painter on 17-1-4.
 */
@XmlRootElement
public class Relationship {
    public Integer uid;
    public Integer frienduid;


    public Relationship(){
        super();
    }

    public Relationship(int uid,int frienduid){
        this.uid = uid;
        this.frienduid = frienduid;
    }
}
