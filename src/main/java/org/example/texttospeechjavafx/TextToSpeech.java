package org.example.texttospeechjavafx;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class TextToSpeech extends Application {
    // Define the dimensions of the application window
    private static final int APP_WIDTH = 375;
    private static final int APP_HEIGHT = 475;

    private TextArea textArea;
    private ComboBox<String> voices, rates, volumes;

    @Override
    public void start(Stage stage) {
        // Create the main scene
        Scene scene = createScene();

        // Load the stylesheet
        //scene.getStylesheets().add(getClass().getResource("/org/Style.css").toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(
                "style.css"
        )).toExternalForm());

        stage.setTitle("Text To Speech App");
        // Attach the scene to the stage and display it
        stage.setScene(scene);
        stage.show();
    }

    private Scene createScene() {
        // Create the main layout container
        VBox box = new VBox();
        box.getStyleClass().add("body");

        // Add the main label
        box.getChildren().add(createLabel("Text-To-Speech", "text-to-speech-label"));

        // Create and configure the text area
        textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.getStyleClass().add("text-area");

        // Add padding around the text area
        StackPane textAreaPane = new StackPane();
        textAreaPane.setPadding(new Insets(0, 15, 0, 15));
        textAreaPane.getChildren().add(textArea);
        box.getChildren().add(textAreaPane);

        // Add the settings (voice, rate, volume) to the layout
        box.getChildren().add(createSettingComponents());

        // Create the speak button and set its action
        Button speakButton = createImageButton();
        speakButton.setOnAction(e -> speakText());

        // Add the speak button to the layout
        StackPane speakButtonPane = new StackPane();
        speakButtonPane.setPadding(new Insets(40, 20, 0, 20));
        speakButtonPane.getChildren().add(speakButton);
        box.getChildren().add(speakButtonPane);

        // Return the scene containing all the components
        return new Scene(box, APP_WIDTH, APP_HEIGHT);
    }

    private Label createLabel(String text, String styleClass) {
        // Create a label with the specified text and style class
        Label label = new Label(text);
        label.getStyleClass().add(styleClass);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setAlignment(Pos.CENTER);
        return label;
    }

    private Button createImageButton() {
        // Create a button with the text "Speak"
        Button button = new Button("Speak");
        button.getStyleClass().add("speak-btn");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER);

        // Add an image to the button
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("speak.png"))));
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        button.setGraphic(imageView);
        return button;
    }

    private GridPane createSettingComponents() {
        // Create a grid layout for the settings
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10, 0, 0, 0));
        gridPane.setAlignment(Pos.CENTER);

        // Create and add labels for the settings
        Label voiceLabel = createLabel("Voice", "setting-label");
        Label rateLabel = createLabel("Rate", "setting-label");
        Label volumeLabel = createLabel("Volume", "setting-label");

        gridPane.add(voiceLabel, 0, 0);
        gridPane.add(rateLabel, 1, 0);
        gridPane.add(volumeLabel, 2, 0);

        // Center align the labels
        GridPane.setHalignment(voiceLabel, HPos.CENTER);
        GridPane.setHalignment(rateLabel, HPos.CENTER);
        GridPane.setHalignment(volumeLabel, HPos.CENTER);

        // Create and configure the combo boxes for the settings
        voices = new ComboBox<>();
        voices.getItems().addAll(TextToSpeechController.getVoices());
        voices.setValue(voices.getItems().getFirst());
        voices.getStyleClass().add("setting-combo-box");

        rates = new ComboBox<>();
        rates.getItems().addAll(TextToSpeechController.getSpeedRates());
        rates.setValue(rates.getItems().getFirst());
        rates.getStyleClass().add("setting-combo-box");

        volumes = new ComboBox<>();
        volumes.getItems().addAll(TextToSpeechController.getVolumeLevels());
        volumes.setValue(volumes.getItems().getFirst());
        volumes.getStyleClass().add("setting-combo-box");

        // Add the combo boxes to the grid
        gridPane.add(voices, 0, 1);
        gridPane.add(rates, 1, 1);
        gridPane.add(volumes, 2, 1);

        return gridPane;
    }

    private void speakText() {
        // Get the text and settings from the user inputs
        String msg = textArea.getText();
        String voice = voices.getValue();
        String rate = rates.getValue();
        String volume = volumes.getValue();

        // Call the speak method in the controller to perform text-to-speech
        TextToSpeechController.speak(msg, voice, rate, volume);
    }

    public static void main(String[] args) {
        launch();
    }
}