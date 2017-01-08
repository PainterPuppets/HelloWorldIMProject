package helloworld.Controller;
import helloworld.DataType.UserInfo;
import helloworld.Form.ChatForm;
import helloworld.Form.LoginForm;
import helloworld.Form.MainForm;
import helloworld.Module.BeanMoudle;
import helloworld.Module.LoadMoudle;
import helloworld.Module.LogoutMoudle;
import helloworld.Share.Common;
import helloworld.Component.FriendPane;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.text.html.parser.Entity;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.ResourceBundle;

public class MainFormController implements Initializable{
    public boolean Online = false;

    Hashtable Friendtable = new Hashtable();
    public Hashtable Chattable = new Hashtable();

    @FXML protected AnchorPane Friendpane;
    @FXML protected ImageView Avatar;
    @FXML protected Label Nickname;
    @FXML protected Label Introduction;


    @FXML protected void AvatarButtonAction(ActionEvent even){
    }//个人头像点击事件

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Common.mainFormController = this;
        LoadingNewMoudle();
        ((LoadMoudle)Common.currentcommunication.GetMoudle(Common.LOADMOUDLE)).FirstLoad();
        Avatar.setImage(new Image(Common.GetAvatarPath(Common.currentcommunication.currentuser.avatar)));
        Nickname.setText(Common.currentcommunication.currentuser.nickname);
        Introduction.setText(Common.currentcommunication.currentuser.introduction);
    }//初始化


    @FXML protected void closeButtonAction(){
        ((LogoutMoudle)Common.currentcommunication.GetMoudle(Common.LOGOUTMOUDLE)).Logout();
        try {
            Thread.sleep(100);
        }catch (Exception e){ e.printStackTrace(); }
        System.exit(0);
    }//点击关闭按钮

    public void IncreaseFriend(UserInfo userInfo){
        if(!Friendtable.containsKey(userInfo.uid)) {
            FriendPane fp = new FriendPane(userInfo.uid,userInfo.avatar, userInfo.nickname, userInfo.introduction);
            Friendtable.put(userInfo.uid, fp);
            int anchor = Friendtable.size()-1;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Friendpane.getChildren().add(fp);
                    Friendpane.setLeftAnchor(fp,10.0);
                    Friendpane.setRightAnchor(fp,10.0);
                    Friendpane.setTopAnchor(fp,anchor*60.0);
                }
            });
        }else{
            System.out.println("添加"+userInfo.uid+"好友栏错误");
        }
    }//加载好友

    public void LoadingNewMoudle(){
        Common.currentcommunication.IncreaseMoudle(new LoadMoudle(Common.currentcommunication));
        Common.currentcommunication.IncreaseMoudle(new BeanMoudle(Common.currentcommunication));
        Common.currentcommunication.IncreaseMoudle(new LogoutMoudle(Common.currentcommunication));

        Common.currentcommunication.DeleteMoudle(Common.LOGINMOUDLE);
    }//加载新组件

    public void OpenChatWindow(int uid){
        if(!Chattable.containsKey(uid)){
            Chattable.put(uid,new ChatFormController());
            Platform.runLater(() -> {
                try {
                    ChatForm chatForm = new ChatForm();
                    chatForm.userInfo = ((FriendPane)Friendtable.get(uid)).userInfo;
                    chatForm.start(new Stage());
                }catch (Exception e){
                    Chattable.remove(uid);
                    e.printStackTrace();
                }
            });
        }else {
            ((ChatFormController)Chattable.get(uid)).OpenWindow();
        }
    }//点击好友事件

    public void Logout(){
        Common.currentcommunication.LinkFlag = false;
        System.out.println("您已离线");
        System.exit(0);
        /*Platform.runLater(() -> {
            try {
                new LoginForm().start(new Stage());
                closeButtonAction();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        */
    }//离线


}
