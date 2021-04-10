package com.dental.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;

public class LayoutInflater {

    public static Node inflate(Object controller, URL url) throws IOException {
        FXMLLoader loader = new FXMLLoader(url);
        loader.setController(controller);
        return loader.load();
    }

}
