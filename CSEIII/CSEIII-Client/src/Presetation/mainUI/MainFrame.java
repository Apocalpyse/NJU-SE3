package Presetation.mainUI; /**
 * Created by chr on 2017/3/4.
 */
import VO.UserVO;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MainFrame extends Application {
    private double xOffset = 0;
    private double yOffset = 0;
    private Stage dialogStage;
    private UserVO userVO;
    private ProgressIndicator progressIndicator;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainFrame.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(null);
        scene.getStylesheets().add(getClass().getResource("MainFrame-transparent.css").toExternalForm());
        primaryStage.setScene(scene);
        StageStyle style = StageStyle.TRANSPARENT;
        primaryStage.initStyle(style);
        primaryStage.show();


        // 窗口父子关系
        dialogStage=new Stage();
//        dialogStage.setAlwaysOnTop(true);
        dialogStage.initOwner(primaryStage);
        dialogStage.initStyle(StageStyle.UNDECORATED);
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.initModality(Modality.NONE);
        dialogStage.setHeight(700);
        dialogStage.setWidth(1190);
        dialogStage.setResizable(false);
        dialogStage.setX(primaryStage.getX()+250);
        dialogStage.setY(primaryStage.getY()+110);
        // progress bar
        progressIndicator = new ProgressIndicator();
        Label label = new Label("   数据加载中, 请稍后...");
        VBox vBox = new VBox();
        vBox.setBackground(Background.EMPTY);
        vBox.getChildren().addAll(progressIndicator,label);

        Scene scene2 = new Scene(vBox);
        scene2.getStylesheets().add(getClass().getResource("WaitingFrame-transparent.css").toExternalForm());
        scene2.setFill(null);        dialogStage.setScene(scene2);
//        dialogStage.show();
        root.setOnMousePressed((MouseEvent event) -> {
            event.consume();
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) -> {
            event.consume();
            primaryStage.setX(event.getScreenX() - xOffset);
            dialogStage.setX(primaryStage.getX()+250);

            if (event.getScreenY() - yOffset < 0) {
                primaryStage.setY(0);
                dialogStage.setY(primaryStage.getY()+110);
            } else {
                primaryStage.setY(event.getScreenY() - yOffset);
                dialogStage.setY(primaryStage.getY()+110);
            }
        });

        Button closeButton = (Button) root.lookup("#CloseButton");
        closeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.close();
            }
        });

        Button minButton = (Button) root.lookup("#MinButton");
        minButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setIconified(true);
            }
        });

    }

    public MainFrame(){}

    public MainFrame(UserVO vo){
        this.userVO=vo;
        MainController.userVO=vo;
    }

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public static void  main(String[] args){
        launch(args);
    }
}

