package com.javaFX_1C.scene;

import com.javaFX_1C.service.Service;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainPage {

    public void mainScene(Stage stage) {
        stage.setTitle("1C");
        VBox root = new VBox();
        root.setSpacing(10);
        root.setAlignment(Pos.TOP_CENTER);
        Label label = new Label("Main page");
        Scene mainScene = new Scene(root, 1500, 900);

        //set style
        Service.setStyle(root);

        //set buttons switcher scenes
        AnchorPane buttonsNavigation = new AnchorPane();
        Button buttonClientScene = new Button("Add a new client");
        Button buttonClientsListScene = new Button("Clients list");
        Button buttonExit = new Button("Exit");
        HBox buttonsNavigationHBox = new HBox();
        AnchorPane.setRightAnchor(buttonExit, 0.0);
        buttonsNavigationHBox.getChildren().addAll(buttonClientScene, buttonClientsListScene);
        buttonsNavigation.getChildren().addAll(buttonsNavigationHBox, buttonExit);

        //handle buttons actions
        buttonClientScene.setOnAction(e -> new SetNewClient().clientScene(stage));
        buttonClientsListScene.setOnAction(e -> new ClientsList().clientsListScene(stage));
        buttonExit.setOnAction(e -> Service.confirmExit());

        root.getChildren().addAll(buttonsNavigation, label);
        stage.setScene(mainScene);
        stage.show();
    }
}
