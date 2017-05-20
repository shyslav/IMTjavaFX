package com.shyslav.controllers;

import com.shyslav.func.IMT;
import com.shyslav.func.IMTAlgoNoStandart;
import com.shyslav.func.IMTAlgoStandart;
import com.sukhaniuk.charts.BarChartTmp;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

/**
 * @author Shyshkin Vladyslav on 19.05.17.
 */
public class IMTTabController {
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

    private String tabName;

    private IMTAlgoStandart standart;
    private IMTAlgoNoStandart noStandart;
    private ArrayList<IMT> imtArrayList;

    /**
     * Calc by standard algorithm
     *
     * @param h - затраты на хранение
     * @param d - спрос на продукцию
     * @param A - затраты на перевозку
     * @param n - количество элементов в массиве
     */
    public void dataOutput(int[] h, int[] d, int[] A, int n) {
        standart = new IMTAlgoStandart(d, A, h, n);
        //заполнить лист стандартного алгоритма
        imtArrayList = new ArrayList(standart.run());

        if (imtArrayList.size() != 0) {
            //заполнить таблицу
            tableStandartInitialize();
            //Заполнить stackedBarChart данными из стандартного алгоритма
            stackedBarChartbarChart = BarChartTmp.generateStackedChart(stackedBarChartbarChart, imtArrayList);
            stackedBarChartbarChartT1 = BarChartTmp.generateBarChart(stackedBarChartbarChartT1, imtArrayList, standart.getX());
        }
    }

    /**
     * Calc by no standard algorithm
     *
     * @param h - затраты на хранение
     * @param d - спрос на продукцию
     * @param A - затраты на перевозку
     * @param C - стоимость в зависимости от обьема
     * @param n - количество элементов в массиве
     */
    public void dataOutput(int[] h, int[] d, int[] A, int[] C, int n) {
        noStandart = new IMTAlgoNoStandart(d, A, h, C, n);
        imtArrayList = new ArrayList(noStandart.run());
        if (imtArrayList.size() != 0) {
            tableStandartInitialize();
            //Заполнить stackedBarChart данными из стандартного алгоритма
            stackedBarChartbarChart = BarChartTmp.generateStackedChart(stackedBarChartbarChart, imtArrayList);
            stackedBarChartbarChartT1 = BarChartTmp.generateBarChart(stackedBarChartbarChartT1, imtArrayList, noStandart.getX());
        }

    }

    /**
     * Функция очистки графиков
     */
    public void clearDataStackedBarChart() {
        stackedBarChartbarChart.getData().clear();
        stackedBarChartbarChartT1.getData().clear();
    }

    /**
     * Инициализация таблицы стандатртного алогоритма
     */
    public void tableStandartInitialize() {
        StepsK.setCellValueFactory(new PropertyValueFactory<>("k"));
        StepsJ.setCellValueFactory(new PropertyValueFactory<>("j"));
        Count.setCellValueFactory(new PropertyValueFactory<>("formula"));
        optimumL.setCellValueFactory(new PropertyValueFactory<>("l"));
        optimumFk.setCellValueFactory(new PropertyValueFactory<>("sum"));
        table.setItems(FXCollections.observableList(imtArrayList));
    }

    public IMTAlgoStandart getStandart() {
        return standart;
    }

    public IMTAlgoNoStandart getNoStandart() {
        return noStandart;
    }

    public ArrayList<IMT> getImtArrayList() {
        return imtArrayList;
    }


    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getTabName() {
        return tabName;
    }
}
