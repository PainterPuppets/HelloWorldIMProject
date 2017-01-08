package helloworld.Controller;
import helloworld.Component.Chatbubbles;
import helloworld.Form.ChatForm;
import helloworld.Form.MainForm;
import helloworld.Share.Common;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatFormController implements Initializable{

    @FXML protected VBox Chatbox;
    @FXML protected TextField MessageText;

    @FXML protected ImageView Avatar;
    @FXML protected Label Nickname;
    @FXML protected Label Introduction;

    @FXML protected ScrollPane ChatScrollPane;


    public ChatForm chatForm;

    @FXML protected void InputButtonAction(){
        Chatbubbles chatbubbles=new Chatbubbles(MessageText.getText(),Common.currentcommunication.currentuser,0);
        IncreaseChat(chatbubbles);
        MessageText.setText("");
    }

    @FXML public void CloseButtonAction(){
        Platform.runLater(() -> {
            try {
                Common.mainFormController.Chattable.remove(chatForm.userInfo.uid);
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        chatForm.Close();
    }

    @FXML protected void KeyPressAction(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            InputButtonAction();
            event.consume();
        }else if(event.getCode() == KeyCode.ESCAPE){
            CloseButtonAction();
            event.consume();
        }
    }


    @FXML protected void AvatarButtonAction(ActionEvent even){
    }//资料页

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }//初始化

    public void IncreaseChat(Chatbubbles chatbubbles){
        Chatbox.getChildren().add(chatbubbles);
        ChatScrollPane.setVvalue(1);
    }

    public void OpenWindow(){
        chatForm.n_stage.setAlwaysOnTop(true);
        chatForm.n_stage.setAlwaysOnTop(false);
    }

    public void InitUserInfo(){
        Avatar.setImage(new Image(Common.GetAvatarPath(chatForm.userInfo.avatar)));
        Nickname.setText(chatForm.userInfo.nickname);
        Introduction.setText(chatForm.userInfo.introduction);
    }


}
