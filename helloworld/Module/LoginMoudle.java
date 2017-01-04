package helloworld.Module;

import helloworld.Share.Common;
import helloworld.DataType.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by painter on 17-1-2.
 */
public class LoginMoudle implements SuperMoudle{
    int Port = 3;
    Communication communication;

    public LoginMoudle(){
        this.communication = Common.currentcommunication;
    }
    public LoginMoudle(Communication com){ this.communication = com;}

    public void Login(String userid,String password){
        Logingram logingram = new Logingram(userid,password);
        communication.SendDatagram(this.Port,Common.GetXml(logingram));
    }

    @Override
    public void Receive(String Msg) {
        System.out.println("登录模块接收到"+Msg);
        RecLogingram rlc = (RecLogingram) Common.GetObject(Msg,RecLogingram.class);
        if(rlc != null) {
            switch (rlc.type) {
                case 0://账号不存在
                    Common.loginFormController.Loginerror();
                    break;
                case 1://登录成功
                    Common.loginFormController.Loginpass();
                    break;
                case 2://密码错误
                    Common.loginFormController.Loginfail();
                    break;
                default:
                    break;
            }
        }else{
            System.out.println("2");
        }
        System.out.println("login模块处理信息中");
    }

    @Override
    public int GetPort() {
        return Port;
    }



}
