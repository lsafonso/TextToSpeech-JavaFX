package org.example.texttospeechjavafx;

import com.sun.jdi.PrimitiveValue;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TextToSpeech extends Application {
    private static final int APP_WIDTH = 375;
    private static final int APP_HEIGHT = 475;

    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = createScene();

        //scene.getStylesheets().add(getClass().getResource("/org/Style.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource(
                "style.css"
        ).toExternalForm());

        stage.setTitle("Text-To-Speech App");

        stage.setScene(scene);
        stage.show();
    }

    private Scene createScene(){
        VBox box = new VBox();
        box.getStyleClass().add("body");

        Label textToSpeechLabel = new Label("Text-To-Speech");
        textToSpeechLabel.setMaxWidth(Double.MAX_VALUE);
        textToSpeechLabel.setAlignment(Pos.CENTER);
        box.getChildren().add(textToSpeechLabel);
        return new Scene(box, APP_WIDTH, APP_HEIGHT);
    }

    public static void main(String[] args) {
        launch();
    }
}