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

    /**
     * Функция вывода ошибки ввода неверных значений
     */
    public static void ValuesError()
    {
        SampleAlert sa = new SampleAlert("Ошибка ввода",
                "Проверьте правильность введенных данных","Все поля должны быть заполненны правильными данными, правильные данные отображаются зеленым цветом", Alert.AlertType.ERROR);
    }

    /**
     * Функция вывода ошибки сохранения
     */
    public static void SaveError()
    {
        SampleAlert sa = new SampleAlert("Ошибка сохранения","Нечего сохранять","Для выполнения этого действия нужно ввести значения", Alert.AlertType.ERROR);
    }
    /**
     * Функция вывода ошибки подключения
     */
    public static void ConnectionError()
    {
        SampleAlert sa = new SampleAlert("Ошибка","Сервер не отвечает, попробуйте позже","Обратитесь к администратору или разработчикам", Alert.AlertType.ERROR);
        System.exit(0);
    }
    /**
     * Функция вывода ошибки записи
     */
    public static void WriteError(Exception e)
    {
        SampleAlert sa = new SampleAlert("Ошибка записи","Файл не знайдений",e.toString(), Alert.AlertType.ERROR);
    }
    /**
     * Функция вывода ошибки нахождения файла
     */
    public static void FileNotFound(Exception e)
    {
        SampleAlert sa = new SampleAlert("Ошибка чтения","Файл не знайдений",e.toString(), Alert.AlertType.ERROR);
    }
    /**
     * Функция вывода ошибки пустых данных
     */
    public static void FileIO(Exception e)
    {
        SampleAlert sa = new SampleAlert("Ошибка","Вы пытаетесь записать пустые данные",e.toString(), Alert.AlertType.ERROR);
    }
    /**
     * Функция вывода ошибки количества переменных в тхт файле
     */
    public static void ReadFileErrorAmountVariables()
    {
        SampleAlert sa = new SampleAlert("Ошибка","Количество элементов не совпадает, проверьте ваш txt файл","Нажмите F1 для справки", Alert.AlertType.ERROR);
    }
    /**
     * Функция вывода ошибки ошибка синтаксиса txt файла
     */
    public static void ReadFileStructureError()
    {
        SampleAlert sa = new SampleAlert("Ошибка","Ошибка синтаксиса txt файла","Нажмите F1 для справки", Alert.AlertType.ERROR);
    }
}
