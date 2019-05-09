package com.javaFX_1C.scene;

import com.javaFX_1C.model.Client;
import com.javaFX_1C.repository.Repository;
import com.javaFX_1C.service.Service;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

class SetNewClient {

    private Text textException = new Text("");

    void clientScene(Stage stage) {
        stage.setTitle("1C");
        VBox root = new VBox();
        root.setSpacing(10);
        Label label = new Label("Set a new client");
        Scene scene = new Scene(root, 1500, 900);

        //set textException move animation
        Pane paneException = Service.textExceptionView(textException, scene);

        //set style
        Service.setStyle(root);

        //set text fields
        VBox textFields = new VBox();
        textFields.setSpacing(10);
        textFields.setAlignment(Pos.CENTER);
        TextField fieldName = new TextField();
        fieldName.setMaxWidth(500);
        fieldName.setPromptText("First name and Second name");
        TextField fieldAddress = new TextField();
        fieldAddress.setMaxWidth(500);
        fieldAddress.setPromptText("Address");
        TextField fieldPhone = new TextField();
        fieldPhone.setMaxWidth(500);
        fieldPhone.setPromptText("Phone");
        textFields.getChildren().addAll(label, fieldName, fieldAddress, fieldPhone);

        //set buttons
        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(20);
        Button buttonSet = new Button("set");
        Button buttonClear = new Button("clear");
        AnchorPane buttonsNavigation = new AnchorPane();
        Button buttonMainScene = new Button("main page");
        Button buttonExit = new Button("Exit");
        AnchorPane.setLeftAnchor(buttonMainScene, 0.0);
        AnchorPane.setRightAnchor(buttonExit, 0.0);
        buttonsNavigation.getChildren().addAll(buttonMainScene, buttonExit);
        buttons.getChildren().addAll(buttonSet, buttonClear);

        //handle buttons actions
        buttonMainScene.setOnAction(e -> new MainPage().mainScene(stage));//switch to mainScene
        buttonExit.setOnAction(e -> Service.confirmExit());

        //adding components to the scene
        root.getChildren().addAll(buttonsNavigation, textFields, buttons, paneException);
        stage.setScene(scene);
        stage.show();

        //event handling
        fieldsDataClearing(buttonClear, fieldName, fieldAddress, fieldPhone);
        writeDataToFile(stage, buttonSet, fieldName, fieldAddress, fieldPhone);
    }

    private void writeDataToFile(Stage stage, Button buttonSet, TextField fieldName, TextField fieldAddress, TextField fieldPhone) {
        buttonSet.setOnAction(a -> {
            //check if data has been entered
            if (fieldName.getText().length() == 0){ textException.setText("Enter name"); throw new IllegalArgumentException("Name not entered"); }
            else if (fieldAddress.getText().length() == 0){ textException.setText("Enter address"); throw new IllegalArgumentException("Address not entered"); }
            else if (fieldPhone.getText().length() == 0){ textException.setText("Enter number of phone"); throw new IllegalArgumentException("Number of phone not entered"); }


            //write dada to DB
            Client client = new Client();
            client.setName(fieldName.getText());
            client.setAddress(fieldAddress.getText());
            client.setPhone(fieldPhone.getText());
            try {
                new Repository().save(client);
            }catch (Exception e){e.printStackTrace();}

            //switch to main page
            new MainPage().mainScene(stage);

        });
    }

    private void fieldsDataClearing(Button buttonClear, TextField fieldName, TextField fieldAddress, TextField fieldPhone) {
        buttonClear.setOnAction(e ->
        {
            fieldName.clear();
            fieldAddress.clear();
            fieldPhone.clear();
        });
    }
}