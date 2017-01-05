package helloworld.DataType;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by painter on 17-1-4.
 */
@XmlRootElement
public class UserInfo {
    public int uid;
    public int avatar;
    public String nickname;
    public String introduction;

    public UserInfo(){
        super();
    }

    public UserInfo(int n_uid,int n_avatar,String n_nickname,String n_introduction){
        this.avatar = n_avatar;
        this.uid = n_uid;
        this.nickname = n_nickname;
        this.introduction = n_introduction;
    }

}
