package helloworld.Form;

import helloworld.Component.Move;
import helloworld.Share.Common;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainForm extends Application {

    public Stage n_stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Common.mainform = this;
        n_stage = primaryStage;
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Parent root = FXMLLoader.load(getClass().getResource("../Resources/FXML/MainForm.fxml"));
        Scene scene = new Scene(root);
        Move move = new Move(primaryStage,scene);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setX(1500);
        primaryStage.setY(160);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void Close(){
        n_stage.close();
    }

}