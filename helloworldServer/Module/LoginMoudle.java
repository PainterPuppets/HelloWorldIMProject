package helloworldServer.Module;

import helloworldServer.Control.LoginController;
import helloworldServer.DataController.UserController;
import helloworldServer.DataType.*;
import helloworldServer.Model.User;
import helloworldServer.Share.Common;

/**
 * Created by painter on 17-1-2.
 */
public class LoginMoudle implements SuperMoudle{
    int Port = Common.LOGINMOUDLE;
    Communication communication;

    public LoginMoudle(Communication com){ this.communication = com;}


    @Override
    public void Receive(String Msg) {
        System.out.println(Msg);
        Logingram logingram = (Logingram) Common.GetObject(Msg,Logingram.class);
        if(logingram != null) {
            User user = UserController.SelectUser(logingram.Userid);
            RecLogingram recLogingram;
            if (user != null) {
                if(user.password.equals(logingram.Password)){
                    recLogingram = new RecLogingram(1,new UserInfo(user.uid,user.avatar,user.nickname,user.introduction));
                    communication.SendDatagram(3, Common.GetXml(recLogingram));
                    communication.SetUser(user);
                    LoginController.Loginpass(communication);
                }else{
                    recLogingram = new RecLogingram(2);
                    communication.SendDatagram(3, Common.GetXml(recLogingram));
                    LoginController.Loginfail(communication);
                }
            } else {
                recLogingram = new RecLogingram(0);
                communication.SendDatagram(3, Common.GetXml(recLogingram));
                LoginController.Loginfail(communication);
            }
        }
    }

    @Override
    public int GetPort() {
        return Port;
    }


}
