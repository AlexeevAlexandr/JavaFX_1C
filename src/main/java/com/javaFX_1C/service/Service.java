package com.javaFX_1C.service;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Service {

    public static void setStyle(VBox root) {
        BackgroundImage myBI= new BackgroundImage(
                new Image("8M5A7719 edited.jpg",800,600,false,true),
                BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));
    }

    public static void confirmExit(){
            StackPane stackPane = new StackPane();
            Scene modalScene = new Scene(stackPane, 200, 100);
            HBox modalHBox = new HBox();
            Button yes = new Button("Yes");
            Button no = new Button("No");
            modalHBox.setAlignment(Pos.CENTER);
            modalHBox.setSpacing(50);
            modalHBox.getChildren().addAll(yes, no);
            stackPane.getChildren().addAll(modalHBox);
            Stage modalStage = new Stage();
            modalStage.setTitle("Confirm exit");
            modalStage.setAlwaysOnTop(true);
            modalStage.setScene(modalScene);
            modalStage.show();

            no.setOnAction(actionEvent1 -> modalStage.close());
            yes.setOnAction(actionEvent1 -> System.exit(0));
    }

    public static Pane textExceptionView(Text textException, Scene scene){
        textException.setTextOrigin(VPos.TOP);
        textException.setFont(Font.font(20));
        Pane rootTextException = new Pane(textException);
        KeyValue initKeyValue = new KeyValue(textException.translateXProperty(), scene.getWidth());
        KeyFrame initFrame = new KeyFrame(Duration.ZERO, initKeyValue);
        KeyValue endKeyValue = new KeyValue(textException.translateXProperty(), -1.0 * textException.getLayoutBounds().getWidth());
        KeyFrame endFrame = new KeyFrame(Duration.seconds(7), endKeyValue);
        Timeline timeline = new Timeline(initFrame, endFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        return rootTextException;
    }
}