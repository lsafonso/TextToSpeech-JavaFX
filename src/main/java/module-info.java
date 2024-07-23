module org.example.texttospeechjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jdi;
    requires freetts;


    opens org.example.texttospeechjavafx to javafx.fxml;
    exports org.example.texttospeechjavafx;
}