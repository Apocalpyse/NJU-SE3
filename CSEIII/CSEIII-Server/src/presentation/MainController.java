package presentation;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import rmi.ServerRunner;

public class MainController {
  @FXML private TextArea TextArea;
  @FXML private Button StartButton;
  @FXML private Label ErrorText;

  public void initialize(){
    ErrorText.setVisible(false);
  }

  public void StartButtonOnClicked(){
      ServerRunner serverRunner=new ServerRunner();
      this.TextArea.setText("服务器端启动成功");
      StartButton.setStyle("-fx-effect: dropshadow(three-pass-box, yellow, 2, 0.4, 0, 0)");
  }
}