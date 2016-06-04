package starter;

import com.shyslav.controllers.Controller;
import com.shyslav.form.newElementForm;
import com.sukhaniuk.fxml.fxmlLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Optional;

public class StartFrame extends Application {
    private static Stage primaryStage;
    public static Controller controller;
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage=primaryStage;
        handlerExit();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(fxmlLoader.class.getResource("mainFrame.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Задача Управления Запасами");
        primaryStage.setScene(new Scene(root, 640, 400));
        primaryStage.show();
        controller = loader.getController();
    }
    private void handlerExit()
    {
        primaryStage.setOnCloseRequest((WindowEvent we) ->
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Подтверждение");
            alert.setHeaderText("Вы дейстивительно хотите выйти из программы?");
            Optional<ButtonType> closeResponse = alert.showAndWait();
            if (!ButtonType.OK.equals(closeResponse.get())) {
                we.consume();
            }else
            {
                System.exit(0);
            }
        });
    }
    public static void newElementAdd()
    {
        newElementForm newElement = new newElementForm("Ввести данні",primaryStage);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
