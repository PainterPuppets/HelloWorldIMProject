package helloworld.Controller;
import helloworld.Form.MainForm;
import helloworld.Module.Communication;
import helloworld.Module.LoadMoudle;
import helloworld.Module.LoginMoudle;
import helloworld.Share.Common;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable{
    @FXML private String Address = "127.0.0.1";
    @FXML private int Port = 19001;
    @FXML private Label actiontarget;
    @FXML private TextField useridtext;
    @FXML private PasswordField passwordtext;
    @FXML private Button LogButton;

    @FXML protected void handleSubmitButtonAction(Event event) {
        if(useridtext.getText().isEmpty()) {
            //System.out.println("用户名不能为空");
            actiontarget.setText("用户名不能为空");
        }else if(passwordtext.getText().isEmpty()){

            //System.out.println("密码不能为空");
            actiontarget.setText("密码不能为空");
        }else {
            try {
                String user = useridtext.getText().trim();
                String password = passwordtext.getText().trim();
                Common.currentcommunication = new Communication(new Socket(Address, Port));
                LoginMoudle lm = new LoginMoudle();
                if(Common.currentcommunication.IncreaseMoudle(lm)){
                    Common.currentcommunication.start();
                    ((LoginMoudle) Common.currentcommunication.GetMoudle(Common.LOGINMOUDLE)).Login(user, password);
                }else{
                    System.out.println("模块加载失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
                //actiontarget.setText("无法连接至服务器，请检查网络设备");
                //链接服务器失败，请检查网络设施
            }
        }
    }

    @FXML protected void KeyPressAction(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            handleSubmitButtonAction((Event)event);
            event.consume();
        }else if(event.getCode() == KeyCode.ESCAPE){
            closeButtonAction((Event)event);
            event.consume();
        }
    }

    @FXML protected void closeButtonAction(Event event){
        Common.loginform.Close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Common.loginFormController = this;
    }//初始化


    public void Loginpass(){
        Platform.runLater(() -> {
            try {
                Common.loginform.n_stage.hide();
                new MainForm().start(new Stage());
            }catch (Exception e){
                e.printStackTrace();
            }
        });

    }//登录成功

    public void Loginerror(){
        Common.currentcommunication.Close();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
              actiontarget.setText("账号不存在");
            }
        });
    }//账号不存在

    public void Loginfail() {
        Common.currentcommunication.Close();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                actiontarget.setText("密码错误");
            }
        });
    }//密码错误




}
