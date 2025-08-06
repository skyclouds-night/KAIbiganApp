package ph.edu.dlsu.lbycpei.kaibiganapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SettingsController {

    @FXML
    private TextField updateHeight;

    @FXML
    private TextField updateWeight;

    @FXML
    private TextField updateEmail;

    @FXML
    private TextField updatePassword;


    @FXML
    public void MainMenuButton (ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader(getClass().getResource("/ph/edu/dlsu/lbycpei/kaibiganapp/accountname.fxml"));
        Parent root = load.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
