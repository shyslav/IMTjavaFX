package com.sukhaniuk.func;

import com.shyslav.func.IMT;
import data.DataUpdate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 05.06.2016.
 */
public class HtmlParser {
    /**
     * Функция преобразования шаблона
     * @param imtList - лист  обьекта ИМТ
     * @param Answer - ответ массив х
     * @param h - затраты на хранение
     * @param d - спрос
     * @param A - затраты на доставку
     * @param C - затраты от спроса
     * @return html документ
     */
    public static Document generateTableView(ArrayList <IMT> imtList ,int [] Answer, int[] h, int[] d, int[] A, int[] C)
    {
        Document htmlFile = null;
        File input = new File(DataUpdate.class.getResource("html/tableView.html").getPath());
        try {
            htmlFile = Jsoup.parse(input,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Тег с ид answer для записи ответа
        Element div = htmlFile.getElementById("answer");
        div.text("\nВідповідь: Мінімальні витрати z = "+imtList.get(imtList.size()-1).getSum()+ "(од. вартості). Об'єми поставок: " + fileSave.generateXanswer(Answer) +"\n");
        //Тег с ид name для записи имени алгоритма
        Element h1 = htmlFile.getElementById("name");
        h1.text("\n Звичайна задача управління запасами\n");
        //Получить таблицу с ид table
        Element table = htmlFile.getElementById("table");
        //Получить в таблице все элементы по тегу tbody
        Elements rows = table.select("tbody");
        //Получить данные по тегу description для массива входящих данныех
        Element spanData = htmlFile.getElementById("description");
        spanData.append(generateDataToHtml(h,d,A,C));
        //Генерация таблицы
        for (int i = 0; i < imtList.size(); i++) {
            rows.append("<tr>" +
                    "<td>"+imtList.get(i).getK()+"</td>" +
                    "<td>"+imtList.get(i).JAsHtml(imtList.get(i).getJList())+"</td>" +
                    "<td>"+imtList.get(i).formulaAsHtml(imtList.get(i).getFormulaList())+"</td>" +
                    "<td>"+imtList.get(i).getL()+"</td>" +
                    "<td>"+imtList.get(i).getSum()+"</td>" +
                    "</tr>");
        }
        return htmlFile;
    }

    /**
     * Функция вывода входящего массива
     * @param h - затраты на хранение
     * @param d - спрос
     * @param A - затраты на доставку
     * @param C - затраты от спроса
     * @return - строка входящего массива
     */
    private static String generateDataToHtml(int[] h, int[] d, int[] A, int[] C)
    {
        String answer = "";
        answer += "<p>"+h.length + "</p>";
        answer += "<p>d:"+generateMass(d)+"</p>";
        answer += "<p>h:"+generateMass(h)+"</p>";
        answer += "<p>A:"+generateMass(A)+"</p>";
        answer += "<p>C:"+generateMass(C)+"</p>";
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
}
