package com.javaFX_1C;

import com.javaFX_1C.scene.MainPage;
import com.javaFX_1C.service.Service;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setOnCloseRequest(e -> {
            e.consume();
            Service.confirmExit();
        });
        stage.setResizable(false);
        new MainPage().mainScene(stage);
    }

}
