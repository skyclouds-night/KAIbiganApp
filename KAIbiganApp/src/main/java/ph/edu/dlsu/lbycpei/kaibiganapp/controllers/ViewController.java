package ph.edu.dlsu.lbycpei.kaibiganapp.controllers;

import javafx.application.Platform;
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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewController extends LoadScene implements DataReceiver{

    private final String FILE_PATH = "users.csv";
    private String currentUserEmail;

    @FXML
    public void viewToPersonalInfo(ActionEvent event) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String header = reader.readLine();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 13 && values[6].equals(currentUserEmail)) {
                    loadProfileScene("/ph/edu/dlsu/lbycpei/kaibiganapp/Personal Info.fxml",
                            values[0], values[1], values[2], values[3], values[4], values[5],
                            values[6], values[7], values[8], values[9], values[10], values[11], values[12]);
                    return;
                }
            }
        }
    }

    @Override
    public void loadProfileScene(String fxml, String firstName, String middleName, String lastName,
                                 String birthDate, String height, String weight, String email, String password,
                                 String healthCondition, String medication, String workout,
                                 String workoutFrequency, String workoutType) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));

            if (loader.getLocation() == null) {
                System.out.println("FXML file not found. Please check the path.");
                return;
            }

            Parent root = loader.load();

            Object controller = loader.getController();

            // Use the controller depending on what was loaded
            if (controller instanceof PersonalInfoController) {
                ((PersonalInfoController) controller).setUserData(
                        firstName, middleName, lastName, birthDate,
                        height, weight, email, password,
                        healthCondition, medication, workout,
                        workoutFrequency, workoutType
                );
            } else if (controller instanceof IDCardController) {
                ((IDCardController) controller).setUserData(
                        firstName, middleName, lastName, birthDate,
                        height, weight, email, password,
                        healthCondition, medication, workout,
                        workoutFrequency, workoutType
                );
            }
            else if (controller instanceof SettingsController) {
                ((SettingsController) controller).setUserData(
                        firstName, middleName, lastName, birthDate,
                        height, weight, email, password,
                        healthCondition, medication, workout,
                        workoutFrequency, workoutType
                );
            } else {
                System.out.println("Unknown controller type: " + controller.getClass().getName());
            }

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading the profile scene. Check your FXML path and controller setup.");
        }
    }

    @Override
    public void setUserData(String firstName, String middleName, String lastName, String birthDate, String setheight,
                            String setweight, String email, String password, String healthCondition,
                            String medication, String workout, String workoutFrequency, String workoutType) {
        currentUserEmail = email;
    }

    @FXML
    public void viewToSettings(ActionEvent event) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String header = reader.readLine(); // skip the header
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] values = line.split(",");

                    if (values.length >= 13) {
                        loadProfileScene("/ph/edu/dlsu/lbycpei/kaibiganapp/IDCard.fxml",
                                values[0], values[1], values[2], values[3], values[4], values[5],
                                values[6], values[7], values[8], values[9], values[10], values[11], values[12]);
                        return;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void ExitApp (ActionEvent event) {
        Platform.exit();
    }

    @FXML
    public void viewToIDCard(ActionEvent actionEvent) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String header = reader.readLine(); // skip the header
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] values = line.split(",");

                    if (values.length >= 13) {
                        loadProfileScene("/ph/edu/dlsu/lbycpei/kaibiganapp/IDCard.fxml",
                                values[0], values[1], values[2], values[3], values[4], values[5],
                                values[6], values[7], values[8], values[9], values[10], values[11], values[12]);
                        return;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
