package com.shyslav.controllers;

import com.shyslav.func.IMT;
import com.shyslav.func.IMTStartAlgo;
import com.shyslav.func._IMTStartAlgo;
import com.sukhaniuk.func.readFromFile;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.sukhaniuk.charts.BarChartTmp;
import javafx.stage.FileChooser;
import starter.StartFrame;

import java.io.File;
import java.util.ArrayList;

public class Controller {
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
//    @FXML
//    private LineChart lineChart;
    private ArrayList<IMT> imt;
    @FXML
    private void initialize()
    {

    }
    public void dataOutput(int [] h, int [] d, int [] A, int n)
    {
        stackedBarChartbarChart.getData().clear();
        stackedBarChartbarChartT1.getData().clear();
        _IMTStartAlgo main = new _IMTStartAlgo(d,A,h,n);
        imt = new ArrayList(main.run());
        if(imt.size()!=0) {
            tableInitialize();
            stackedBarChartbarChart = BarChartTmp.generateStackedChart(stackedBarChartbarChart, imt);
            stackedBarChartbarChartT1 = BarChartTmp.generateBarChart(stackedBarChartbarChartT1, imt,main.getX());
//            lineChart = LineChartTmp.generate(lineChart, imt, main.getX());
//            lineChartEventHandler();
        }
    }
//    private void lineChartEventHandler()
//    {
//        lineChart.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                ObservableList<XYChart.Data> dataList = ((XYChart.Series) lineChart.getData().get(0)).getData();
//                dataList.forEach(data->{
//                    Node node = data.getNode();
//                    int xValue = Integer.parseInt(data.getXValue().toString());
//                    int yValue = Integer.parseInt(data.getYValue().toString()) -1;
//                    Tooltip tooltip = new Tooltip('(' +"X"+String.valueOf(xValue)+'='+String.valueOf(yValue)+')');
//                    Tooltip.install(node, tooltip);
//                });
//            }
//        });
//    }
    private void tableInitialize() {
        StepsK.setCellValueFactory(new PropertyValueFactory<>("k"));
        StepsJ.setCellValueFactory(new PropertyValueFactory<>("j"));
        Count.setCellValueFactory(new PropertyValueFactory<>("formula"));
        optimumL.setCellValueFactory(new PropertyValueFactory<>("l"));
        optimumFk.setCellValueFactory(new PropertyValueFactory<>("sum"));
        table.setItems(FXCollections.observableList(imt));
    }

    public void newMenuItemGenerateDataToIMT(ActionEvent event) {
        System.out.println("lalal");
        StartFrame.newElementAdd();
    }

    public void readFromFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        readFromFile.configureFileChooser(fileChooser,"Обрати файл");
        File file = fileChooser.showOpenDialog(StartFrame.getPrimaryStage());
        if (file != null) {
            readFromFile.read(file.getPath());
        }
    }
}
