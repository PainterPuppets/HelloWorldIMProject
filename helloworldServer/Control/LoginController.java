package helloworldServer.Control;

import helloworldServer.Module.*;
import helloworldServer.Share.Common;

/**
 * Created by painter on 17-1-4.
 */
public class LoginController {
    public static void Loginpass(Communication communication){
        communication.IncreaseMoudle(new LoadMoudle(communication));
        communication.IncreaseMoudle(new MessageMoudle(communication));
        communication.IncreaseMoudle(new BeanMoudle(communication));
        communication.IncreaseMoudle(new LogoutMoudle(communication));
        communication.IncreaseMoudle(new InfomationMoudle(communication));

        communication.DeleteMoudle(Common.LOGINMOUDLE);
    }

    public static void Loginfail(Communication communication){
        communication.Close();
    }
}
