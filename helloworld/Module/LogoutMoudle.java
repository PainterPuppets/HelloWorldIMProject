package helloworld.Module;

import helloworld.Share.Common;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by painter on 17-1-2.
 */
public class LogoutMoudle implements SuperMoudle{
    int Port = 4;
    Communication communication;

    public LogoutMoudle(){
        this.communication = Common.currentcommunication;
    }
    public LogoutMoudle(Communication com){ this.communication = com;}

    @Override
    public void Receive(String Msg) {
        System.out.println("login模块处理信息中");
    }

    @Override
    public int GetPort() {
        return Port;
    }

    @XmlRootElement
    class Logingram{
        String Userid;
        String Password;

        public Logingram(){
            super();
        }
    }
}
