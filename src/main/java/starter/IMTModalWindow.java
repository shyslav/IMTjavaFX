package starter;

import appmodels.IMTDataList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static starter.StartFrame.loadIMTmainFrame;

/**
 * @author Shyshkin Vladyslav on 18.05.17.
 */
public class IMTModalWindow {
    private final Stage stage;
    private final Stage primaryStage;
    private final IMTDataList dataList;

    private final int h;
    private final int a;

    public IMTModalWindow(Stage primaryStage, IMTDataList dataList, int h, int a) throws IOException {
        this.primaryStage = primaryStage;
        this.stage = new Stage();
        this.dataList = dataList;
        this.a = a;
        this.h = h;
        start();
    }

    private void start() throws IOException {
        int n = 7;
        int[] valuesD = new int[n];
        for (int i = 0; i < dataList.size(); i++) {
            valuesD[i] = dataList.get(0).getCount();
        }
        int[] valueH = new int[n];
        for (int i = 0; i < n; i++) {
            valueH[i] = h;
        }
        int[] valueA = new int[n];
        for (int i = 0; i < n; i++) {
            valueA[i] = a;
        }

        int[] valueC = new int[n];

        FXMLLoader loader = loadIMTmainFrame();
        VBox vBox = loader.load();
        StartFrame.controller = loader.getController();

        StartFrame.controller.initializeArrayOfTabs(valueH, valuesD, valueA, valueC, n);

        //show scene
        Scene scene = new Scene(vBox);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);
        stage.setResizable(true);
        stage.setTitle("IMT algorithm");
        stage.setScene(scene);
        stage.show();

        StartFrame.controller.dataOutput(valueH, valuesD, valueA, valueC, n);

    }
}
