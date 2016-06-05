package com.sukhaniuk.func;

import com.shyslav.func.IMT;
import data.DataUpdate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 05.06.2016.
 */
public class HtmlParser {
    public static void generateTableView(ArrayList<IMT> imtStandart, ArrayList <IMT> imtNoStandart ,int [] xStandart, int [] xNoStandart)
    {
        Document htmlFile = null;
        File input = new File(DataUpdate.class.getResource("html/tableView.html").getPath());
        try {
            htmlFile = Jsoup.parse(input,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String title = htmlFile.title();
        Element div = htmlFile.getElementById("answer");
        div.text("\nВідповідь: Мінімальні витрати z = "+imtNoStandart.get(imtNoStandart.size()-1).getSum()+ "(од. вартості). Об'єми поставок: " + fileSave.generateXanswer(xNoStandart) +"\n");
        String cssClass = div.text(); // getting class form HTML element

        System.out.println("Jsoup can also parse HTML file directly");
        System.out.println("title : " + title);
        System.out.println("class of div tag : " + cssClass);
    }
}
