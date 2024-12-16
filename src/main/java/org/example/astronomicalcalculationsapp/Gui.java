package org.example.astronomicalcalculationsapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Gui extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 720, 640);
        scene.getStylesheets().add(getClass().getResource("/org/example/astronomicalcalculationsapp/styles.css").toExternalForm());
        stage.setTitle("Astronomical Calculations App");
        stage.setScene(scene);
        stage.show();
    }

}
