package ph.edu.dlsu.lbycpei.kaibiganapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PersonalInfoController {

    @FXML
    private Label weight;

    @FXML
    private Label height;

    @FXML
    private Label BMI;

    @FXML
    private Label medicalHistory;

    @FXML
    private Label medicalInformation;

    @FXML
    private Label medicalAdvice;

    private final String FILE_PATH = "data.txt";

    public void setUserData(String firstName, String middleName, String lastName, String birthDate, String setheight, String setweight, String email, String password, String healthCondition, String medication, String workout, String workoutFrequency, String workoutType) {
        weight.setText(setweight);
        height.setText(setheight);
        double BMIValue = Double.parseDouble(weight.getText())/Math.pow((Double.parseDouble(height.getText())/100),2);
        String setBMI = String.valueOf(BMIValue);
        BMI.setText(setBMI);
        medicalHistory.setText(healthCondition);
//        medicalInformation.setText();
//        medicalAdvice.setText("Medication: " + medication);

    }

    @FXML
    public void MainMenuButton (ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader(getClass().getResource("/ph/edu/dlsu/lbycpei/kaibiganapp/accountname.fxml"));
        Parent root = load.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
