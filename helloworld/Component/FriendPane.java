package helloworld.Component;
import helloworld.DataType.UserInfo;
import helloworld.Share.Common;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

/**
 * Created by painter on 17-1-2.
 */
public class FriendPane extends AnchorPane {
    ImageView avatar = new ImageView();
    Label nickname = new Label();
    Label introduction = new Label();
    Button btn = new Button();

    public UserInfo userInfo;

    private int FriendID;

    public FriendPane(int id,String n_nickname){
        this.FriendID = id;
        this.setPrefSize(280.0,60.0);
        //this.setBackground(Color.rgb(255,255,255,0));

        this.avatar.setImage(new Image("../Resources/Image/Avatar1.png"));
        this.avatar.prefHeight(50.0);
        this.avatar.prefWidth(50.0);
        this.avatar.setLayoutX(5);
        this.avatar.setLayoutY(5);

        this.nickname.setText(n_nickname);
        this.nickname.setFont(new Font("Ubuntu Light",15.0));
        this.nickname.prefWidth(25);
        this.nickname.prefHeight(100);
        this.nickname.setLayoutX(60.0);
        this.nickname.setLayoutY(5);

        this.getChildren().add(this.avatar);
        this.getChildren().add(this.nickname);

        this.setOnMouseEntered((MouseEvent) -> {
            //this.
        });
    }

    public FriendPane(int id,int Avatar,String n_nickname,String n_introduction){
        this.FriendID = id;
        userInfo = new UserInfo(id,Avatar,n_nickname,n_introduction);
        this.avatar.setImage(new Image(Common.GetAvatarPath(Avatar)));
        this.avatar.setFitHeight(50.0);
        this.avatar.setFitWidth(50.0);

        this.setId("friend");
        this.btn.setId("FriendButton");
        this.nickname.setId("friendnickname");
        this.introduction.setId("friendintroduction");

        this.nickname.setText(n_nickname);

        this.introduction.setText(n_introduction);

        this.btn.setOnAction((ActionEvent event) -> {
            Common.mainFormController.OpenChatWindow(FriendID);
        });

        this.getChildren().add(this.avatar);
        this.getChildren().add(this.nickname);
        this.getChildren().add(this.introduction);
        this.getChildren().add(this.btn);

        this.setLeftAnchor(this.btn,0.0);
        this.setLeftAnchor(this.avatar,20.0);
        this.setLeftAnchor(this.nickname,80.0);
        this.setLeftAnchor(this.introduction,80.0);

        this.setTopAnchor(this.btn,0.0);
        this.setTopAnchor(this.avatar,5.0);
        this.setTopAnchor(this.nickname,10.0);

        this.setBottomAnchor(this.btn,0.0);
        this.setBottomAnchor(this.introduction,10.0);


        this.setRightAnchor(this.btn,0.0);
        this.setRightAnchor(this.nickname,5.0);
        this.setRightAnchor(this.introduction,5.0);
    }
}
