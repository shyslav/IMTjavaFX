package com.shyslav.controllers;

import com.shyslav.controllers.alerts.JavaFxSimpleAlert;
import com.sukhaniuk.func.PDFSave;
import com.sukhaniuk.func.ReadFromFile;
import com.sukhaniuk.func.TXTSave;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;
import javafx.stage.WindowEvent;
import starter.StartFrame;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;

public class IMTMainPageController {
    public TabPane IMTTabPane;

    @FXML
    private MenuItem Help;

    @FXML
    private IMTTabController IMTStandardTabController;

    @FXML
    private IMTTabController IMTNoStandardTabController;

    private final ArrayList<IMTTabController> controllers = new ArrayList<>();

    @FXML
    private void initialize() {
        controllers.add(IMTStandardTabController);
        controllers.add(IMTNoStandardTabController);
        Help.setAccelerator(KeyCombination.keyCombination("F1"));
    }

    public void initializeArrayOfTabs(int[] h, int[] d, int[] A, int[] C, int n) {
//        IMTTabPane.getTabs().clear();
//        for (int i = 0; i < n; i++) {
//            final Tab tab = new Tab("Tab ");
//            IMTTabPane.getTabs().add(tab);
//        }
    }

    /**
     * Функция заполнения данных
     *
     * @param h - затраты на хранение
     * @param d - спрос на продукцию
     * @param A - затраты на перевозку
     * @param C - стоимость в зависимости от обьема
     * @param n - количество элементов в массиве
     */
    public void dataOutput(int[] h, int[] d, int[] A, int[] C, int n) {
        clearDataStackedBarChart();
        IMTStandardTabController.dataOutput(h, d, A, n);
        IMTNoStandardTabController.dataOutput(h, d, A, C, n);
    }

    /**
     * Функция очистки даты графиков
     */
    private void clearDataStackedBarChart() {
        IMTStandardTabController.clearDataStackedBarChart();
        IMTNoStandardTabController.clearDataStackedBarChart();
    }

    /**
     * Ивент на нажатие в меня "Ввести значения"
     *
     * @param event
     */
    public void newMenuItemGenerateDataToIMT(ActionEvent event) {
        StartFrame.newElementAdd();
    }

    /**
     * Ивен на нажатие в меню "Считать с файла"
     *
     * @param event
     */
    public void readFromFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        ReadFromFile.configureFileChooser(fileChooser, "Обрати файл");
        //добавить розширения допустимых файлов
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("txt", "*.txt"));
        File file = fileChooser.showOpenDialog(StartFrame.getPrimaryStage());
        if (file != null) {
            ReadFromFile.read(file.getPath());
        }
    }

    /**
     * Ивент на нажатие в меню "Выход"
     *
     * @param event
     */
    public void сloseProgramm(ActionEvent event) {
        StartFrame.getPrimaryStage().getOnCloseRequest()
                .handle(
                        new WindowEvent(
                                StartFrame.getPrimaryStage(),
                                WindowEvent.WINDOW_CLOSE_REQUEST
                        )
                );
    }

    /**
     * Функция сохранения данных в тхт
     *
     * @param event
     */
    public void saveInitializeData(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        ReadFromFile.configureFileChooser(fileChooser, "Збереження значень в TXT");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(StartFrame.getPrimaryStage());
        StringBuilder sb = new StringBuilder();
        for (IMTTabController controller : controllers) {
            if (controller.getStandart() != null) {
                String s = TXTSave.generateDataToTxt(controller.getStandart().getH(), controller.getStandart().getD(), controller.getStandart().getA(), null);
                sb.append(s);
            } else if (controller.getNoStandart() != null) {
                String s = TXTSave.generateDataToTxt(controller.getNoStandart().getH(), controller.getNoStandart().getD(), controller.getNoStandart().getA(), controller.getNoStandart().getC());
                sb.append(s);
            } else {
                JavaFxSimpleAlert.SaveError();
            }
        }
        if (file != null) {
            TXTSave.SaveVariablesToTXT(sb.toString(), file);
        }
    }

    /**
     * Функция сохранения решения в тхт
     *
     * @param event
     */
    public void saveToTXT(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        ReadFromFile.configureFileChooser(fileChooser, "Збереження в TXT");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(StartFrame.getPrimaryStage());
        StringBuilder sb = new StringBuilder();
        for (IMTTabController controller : controllers) {
            if (controller.getStandart() != null) {
                String s = TXTSave.generateFormulaAnswer(controller.getImtArrayList(), controller.getStandart().getH(), controller.getStandart().getD(), controller.getStandart().getA(), null, controller.getStandart().getX());
                sb.append(s);
            } else if (controller.getNoStandart() != null) {
                String s = TXTSave.generateFormulaAnswer(controller.getImtArrayList(), controller.getNoStandart().getH(), controller.getNoStandart().getD(), controller.getNoStandart().getA(), controller.getNoStandart().getC(), controller.getNoStandart().getX());
                sb.append(s);
            } else {
                JavaFxSimpleAlert.SaveError();
            }
        }
        TXTSave.SaveVariablesToTXT(sb.toString(), file);
    }

    /**
     * Функция выбора директории где сохранить пдф
     *
     * @param event
     */
    public void saveToPDF(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        ReadFromFile.configureFileChooser(fileChooser, "Збереження в PDF");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(StartFrame.getPrimaryStage());

        ArrayList<ByteArrayInputStream> inputStreams = new ArrayList<>();
        for (IMTTabController controller : controllers) {
            if (controller.getStandart() != null) {
                ByteArrayInputStream byteArrayInputStream = PDFSave.generateHTMLTableView(controller.getImtArrayList(), controller.getStandart().getX(),
                        controller.getStandart().getH(), controller.getStandart().getD(), controller.getStandart().getA(), null);
                inputStreams.add(byteArrayInputStream);
            } else if (controller.getNoStandart() != null) {
                ByteArrayInputStream byteArrayInputStream = PDFSave.generateHTMLTableView(controller.getImtArrayList(), controller.getNoStandart().getX(),
                        controller.getNoStandart().getH(), controller.getNoStandart().getD(), controller.getNoStandart().getA(), controller.getNoStandart().getC());
                inputStreams.add(byteArrayInputStream);
            } else {
                JavaFxSimpleAlert.SaveError();
            }
        }
        PDFSave.writeIMTtoPDFFile(inputStreams, file);
    }

    public void aboutProgramMenuClick(ActionEvent event) {
        StartFrame.startHelp();
    }
}
