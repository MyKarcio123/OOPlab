package agh.ics.oop.gui;

import agh.ics.oop.AbstractMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class GuiElementBox {

    private Image image;
    private ImageView imageView = new ImageView();
    private VBox vBox = new VBox();

    public GuiElementBox(AbstractMapElement element) {
            this.image = new Image("file:src/main/resources/N.png");
            imageView.setImage(image);
            imageView.setFitHeight(20);
            imageView.setFitWidth(20);



        Label label = new Label(element.getDesc());
        vBox.getChildren().addAll(imageView, label);
        vBox.setAlignment(Pos.CENTER);
    }

    public VBox getvBox() {
        return vBox;
    }
}