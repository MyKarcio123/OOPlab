package agh.ics.oop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static agh.ics.oop.Parameters.*;

public class ConfigLoader {
    String path;
    ConfigLoader(String path){
        this.path = path;
    }
    public void loadData() throws Exception{
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;

        while((st = br.readLine()) != null){
            String[] words = st.split("\\s+");
            switch (words[0]) {
                case "height" -> HEIGHT_MAP = Integer.parseInt(words[1]);
                case "width" -> WIDTH_MAP = Integer.parseInt(words[1]);
                case "map_variant" -> MAP_VARIANT = Integer.parseInt(words[1]);
                case "starting_grass" -> STARTING_GRASS = Integer.parseInt(words[1]);
                case "energy_from_grass" -> ENERGY_VALUE_FROM_GRASS = Integer.parseInt(words[1]);
                case "new_grass_per_day" -> NEW_GRASS_PER_DAY = Integer.parseInt(words[1]);
                case "grass_grow_variant" -> GRASS_GROW_VARIANT = Integer.parseInt(words[1]);
                case "starting_animals" -> STARTING_ANIMALS  = Integer.parseInt(words[1]);
                case "starting_animals_energy" -> STARTING_ENERGY  = Integer.parseInt(words[1]);
                case "minimum_copulate_energy" -> MINIMUM_COPULATE_ENERGY  = Integer.parseInt(words[1]);
                case "copulate_energy_decrease" -> COPULATE_ENERGY_DECREASE  = Integer.parseInt(words[1]);
                case "minimum_mutation" -> MINIMUM_MUTATION  = Integer.parseInt(words[1]);
                case "maximum_mutation" -> MAXIMUM_MUTATION  = Integer.parseInt(words[1]);
                case "mutation_variant" -> MUTATION_VARIANT  = Integer.parseInt(words[1]);
                case "next_gen_type" -> NEXT_GEN_TYPE  = Integer.parseInt(words[1]);
                case "gen_length" -> GEN_LENGTH  = Integer.parseInt(words[1]);
            }
        }
    }
}

