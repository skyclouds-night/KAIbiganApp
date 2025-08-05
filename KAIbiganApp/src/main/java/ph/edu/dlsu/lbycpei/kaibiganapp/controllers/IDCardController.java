package ph.edu.dlsu.lbycpei.kaibiganapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class IDCardController {
    public void loadIDCardData(String firstName, String lastName, String birthDate, String height, String weight) {
    }

    public void setUserData(String firstName, String middleName, String lastName, String birthDate,
                            String height, String weight, String email, String password,
                            String healthCondition, String medication, String workout,
                            String workoutFrequency, String workoutType) {

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
