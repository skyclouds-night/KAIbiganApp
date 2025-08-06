package ph.edu.dlsu.lbycpei.kaibiganapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.IOException;

public class IDCardController implements DataReceiver {

    @FXML
    private Label firstName2;
    @FXML
    private Label middleName2;
    @FXML
    private Label lastName2;
    @FXML
    private Label birthDate2;
    @FXML
    private Label height2;
    @FXML
    private Label weight2;
    @FXML
    private Label medicalConditions;
    @FXML
    private Label emergencyName;
    @FXML
    private Label emergencyContact;
    @FXML
    private TextField emergencyName1;
    @FXML
    private TextField emergencyContact1;

    @Override
    public void setUserData(String firstName, String middleName, String lastName, String birthDate,
                            String height, String weight, String email, String password,
                            String healthCondition, String medication, String workout,
                            String workoutFrequency, String workoutType) {
        firstName2.setText(firstName);
        middleName2.setText(middleName);
        lastName2.setText(lastName);
        birthDate2.setText(birthDate);
        height2.setText(height);
        weight2.setText(weight);
        medicalConditions.setText(healthCondition);
        this.emergencyName.setText(emergencyName1.getText());
        this.emergencyContact.setText(emergencyContact1.getText());
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
