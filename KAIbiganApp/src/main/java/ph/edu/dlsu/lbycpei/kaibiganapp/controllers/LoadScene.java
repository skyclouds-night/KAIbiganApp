package ph.edu.dlsu.lbycpei.kaibiganapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class LoadScene {
    public abstract void loadProfileScene(ActionEvent event, String fxml, String firstName, String middleName, String lastName, String birthDate, String height, String weight, String email, String password, String healthCondition, String medication, String workout, String workoutFrequency, String workoutType);
}
