package Presetation.loginUI;

import Presetation.mainUI.MainController;
import Presetation.mainUI.MainFrame;
import VO.InputLoginVO;
import VO.InputUserVO;
import VO.UserVO;
import bl.userBl.UserController;
import blSer.UserBlSer;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import rmi.ClientRunner;

public class LoginController{
    @FXML private SplitPane LoginUI;
    @FXML private TextField UserNameField;
    @FXML private PasswordField PasswordField;
    @FXML private Button LoginButtonForLogin;
    @FXML private Button RegistButtonForLogin;
    @FXML private Pane LoginPane;
    @FXML private Pane RegistPane;
    @FXML private Pane RegistInfoPane;
    @FXML private TextField UserNameFieldForRegist;
    @FXML private PasswordField PasswordFieldForRegist;
    @FXML private PasswordField SecondPasswordFieldForRegist;
    @FXML private Label WrongNameForRegist;
    @FXML private Label WrongPasswordForRegist;
    @FXML private Button BackButtonForRegistInfo;
    @FXML private Button RegistButtonForRegistInfo;
    @FXML private Label WrongNameOrPasswordForLogin;
    @FXML private TextField NickNameFieldForRegistInfo;
    @FXML private TextField RealNameFieldForRegistInfo;
    @FXML private TextField PhoneNumberFieldForRegistInfo;
    @FXML private TextField MailFieldForRegistInfo;
    @FXML private TextField BirthdayFieldForRegistInfo;
    @FXML private Label ErrorForRegistInfo;

    private UserBlSer user;

    public void initialize(){
        ClientRunner clientRunner=new ClientRunner();
        LoginPane.setVisible(true);
        RegistPane.setVisible(false);
        RegistInfoPane.setVisible(false);
        WrongPasswordForRegist.setVisible(false);
        WrongNameOrPasswordForLogin.setVisible(false);
        WrongNameForRegist.setVisible(false);
    }

    public void LoginButtonFForLoginClicked(){ //登录按钮监听;
        if(!UserNameField.getText().equals("")&&!PasswordField.equals("")){
            user=new UserController();
            InputLoginVO loginVO=new InputLoginVO();
            loginVO.setAccount(UserNameField.getText());
            loginVO.setPassword(PasswordField.getText());
            try {
                if(user.login(loginVO)){
                    Stage stage=(Stage)LoginUI.getScene().getWindow();
                    stage.close();
                    UserVO userVO=user.getUserInfo(UserNameField.getText());
                   MainFrame mainFrame=new MainFrame(userVO);
                   mainFrame.start(new Stage());
                }
                else{
                    WrongNameOrPasswordForLogin.setVisible(true);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            WrongNameOrPasswordForLogin.setVisible(true);
        }
    }

    public void RegistButtonForLoginClicked(){//登录界面的注册按钮监听;
        LoginPane.setVisible(false);
        RegistInfoPane.setVisible(false);
        RegistPane.setVisible(true);
    }

    public void RegistButtonForRegistClicked(){//注册界面的下一步按钮监听;
         if(!UserNameFieldForRegist.getText().equals("")&&!PasswordFieldForRegist.getText().equals("")&&!SecondPasswordFieldForRegist.getText().equals("")){
             if(PasswordFieldForRegist.getText().equals(SecondPasswordFieldForRegist.getText())){
                 RegistPane.setVisible(false);
                 LoginPane.setVisible(false);
                 RegistInfoPane.setVisible(true);

             }else {
              WrongPasswordForRegist.setVisible(true);
              WrongPasswordForRegist.setText("两次输入的密码不一致");
             }
         }else{
             WrongPasswordForRegist.setVisible(true);
             WrongPasswordForRegist.setText("请输入完整信息！");
         }
    }

    public void LoginButtonForRegistClicked(){//注册界面的登录按钮监听;
         LoginPane.setVisible(true);
         RegistPane.setVisible(false);
         RegistInfoPane.setVisible(false);
    }

    public void EditButtonClicked(){//返回上一步;
       RegistPane.setVisible(true);
       RegistInfoPane.setVisible(false);
       LoginPane.setVisible(false);
    }

    public void SaveButtonClicked(){//完成初始信息填写;
        UserVO registVO=new UserVO();
        user=new UserController();
        registVO.setAccount(UserNameFieldForRegist.getText());
        registVO.setPassword(PasswordFieldForRegist.getText());
        registVO.setUsername(NickNameFieldForRegistInfo.getText());
        registVO.setPhone(PhoneNumberFieldForRegistInfo.getText());
        registVO.setRealName(RealNameFieldForRegistInfo.getText());
        registVO.setMail(MailFieldForRegistInfo.getText());
        registVO.setBirth(BirthdayFieldForRegistInfo.getText());
        if(!UserNameFieldForRegist.getText().equals("")&&!PasswordFieldForRegist.getText().equals("")){
            try {
                if(this.user.register(registVO)){
                    ErrorForRegistInfo.setVisible(true);
                    ErrorForRegistInfo.setText("注册成功");
                } else{
                    ErrorForRegistInfo.setText("用户名已存在");
                    ErrorForRegistInfo.setVisible(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            ErrorForRegistInfo.setVisible(true);
            ErrorForRegistInfo.setText("请填写必填项！");
        }
    }
}