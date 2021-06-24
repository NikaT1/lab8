package client.clientUtils;

import client.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import sharedClasses.commands.Command;
import sharedClasses.commands.CommandsControl;
import sharedClasses.elementsOfCollection.City;
import sharedClasses.utils.WrapperForObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class CommandManager {
    private final Client client;
    private final CommandsControl commandsControl;
    private final LocalizationTool localizationTool;

    public CommandManager(Client client, LocalizationTool localizationTool) {
        this.client = client;
        commandsControl = client.getCommandsControl();
        this.localizationTool = localizationTool;
    }

    public ObservableList<City> getCollectionForTable() {
        ObservableList<City> list;
        ArrayList<City> coll = new ArrayList<>();
        try {
            coll = (ArrayList<City>) client.execute(commandsControl.getCommands().get("show")).getObject();
        } catch (IOException e) {
            new InputAndOutput().output("FatalError", "Error", null, Alert.AlertType.ERROR);
            System.exit(-1);
        }
        list = FXCollections.observableArrayList(coll);
        return list;
    }

    public WrapperForObjects addToCollection(City city, String com) {
        WrapperForObjects wrapObject = null;
        Command command = commandsControl.getCommands().get(com);
        command.setCity(city);
        try {
            wrapObject = client.execute(command);
        } catch (IOException e) {
            new InputAndOutput().output("FatalError", "Error", null, Alert.AlertType.ERROR);
            System.exit(-1);
        }
        return wrapObject;
    }

    public Optional<String> askArg(String s) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(localizationTool.getString("Arguments"));
        dialog.setHeaderText(null);
        dialog.setContentText(localizationTool.getString(s));
        dialog.setResizable(false);
        return dialog.showAndWait();
    }

    public WrapperForObjects getInfoAboutCollectionWithArg(String com, String arg) {
        Command command = commandsControl.getCommands().get(com);
        command.setArgument(arg);
        WrapperForObjects wrapObject = null;
        try {
            wrapObject = client.execute(command);
        } catch (IOException e) {
            new InputAndOutput().output("FatalError", "Error", null, Alert.AlertType.ERROR);
            System.exit(-1);
        }
        return wrapObject;
    }

    public WrapperForObjects getInfoAboutCollectionWithArgs(String com, String arg, City city) {
        Command command = commandsControl.getCommands().get(com);
        command.setArgument(arg);
        command.setCity(city);
        WrapperForObjects wrapObject = null;
        try {
            wrapObject = client.execute(command);
        } catch (IOException e) {
            new InputAndOutput().output("FatalError", "Error", null, Alert.AlertType.ERROR);
            System.exit(-1);
        }
        return wrapObject;
    }

    public WrapperForObjects getInfoAboutCollection(String com) {
        Command command = commandsControl.getCommands().get(com);
        WrapperForObjects wrapObject = null;
        try {
            wrapObject = client.execute(command);
        } catch (IOException e) {
            new InputAndOutput().output("FatalError", "Error", null, Alert.AlertType.ERROR);
            System.exit(-1);
        }
        return wrapObject;
    }
}
