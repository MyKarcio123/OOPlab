package agh.ics.oop.gui;

import agh.ics.oop.AbstractMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GuiElementBox {

    private Image image;
    private ImageView imageView = new ImageView();
    private VBox vBox = new VBox();
    private boolean clicked = false;

    public GuiElementBox(AbstractMapElement element) {

        this.image = new Image("file:src/main/resources/N.png");
        this.clicked = element.isClicked();

        if (!this.clicked) {
            makeNormalView(element);

        } else {
            vBox.getChildren().clear();
            vBox.getChildren().add(new Text("sledza mnie"));
        }

        vBox.setOnMouseClicked(event -> {
            if (!this.clicked) {
                vBox.getChildren().clear();
                vBox.getChildren().add(new Text("sledza mnie"));
                clicked = true;
                element.setClicked(true);
            } else {
                makeNormalView(element);
                clicked = false;
                element.setClicked(false);
            }
        });
    }

    public VBox getvBox() {
        return vBox;
    }

    private void makeNormalView(AbstractMapElement element){
        imageView.setImage(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);


        Label label = new Label(element.getDesc());
        vBox.getChildren().addAll(imageView, label);
        vBox.setAlignment(Pos.CENTER);
    }
}