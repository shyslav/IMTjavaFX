package com.shyslav.controllers;

import com.shyslav.controllers.alerts.SampleAlert;
import com.shyslav.func.IMT;
import com.shyslav.func.IMTAlgoNoStandart;
import com.shyslav.func.IMTAlgoStandart;
import com.sukhaniuk.charts.LineChartTmp;
import com.sukhaniuk.func.fileSave;
import com.sukhaniuk.func.readFromFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.sukhaniuk.charts.BarChartTmp;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import starter.StartFrame;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

public class Controller {
    //Элементы стандартного алгоритма

    @FXML
    private TableView table;
    @FXML
    private TableColumn StepsK;
    @FXML
    private TableColumn StepsJ;
    @FXML
    private TableColumn Count;
    @FXML
    private TableColumn optimumL;
    @FXML
    private TableColumn optimumFk;
    @FXML
    private StackedBarChart stackedBarChartbarChart;
    @FXML
    private StackedBarChart stackedBarChartbarChartT1;

    //Элементы не стандартного алгоритма

    @FXML
    private TableView tableNoStandart;
    @FXML
    private TableColumn StepsKNoStandart;
    @FXML
    private TableColumn StepsJNoStandart;
    @FXML
    private TableColumn CountNoStandart;
    @FXML
    private TableColumn optimumLNoStandart;
    @FXML
    private TableColumn optimumFkNoStandart;
    @FXML
    private StackedBarChart stackedBarChartbarChartNoStandart;
    @FXML
    private StackedBarChart stackedBarChartbarChartNoStandartT1;
    @FXML
    private LineChart lineChart;
    private IMTAlgoStandart standart;
    private IMTAlgoNoStandart noStandart;
    private ArrayList<IMT> imtStandartList;
    private ArrayList<IMT> imtNoStandartList;

    @FXML
    private void initialize() {

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
        standart = new IMTAlgoStandart(d, A, h, n);
        //заполнить лист стандартного алгоритма
        imtStandartList = new ArrayList(standart.run());

        if (imtStandartList.size() != 0) {
            //заполнить таблицу
            tableStandartInitialize();
            //Заполнить stackedBarChart данными из стандартного алгоритма
            stackedBarChartbarChart = BarChartTmp.generateStackedChart(stackedBarChartbarChart, imtStandartList);
            stackedBarChartbarChartT1 = BarChartTmp.generateBarChart(stackedBarChartbarChartT1, imtStandartList, standart.getX());
        }

        noStandart = new IMTAlgoNoStandart(d, A, h, C, n);
        imtNoStandartList = new ArrayList(noStandart.run());
        if (imtNoStandartList.size() != 0) {
            tableNoStandartInitialize();
            //Заполнить stackedBarChart данными из стандартного алгоритма
            stackedBarChartbarChartNoStandart = BarChartTmp.generateStackedChart(stackedBarChartbarChartNoStandart, imtNoStandartList);
            stackedBarChartbarChartNoStandartT1 = BarChartTmp.generateBarChart(stackedBarChartbarChartNoStandartT1, imtNoStandartList, noStandart.getX());
        }
        lineChart = LineChartTmp.generate(lineChart, standart.getX(), noStandart.getX());
    }

    /**
     * Функция очистки даты графиков
     */
    private void clearDataStackedBarChart() {
        lineChart.getData().clear();
        stackedBarChartbarChart.getData().clear();
        stackedBarChartbarChartT1.getData().clear();
        stackedBarChartbarChartNoStandart.getData().clear();
        stackedBarChartbarChartNoStandartT1.getData().clear();
    }

    /**
     * Инициализация таблицы стандатртного алогоритма
     */
    private void tableStandartInitialize() {
        StepsK.setCellValueFactory(new PropertyValueFactory<>("k"));
        StepsJ.setCellValueFactory(new PropertyValueFactory<>("j"));
        Count.setCellValueFactory(new PropertyValueFactory<>("formula"));
        optimumL.setCellValueFactory(new PropertyValueFactory<>("l"));
        optimumFk.setCellValueFactory(new PropertyValueFactory<>("sum"));
        table.setItems(FXCollections.observableList(imtStandartList));
    }

    /**
     * Инициализация табилцы дополнительного алгоритма
     */
    private void tableNoStandartInitialize() {
        StepsKNoStandart.setCellValueFactory(new PropertyValueFactory<>("k"));
        StepsJNoStandart.setCellValueFactory(new PropertyValueFactory<>("j"));
        CountNoStandart.setCellValueFactory(new PropertyValueFactory<>("formula"));
        optimumLNoStandart.setCellValueFactory(new PropertyValueFactory<>("l"));
        optimumFkNoStandart.setCellValueFactory(new PropertyValueFactory<>("sum"));
        tableNoStandart.setItems(FXCollections.observableList(imtNoStandartList));
    }

    /**
     * Ивент на нажатие в меня "Ввести значения"
     * @param event
     */
    public void newMenuItemGenerateDataToIMT(ActionEvent event) {
        StartFrame.newElementAdd();
    }

    /**
     * Ивен на нажатие в меню "Считать с файла"
     * @param event
     */
    public void readFromFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        readFromFile.configureFileChooser(fileChooser, "Обрати файл");
        File file = fileChooser.showOpenDialog(StartFrame.getPrimaryStage());
        if (file != null) {
            readFromFile.read(file.getPath());
        }
    }

    /**
     * Ивент на нажатие в меню "Выход"
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
     * @param event
     */
    public void saveInitializeData(ActionEvent event) {
        if(noStandart==null)
        {
            SampleAlert.SaveError();
            return;
         }
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(StartFrame.getPrimaryStage());
        if(file != null){
            fileSave.SaveVariablesToTXT(fileSave.generateDataToTxt(noStandart.getH(),noStandart.getD(),noStandart.getA(),noStandart.getC()), file);
        }
    }

    /**
     * Функция сохранения решения в тхт
     * @param event
     */
    public void saveToTXT(ActionEvent event) {
        if(noStandart==null)
        {
            SampleAlert.SaveError();
            return;
        }
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(StartFrame.getPrimaryStage());
        if(file != null){
            fileSave.SaveVariablesToTXT(fileSave.generateFormulaAnswer(imtStandartList,imtNoStandartList,standart.getH(),standart.getD(),noStandart.getA(),noStandart.getC(),standart.getX(),noStandart.getX()), file);
        }
    }

    public void saveToPDF(ActionEvent event) {
        if(noStandart==null)
        {
            SampleAlert.SaveError();
            return;
        }
        fileSave.generateHTMLTableView(imtStandartList,imtNoStandartList,standart.getX(),noStandart.getX(),
                noStandart.getH(),noStandart.getD(),noStandart.getA(),noStandart.getC());
    }
}
