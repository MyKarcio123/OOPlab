package agh.ics.oop.gui;

import agh.ics.oop.AbstractMapElement;
import agh.ics.oop.Animal;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GuiElementBox {

    private Image image;
    private ImageView imageView = new ImageView();
    private VBox vBox = new VBox();
    private boolean clicked = false;
    private EntityType entityType;
    private double progression;
    private SimulationController observer;
    public GuiElementBox(AbstractMapElement element,String fileName,EntityType entityType,double progression, SimulationController observer) {

        this.image = new Image("file:src/main/resources/"+fileName+".png");
        this.clicked = element.isClicked();
        this.entityType = entityType;
        this.progression = progression;
        this.observer = observer;
        makeNormalView(element);
        if(entityType==EntityType.ANIMAL) {
            vBox.setOnMouseClicked(event -> {
                if (!this.clicked) {
                    clicked = true;
                    observer.setAnimalToFollow((Animal)element, true);
                } else {
                    clicked = false;
                    observer.setAnimalToFollow((Animal)element, false);
                }
            });
        }
    }

    public VBox getvBox() {
        return vBox;
    }

    private void makeNormalView(AbstractMapElement element){
        imageView.setImage(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);

        if(!Double.isNaN(progression)) {
            //System.out.println("PROGRESJA " + progression);
            ProgressBar progressBar = new ProgressBar(progression);
            progressBar.setMaxWidth(50);
            vBox.getChildren().addAll(imageView, progressBar);
        }else{
            vBox.getChildren().addAll(imageView);
        }
        vBox.setAlignment(Pos.CENTER);
    }
}