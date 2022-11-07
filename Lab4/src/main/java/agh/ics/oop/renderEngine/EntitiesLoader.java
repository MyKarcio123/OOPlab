package agh.ics.oop.renderEngine;

import agh.ics.oop.Animal;
import agh.ics.oop.entities.Entity;
import agh.ics.oop.models.Texture;
import agh.ics.oop.models.TexturedModel;

import java.util.ArrayList;
import java.util.List;

public class EntitiesLoader {
    public static List<Entity> loadEntities(TexturedModel[] models){
        List<Entity> entities = new ArrayList<Entity>();
        for(TexturedModel model : models){
            entities.add(new Animal(Game.map, model));
        }
        return entities;
    }
}
