package starter;

import javafx.application.Application;
import javafx.collections.*;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.chart.*;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** Displays a LineChart which displays the value of a plotted Node when you hover over the Node. */
public class LineChartTest extends Application {



    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vBox = new VBox();
        LineChart chart = new LineChart(new NumberAxis(), new NumberAxis());
        chart.getStylesheets().add(StartFrame.class.getResource("lineChart.css").toExternalForm());
        chart.getData().add(new XYChart.Series<>("Temp1", FXCollections.observableArrayList(new XYChart.Data<Object, Object>(1, 1), new XYChart.Data<Object, Object>(2, 2), new XYChart.Data<Object, Object>(3, 4))));

        ObservableList<XYChart.Data> dataList = ((XYChart.Series) chart.getData().get(0)).getData();

        chart.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ObservableList<XYChart.Data> dataList = ((XYChart.Series) chart.getData().get(0)).getData();
                dataList.forEach(data->{
                    Node node = data.getNode();
                    Tooltip tooltip = new Tooltip('('+data.getXValue().toString()+';'+data.getYValue().toString()+')');
                    Tooltip.install(node, tooltip);
                });
            }
        });

        vBox.getChildren().add(chart);
        primaryStage.setScene(new Scene(vBox));
        primaryStage.show();
        ((XYChart.Series) chart.getData().get(0)).getData().addAll(new XYChart.Data<>(5, 5));
    }
}
