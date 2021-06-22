package client.controllers;

import client.CityApplication;
import client.Client;
import client.clientUtils.InputAndOutput;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class AuthorizationWindowController {

    private Client client;
    private CityApplication cityApplication;
    private final InputAndOutput inputAndOutput = new InputAndOutput();

    @FXML
    private TextField userLogin;

    @FXML
    private PasswordField password;


    public void setClient(Client client) {
        this.client = client;
    }

    public void setCityApplication(CityApplication cityApplication) {
        this.cityApplication = cityApplication;
    }

    public void logButClicked() {
        try {
            if (client.startUser(userLogin.getText(), password.getText(), false)) {
                cityApplication.startMainWindow();
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            inputAndOutput.output("Произошла непредвиденная ошибка", "Ошибка", null, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    public void regButClicked() {
        try {
            if (client.startUser(userLogin.getText(), password.getText(), true)) {
                cityApplication.startMainWindow();
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            inputAndOutput.output("Произошла непредвиденная ошибка", "Ошибка", null, Alert.AlertType.ERROR);
        }
    }
}
