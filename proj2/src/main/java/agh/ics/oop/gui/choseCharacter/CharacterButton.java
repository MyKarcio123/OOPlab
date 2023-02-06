package agh.ics.oop.gui.choseCharacter;

import agh.ics.oop.gui.IMainMenuControllerObserver;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class CharacterButton {
    private final Button button;
    private final AnchorPane pane;
    private final IMainMenuControllerObserver observer;

    public CharacterButton(String name, IMainMenuControllerObserver observer){
        this.button = new Button(name);
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
        //TODO żeby ładnie nam czytwywało grafike
    }

}
