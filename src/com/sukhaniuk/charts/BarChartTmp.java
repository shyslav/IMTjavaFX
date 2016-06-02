package com.sukhaniuk.charts;
import com.shyslav.func.IMT;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.*;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by Shyshkin Vladyslav on 31.05.2016.
 */
public class BarChartTmp {
    private static BarChartTmp bars = new BarChartTmp();
    public static StackedBarChart generateStackedChart(StackedBarChart chart, ArrayList<IMT> imt)
    {
        int counter = 0;
        chart.setTitle("Ватість розв'язку");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        ArrayList<String> value = new ArrayList<>();
        for (int i = 0; i < imt.size();i++)
        {
            value.add(String.valueOf(imt.get(i).getK()));
        }
        xAxis.setCategories(FXCollections.<String>observableArrayList(value));
        yAxis.setLabel("Номер шага");
        xAxis.setLabel("Значение");
        for (int i = 0 ; i < imt.size();i++)
        {
            for (int j = 0; j < imt.get(i).getJList().size();j++)
            {
                counter++;
                final XYChart.Series<String, Number> series =
                        new XYChart.Series<String, Number>();
                series.setName(String.valueOf(IMT.GetFormulaValue(imt.get(i).getFormulaList().get(j))));
                series.getData().add(new XYChart.Data<String, Number>(String.valueOf(imt.get(i).getK()), IMT.GetFormulaValue(imt.get(i).getFormulaList().get(j))));
                chart.getData().addAll(series);
            }
        }

        bars.handler(counter,chart,"Шаг ");
        return chart;
    }

    public static StackedBarChart generateBarChart(StackedBarChart chart, ArrayList<IMT> imt , int [] x)
    {
        chart.setTitle("Об'єми поставок");
        for (int i = 1; i <= x.length;i++)
        {
                XYChart.Series series = new XYChart.Series();
                series.getData().add(new XYChart.Data(String.valueOf(i), x[i - 1]));
                series.setName("X"+i);
                chart.getData().add(series);
        }
        bars.handler(x.length,chart, "X");
        return chart;
    }

    private void handler(int length, StackedBarChart chart, String name)
    {
        chart.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                for (int i = 0 ; i < chart.getData().size();i++) {
                    ObservableList<XYChart.Data> dataList = ((XYChart.Series) chart.getData().get(i)).getData();
                    dataList.forEach(data -> {
                        Node node = data.getNode();
                        Tooltip tooltip = new Tooltip('(' +name+ data.getXValue().toString() + '=' + data.getYValue().toString() + ')');
                        Tooltip.install(node, tooltip);
                    });
                }
            }
        });
    }

}
