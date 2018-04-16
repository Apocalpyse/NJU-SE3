package Presetation.userUI;

import VO.UserVO;
import blSer.UserBlSer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class UserController{
  @FXML private Button EditButton;
  @FXML private Button SaveButton;
  @FXML private TextField UserNameField;
  @FXML private TextField RealNameField;
  @FXML private TextField PhoneNumberField;
  @FXML private TextField MailField;
  @FXML private TextField BirthdayField;
  @FXML private Label ErrorLabelForInfo;
  @FXML private Button PasswordChangeButton;
  @FXML private PasswordField NewPasswordField;
  @FXML private Button RegistButtonForPassword;
  @FXML private PasswordField SecondPasswordField;
  @FXML private PasswordField OriginalPasswordField;
  @FXML private Pane InfoPane;
  @FXML private Pane PasswordChangePane;
  @FXML private Label DifferentPassword;

  private UserBlSer user;
  public static UserVO uvo;

  public void initialize(){
    UserNameField.setEditable(false);
    RealNameField.setEditable(false);
    PhoneNumberField.setEditable(false);
    MailField.setEditable(false);
    BirthdayField.setEditable(false);
    if(uvo!=null){
      UserNameField.setText(uvo.getUsername());
      RealNameField.setText(uvo.getRealName());
      PhoneNumberField.setText(uvo.getPhone());
      MailField.setText(uvo.getMail());
      BirthdayField.setText(uvo.getBirth());
    }
  }

  public void EditButtonClicked(){
    UserNameField.setEditable(true);
    RealNameField.setEditable(true);
    PhoneNumberField.setEditable(true);
    MailField.setEditable(true);
    BirthdayField.setEditable(true);
  }

  public void SaveButtonClicked(){
    this.user=new bl.userBl.UserController();
    try {
      uvo.setUsername(UserNameField.getText());
      uvo.setRealName(RealNameField.getText());
      uvo.setPhone(PhoneNumberField.getText());
      uvo.setMail(MailField.getText());
      uvo.setBirth(BirthdayField.getText());
      this.user.changeUserInfo(uvo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    ErrorLabelForInfo.setText("修改成功");
  }

  public void RegistButtonForLoginClicked(){//修改密码
      InfoPane.setVisible(false);
      PasswordChangePane.setVisible(true);
  }

  public void RegistButtonForRegistClicked(){//确认修改
    this.user=new bl.userBl.UserController();
     try {
       if(OriginalPasswordField.getText().equals(user.getUserInfo(uvo.getAccount()).getPassword())){
         if(NewPasswordField.getText().equals(SecondPasswordField.getText())){
           uvo.setPassword(NewPasswordField.getText());
           user.changeUserInfo(uvo);
         }else {
           DifferentPassword.setVisible(true);
           DifferentPassword.setText("前后密码不一致");
         }
       }else{
         DifferentPassword.setVisible(true);
         DifferentPassword.setText("原密码错误");
       }
     }catch (Exception e){
       e.printStackTrace();
     }
  }

}