package com.sukhaniuk.func;

import com.shyslav.controllers.alerts.JavaFxSimpleAlert;
import javafx.stage.FileChooser;
import starter.StartFrame;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Shyshkin Vladyslav on 02.06.2016.
 */
public class ReadFromFile {
    /**
     * Функция генерации настроек fileChooser
     *
     * @param fileChooser - fileChooser к которому приписать настройки
     * @param title       - подпись фрейма
     */
    public static void configureFileChooser(final FileChooser fileChooser, String title) {
        fileChooser.setTitle(title);
        fileChooser.setInitialDirectory(
                //new File(System.getProperty("user.home"))
                new File(new File("").getAbsolutePath())
        );
    }

    /**
     * Функция чтения с файла
     *
     * @param path - путь к фалу
     * @return
     */
    public static boolean read(final String path) {
        //Массивы считывающиеся с файла
        int[] d = null;
        int[] A = null;
        int[] C = null;
        int[] h = null;
        ReadFromFile read = new ReadFromFile();
        boolean done = true;
        try {
            Scanner scanner = new Scanner(Paths.get(path));
            int n = 0;
            try {
                n = Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception ex) {
                JavaFxSimpleAlert.ReadFileStructureError();
                return false;
            }
            d = new int[n];
            A = new int[n];
            h = new int[n];
            C = new int[n];
            while (scanner.hasNextLine() && done) {
                String[] element = scanner.nextLine().split("[: ]");
                if (element.length < n) {
                    done = false;
                    break;
                }
                switch (element[0].trim()) {
                    case "d":
                        d = read.copy(element);
                        break;
                    case "h":
                        h = read.copy(element);
                        break;
                    case "A":
                        A = read.copy(element);
                        break;
                    case "C":
                        C = read.copy(element);
                        break;
                }
                //Вывести считанные параметры
                System.out.println(Arrays.deepToString(element));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!done) {
            JavaFxSimpleAlert.ReadFileErrorAmountVariables();
            return false;
        } else {
            StartFrame.controller.dataOutput(h, d, A, C, h.length);
        }
        return true;
    }

    /**
     * Преобразование массива стрингов в массив интов
     *
     * @param element массив стрингов
     * @return массив интов
     */
    private int[] copy(String[] element) {
        int[] res = new int[element.length - 1];
        for (int i = 1; i < element.length; i++) {
            res[i - 1] = Integer.parseInt(element[i].trim());
        }
        return res;
    }
}
