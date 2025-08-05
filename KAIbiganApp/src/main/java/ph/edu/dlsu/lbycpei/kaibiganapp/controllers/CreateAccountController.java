package ph.edu.dlsu.lbycpei.kaibiganapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    // Save data.txt in the current working directory (not in resources)
    private final String FILE_PATH = "data.txt";

    @FXML
    public void viewToAccountName(ActionEvent event) {
        // Save input values to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(firstName.getText()); writer.newLine();
            writer.write(middleName.getText()); writer.newLine();
            writer.write(lastName.getText()); writer.newLine();
            writer.write(birthDate.getText()); writer.newLine();
            writer.write(height.getText()); writer.newLine();
            writer.write(weight.getText()); writer.newLine();
            writer.write(email.getText()); writer.newLine();
            writer.write(password.getText()); writer.newLine();
            writer.write(healthCondition.getText()); writer.newLine();
            writer.write(medication.getText()); writer.newLine();
            writer.write(workout.getText()); writer.newLine();
            writer.write(workoutFrequency.getText()); writer.newLine();
            writer.write(workoutType.getText()); writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing to file: " + e.getMessage());
            return;
        }

        // Read data back from file to load the most recent account
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

                loadProfileScene("/ph/edu/dlsu/lbycpei/kaibiganapp/accountname.fxml", setFirstName, setMiddleName, setLastName, setBirthDate, setHeight,
                        setWeight, setEmail, setPassword, setHealthCondition, setMedication,
                        setWorkout, setWorkoutFrequency, setWorkoutType);
                return;
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading from file: " + e.getMessage());
        }

        // Optional: clear fields after saving
        clearFields();
    }

    private void clearFields() {
        firstName.clear();
        middleName.clear();
        lastName.clear();
        birthDate.clear();
        height.clear();
        weight.clear();
        email.clear();
        password.clear();
        healthCondition.clear();
        medication.clear();
        workout.clear();
        workoutFrequency.clear();
        workoutType.clear();
    }

    @Override
    protected void loadProfileScene(String fxml, String firstName, String middleName, String lastName, String birthDate,
                                    String height, String weight, String email, String password,
                                    String healthCondition, String medication, String workout,
                                    String workoutFrequency, String workoutType) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            if (loader.getLocation() == null) {
                System.out.println("FXML file not found. Please check the path.");
                return;
            }

            Parent root = loader.load();

            ViewController controller = loader.getController();
            controller.setUserData(firstName, middleName, lastName, birthDate, height, weight,
                    email, password, healthCondition, medication, workout, workoutFrequency, workoutType);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading the profile scene. Check your FXML path and controller setup.");
        }
    }
}
