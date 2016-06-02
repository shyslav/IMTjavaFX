package com.sukhaniuk.charts;
import com.shyslav.func.IMT;
import data.DataUpdate;
import javafx.scene.chart.*;

import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 31.05.2016.
 */
public class LineChartTmp {
    public static LineChart generate(LineChart chart, ArrayList<IMT> imt , int [] x)
    {
        XYChart.Series series = new XYChart.Series();
        for (int i = 1; i <= x.length;i++)
        {
            series.getData().add(new XYChart.Data(i, x[i-1]+1));
        }
        chart.getStylesheets().add(DataUpdate.class.getResource("css/lineChart.css").toExternalForm());
        chart.getData().add(series);

        return chart;
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
}
