package com.sukhaniuk.charts;
import com.shyslav.func.IMT;
import data.DataUpdate;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 31.05.2016.
 */
public class LineChartTmp {
    private static LineChartTmp lineChartTmp = new LineChartTmp();
    public static LineChart generate(LineChart chart, int [] xStandart, int [] xNoStandart)
    {
        XYChart.Series seriesStandart = new XYChart.Series();
        seriesStandart.setName("Задача управління запасами");
        XYChart.Series seriesNoStandart = new XYChart.Series();
        seriesNoStandart.setName("Ускладнена задача управління запасами");
        for (int i = 1; i <= xStandart.length;i++)
        {
            seriesStandart.getData().add(new XYChart.Data(i, xStandart[i-1]+1));
            seriesNoStandart.getData().add(new XYChart.Data(i, xNoStandart[i-1]+1));
        }
        //chart.getStylesheets().add(DataUpdate.class.getResource("css/lineChart.css").toExternalForm());
        chart.getData().addAll(seriesStandart,seriesNoStandart);
        lineChartTmp.lineChartEventHandler(chart);
        return chart;
    }
    private void lineChartEventHandler(LineChart lineChart)
    {
        lineChart.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                for (int i = 0; i < lineChart.getData().size();i++ ) {
                    ObservableList<XYChart.Data> dataList = ((XYChart.Series) lineChart.getData().get(i)).getData();
                    dataList.forEach(data -> {
                        Node node = data.getNode();
                        int xValue = Integer.parseInt(data.getXValue().toString());
                        int yValue = Integer.parseInt(data.getYValue().toString()) - 1;
                        Tooltip tooltip = new Tooltip('(' + "X" + String.valueOf(xValue) + '=' + String.valueOf(yValue) + ')');
                        Tooltip.install(node, tooltip);
                    });
                }
            }
        });
    }
}
