package com.dental.utils;




        import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class LegendBorder extends StackPane {

    String rootStyle = "-fx-content-display: top;" +
            "-fx-padding: 20 16 16 16;" +
            "-fx-border-width: 2;",
            labelStyle = "-fx-translate-y: -32;" +
                    "-fx-padding: 0 5;";

    private final Label lbl_title;
    private String labelBackgroundColor;

    public LegendBorder() {
        lbl_title = new Label();
        StackPane.setAlignment(lbl_title, Pos.TOP_LEFT);
        lbl_title.setStyle(labelStyle + "-fx-text-fill: #333;");
        super.setStyle(rootStyle + "-fx-border-color: #333;");
        super.getChildren().add(lbl_title);
    }

    public void setLabelTitle(String title) {
        this.lbl_title.setText(title);
    }

    public String getLabelTitle() {
        return this.lbl_title.getText();
    }

    public void setLabelBackgroundColor(String color) {
        this.lbl_title.setStyle(this.lbl_title.getStyle() + "-fx-background-color: " + color + ";");
        this.labelBackgroundColor = color;
    }

    public String getLabelBackgroundColor() {
        return this.labelBackgroundColor;
    }

}