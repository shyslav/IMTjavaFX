package com.sukhaniuk.func;

import com.shyslav.controllers.alerts.SampleAlert;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Shyshkin Vladyslav on 05.06.2016.
 */
public class fileSave {
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
    private static String generateMass(int [] mas)
    {
        String answ = "";
        for (int i = 0 ; i < mas.length ; i++)
        {
            answ += mas[i] +" ";
        }
        return answ;
    }
}
