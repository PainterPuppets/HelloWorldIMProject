package helloworld.Form;
import helloworld.Share.Common;
import helloworld.Component.Move;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.stage.StageStyle;

public class LoginForm extends Application {

    public Stage n_stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Common.loginform = this;
        n_stage = primaryStage;
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Parent root = FXMLLoader.load(getClass().getResource("../Resources/FXML/LoginForm.fxml"));
        Scene scene = new Scene(root);
        Move move = new Move(primaryStage,scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        n_stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void Close(){
        n_stage.close();
    }

}