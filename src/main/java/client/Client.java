package client;

import client.clientUtils.InputAndOutput;
import client.clientUtils.ScriptInput;
import client.clientUtils.UserControl;
import javafx.scene.control.Alert;
import sharedClasses.commands.Command;
import sharedClasses.commands.CommandsControl;
import sharedClasses.elementsOfCollection.City;
import sharedClasses.utils.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Client {
    private Selector selector;
    private DatagramChannel datagramChannel;
    private SocketAddress socketAddress;
    private final Serialization serialization;
    private CommandsControl commandsControl;
    private final InputAndOutput inputAndOutput;
    private final String host = "localhost";
    private User user;
    private CityApplication cityApplication;
    private final HashSet<String> paths = new HashSet<>();

    public Client() {
        inputAndOutput = new InputAndOutput(true);
        serialization = new Serialization();
    }

    public void setCityApplication(CityApplication cityApplication) {
        this.cityApplication = cityApplication;
    }

    private void initialize() throws IOException {
        selector = Selector.open();
        datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);
    }

    public void start() {
        boolean flag = true;
        while (flag) {
            try {
                initialize();
                connect(host, 1200);
                flag = false;
            } catch (IOException e) {
                inputAndOutput.output("ConnectError", "Error", null, Alert.AlertType.ERROR);
                flag = inputAndOutput.readAnswer("TryToConnect", "Warn", null, Alert.AlertType.INFORMATION);
                if (!flag) cityApplication.stop();
            }
        }
    }

    public boolean startUser(String password, String login, boolean newUser) {
        try {
            boolean flag = sendUser(password, login, newUser);
            if (flag) {
                commandsControl = new CommandsControl(user);
            }
            return flag;
        } catch (Exception e) {
            inputAndOutput.output("AuthError", "Error", null, Alert.AlertType.ERROR);
            return false;
        }
    }

    public CommandsControl getCommandsControl() {
        return commandsControl;
    }

    private void connect(String host, int port) throws IOException {
        socketAddress = new InetSocketAddress(host, port);
        datagramChannel.connect(socketAddress);
    }

    private void sendCommand(Command command) throws IOException {
        WrapperForObjects object = new WrapperForObjects(command, DescriptionForObject.COMMAND);
        byte[] ser = Serialization.serializeData(object);
        if (ser != null) {
            ByteBuffer buffer = ByteBuffer.wrap(ser);
            datagramChannel.send(buffer, socketAddress);
        }

    }

    private WrapperForObjects outputAnswer() throws IOException {
        byte[] bytes = new byte[100000];
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        socketAddress = datagramChannel.receive(buffer);
        bytes = buffer.array();
        return (WrapperForObjects) serialization.deserializeData(bytes);
    }

    public void executeScript(File script) throws IOException, ParseException, InvalidAlgorithmParameterException {
        if (!paths.add(script.getAbsolutePath())) {
            paths.clear();
            throw new InvalidAlgorithmParameterException();
        } else {
            FileInputStream fileInputStream = new FileInputStream(script);
            BufferedInputStream file = new BufferedInputStream(fileInputStream);
            Scanner scanner = new Scanner(file);
            ScriptInput scriptInput = new ScriptInput(scanner);
            while (scanner.hasNext()) {
                CommandsControl commandsControl = getCommandsControl();
                String[] s = scanner.nextLine().split(" ");
                if (!s[0].equals("execute_script")) {
                    if (commandsControl.getCommands().containsKey(s[0])) {
                        Command currentCommand = commandsControl.getCommands().get(s[0]);
                        if (currentCommand.getAmountOfArguments() > 0) {
                            currentCommand.setArgument(s[1]);
                        }
                        if (currentCommand.isNeedCity()) {
                            City city = scriptInput.readCity();
                            city.setOwner(getUser().getLogin());
                            currentCommand.setCity(city);
                        }
                        execute(currentCommand);
                    } else {
                        paths.clear();
                        throw new InvalidAlgorithmParameterException();
                    }
                } else {
                    executeScript(new File(s[1]));
                }
            }
        }
        paths.remove(script.getAbsolutePath());
    }

    private boolean sendUser(String login, String password, boolean newUser) throws NoSuchAlgorithmException, IOException {
        UserControl userControl = new UserControl();
        WrapperForObjects wrapUser = new WrapperForObjects(userControl.logIn(login, password), userControl.getTypeOfUser(newUser));
        Set keys = selector.selectedKeys();
        keys.clear();
        datagramChannel.register(selector, SelectionKey.OP_WRITE);
        try {
            byte[] ser = Serialization.serializeData(wrapUser);
            if (ser != null) {
                ByteBuffer buffer = ByteBuffer.wrap(ser);
                datagramChannel.send(buffer, socketAddress);
            }
        } catch (Exception e) {
            inputAndOutput.output("FatalError", "Error", null, Alert.AlertType.ERROR);
            cityApplication.stop();
        }
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {}
        datagramChannel.register(selector, SelectionKey.OP_READ);
        selector.select();
        WrapperForObjects answer = outputAnswer();
        if (answer == null) {
            inputAndOutput.output("SerialError", "Error", null, Alert.AlertType.ERROR);
            return false;
        }
        if (answer.getStatus() == Status.ERROR) {
            inputAndOutput.output((String) answer.getObject(), "Warn", null, Alert.AlertType.INFORMATION);
            return false;
        }
        user = (User) wrapUser.getObject();
        return true;
    }

    public User getUser() {
        return user;
    }

    public WrapperForObjects execute(Command command) throws IOException {
        Set keys = selector.selectedKeys();
        keys.clear();
        datagramChannel.register(selector, SelectionKey.OP_WRITE);
        sendCommand(command);
        datagramChannel.register(selector, SelectionKey.OP_READ);
        selector.select();
        return outputAnswer();
    }
}
