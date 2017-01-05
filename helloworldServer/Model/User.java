package helloworldServer.Model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by painter on 17-1-4.
 */
@XmlRootElement
public class User {
    public Integer uid;
    public Integer avatar;
    public String userid;
    public String password;
    public String nickname;
    public String introduction;

    public User(){
        super();
    }

    public User(String n_userid,String n_password,String n_nickname){
        this.nickname = n_nickname;
        this.userid = n_userid;
        this.password = n_password;
    }
    public User(Integer n_uid,String n_userid,String n_password,String n_nickname,String n_introduction){
        this.nickname = n_nickname;
        this.userid = n_userid;
        this.password = n_password;
        this.uid = n_uid;
        this.introduction = n_introduction;
    }
}
