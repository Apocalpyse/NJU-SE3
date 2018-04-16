package Presetation.industryUI;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by A on 2017/5/18.
 */
public class SpecificIndustry extends Application {
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SpecificIndustry.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(null);
//        scene.getStylesheets().add(getClass().getResource("MainFrame-transparent.css").toExternalForm());
        primaryStage.setScene(scene);
        StageStyle style = StageStyle.TRANSPARENT;
        primaryStage.initStyle(style);
        primaryStage.show();

        root.setOnMousePressed((MouseEvent event) -> {
            event.consume();
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) -> {
            event.consume();
            primaryStage.setX(event.getScreenX() - xOffset);


            if (event.getScreenY() - yOffset < 0) {
                primaryStage.setY(0);
            } else {
                primaryStage.setY(event.getScreenY() - yOffset);
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

    public static void main(String args[]){
        launch(args);
    }
}
