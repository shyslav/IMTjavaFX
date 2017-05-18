package com.sukhaniuk.func;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.shyslav.controllers.alerts.SampleAlert;
import com.shyslav.func.IMT;
import javafx.scene.control.Alert;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 05.06.2016.
 */
public class PDFSave {
    /**
     * Функция генерации таблицы в html и записи ее
     *
     * @param imtStandart   - лист стандартного ЗУЗ
     * @param imtNoStandart - лист не стандартного ЗУЗ
     * @param xStandart     - массив ответов стандартного ЗУЗ
     * @param xNoStandart   - массив ответов не стандартного ЗУЗ
     * @param h             - затраты на хранение
     * @param d             - спрос
     * @param A             - затраты на доставку
     * @param C             - затраты от спроса
     */
    public static void generateHTMLTableView(File file, ArrayList<IMT> imtStandart, ArrayList<IMT> imtNoStandart, int[] xStandart, int[] xNoStandart, int[] h, int[] d, int[] A, int[] C) {
        // step 1 - Создать документ
        Document document = new Document();
        // step 2 - Создать принтврайтер
        PdfWriter writer = null;
        try {
            //Создать документ с именем
            writer = PdfWriter.getInstance(document, new FileOutputStream(file));
            writer.setLanguage("UTF-8");
        } catch (DocumentException e) {
            SampleAlert.WriteError(e);
        } catch (FileNotFoundException e) {
            SampleAlert.FileNotFound(e);
        }
        // step 3 - открыть документ
        document.open();
        document.addAuthor("Шишкін В.І. | Суханюк М.В.");
        document.addCreator("Задача управління запасами 2016 ІС-33");
        document.addSubject("Задача управління запасами");
        document.addTitle("Дякуємо за використання нашого продукту");
        // step 4 - вставить в документ данные из html обычного алгоритма
        try {
            BaseFont bf = BaseFont.createFont(String.valueOf(PDFSave.class.getResource("/data/html/arial.ttf")), BaseFont.IDENTITY_H, BaseFont.EMBEDDED); //подключаем файл шрифта, который поддерживает кириллицу
            Font font = new Font(bf);
            document.add(new Paragraph("Задача управління запасами", font));

            ByteArrayInputStream htmlDocument = new ByteArrayInputStream(String.valueOf(HtmlParser.generateTableView("Звичайна задача управління запасами", imtStandart, xStandart, h, d, A, C)).getBytes());
            XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                    htmlDocument, PDFSave.class.getResourceAsStream("/data/html/tableView.css"));
        } catch (IOException e) {
            SampleAlert.FileIO(e);
        } catch (DocumentException e) {
            SampleAlert.WriteError(e);
        }

        //step 5 - вставить в документ данные из html дополненного алгоритма
        document.newPage();
        try {
            ByteArrayInputStream htmlDocument = new ByteArrayInputStream(String.valueOf(HtmlParser.generateTableView("Ускладнена задача управління запасами", imtNoStandart, xNoStandart, h, d, A, C)).getBytes());
            XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                    htmlDocument, PDFSave.class.getResourceAsStream("/data/html/tableView.css"));
        } catch (IOException e) {
            SampleAlert.FileIO(e);
        }
        //step 6 - закрыть документ
        document.close();
        SampleAlert sa = new SampleAlert("Создание pdf прошло успешно", "Файл сохранен", "Вы можете его открыть", Alert.AlertType.INFORMATION);
        System.out.println("PDF Created!");
    }
}
