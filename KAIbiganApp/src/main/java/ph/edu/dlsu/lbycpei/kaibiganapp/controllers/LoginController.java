package ph.edu.dlsu.lbycpei.kaibiganapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.scene.control.Label;

import javax.swing.text.View;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginController extends LoadScene {

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label errorLabel;

    private final String FILE_PATH = "data.txt";


    @FXML
    public void viewToAccountName(ActionEvent event) throws IOException {
        System.out.println("Login button pressed!");

        String enteredEmail = username.getText().trim();
        String enteredPassword = password.getText().trim();

        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) lines.add(line);
            }

            // Each account has 13 lines of data
            for (int i = 0; i <= lines.size() - 13; i += 13) {
                String setFirstName = lines.get(i);
                String setMiddleName = lines.get(i + 1);
                String setLastName = lines.get(i + 2);
                String setBirthDate = lines.get(i + 3);
                String setHeight = lines.get(i + 4);
                String setWeight = lines.get(i + 5);
                String setEmail = lines.get(i + 6);
                String setPassword = lines.get(i + 7);
                String setHealthCondition = lines.get(i + 8);
                String setMedication = lines.get(i + 9);
                String setWorkout = lines.get(i + 10);
                String setWorkoutFrequency = lines.get(i + 11);
                String setWorkoutType = lines.get(i + 12);

                if (enteredEmail.equals(setEmail) && enteredPassword.equals(setPassword)) {
                    loadProfileScene("/ph/edu/dlsu/lbycpei/kaibiganapp/accountname.fxml", setFirstName, setMiddleName, setLastName, setBirthDate, setHeight,
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
            errorLabel.setText("Error reading file.");
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

            if (loader.getLocation() == null) {
                System.out.println("FXML file not found. Please check the path.");
                return;
            }

            Parent root = loader.load();

            ViewController controller = loader.getController();
            controller.setUserData(
                    firstName, middleName, lastName, birthDate,
                    height, weight, email, password,
                    healthCondition, medication, workout,
                    workoutFrequency, workoutType
            );

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading the profile scene. Check your FXML path and controller setup.");
        }
    }
}

