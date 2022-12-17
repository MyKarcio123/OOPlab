package agh.ics.oop.gui;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static agh.ics.oop.gui.MainMenuMethods.loadDataStatic;
import static agh.ics.oop.gui.MainMenuMethods.saveTextToFileStatic;

public class MainMenuController implements IMainMenuControllerObserver {

    private List<TextField> textFields = new ArrayList<>();
    private List<RadioButton> radioButtonList = new ArrayList<>();
    private List<ConfigButton> configButtons = new ArrayList<>();
    private List<String> currentConfig = new ArrayList<>();

    @Override
    public void setCurrentConfig(List<String> currentConfig) {
        this.currentConfig = new ArrayList<>(currentConfig);
        loadToArray();
        updateData();
    }

    @FXML
    private GridPane settingGrid;

    @FXML
    private void makeButton(List<String> configProperties) {
        ConfigButton currentButton = new ConfigButton(configProperties, (IMainMenuControllerObserver) this);
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
                if (Objects.equals(currentLine[0], "next_gen_type")) {
                    makeButton(configProperties);
                    configProperties.clear();
                }

            }
            reader.close();
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
        currentConfig = loadData();
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File file = fc.showSaveDialog(null);

        if (file != null) {
            saveTextToFile(file);
        }
    }

    @FXML
    protected void onAddToFavoritesButtonClick() {
        try {
            currentConfig = loadData();
            //BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(Objects.requireNonNull(getClass().getResource("/template.txt")).getPath())));

            FileWriter out = new FileWriter("/saves.txt", true);
            for (String s : currentConfig) {
                out.write(s + "\n");
            }
            makeButton(currentConfig);
            //reader.close();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveTextToFile(File file) {
        saveTextToFileStatic(file, currentConfig, textFields, radioButtonList);
    }

    @FXML
    TextField configurationName;
    @FXML
    TextField heighField;
    @FXML
    TextField widthField;
    @FXML
    RadioButton earthVariant;
    @FXML
    RadioButton hellVariant;
    @FXML
    TextField grassAmountField;
    @FXML
    TextField energyFromGrassField;
    @FXML
    TextField grassPerDayField;
    @FXML
    RadioButton goodVariant;
    @FXML
    RadioButton toxicVariant;
    @FXML
    TextField startingAnimalField;
    @FXML
    TextField startingEnergyField;
    @FXML
    TextField energyNeededField;
    @FXML
    TextField energyCopulationField;
    @FXML
    TextField minMutationField;
    @FXML
    TextField maxMutationField;
    @FXML
    RadioButton randomVariant;
    @FXML
    RadioButton correctionVariant;
    @FXML
    TextField genomeLengthField;
    @FXML
    RadioButton fullControlVariant;
    @FXML
    RadioButton bitOfRandomVariant;

    private void loadToArray() {
        textFields.clear();
        radioButtonList.clear();
        textFields.add(configurationName);
        textFields.add(heighField);
        textFields.add(widthField);
        radioButtonList.add(earthVariant);
        radioButtonList.add(hellVariant);
        textFields.add(grassAmountField);
        textFields.add(energyFromGrassField);
        textFields.add(grassPerDayField);
        radioButtonList.add(goodVariant);
        radioButtonList.add(toxicVariant);
        textFields.add(startingAnimalField);
        textFields.add(startingEnergyField);
        textFields.add(energyNeededField);
        textFields.add(energyCopulationField);
        textFields.add(minMutationField);
        textFields.add(maxMutationField);
        radioButtonList.add(randomVariant);
        radioButtonList.add(correctionVariant);
        radioButtonList.add(fullControlVariant);
        radioButtonList.add(bitOfRandomVariant);
        textFields.add(genomeLengthField);
    }

    private List<String> loadData() {
        loadToArray();
        return loadDataStatic( textFields, radioButtonList);
    }

    private void updateData() {
        MainMenuMethods.updateDataStatic(currentConfig, textFields,radioButtonList);
    }
}
