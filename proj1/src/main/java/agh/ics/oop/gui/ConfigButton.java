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
    private final IMainMenuControllerObserver observer;

    public ConfigButton(List<String> configProperties,IMainMenuControllerObserver observer){
        this.configProperties = new ArrayList<>(configProperties);
        this.button = new Button(configProperties.get(0));
        this.pane = new AnchorPane(button);
        this.observer = observer;
        AnchorPane.setBottomAnchor(button,5.0);
        AnchorPane.setLeftAnchor(button,5.0);
        AnchorPane.setRightAnchor(button,5.0);
        AnchorPane.setTopAnchor(button,5.0);
        button.setOnAction(event -> {
            clickedConfig();
        });
    }
    private void clickedConfig(){
        observer.setCurrentConfig(configProperties);
    }

    public AnchorPane getPane() {
        return pane;
    }
}
