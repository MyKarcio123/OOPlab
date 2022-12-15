package agh.ics.oop.gui;

import com.google.common.io.Files;
import com.google.errorprone.annotations.FormatMethod;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainMenuController {
    @FXML
    private GridPane settingGrid;
    @FXML
    private void addButton(String text){
        Button button = new Button(text);
        AnchorPane pane = new AnchorPane(button);
        AnchorPane.setBottomAnchor(button,5.0);
        AnchorPane.setLeftAnchor(button,5.0);
        AnchorPane.setRightAnchor(button,5.0);
        AnchorPane.setTopAnchor(button,5.0);
        settingGrid.add(pane,0,settingGrid.getRowCount());
    }
    @FXML
    public void loadSaves(){
        URL resource = getClass().getClassLoader().getResource("saves.txt");
        if(resource == null) throw new IllegalArgumentException("File not found!");
        FileReader fr = null;
        System.out.println(String.valueOf(resource));
        try {
            fr = new FileReader(String.valueOf(resource));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader reader =new BufferedReader(fr);
        String line;
        try{
            while ((line = reader.readLine()) != null){
                String[] currentLine = line.split(":");
                if(Objects.equals(currentLine[0], "name")) addButton(currentLine[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    protected void onOpenFileButtonClick() throws RuntimeException {
        FileChooser fc = new FileChooser();
        fc.setTitle("Wybierz plik konfiguracyjny");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));

        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files","*.txt"));

        File file = fc.showOpenDialog(null);
        System.out.println(file.getAbsolutePath()+"\n");
    }

    @FXML
    protected void onSaveFileButtonClick(){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files","*.txt"));

        File file = fc.showSaveDialog(null);
        if(file != null){
            saveTextToFile("test",file);
        }
    }
    private void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
