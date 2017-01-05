package helloworldServer.Module;

import helloworldServer.Share.Common;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by painter on 17-1-2.
 */
public class BeanMoudle implements SuperMoudle{
    int Port = Common.BEANNMOUDLE;
    Communication communication;

    public BeanMoudle(Communication com){ this.communication = com;}

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
