package helloworld.Module;

import helloworld.Share.Common;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by painter on 17-1-2.
 */
public class LogoutMoudle implements SuperMoudle{
    int Port = Common.LOGOUTMOUDLE;
    Communication communication;

    public LogoutMoudle(){
        this.communication = Common.currentcommunication;
    }
    public LogoutMoudle(Communication com){ this.communication = com;}

    @Override
    public void Receive(String Msg) {
        Common.mainFormController.Logout();
    }

    @Override
    public int GetPort() {
        return Port;
    }

    public void Logout(){
        communication.SendDatagram(this.GetPort(),"");
    }//主动离线

}
