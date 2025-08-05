package ph.edu.dlsu.lbycpei.kaibiganapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class KAIbiganApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(KAIbiganApplication.class.getResource("loginview.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("KAI.bigan App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}