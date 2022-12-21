package agh.ics.oop;

import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public interface IWindow {
    void runApp(Stage primaryStage, DataParameters currentConfig) throws IOException;
}
