package starter;

import com.shyslav.models.IMTRequestList;
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
    private final IMTRequestList requestList;

    public IMTModalWindow(Stage primaryStage, IMTRequestList requestList) throws IOException {
        this.primaryStage = primaryStage;
        this.stage = new Stage();
        this.requestList = requestList;
        start();
    }

    private void start() throws IOException {
        FXMLLoader loader = loadIMTmainFrame();
        VBox vBox = loader.load();
        StartFrame.controller = loader.getController();

        //show scene
        Scene scene = new Scene(vBox);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);
        stage.setResizable(true);
        stage.setTitle("IMT algorithm");
        stage.setScene(scene);
        stage.show();

        StartFrame.controller.initializeArrayOfTabs(requestList);
    }
}
