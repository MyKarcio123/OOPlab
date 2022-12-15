package agh.ics.oop.gui;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class ConfigButton {
    private final List<String> configProperties;
    private final Button button;
    private final AnchorPane pane;

    public ConfigButton(List<String> configProperties){
        this.configProperties = new ArrayList<>(configProperties);
        this.button = new Button(configProperties.get(0));
        this.pane = new AnchorPane(button);
        AnchorPane.setBottomAnchor(button,5.0);
        AnchorPane.setLeftAnchor(button,5.0);
        AnchorPane.setRightAnchor(button,5.0);
        AnchorPane.setTopAnchor(button,5.0);
        button.setOnAction(event -> {
            clickedConfig();
        });
    }
    private void clickedConfig(){
        System.out.println("hey");
    }

    public AnchorPane getPane() {
        return pane;
    }
}
