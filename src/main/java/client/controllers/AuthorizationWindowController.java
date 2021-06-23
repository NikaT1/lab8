package client.controllers;

import client.CityApplication;
import client.Client;
import client.clientUtils.InputAndOutput;
import client.clientUtils.LocalizationTool;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class AuthorizationWindowController {

    private Client client;
    private CityApplication cityApplication;
    private final InputAndOutput inputAndOutput = new InputAndOutput();
    private LocalizationTool localizationTool;

    @FXML
    private TextField userLogin;
    @FXML
    private PasswordField password;
    @FXML
    private Label authLabel;
    @FXML
    private Button regBut;
    @FXML
    private Button logBut;

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
            inputAndOutput.output("FatalError", "Error", null, Alert.AlertType.ERROR);
        }
    }

    public void regButClicked() {
        try {
            if (client.startUser(userLogin.getText(), password.getText(), true)) {
                cityApplication.startMainWindow();
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            inputAndOutput.output("FatalError", "Error", null, Alert.AlertType.ERROR);
        }
    }

    public void setLocalizationTool(LocalizationTool localizationTool) {
        this.localizationTool = localizationTool;
    }

    public void changeLang() {
        logBut.setText(localizationTool.getString("logBut"));
        regBut.setText(localizationTool.getString("regBut"));
        authLabel.setText(localizationTool.getString("authLabel"));
        password.setPromptText(localizationTool.getString("passLabel"));
        userLogin.setPromptText(localizationTool.getString("logLabel"));
    }
}
