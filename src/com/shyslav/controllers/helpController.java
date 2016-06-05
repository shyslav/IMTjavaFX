package com.shyslav.controllers;

import data.DataUpdate;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * Created by Shyshkin Vladyslav on 05.06.2016.
 */
public class helpController {
    @FXML
    private WebView webView;

    @FXML
    private void initialize() {
        WebEngine webEngine = webView.getEngine();
        webEngine.load(String.valueOf(DataUpdate.class.getResource("html/help/about.html")));
    }

    public void mouseClickedEvent(Event event) {
        Object sourse = event.getSource();
        if (!(sourse instanceof Button)) {
            return;
        }
        WebEngine webEngine = webView.getEngine();
        Button btn = (Button) sourse;
        switch (btn.getId()) {
            case "about":
                webEngine.load(String.valueOf(DataUpdate.class.getResource("html/help/about.html")));
                break;
            case "startWorking":
                webEngine.load(String.valueOf(DataUpdate.class.getResource("html/help/startWorking.html")));
                break;
            case "PDFsave":
                webEngine.load(String.valueOf(DataUpdate.class.getResource("html/help/savePdf.html")));
                break;
            case "TXTsave":
                webEngine.load(String.valueOf(DataUpdate.class.getResource("html/help/saveTxt.html")));
                break;
            case "VARsave":
                webEngine.load(String.valueOf(DataUpdate.class.getResource("html/help/saveVariables.html")));
                break;
            case "Errors":
                webEngine.load(String.valueOf(DataUpdate.class.getResource("html/help/errors.html")));
                break;
        }
    }
}
