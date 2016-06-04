package com.shyslav.controllers.alerts;

import javafx.scene.control.Alert;

public class SampleAlert {
    public SampleAlert(String title,String description, String content,Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(description);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public static void ValuesError()
    {
        SampleAlert sa = new SampleAlert("Ошибка ввода",
                "Проверьте правильность введенных данных","Все поля должны быть заполненны правильными данными, правильные данные отображаются зеленым цветом", Alert.AlertType.ERROR);
    }
    public static void SaveError()
    {
        SampleAlert sa = new SampleAlert("Ошибка сохранения","Нечего сохранять","Для выполнения этого действия нужно ввести значения", Alert.AlertType.ERROR);
    }
    public static void ConnectionError()
    {
        SampleAlert sa = new SampleAlert("Ошибка","Сервер не отвечает, попробуйте позже","Обратитесь к администратору или разработчикам", Alert.AlertType.ERROR);
        System.exit(0);
    }
}
