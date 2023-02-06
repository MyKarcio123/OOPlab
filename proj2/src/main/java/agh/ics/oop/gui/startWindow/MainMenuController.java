package agh.ics.oop.gui.startWindow;

import agh.ics.oop.gui.IMainMenuControllerObserver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//import static agh.ics.oop.gui.startWindow.MainMenuMethods.loadDataStatic;
//import static agh.ics.oop.gui.startWindow.MainMenuMethods.saveTextToFileStatic;

public class MainMenuController /*implements IMainMenuControllerObserver*/ {
//
////    private final List<TextField> textFields = new ArrayList<>();
//    private final List<RadioButton> radioButtonList = new ArrayList<>();
//    //private final List<ConfigButton> configButtons = new ArrayList<>();
//    private List<String> currentConfig = new ArrayList<>();
//
//    @Override
//    public void setCurrentConfig(List<String> currentConfig) {
//        this.currentConfig = new ArrayList<>(currentConfig);
//        updateData();
//    }
//
//    @FXML
//    private GridPane settingGrid;
    @FXML
    private Button play;
//
//    @FXML
//    private void makeButton(List<String> configProperties) {
//        //ConfigButton currentButton = new ConfigButton(configProperties, this);
//        //configButtons.add(currentButton);
//        //settingGrid.add(currentButton.getPane(), 0, settingGrid.getRowCount());
//    }
//
//    @FXML
//    public void loadSaves() throws IOException {
//        loadToArray();
//        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/saves.txt"));
//        String line;
//        List<String> configProperties = new ArrayList<>();
//        while ((line = reader.readLine()) != null) {
//            if (line.equals("")) continue;
//            String[] currentLine = line.split(":");
//            configProperties.add(currentLine[1]);
//            if (Objects.equals(currentLine[0], "next_gen_type")) {
//                makeButton(configProperties);
//                configProperties.clear();
//            }
//        }
//        reader.close();
//
//    }
//    //metoda która usuwa aktualnie wybraną konfigurację z pliku .txt
//    @FXML
//    protected void deleteCurrentConfiguration() throws IndexOutOfBoundsException, IOException {
//        boolean delete=false;
//        int iter=-1;
//        String nameSaveToDelete = currentConfig.get(0);
//        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/saves.txt"));
//        String line;
//        StringBuilder newSaveString = new StringBuilder();
//        while ((line = reader.readLine()) != null) {
//            if(line.equals("")) continue;
//            String[] currentLine = line.split(":");
//            if(currentLine[0].equals("name")){
//                iter+=1;
//                if(delete) {
//                    delete = false;
//                }
//            }
////            if(currentLine[1].equals(nameSaveToDelete)){
////                settingGrid.getChildren().remove(configButtons.get(iter).getPane());
////                configButtons.remove(iter);
////                iter-=1;
////                delete = true;
////            }else if(!delete){
////                newSaveString.append(line).append("\n");
////            }
//        }
//        reader.close();
//        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/saves.txt"));
//        writer.write(String.valueOf(newSaveString));
//        writer.close();
//    }
//    @FXML
//    protected void onOpenFileButtonClick() throws Exception {
//        FileChooser fc = new FileChooser();
//        fc.setTitle("Wybierz plik konfiguracyjny");
//        fc.setInitialDirectory(new File(System.getProperty("user.home")));
//
//        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
//
//        File file = fc.showOpenDialog(null);
//
//        //currentConfig = new ArrayList<>(ConfigLoader.loadData(file.getAbsolutePath()));
//
//    }
//
//    @FXML
//    protected void onSaveFileButtonClick() {
//        currentConfig = loadData();
//        FileChooser fc = new FileChooser();
//        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
//
//        File file = fc.showSaveDialog(null);
//
//        if (file != null) {
//            saveTextToFile(file);
//        }
//    }
//
//    @FXML
//    protected void onAddToFavoritesButtonClick() throws IOException {
//        currentConfig = loadData();
//
//        BufferedReader reader = new BufferedReader(new FileReader(Objects.requireNonNull(getClass().getResource("/template.txt")).getPath()));
//
//        String ans = "\n";
//
//        for (String s : currentConfig) {
//            ans= ans + reader.readLine() + s + "\n";
//        }
//        reader.close();
//
//        Files.write(Path.of("src/main/resources/saves.txt"),ans.getBytes(), StandardOpenOption.APPEND);
//
//
//        makeButton(currentConfig);
//    }
//
//    private void saveTextToFile(File file) {
//        saveTextToFileStatic(file, textFields, radioButtonList);
//    }
//
//    @FXML
//    TextField configurationName;
//    @FXML
//    TextField heighField;
//    @FXML
//    TextField widthField;
//    @FXML
//    RadioButton earthVariant;
//    @FXML
//    RadioButton hellVariant;
//    @FXML
//    TextField grassAmountField;
//    @FXML
//    TextField energyFromGrassField;
//    @FXML
//    TextField grassPerDayField;
//    @FXML
//    RadioButton goodVariant;
//    @FXML
//    RadioButton toxicVariant;
//    @FXML
//    TextField startingAnimalField;
//    @FXML
//    TextField startingEnergyField;
//    @FXML
//    TextField energyNeededField;
//    @FXML
//    TextField energyCopulationField;
//    @FXML
//    TextField minMutationField;
//    @FXML
//    TextField maxMutationField;
//    @FXML
//    RadioButton randomVariant;
//    @FXML
//    RadioButton correctionVariant;
//    @FXML
//    TextField genomeLengthField;
//    @FXML
//    RadioButton fullControlVariant;
//    @FXML
//    RadioButton bitOfRandomVariant;
//    @FXML
//    RadioButton biomesVariant;
//
//    @FXML
//    CheckBox saveStillToCSV;
//
//    private void loadToArray() {
//        textFields.clear();
//        radioButtonList.clear();
//        textFields.add(configurationName);
//        textFields.add(heighField);
//        textFields.add(widthField);
//        textFields.add(grassAmountField);
//        textFields.add(energyFromGrassField);
//        textFields.add(grassPerDayField);
//        radioButtonList.add(earthVariant);
//        radioButtonList.add(hellVariant);
//        radioButtonList.add(goodVariant);
//        radioButtonList.add(toxicVariant);
//        radioButtonList.add(biomesVariant);
//        textFields.add(startingAnimalField);
//        textFields.add(startingEnergyField);
//        textFields.add(energyNeededField);
//        textFields.add(energyCopulationField);
//        textFields.add(minMutationField);
//        textFields.add(maxMutationField);
//        radioButtonList.add(randomVariant);
//        radioButtonList.add(correctionVariant);
//        radioButtonList.add(fullControlVariant);
//        radioButtonList.add(bitOfRandomVariant);
//        textFields.add(genomeLengthField);
//    }
//
//    private List<String> loadData() {
//        return loadDataStatic( textFields, radioButtonList);
//    }
//
//    private void updateData() {
//        MainMenuMethods.updateDataStatic(currentConfig, textFields,radioButtonList);
//    }
//
    public Button getPlayButton(){
        return play;
    }
//
//    public CheckBox getSaveStillToCSV(){return saveStillToCSV;}
//
//    public List<String> getCurrentConfig(){
//        currentConfig = loadData();
//        return currentConfig;
//    }
}
