package com.sukhaniuk.func;

import com.shyslav.controllers.alerts.JavaFxSimpleAlert;
import com.shyslav.func.IMT;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Shyshkin Vladyslav on 05.06.2016.
 */
public class HtmlParser {
    /**
     * Функция преобразования шаблона
     *
     * @param imtList - лист  обьекта ИМТ
     * @param Answer  - ответ массив х
     * @param h       - затраты на хранение
     * @param d       - спрос
     * @param A       - затраты на доставку
     * @param C       - затраты от спроса
     * @return html документ
     */
    public static Document generateTableView(String title, ArrayList<IMT> imtList, int[] Answer, int[] h, int[] d, int[] A, int[] C) {
        Document htmlFile = null;
        File input = new File(HtmlParser.class.getResource("/data/html/tableView.html").getFile());
        try {
            htmlFile = Jsoup.parse(input, "UTF-8");
        } catch (IOException e) {
            JavaFxSimpleAlert.FileNotFound(e);
        }
        //Тег с ид answer для записи ответа
        Element div = htmlFile.getElementById("answer");
        div.text("\nВідповідь: Мінімальні витрати z = " + imtList.get(imtList.size() - 1).getSum() + "(од. вартості). Об'єми поставок: " + TXTSave.generateXanswer(Answer) + "\n");
        //Тег с ид name для записи имени алгоритма
        Element h1 = htmlFile.getElementById("name");
        h1.text("\n" + title + "\n");
        //Получить таблицу с ид table
        Element table = htmlFile.getElementById("table");
        //Получить в таблице все элементы по тегу tbody
        Elements rows = table.select("tbody");
        //Получить данные по тегу description для массива входящих данныех
        Element spanData = htmlFile.getElementById("description");
        spanData.append(generateDataToHtml(h, d, A, C));
        //Генерация таблицы
        for (IMT element : imtList) {
            rows.append("<tr>" +
                    "<td>" + element.getK() + "</td>" +
                    "<td>" + element.JAsHtml(element.getJList()) + "</td>" +
                    "<td>" + element.formulaAsHtml(element.getFormulaList()) + "</td>" +
                    "<td>" + element.getL() + "</td>" +
                    "<td>" + element.getSum() + "</td>" +
                    "</tr>");
        }
        return htmlFile;
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
    private static String generateDataToHtml(int[] h, int[] d, int[] A, int[] C) {
        String answer = "";
        answer += "<p>" + h.length + "</p>";
        answer += "<p>d:" + generateMass(d) + "</p>";
        answer += "<p>h:" + generateMass(h) + "</p>";
        answer += "<p>A:" + generateMass(A) + "</p>";
        answer += "<p>C:" + generateMass(C) + "</p>";
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
        for (int ma : mas) {
            answ.append(ma).append(" ");
        }
        return answ.toString();
    }
}
