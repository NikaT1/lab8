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

    public CommandManager(Client client) {
        this.client = client;
        commandsControl = client.getCommandControl();
    }

    public ObservableList<City> getCollectionForTable() {
        ObservableList<City> list;
        ArrayList<City> coll = new ArrayList<>();
        try {
            coll = (ArrayList<City>) client.execute(commandsControl.getCommands().get("show")).getObject();
        } catch (IOException e) {
            System.exit(-1);
        }
        list = FXCollections.observableArrayList(coll);
        return list;
    }

    public ObservableList<City> addToCollection(City city, String com) {
        ObservableList<City> list;
        ArrayList<City> coll = new ArrayList<>();
        Command command = commandsControl.getCommands().get(com);
        command.setCity(city);
        try {
            coll = (ArrayList<City>) client.execute(command).getObject();
        } catch (IOException e) {
            System.exit(-1);
        }
        list = FXCollections.observableArrayList(coll);
        return list;
    }

    public Optional<String> askArg(String s) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Аргументы");
        dialog.setHeaderText(null);
        dialog.setContentText(s);
        dialog.setResizable(false);
        Optional<String> result = dialog.showAndWait();
        return result;
    }

    public WrapperForObjects getInfoAboutCollectionWithArg(String com, String arg) {
        Command command = commandsControl.getCommands().get(com);
        command.setArgument(arg);
        WrapperForObjects wrapObject = null;
        try {
            wrapObject = client.execute(command);
        } catch (IOException e) {
            System.exit(-1);
        }
        return wrapObject;
    }

    public ObservableList<City> getInfoAboutCollectionWithArgs(String com, String arg, City city) {
        Command command = commandsControl.getCommands().get(com);
        command.setArgument(arg);
        command.setCity(city);
        WrapperForObjects wrapObject = null;
        try {
            wrapObject = client.execute(command);
        } catch (IOException e) {
            System.exit(-1);
        }
        ObservableList<City> list;
        ArrayList<City> coll = new ArrayList<>();
        coll = (ArrayList<City>) wrapObject.getObject();
        list = FXCollections.observableArrayList(coll);
        return list;
    }

    public WrapperForObjects getInfoAboutCollection(String com) {
        Command command = commandsControl.getCommands().get(com);
        WrapperForObjects wrapObject = null;
        try {
            wrapObject = client.execute(command);
        } catch (IOException e) {
            System.exit(-1);
        }
        return wrapObject;
    }
}
