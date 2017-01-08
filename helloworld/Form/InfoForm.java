package helloworld.Form;

import helloworld.Component.Move;
import helloworld.Share.Common;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InfoForm extends Application {

    public Stage n_stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        n_stage = primaryStage;
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Parent root = FXMLLoader.load(getClass().getResource("../Resources/FXML/InfoForm.fxml"));
        Scene scene = new Scene(root);
        Move move = new Move(primaryStage,scene);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void Close(){
        n_stage.close();
    }

}