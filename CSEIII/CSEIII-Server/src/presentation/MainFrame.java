package presentation;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainFrame extends Application {
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainFrame.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("MainFrame.css").toExternalForm());
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
                System.exit(0);
            }
        });

        Button minButton = (Button) root.lookup("#minButton");
        minButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setIconified(true);
            }
        });


    }

    public MainFrame(){}

    public static void main(String[] args){
       launch(args);
    }

}
