package helloworld.Controller;
import helloworld.Share.Common;
import helloworld.Component.FriendPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Hashtable;
import java.util.ResourceBundle;

public class MainFormController implements Initializable{
    public boolean Online = false;

    Hashtable Friendtable = new Hashtable();
    @FXML protected AnchorPane Friendpane;

    static int a = 10000;

    @FXML protected void AvatarButtonAction(ActionEvent even){
        a++;
        IncreaseFriend(a,String.valueOf(a),"I am"+a+"User");
        System.out.println("插入好友信息中");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Common.mainFormController = this;
    }//初始化

    public void IncreaseFriend(int uid,String nickname,String introduction){
        if(!Friendtable.containsKey(uid)) {
            FriendPane fp = new FriendPane(uid, nickname, introduction);
            Friendtable.put(uid, fp);
            Friendpane.getChildren().add(fp);

            Friendpane.setLeftAnchor(fp,10.0);
            Friendpane.setRightAnchor(fp,10.0);
            Friendpane.setTopAnchor(fp,(Friendtable.size()-1)*60.0);

        }else{}
    }


}
