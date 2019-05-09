package com.javaFX_1C.scene;

import com.javaFX_1C.repository.Repository;
import com.javaFX_1C.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

class ClientsList {

    private Text textException = new Text("");

    void clientsListScene(Stage stage) {
        VBox root = new VBox();
        root.setSpacing(20);
        Label label = new Label("List of clients");
        root.setAlignment(Pos.TOP_CENTER);
        Scene scene = new Scene(root, 1500, 900);

        //set style
        Service.setStyle(root);

        //set buttons
        AnchorPane anchorPane = new AnchorPane();
        HBox buttonsScene = new HBox();
        Button buttonToMaineScene = new Button("Maine page");
        Button buttonExit = new Button("Exit");
        AnchorPane.setRightAnchor(buttonExit, 0.0);
        buttonsScene.getChildren().addAll(buttonToMaineScene);
        anchorPane.getChildren().addAll(buttonsScene, buttonExit);

        HBox buttonsAction = new HBox();
        buttonsAction.setAlignment(Pos.CENTER);
        buttonsAction.setSpacing(50);
        Button edit = new Button("Edit");
        Button delete = new Button("Delete");
        buttonsAction.getChildren().addAll(edit, delete);

        //reading from DB
        List<String> arrayList = new ArrayList<>();
        for(Object a : new Repository().getClients()){
            arrayList.add(a.toString());
        }

        //view data on window
        ListView<String> list = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList(arrayList);
        list.setItems(items);
        list.setMaxWidth(1400);
        list.setMaxHeight(700);

        //set window for list of clients
        VBox listScene = new VBox();
        listScene.setAlignment(Pos.CENTER);
        listScene.getChildren().addAll(list);

        //handle buttons actions
        buttonToMaineScene.setOnAction(e -> new MainPage().mainScene(stage));//switch to mainScene
        buttonExit.setOnAction(e -> Service.confirmExit());
        edit.setOnAction(e -> {
            if (list.getSelectionModel().isEmpty()) {
                textException.setText("Selected nothing");
            }else{
                ObservableList item = list.getSelectionModel().getSelectedItems();
                String[] arr = item.get(0).toString().split(";");
                new UpdateClientModal().updateClient(arr, stage);
            }
        });
        delete.setOnAction(e -> {
            if (list.getSelectionModel().isEmpty()) {
                textException.setText("Selected nothing");
            }else{
                ObservableList item = list.getSelectionModel().getSelectedItems();
                String[] arr = item.get(0).toString().split(";");
                if (new Repository().deleteClient(Integer.parseInt(arr[0]))){
                    textException.setText("Client deleted");
                    new ClientsList().clientsListScene(stage);
                } else {
                    textException.setText("Client not found");
                }
            }
        });

        //set text exception
        Pane paneException = Service.textExceptionView(textException, scene);

        root.getChildren().addAll(anchorPane, label, listScene, buttonsAction, paneException);
        stage.setScene(scene);
    }
}