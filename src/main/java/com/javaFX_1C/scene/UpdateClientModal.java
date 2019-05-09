package com.javaFX_1C.scene;

import com.javaFX_1C.repository.Repository;
import com.javaFX_1C.service.Service;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

class UpdateClientModal {

    void updateClient(String[] arr, Stage stage){

        Text textException = new Text("");

        Label label = new Label("Edit client");
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(30);
        Scene modalScene = new Scene(vBox, 800, 600);

        VBox textFields = new VBox();
        textFields.setSpacing(10);
        textFields.setAlignment(Pos.TOP_CENTER);
        TextField fieldName = new TextField(arr[1]);
        fieldName.setMaxWidth(500);
        fieldName.setPromptText("First name and Second name");
        TextField fieldAddress = new TextField(arr[2]);
        fieldAddress.setMaxWidth(500);
        fieldAddress.setPromptText("Address");
        TextField fieldPhone = new TextField(arr[3]);
        fieldPhone.setMaxWidth(500);
        fieldPhone.setPromptText("Phone");
        textFields.getChildren().addAll(fieldName, fieldAddress, fieldPhone);

        HBox buttons = new HBox();
        buttons.setSpacing(50);
        buttons.setAlignment(Pos.CENTER);
        Button buttonSet = new Button("Set");
        Button buttonAbort = new Button("Abort");
        buttons.getChildren().addAll(buttonSet, buttonAbort);

        Service.setStyle(vBox);
        Pane paneException = Service.textExceptionView(textException, modalScene);

        vBox.getChildren().addAll(label, textFields, buttons, paneException);
        Stage modalStage = new Stage();
        modalStage.setTitle("Edit client");
        modalStage.setAlwaysOnTop(true);
        modalStage.setScene(modalScene);
        modalStage.show();

        buttonSet.setOnAction(e -> {
            if (fieldName.getText().isEmpty() || fieldAddress.getText().isEmpty() || fieldPhone.getText().isEmpty()){
                textException.setText("Data mustn't be empty");
            } else {
                new Repository().updateClient(Integer.parseInt(arr[0]), fieldName.getText(), fieldAddress.getText(), fieldPhone.getText());
                new ClientsList().clientsListScene(stage);
                modalStage.close();
            }
        });
        buttonAbort.setOnAction(e -> modalStage.close());
    }
}
