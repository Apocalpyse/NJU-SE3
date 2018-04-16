package Presetation.messageUI;

import Presetation.loginUI.LoginFrame;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

/**
 * Created by thinkpad on 2017/4/14.
 */
public class MessageController {
    @FXML private SplitPane MessageUI;
    @FXML private Button LoginButtonForMessage;
    @FXML private Button NotLoginButtonForMessage;

    public void LoginButtonForMessageOnClicked(){
        LoginFrame loginFrame=new LoginFrame();
        try{
            loginFrame.start(new Stage());
        }catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage=(Stage)MessageUI.getScene().getWindow();
        stage.close();
    }

    public void NotLoginButtonForMessageOnClicked(){
        Stage stage=(Stage)MessageUI.getScene().getWindow();
        stage.close();
    }
}
