package agh.ics.oop.gui.choseCharacter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ChoseCharacterController {
    @FXML
    private Button choseMag;
    @FXML
    private Button choseWoj;
    @FXML
    private Button choseLucznik;
    @FXML
    private Button start;

    public Button getMagButton(){
        return choseMag;
    }
    public Button getWojButton(){
        return choseWoj;
    }
    public Button getLucznikButton(){
        return choseLucznik;
    }
    public Button getStartButton(){
        return start;
    }

    public void prepareBackground() {
        //TODO make button, z tymi postaciami
    }
}
