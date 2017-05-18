package starter;

import com.shyslav.controllers.Controller;
import com.shyslav.form.newElementForm;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Optional;

public class StartFrame extends Application {
    private static Stage primaryStage;
    public static Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        handlerExit();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(StartFrame.class.getResource("/fxml/mainframe.fxml"));
        VBox root = loader.load();
        primaryStage.setTitle("Задача Управления Запасами");
        primaryStage.setScene(new Scene(root, 640, 400));
        primaryStage.show();
        controller = loader.getController();
    }

    private void handlerExit() {
        primaryStage.setOnCloseRequest((WindowEvent we) ->
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Подтверждение");
            alert.setHeaderText("Вы дейстивительно хотите выйти из программы?");
            Optional<ButtonType> closeResponse = alert.showAndWait();
            if (!ButtonType.OK.equals(closeResponse.get())) {
                we.consume();
            } else {
                System.exit(0);
            }
        });
    }

    public static void startHelp() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(StartFrame.class.getResource("/fxml/helpFrame.fxml"));
        try {
            SplitPane root = loader.load();
            Stage st = new Stage();
            st.setResizable(false);
            st.setScene(new Scene(root, 600, 400));
            st.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void newElementAdd() {
        newElementForm newElement = new newElementForm("Ввести данні", primaryStage);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
