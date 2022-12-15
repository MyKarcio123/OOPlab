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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainMenuController {
    private List<ConfigButton> configButtons = new ArrayList<>();
    private List<String> currentConfig = new ArrayList<>();
    @FXML
    private GridPane settingGrid;

    @FXML
    private void makeButton(List<String> configProperties) {
        ConfigButton currentButton = new ConfigButton(configProperties);
        configButtons.add(currentButton);
        settingGrid.add(currentButton.getPane(), 0, settingGrid.getRowCount());
    }

    @FXML
    public void loadSaves() {
        URL resource = getClass().getResource("/saves.txt");
        if (resource == null) throw new IllegalArgumentException("File not found!");
        FileReader fr = null;
        try {
            fr = new FileReader(resource.getPath());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader reader = new BufferedReader(fr);
        String line;
        try {
            List<String> configProperties = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if (line.equals("")) continue;
                String[] currentLine = line.split(":");
                configProperties.add(currentLine[1]);
                if (Objects.equals(currentLine[0], "gen_length")) {
                    makeButton(configProperties);
                    configProperties.clear();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void onOpenFileButtonClick() throws Exception {
        FileChooser fc = new FileChooser();
        fc.setTitle("Wybierz plik konfiguracyjny");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));

        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File file = fc.showOpenDialog(null);

        List<String> configProperties = new ArrayList<>(ConfigLoader.loadData(file.getAbsolutePath()));

        currentConfig = configProperties;
    }

    @FXML
    protected void onSaveFileButtonClick() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File file = fc.showSaveDialog(null);
        if (file != null) {
            saveTextToFile(file);
        }
    }

    private void saveTextToFile(File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);

            URL resource = getClass().getResource("/template.txt");
            if (resource == null) throw new IllegalArgumentException("File not found!");
            FileReader fr = null;
            try {
                fr = new FileReader(resource.getPath());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            BufferedReader reader = new BufferedReader(fr);
            String line;
            int iter = 0;
            while ((line = reader.readLine()) != null) {
                line += currentConfig.get(iter);
                iter += 1;
                writer.println(line);
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
