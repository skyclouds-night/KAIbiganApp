package ph.edu.dlsu.lbycpei.kaibiganapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginController extends LoadScene {

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label errorLabel;

    private final String CSV_PATH = "users.csv";

    @FXML
    public void viewToAccountName(ActionEvent event) {
        String enteredEmail = username.getText().trim();
        String enteredPassword = password.getText().trim();

        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_PATH))) {
            String line;

            // Skip header
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");

                if (userData.length != 13) continue;

                String setFirstName = userData[0];
                String setMiddleName = userData[1];
                String setLastName = userData[2];
                String setBirthDate = userData[3];
                String setHeight = userData[4];
                String setWeight = userData[5];
                String setEmail = userData[6];
                String setPassword = userData[7];
                String setHealthCondition = userData[8];
                String setMedication = userData[9];
                String setWorkout = userData[10];
                String setWorkoutFrequency = userData[11];
                String setWorkoutType = userData[12];

                if (enteredEmail.equals(setEmail) && enteredPassword.equals(setPassword)) {
                    loadProfileScene("/ph/edu/dlsu/lbycpei/kaibiganapp/accountname.fxml",
                            setFirstName, setMiddleName, setLastName, setBirthDate, setHeight,
                            setWeight, setEmail, setPassword, setHealthCondition, setMedication,
                            setWorkout, setWorkoutFrequency, setWorkoutType);
                    found = true;
                    break;
                }
            }

            if (!found) {
                errorLabel.setText("Invalid email or password.");
            }

        } catch (IOException e) {
            errorLabel.setText("Error reading CSV file.");
            e.printStackTrace();
        }
    }

    @FXML
    public void viewToCreateAccount(ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader(getClass().getResource("/ph/edu/dlsu/lbycpei/kaibiganapp/createaccount.fxml"));
        Parent root = load.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void loadProfileScene(String fxml, String firstName, String middleName, String lastName, String birthDate, String height, String weight, String email, String password, String healthCondition, String medication, String workout, String workoutFrequency, String workoutType) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));

            Parent root = loader.load();
            ViewController controller = loader.getController();
            controller.setUserData(firstName, middleName, lastName, birthDate,
                    height, weight, email, password, healthCondition, medication,
                    workout, workoutFrequency, workoutType);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading the profile scene.");
        }
    }
}
