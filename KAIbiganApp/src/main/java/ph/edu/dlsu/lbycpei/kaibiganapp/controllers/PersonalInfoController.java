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

public class PersonalInfoController extends LoadScene implements DataReceiver{

    private String currentUserEmail;
    private final String FILE_PATH = "users.csv";

    @FXML
    private Label weight2;

    @FXML
    private Label height2;

    @FXML
    private Label BMI;

    @FXML
    private Label medicalConditions;

    @FXML
    private Label medicalInformation;

    @FXML
    private Label medicalAdvice;

    @Override
    public void setUserData(String firstName, String middleName, String lastName, String birthDate, String setheight, String setweight, String email, String password, String healthCondition, String medication, String workout, String workoutFrequency, String workoutType) {
        weight2.setText(setweight);
        height2.setText(setheight);
        double BMIValue = Double.parseDouble(weight2.getText())/Math.pow((Double.parseDouble(height2.getText())/100),2);
        String setBMI = String.valueOf(BMIValue);
        BMI.setText(setBMI);
        medicalConditions.setText(healthCondition);
//        medicalInformation.setText();
//        medicalAdvice.setText("Medication: " + medication);

        currentUserEmail = email;
    }

    @FXML
    public void MainMenuButton(ActionEvent event) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String header = reader.readLine();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 13 && values[6].equals(currentUserEmail)) {
                    loadProfileScene(event, "/ph/edu/dlsu/lbycpei/kaibiganapp/accountname.fxml",
                            values[0], values[1], values[2], values[3], values[4], values[5],
                            values[6], values[7], values[8], values[9], values[10], values[11], values[12]);
                    return;
                }
            }
        }
    }

    @Override
    public void loadProfileScene(ActionEvent event, String fxml, String firstName, String middleName, String lastName, String birthDate, String height, String weight, String email, String password, String healthCondition, String medication, String workout, String workoutFrequency, String workoutType) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));

            Parent root = loader.load();
            ViewController controller = loader.getController();
            controller.setUserData(firstName, middleName, lastName, birthDate,
                    height, weight, email, password, healthCondition, medication,
                    workout, workoutFrequency, workoutType);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading the profile scene.");
        }
    }
}
