package ph.edu.dlsu.lbycpei.kaibiganapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class CreateAccountController extends LoadScene {

    @FXML private TextField firstName;
    @FXML private TextField middleName;
    @FXML private TextField lastName;
    @FXML private TextField birthDate;
    @FXML private TextField height;
    @FXML private TextField weight;
    @FXML private TextField email;
    @FXML private TextField password;
    @FXML private TextField healthCondition;
    @FXML private TextField medication;
    @FXML private TextField workout;
    @FXML private TextField workoutFrequency;
    @FXML private TextField workoutType;

    private final String CSV_FILE = "users.csv";

    @FXML
    public void viewToAccountName(ActionEvent event) {
        boolean fileExists = new File(CSV_FILE).exists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE, true))) {
            if (!fileExists) {
                writer.write("FirstName,MiddleName,LastName,BirthDate,Height,Weight,Email,Password,HealthCondition,Medication,Workout,WorkoutFrequency,WorkoutType");
                writer.newLine();
            }

            String csvLine = String.join(",",
                    escapeCSV(firstName.getText()),
                    escapeCSV(middleName.getText()),
                    escapeCSV(lastName.getText()),
                    escapeCSV(birthDate.getText()),
                    escapeCSV(height.getText()),
                    escapeCSV(weight.getText()),
                    escapeCSV(email.getText()),
                    escapeCSV(password.getText()),
                    escapeCSV(healthCondition.getText()),
                    escapeCSV(medication.getText()),
                    escapeCSV(workout.getText()),
                    escapeCSV(workoutFrequency.getText()),
                    escapeCSV(workoutType.getText())
            );

            writer.write(csvLine);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing to CSV: " + e.getMessage());
            return;
        }

        loadProfileScene(event, "/ph/edu/dlsu/lbycpei/kaibiganapp/accountname.fxml",
                firstName.getText(), middleName.getText(), lastName.getText(), birthDate.getText(),
                height.getText(), weight.getText(), email.getText(), password.getText(),
                healthCondition.getText(), medication.getText(), workout.getText(),
                workoutFrequency.getText(), workoutType.getText());
    }

    private String escapeCSV(String value) {
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            value = value.replace("\"", "\"\"");
            return "\"" + value + "\"";
        }
        return value;
    }

    @Override
    public void loadProfileScene(ActionEvent event, String fxml, String firstName, String middleName, String lastName, String birthDate,
                                 String height, String weight, String email, String password,
                                 String healthCondition, String medication, String workout,
                                 String workoutFrequency, String workoutType) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();

            ViewController controller = loader.getController();
            controller.setUserData(firstName, middleName, lastName, birthDate, height, weight,
                    email, password, healthCondition, medication, workout, workoutFrequency, workoutType);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading profile scene.");
        }
    }
}
