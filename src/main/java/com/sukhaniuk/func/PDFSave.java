package com.sukhaniuk.func;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.shyslav.controllers.alerts.JavaFxSimpleAlert;
import com.shyslav.func.IMT;
import javafx.scene.control.Alert;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Shyshkin Vladyslav on 05.06.2016.
 */
public class PDFSave {
    /**
     * Функция генерации таблицы в html и записи ее
     *
     * @param imtAnswerArray - лист стандартного ЗУЗ
     * @param X              - массив ответов стандартного ЗУЗ
     * @param h              - затраты на хранение
     * @param d              - спрос
     * @param A              - затраты на доставку
     * @param C              - затраты от спроса
     */
    public static ByteArrayInputStream generateHTMLTableView(ArrayList<IMT> imtAnswerArray, int[] X, int[] h, int[] d, int[] A, int[] C, String name) {
        byte[] html;
        if (C == null) {
            html = String.valueOf(HtmlParser.generateTableView(name, imtAnswerArray, X, h, d, A, C)).getBytes();
        } else {
            html = String.valueOf(HtmlParser.generateTableView(name, imtAnswerArray, X, h, d, A, C)).getBytes();
        }
        return new ByteArrayInputStream(html);
    }

    /**
     * Write imt to pdf file
     *
     * @param inputStreams input streams
     * @param file         file to write
     */
    public static void writeIMTtoPDFFile(ArrayList<ByteArrayInputStream> inputStreams, File file) {
        // step 1 - Создать документ
        Document document = new Document();
        // step 2 - Создать принтврайтер
        PdfWriter writer = null;
        try {
            //Создать документ с именем
            writer = PdfWriter.getInstance(document, new FileOutputStream(file));
            writer.setLanguage("UTF-8");
        } catch (DocumentException e) {
            JavaFxSimpleAlert.WriteError(e);
        } catch (FileNotFoundException e) {
            JavaFxSimpleAlert.FileNotFound(e);
        }
        // step 3 - открыть документ
        document.open();
        document.addAuthor("Шишкін В.І. | Суханюк М.В.");
        document.addCreator("Задача управління запасами 2016 ІС-33");
        document.addSubject("Задача управління запасами");
        document.addTitle("Дякуємо за використання нашого продукту");
        // step 4 - вставить в документ данные из html обычного алгоритма
        try {
            for (ByteArrayInputStream inputStream : inputStreams) {
                BaseFont bf = BaseFont.createFont(String.valueOf(PDFSave.class.getResource("/data/html/arial.ttf")), BaseFont.IDENTITY_H, BaseFont.EMBEDDED); //подключаем файл шрифта, который поддерживает кириллицу
                Font font = new Font(bf);
                document.add(new Paragraph("Задача управління запасами", font));
                XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                        inputStream, PDFSave.class.getResourceAsStream("/data/html/tableView.css"));
                document.newPage();
            }
            document.setPageCount(document.getPageNumber() - 1);
        } catch (IOException e) {
            JavaFxSimpleAlert.FileIO(e);
        } catch (DocumentException e) {
            JavaFxSimpleAlert.WriteError(e);
        } finally {
            document.close();
        }
        new JavaFxSimpleAlert("Создание pdf прошло успешно", "Файл сохранен", "Вы можете его открыть", Alert.AlertType.INFORMATION);
    }
}
