package ph.edu.dlsu.lbycpei.kaibiganapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SettingsController extends LoadScene implements DataReceiver {
    private String currentUserEmail;
    private final String FILE_PATH = "users.csv";

    @FXML
    private TextField updateHeight;

    @FXML
    private TextField updateWeight;

    @FXML
    private TextField updateEmail;

    @FXML
    private TextField updatePassword;

    @Override
    public void setUserData(String firstName, String middleName, String lastName, String birthDate, String setheight,
                            String setweight, String email, String password, String healthCondition,
                            String medication, String workout, String workoutFrequency, String workoutType) {

        currentUserEmail = email;
        updateHeight.setText(setheight);
        updateWeight.setText(setweight);
        updateEmail.setText(email);
        updatePassword.setText(password);
    }

    @FXML
    public void MainMenuButton (ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader(getClass().getResource("/ph/edu/dlsu/lbycpei/kaibiganapp/accountname.fxml"));
        Parent root = load.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void saveChanges(ActionEvent event) throws IOException {
        List<String> updatedLines = new ArrayList<>();
        boolean updated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String header = reader.readLine();
            updatedLines.add(header);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length < 13) continue;

                if (values[6].equals(currentUserEmail)) {
                    values[4] = updateHeight.getText().trim();
                    values[5] = updateWeight.getText().trim();
                    values[6] = updateEmail.getText().trim();
                    values[7] = updatePassword.getText().trim();

                    updated = true;
                    currentUserEmail = values[6]; // Update reference
                }

                updatedLines.add(String.join(",", values));
            }
        }

        if (updated) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (String line : updatedLines) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        }


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
