package helloworld.Controller;
import helloworld.Component.Chatbubbles;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoFormController implements Initializable{

    @FXML protected VBox Chatbox;
    @FXML protected TextField MessageText;

    @FXML protected void InputButtonAction(){
    }


    @FXML protected void AvatarButtonAction(ActionEvent even){
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }//初始化

    public void IncreaseChat(Chatbubbles chatbubbles){
        Chatbox.getChildren().add(chatbubbles);
    }


}
