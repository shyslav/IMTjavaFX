package com.sukhaniuk.func;

import com.shyslav.controllers.alerts.SampleAlert;
import com.shyslav.func.IMT;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 05.06.2016.
 */
public class TXTSave {
    /**
     * Функция записи файла
     * @param content - строка которую нужно записать в файл
     * @param file - файл который нужно записать на диск
     */
    public static void SaveVariablesToTXT(String content, File file)
    {
        try {
            FileWriter fileWriter = null;
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            SampleAlert sa = new SampleAlert("Ошибка записи","Файл не удалось записать",ex.toString(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Генерировать ответ в txt
     * @param imtStandart - лист стандартного ЗУЗ
     * @param imtNoStandart - лист усложненного ЗУЗ
     * @param h - затраты на хранение
     * @param d - спрос
     * @param A - затраты на доставку
     * @param C - затраты от спроса
     * @param xStandart - массив ответов стандартного ЗУЗ
     * @param xNoStandart - массив ответов усложненного ЗУЗ
     * @return - строка для записи в тхт
     */
    public static String generateFormulaAnswer(ArrayList<IMT> imtStandart, ArrayList <IMT> imtNoStandart ,int[] h, int[] d, int[] A, int[] C, int [] xStandart, int [] xNoStandart)
    {
        String answer = generateDataToTxt(h,d,A,C);
        answer += "\n Задача управління запасами \n\n";
        for (int i = 0; i < imtStandart.size();i++) {
            answer+="Крок " + (i+1) +"\n";
            answer+=imtStandart.get(i).getFormula()+"\n";
        }
        answer += "\nВідповідь: Мінімальні витрати z = "+imtStandart.get(imtStandart.size()-1).getSum()+ "(од. вартості). Об'єми поставок: " + generateXanswer(xStandart) +"\n";

        answer += "\n Вдосконалена задача управління запасами \n\n";
        for (int i = 0; i < imtStandart.size();i++) {
            answer+="Крок " + (i+1) +"\n";
            answer+=imtNoStandart.get(i).getFormula()+"\n";
        }
        answer += "\nВідповідь: Мінімальні витрати z = "+imtNoStandart.get(imtNoStandart.size()-1).getSum()+ "(од. вартості). Об'єми поставок: " + generateXanswer(xNoStandart) +"\n";

        return answer;
    }

    /**
     * Функция вывода входящего массива
     * @param h - затраты на хранение
     * @param d - спрос
     * @param A - затраты на доставку
     * @param C - затраты от спроса
     * @return - строка входящего массива
     */
    public static String generateDataToTxt(int[] h, int[] d, int[] A, int[] C)
    {
        String answer = "";
        answer += h.length + "\n";
        answer += "d:"+generateMass(d)+"\n";
        answer += "h:"+generateMass(h)+"\n";
        answer += "A:"+generateMass(A)+"\n";
        answer += "C:"+generateMass(C)+"\n";
        return answer;
    }

    /**
     * Генерировать стринговый массив с интового
     * @param mas - интовый массив
     * @return - стринговый массив
     */
    private static String generateMass(int [] mas)
    {
        String answ = "";
        for (int i = 0 ; i < mas.length ; i++)
        {
            answ += mas[i] +" ";
        }
        return answ;
    }

    /**
     * Генерация массива ответов
     * @param x - массив ответов
     * @return - массив ответов в строке
     */
    public static String generateXanswer(int [] x)
    {
        String answ = "";
        for (int i = 1 ; i <= x.length; i++)
        {
            answ += "X["+i+"] = "+x[i-1]+" ";
        }
        return answ;
    }
}
