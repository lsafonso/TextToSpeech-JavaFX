module org.example.texttospeechjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.texttospeechjavafx to javafx.fxml;
    exports org.example.texttospeechjavafx;
}