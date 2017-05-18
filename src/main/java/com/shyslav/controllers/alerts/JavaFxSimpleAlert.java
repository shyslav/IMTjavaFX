package com.shyslav.controllers.alerts;

import javafx.scene.control.Alert;

public class JavaFxSimpleAlert {
    public JavaFxSimpleAlert(String title, String description, String content, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(description);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Функция вывода ошибки ввода неверных значений
     */
    public static void ValuesError()
    {
        JavaFxSimpleAlert sa = new JavaFxSimpleAlert("Ошибка ввода",
                "Проверьте правильность введенных данных","Все поля должны быть заполненны правильными данными, правильные данные отображаются зеленым цветом", Alert.AlertType.ERROR);
    }

    /**
     * Функция вывода ошибки сохранения
     */
    public static void SaveError()
    {
        JavaFxSimpleAlert sa = new JavaFxSimpleAlert("Ошибка сохранения","Нечего сохранять","Для выполнения этого действия нужно ввести значения", Alert.AlertType.ERROR);
    }
    /**
     * Функция вывода ошибки подключения
     */
    public static void ConnectionError()
    {
        JavaFxSimpleAlert sa = new JavaFxSimpleAlert("Ошибка","Сервер не отвечает, попробуйте позже","Обратитесь к администратору или разработчикам", Alert.AlertType.ERROR);
        System.exit(0);
    }
    /**
     * Функция вывода ошибки записи
     */
    public static void WriteError(Exception e)
    {
        JavaFxSimpleAlert sa = new JavaFxSimpleAlert("Ошибка записи","Файл не знайдений",e.toString(), Alert.AlertType.ERROR);
    }
    /**
     * Функция вывода ошибки нахождения файла
     */
    public static void FileNotFound(Exception e)
    {
        JavaFxSimpleAlert sa = new JavaFxSimpleAlert("Ошибка чтения","Файл не знайдений",e.toString(), Alert.AlertType.ERROR);
    }
    /**
     * Функция вывода ошибки пустых данных
     */
    public static void FileIO(Exception e)
    {
        JavaFxSimpleAlert sa = new JavaFxSimpleAlert("Ошибка","Вы пытаетесь записать пустые данные",e.toString(), Alert.AlertType.ERROR);
    }
    /**
     * Функция вывода ошибки количества переменных в тхт файле
     */
    public static void ReadFileErrorAmountVariables()
    {
        JavaFxSimpleAlert sa = new JavaFxSimpleAlert("Ошибка","Количество элементов не совпадает, проверьте ваш txt файл","Нажмите F1 для справки", Alert.AlertType.ERROR);
    }
    /**
     * Функция вывода ошибки ошибка синтаксиса txt файла
     */
    public static void ReadFileStructureError()
    {
        JavaFxSimpleAlert sa = new JavaFxSimpleAlert("Ошибка","Ошибка синтаксиса txt файла","Нажмите F1 для справки", Alert.AlertType.ERROR);
    }
}
