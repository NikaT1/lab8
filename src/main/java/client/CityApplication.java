package client;

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

public class CityApplication extends Application {

    private static Client client;
    private CommandManager commandManager;
    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/AuthorizationWindow.fxml"));
        Parent root = loader.load();
        AuthorizationWindowController controller = loader.getController();
        controller.setClient(client);
        controller.setCityApplication(this);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void returnAuthWindow() throws IOException {
            start(stage);
    }

    public void startMainWindow() throws IOException {
        commandManager = new CommandManager(client);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/MainWindow.fxml"));
        Parent root = loader.load();
        MainWindowController controller = loader.getController();
        controller.setClient(client);
        controller.setCommandManager(commandManager);
        controller.setCityApplication(this);
        controller.fillTable();
        FXMLLoader popUpWindowLoader = new FXMLLoader(CityApplication.class.getResource("/PopUpWindow.fxml"));
        Parent popUpWindowRootNode = popUpWindowLoader.load();
        Scene popUpWindowScene = new Scene(popUpWindowRootNode);
        Stage popUpStage = new Stage();
        popUpStage.setScene(popUpWindowScene);
        PopUpWindowController popController = popUpWindowLoader.getController();
        popController.setClient(client);
        popController.setCityApplication(this);
        popController.setPopUpStage(popUpStage);
        popUpStage.setResizable(false);
        popUpStage.setTitle("City DB");

        controller.setPopUpWindowController(popController);
        popController.setCommandManager(commandManager);
        popController.setParentController(controller);
        stage.setTitle("City DB");
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
