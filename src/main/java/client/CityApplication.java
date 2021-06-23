package client;

import client.clientUtils.LocalizationTool;
import client.controllers.AuthorizationWindowController;
import client.controllers.MainWindowController;
import client.controllers.PopUpWindowController;
import client.clientUtils.CommandManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class CityApplication extends Application {

    private static Client client;
    private CommandManager commandManager;
    private Stage stage;
    private LocalizationTool localizationTool;
    private AuthorizationWindowController authcontroller;

    @Override
    public void start(Stage stage) throws IOException {
        try {
            localizationTool = new LocalizationTool(ResourceBundle.getBundle("client.clientUtils.bundles.gui", new Locale("ru", "RU")));
            this.stage = stage;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/authorizationWindow.fxml"));
            Parent root = loader.load();
            authcontroller = loader.getController();
            authcontroller.setClient(client);
            authcontroller.setLocalizationTool(localizationTool);
            authcontroller.setCityApplication(this);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void returnAuthWindow() throws IOException {
        start(stage);
    }

    public void startMainWindow() throws IOException {
            commandManager = new CommandManager(client, localizationTool);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/mainWindow.fxml"));
            Parent root = loader.load();
            MainWindowController controller = loader.getController();
            controller.setClient(client);
            controller.setCommandManager(commandManager);
            controller.setCityApplication(this);
            controller.setLocalizationTool(localizationTool);
            controller.setAuthorizationWindow(authcontroller);
            controller.init();
            FXMLLoader popUpWindowLoader = new FXMLLoader(CityApplication.class.getResource("/popUpWindow.fxml"));
            Parent popUpWindowRootNode = popUpWindowLoader.load();
            Scene popUpWindowScene = new Scene(popUpWindowRootNode);
            Stage popUpStage = new Stage();
            popUpStage.setScene(popUpWindowScene);
            PopUpWindowController popController = popUpWindowLoader.getController();
            popController.setClient(client);
            popController.setCityApplication(this);
            popController.setPopUpStage(popUpStage);
            popUpStage.setResizable(false);
            popUpStage.setTitle(localizationTool.getString("ProgName"));
            popController.setLocalizationTool(localizationTool);
            popController.changeLang();
            controller.setPopUpWindowController(popController);
            popController.setCommandManager(commandManager);
            popController.setParentController(controller);
            stage.setTitle(localizationTool.getString("ProgName"));
            stage.setScene(new Scene(root));
            controller.changeHello(client.getUser().getLogin());
            stage.setResizable(false);
            stage.show();
            controller.startUpdate();
    }

    public static boolean createClient() {
        boolean flag = true;
        try {
            client = new Client();
            client.start();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public static void main(String[] args) {
        createClient();
        Application.launch(args);
    }

    @Override
    public void stop() {
        System.exit(0);
    }
}