package helloworld.Module;

import helloworld.DataType.Loadgram;
import helloworld.DataType.UserInfo;
import helloworld.Share.Common;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by painter on 17-1-2.
 */
public class LoadMoudle implements SuperMoudle{
    int Port = Common.LOADMOUDLE;
    Communication communication;

    public LoadMoudle(){
        this.communication = Common.currentcommunication;
    }
    public LoadMoudle(Communication com){ this.communication = com;}

    @Override
    public void Receive(String Msg) {
        Loadgram loadgram = (Loadgram) Common.GetObject(Msg,Loadgram.class);
        if(loadgram != null){
            Common.mainFormController.IncreaseFriend(loadgram.info);
        }
    }

    public void FirstLoad(){
        Loadgram loadgram = new Loadgram(1);
        communication.SendDatagram(this.Port,Common.GetXml(loadgram));
    }

    @Override
    public int GetPort() {
        return Port;
    }
}
