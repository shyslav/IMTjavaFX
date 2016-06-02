package com.shyslav.form;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.security.Key;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Shyshkin Vladyslav on 01.06.2016.
 */
public class newElementForm {
    private Stage primaryStage;
    Stage newElementStage = new Stage();
    BorderPane root = new BorderPane();
    private String title;

    public newElementForm(String title, Stage stag) {
        this.title = title;
        this.primaryStage = stag;

        GridPane gridPaneNElement = new GridPane();
        gridPaneNElement.setPadding(new Insets(5));
        gridPaneNElement.setHgap(5);
        gridPaneNElement.setVgap(5);
        ColumnConstraints labelColumn = new ColumnConstraints(100);
        ColumnConstraints nColumn = new ColumnConstraints(50, 150, 300);
        nColumn.setHgrow(Priority.ALWAYS);
        gridPaneNElement.getColumnConstraints().addAll(labelColumn, nColumn);

        Label Nlabel = new Label("Введите N");
        TextField NtextField = new TextField();

        GridPane.setHalignment(Nlabel, HPos.RIGHT);
        gridPaneNElement.add(Nlabel, 0, 0);
        GridPane.setHalignment(NtextField, HPos.LEFT);
        gridPaneNElement.add(NtextField, 1, 0);

        AnchorPane anchor = new AnchorPane();
        anchor.getChildren().add(gridPaneNElement);
        anchor.setRightAnchor(gridPaneNElement, 5.0);
        anchor.setLeftAnchor(gridPaneNElement, 5.0);
        anchor.setTopAnchor(gridPaneNElement, 5.0);
        anchor.setBottomAnchor(gridPaneNElement, 5.0);
        root.setTop(anchor);
        Scene scene = new Scene(root, 380, 150, Color.WHITE);

        handlerNtextField(NtextField);

        newElementStage.initModality(Modality.WINDOW_MODAL);
        newElementStage.initOwner(primaryStage);
        newElementStage.setResizable(false);
        newElementStage.setTitle(title);
        newElementStage.setScene(scene);
        newElementStage.show();
    }

    private void handlerNtextField(TextField field) {
        field.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                GridPane gridPane = generateDataGrid();
                if (event.getText() != null && !event.getText().equals("")) {
                    if(event.getCode() != KeyCode.TAB) {
                        if (isInteger(event.getText())) {
                            gridPane.setVisible(true);
                            root.setCenter(gridPane);
                            field.setStyle("-fx-border-color: green");
                        } else {
                            gridPane.setVisible(false);
                            field.setStyle("-fx-border-color: red");
                            root.setCenter(gridPane);
                        }
                    }
                }

            }
        });
    }
    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    private GridPane generateDataGrid() {
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(5);
        gridpane.setVgap(5);
        ColumnConstraints column1 = new ColumnConstraints(100);
        ColumnConstraints column2 = new ColumnConstraints(50, 150, 300);
        column2.setHgrow(Priority.ALWAYS);
        gridpane.getColumnConstraints().addAll(column1, column2);

        Label fNameLbl = new Label("First Name");
        TextField fNameFld = new TextField();
        Label lNameLbl = new Label("Last Name");
        TextField lNameFld = new TextField();

        Button saveButt = new Button("Save");

        // First name label
        GridPane.setHalignment(fNameLbl, HPos.RIGHT);
        gridpane.add(fNameLbl, 0, 0);

        // Last name label
        GridPane.setHalignment(lNameLbl, HPos.RIGHT);
        gridpane.add(lNameLbl, 0, 1);

        // First name field
        GridPane.setHalignment(fNameFld, HPos.LEFT);
        gridpane.add(fNameFld, 1, 0);

        // Last name field
        GridPane.setHalignment(lNameFld, HPos.LEFT);
        gridpane.add(lNameFld, 1, 1);

        // Save button
        GridPane.setHalignment(saveButt, HPos.RIGHT);
        gridpane.add(saveButt, 1, 2);
        return gridpane;
    }

    private void WindowsClose() {
        newElementStage.close();
    }
}
