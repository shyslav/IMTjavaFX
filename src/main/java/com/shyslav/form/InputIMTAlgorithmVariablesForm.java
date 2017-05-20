package com.shyslav.form;

import com.shyslav.controllers.alerts.JavaFxSimpleAlert;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import starter.StartFrame;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Shyshkin Vladyslav on 01.06.2016.
 */
public class InputIMTAlgorithmVariablesForm {
    private GridPane gridOfMassive;
    private Stage primaryStage;
    Stage newElementStage = new Stage();
    BorderPane root = new BorderPane();
    private String title;
    private Map<Integer, TextField> valuesA = new HashMap();
    private Map<Integer, TextField> valuesC = new HashMap();
    private Map<Integer, TextField> valuesH = new HashMap();
    private Map<Integer, TextField> valuesD = new HashMap();
    int n;

    public InputIMTAlgorithmVariablesForm(String title, Stage stag) {
        this.title = title;
        this.primaryStage = stag;

        GridPane gridPaneNElement = new GridPane();
        gridPaneNElement.setPadding(new Insets(5));
        gridPaneNElement.setHgap(5);
        gridPaneNElement.setVgap(5);
        ColumnConstraints labelColumn = new ColumnConstraints(150);
        ColumnConstraints nColumn = new ColumnConstraints();
        nColumn.setHgrow(Priority.ALWAYS);
        gridPaneNElement.getColumnConstraints().addAll(labelColumn, nColumn);

        Label Nlabel = new Label("Введите N");
        TextField NtextField = new TextField();
        Button btnStart = new Button("OK");
        btnStart.setMinWidth(70);
        btnStart.setMinHeight(30);

        GridPane.setHalignment(Nlabel, HPos.RIGHT);
        gridPaneNElement.add(Nlabel, 0, 0);

        GridPane.setHalignment(NtextField, HPos.LEFT);
        gridPaneNElement.add(NtextField, 1, 0);

        GridPane.setHalignment(btnStart, HPos.LEFT);
        gridPaneNElement.add(btnStart, 2, 0);

        btnOkHandler(btnStart, NtextField);

        AnchorPane anchor = new AnchorPane();
        anchor.getChildren().add(gridPaneNElement);
        anchor.setRightAnchor(gridPaneNElement, 5.0);
        anchor.setLeftAnchor(gridPaneNElement, 5.0);
        anchor.setTopAnchor(gridPaneNElement, 5.0);
        anchor.setBottomAnchor(gridPaneNElement, 5.0);
        root.setTop(anchor);
        Scene scene = new Scene(root, Color.WHITE);

        //handlerNtextField(NtextField);

        newElementStage.initModality(Modality.WINDOW_MODAL);
        newElementStage.initOwner(primaryStage);
        newElementStage.setTitle(title);
        newElementStage.setScene(scene);
        newElementStage.show();
    }

    private void btnOkHandler(Button btn, TextField field) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String fielText = field.getText();
                if (fielText != null && !fielText.equals("")) {
                    if (isInteger(fielText)) {
                        setN(Integer.parseInt(fielText));
                        if (n > 3 && n < 25) {
                            //Изменить размеры сцены
                            newElementStage.setWidth(n * 80 + 150);
                            newElementStage.setHeight(255);
                            //переместить окно на центр экрана
                            newElementStage.setX(width / 3);
                            newElementStage.setY(height / 3);
                            //Инициализация gridPane
                            gridOfMassive = generateDataGrid();
                            //сделать видимым gridPane
                            gridOfMassive.setVisible(true);
                            root.setCenter(gridOfMassive);
                            field.setStyle("-fx-border-color: green");
                        } else {
                            if (gridOfMassive != null) {
                                gridOfMassive.setVisible(false);
                            }
                            field.setStyle("-fx-border-color: red");
                        }
                    } else {
                        if (gridOfMassive != null) {
                            gridOfMassive.setVisible(false);
                        }
                        field.setStyle("-fx-border-color: red");
                    }
                }
            }
        });
    }

    /**
     * Функция проверка является ли строка числом
     *
     * @param str строка
     * @return результат является ли строка числом
     */
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
        if (n <= 3) {
            return null;
        }
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(5);
        gridpane.setVgap(5);
        ColumnConstraints column1 = new ColumnConstraints(150);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHgrow(Priority.ALWAYS);
        gridpane.getColumnConstraints().addAll(column1, column2);

        Button saveButt = new Button("Розв'язати");
        saveButt.setMinHeight(30);
        saveButt.setMinWidth(90);

        //Генерация Hbox для 4 элементов
        generateHbox(0, gridpane, "Витрати на доставку", valuesA);
        generateHbox(1, gridpane, "Витрати на зберігання", valuesH);
        generateHbox(2, gridpane, "Попит", valuesD);
        generateHbox(3, gridpane, "Витрати від попиту", valuesC);

        // Кнопка подсчета
        GridPane.setHalignment(saveButt, HPos.RIGHT);

        // Ивент на нажатие кнопки решения
        saveButt.setOnMouseClicked(event -> {
            //проверка на заполненность всех данных
            if (!checkAnswer()) {
                JavaFxSimpleAlert.ValuesError();
            } else {
                //генерировать по заданным числам ответ
                StartFrame.controller.dataOutput(MapToMassive(valuesH), MapToMassive(valuesD), MapToMassive(valuesA), MapToMassive(valuesC), n);
                //закрыть фрейм
                WindowsClose();
            }
        });
        //добавить в ячейку [4,1] кнопку
        gridpane.add(saveButt, 1, 4);
        return gridpane;
    }

    /**
     * Функция генерации hbox
     *
     * @param index - индекс ячейки в которую производить вставку
     * @param pane  - грид в который производится вставка
     * @param title - навзвание поля
     * @param map   - карта в которую производится запись текстового поля
     */
    private void generateHbox(int index, GridPane pane, String title, Map<Integer, TextField> map) {
        HBox hb = new HBox();
        Label label = new Label(title);
        GridPane.setHalignment(label, HPos.RIGHT);
        pane.add(label, 0, index);
        for (int i = 0; i < n; i++) {
            TextField tx = new TextField();
            tx.setMinWidth(50);
            tx.setMaxWidth(Double.MAX_VALUE);
            //добавить хендел на изминение цвета и максимальную длину текстового поля
            addTextLimiter(tx, 3);
            map.put(i, tx);
            hb.getChildren().add(map.get(i));
        }
        GridPane.setHalignment(hb, HPos.LEFT);
        pane.add(hb, 1, index);
    }

    /**
     * Инент на внесение цифры в тектовое поле, если не верно цвет красный, проверяет максимальное к-во символов в тектовом поле
     *
     * @param field     - тектовое поле
     * @param maxLength - максимальная длина текстового поля
     */
    private void addTextLimiter(final TextField field, final int maxLength) {
        field.textProperty().addListener((ov, oldValue, newValue) -> {
            if (!isInteger(field.getText())) {
                field.setStyle("-fx-border-color: red");
            } else {
                field.setStyle("-fx-border-color: green");
            }
            if (field.getText().length() > maxLength) {
                String s = field.getText().substring(0, maxLength);
                field.setText(s);
            }
        });
    }

    /**
     * Проверка на заполнение всех полей
     *
     * @return результат проверки
     */
    private boolean checkAnswer() {
        for (int i = 0; i < n; i++) {
            if (valuesA.get(i).getStyle().contains("red") || valuesA.get(i).getBorder() == null) {
                return false;
            } else if (valuesC.get(i).getStyle().contains("red") || valuesC.get(i).getBorder() == null) {
                return false;
            } else if (valuesD.get(i).getStyle().contains("red") || valuesD.get(i).getBorder() == null) {
                return false;
            } else if (valuesH.get(i).getStyle().contains("red") || valuesH.get(i).getBorder() == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Карта в массив
     *
     * @param map карта для преобразования
     * @return массив
     */
    public int[] MapToMassive(Map<Integer, TextField> map) {
        int[] mas = new int[map.size()];
        for (int i = 0; i < map.size(); i++) {
            mas[i] = Integer.parseInt(map.get(i).getText().trim());
        }
        return mas;
    }

    private void WindowsClose() {
        newElementStage.close();
    }

    public void setN(int n) {
        this.n = n;
    }
}
