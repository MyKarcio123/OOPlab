package agh.ics.oop.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static agh.ics.oop.Parameters.*;

public class ConfigLoader {
    public static List<String> loadData(String path) throws Exception{
        List<String> configProperties = new ArrayList<>();
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;

        while((st = br.readLine()) != null){
            String[] words = st.split(":");
            configProperties.add(words[1]);
        }
        return configProperties;
    }
}

