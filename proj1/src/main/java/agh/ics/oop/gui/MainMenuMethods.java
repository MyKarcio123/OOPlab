package agh.ics.oop.gui;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainMenuMethods {
    protected static void saveTextToFileStatic(File file, List<TextField> textFields, List<RadioButton> radioButtonList) {
        try {
            ArrayList<String> currentConfig = new ArrayList<>(loadDataStatic(textFields, radioButtonList));
            PrintWriter writer;
            writer = new PrintWriter(file);

            URL resource = MainMenuMethods.class.getResource("/template.txt");
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
            reader.close();
            fr.close();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected static List<String> loadDataStatic( List<TextField> textFields, List<RadioButton> radioButtonList) throws IllegalArgumentException {
        List<String> currentConfig = new ArrayList<>();
        int iter=0;
        for (TextField textField : textFields) {
            if(textField.getText().replaceAll("\\s","").equals("")){
                throw new IllegalArgumentException("Pole nie może być puste!");
            }
            if(iter!=0 && !textField.getText().matches("[0-9]+")){
                throw new IllegalArgumentException("Pole musi być liczbą!");
            }
            if(iter!=0 && Integer.parseInt(textField.getText()) <= 0){
                throw new IllegalArgumentException("Pole nie może być mniejsze od 0!");
            }
            currentConfig.add(textField.getText());
            iter+=1;
        }
        for (int i =0; i<8;i+=2) {

            if (radioButtonList.get(i).isSelected()) currentConfig.add("0");
            else currentConfig.add("1");
        }
        return currentConfig;
    }

    protected static void updateDataStatic(List<String> currentConfig, List<TextField> textFields, List<RadioButton> radioButtonList) {
        for (int i = 0; i < currentConfig.size(); ++i) {
            if (i <= 12) {
                textFields.get(i).setText(currentConfig.get(i));
            } else {
                if (Objects.equals(currentConfig.get(i), "0")) {
                    radioButtonList.get(2*(i - 13)).setSelected(Objects.equals(currentConfig.get(i), "0"));
                } else {
                    radioButtonList.get(2*(i - 13)+1).setSelected(Objects.equals(currentConfig.get(i), "1"));
                }

            }
        }
    }
}
