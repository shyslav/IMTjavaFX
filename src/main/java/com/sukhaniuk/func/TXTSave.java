package com.sukhaniuk.func;

import com.shyslav.controllers.alerts.JavaFxSimpleAlert;
import com.shyslav.func.IMT;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Shyshkin Vladyslav on 05.06.2016.
 */
public class TXTSave {
    /**
     * Функция записи файла
     *
     * @param content - строка которую нужно записать в файл
     * @param file    - файл который нужно записать на диск
     */
    public static void SaveVariablesToTXT(String content, File file) {
        try {
            FileWriter fileWriter = null;
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            JavaFxSimpleAlert sa = new JavaFxSimpleAlert("Ошибка записи", "Файл не удалось записать", ex.toString(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Генерировать ответ в txt
     *
     * @param imtAnswerArray - лист усложненного ЗУЗ
     * @param h              - затраты на хранение
     * @param d              - спрос
     * @param A              - затраты на доставку
     * @param C              - затраты от спроса
     * @param X              - массив ответов усложненного ЗУЗ
     * @return - строка для записи в тхт
     */
    public static String generateFormulaAnswer(ArrayList<IMT> imtAnswerArray, int[] h, int[] d, int[] A, int[] C, int[] X) {
        StringBuilder answer = new StringBuilder();
        String incomeData = generateDataToTxt(h, d, A, C);
        answer.append("\n Початкові значення: \n").append(incomeData);
        if (C == null) {
            answer.append("\n Задача управління запасами \n\n");
            for (int i = 0; i < imtAnswerArray.size(); i++) {
                answer.append("Крок ").append(i + 1).append("\n");
                answer.append(imtAnswerArray.get(i).getFormula()).append("\n");
            }
            answer.append("\nВідповідь: Мінімальні витрати z = ").append(imtAnswerArray.get(imtAnswerArray.size() - 1).getSum()).append("(од. вартості). Об'єми поставок: ").append(generateXanswer(X)).append("\n");
        } else {
            answer.append("\n Вдосконалена задача управління запасами \n\n");
            for (int i = 0; i < imtAnswerArray.size(); i++) {
                answer.append("Крок ").append(i + 1).append("\n");
                answer.append(imtAnswerArray.get(i).getFormula()).append("\n");
            }
            answer.append("\nВідповідь: Мінімальні витрати z = ").append(imtAnswerArray.get(imtAnswerArray.size() - 1).getSum()).append("(од. вартості). Об'єми поставок: ").append(generateXanswer(X)).append("\n");
        }
        return answer.toString();
    }

    /**
     * Функция вывода входящего массива
     *
     * @param h - затраты на хранение
     * @param d - спрос
     * @param A - затраты на доставку
     * @param C - затраты от спроса
     * @return - строка входящего массива
     */
    public static String generateDataToTxt(int[] h, int[] d, int[] A, int[] C) {
        String answer = "";
        answer += h.length + "\n";
        answer += "d:" + generateMass(d) + "\n";
        answer += "h:" + generateMass(h) + "\n";
        answer += "A:" + generateMass(A) + "\n";
        if (C != null) {
            answer += "C:" + generateMass(C) + "\n";
        }
        return answer;
    }

    /**
     * Генерировать стринговый массив с интового
     *
     * @param mas - интовый массив
     * @return - стринговый массив
     */
    private static String generateMass(int[] mas) {
        StringBuilder answ = new StringBuilder();
        for (int element : mas) {
            answ.append(element).append(" ");
        }
        return answ.toString();
    }

    /**
     * Генерация массива ответов
     *
     * @param x - массив ответов
     * @return - массив ответов в строке
     */
    public static String generateXanswer(int[] x) {
        StringBuilder answ = new StringBuilder();
        for (int i = 1; i <= x.length; i++) {
            answ.append("X[").append(i).append("] = ").append(x[i - 1]).append(" ");
        }
        return answ.toString();
    }
}
