package helloworldServer.Module;

import helloworldServer.DataController.RelationshipController;
import helloworldServer.DataType.Loadgram;
import helloworldServer.DataType.UserInfo;
import helloworldServer.Model.User;
import helloworldServer.Share.Common;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Vector;

/**
 * Created by painter on 17-1-2.
 */
public class LoadMoudle implements SuperMoudle{
    int Port = Common.LOADMOUDLE;
    Communication communication;

    public LoadMoudle(Communication com){ this.communication = com;}

    @Override
    public void Receive(String Msg) {
        Loadgram loadgram = (Loadgram) Common.GetObject(Msg,Loadgram.class);
        if(loadgram != null){
            if(loadgram.type == 1){
                Vector<User> usertable = RelationshipController.GetFriendTable(communication.GetUser().uid);
                for(int i = 0;i<usertable.size();++i){
                    UserInfo userInfo = new UserInfo(usertable.get(i).uid,usertable.get(i).avatar,usertable.get(i).nickname,usertable.get(i).introduction);
                    //System.out.println("userinfo："+userInfo.nickname+userInfo.avatar+userInfo.uid);
                    Loadgram loadgram1 = new Loadgram(1,userInfo);
                    SendFriendInfo(loadgram1);
                }
            }
        }
    }

    public void SendFriendInfo(Loadgram loadgram){
        communication.SendDatagram(this.Port,Common.GetXml(loadgram));
    }



    @Override
    public int GetPort() {
        return Port;
    }
}
