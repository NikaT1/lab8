package client;

import client.clientUtils.InputAndOutput;
import client.clientUtils.UserControl;
import javafx.scene.control.Alert;
import sharedClasses.commands.Command;
import sharedClasses.commands.CommandsControl;
import sharedClasses.utils.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.security.NoSuchAlgorithmException;
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

    public Client() {
        inputAndOutput = new InputAndOutput(true);
        serialization = new Serialization();
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
                inputAndOutput.output("Соединение не установлено", "Ошибка", null, Alert.AlertType.ERROR);
                flag = inputAndOutput.readAnswer("Повторить попытку? (yes/no)", "Повторное подключение", null, Alert.AlertType.INFORMATION);
            }
        }
    }

    public boolean startUser(String password, String login, boolean newUser) throws IOException, NoSuchAlgorithmException {
        boolean flag = sendUser(password, login, newUser);
        if (flag) {
            commandsControl = new CommandsControl(user);
        }
        return flag;
    }

    public CommandsControl getCommandControl() {
        return commandsControl;
    }

    private void connect(String host, int port) throws IOException {
        socketAddress = new InetSocketAddress(host, port);
        datagramChannel.connect(socketAddress);
    }

    private void sendCommand(Command command) throws IOException {
        /*if (s.length > 0 && commandsControl.getCommands().containsKey(s[0])) {
            currentCommand = commandsControl.getCommands().get(s[0]);
            if (currentCommand.getAmountOfArguments() > 0) {
                currentCommand.setArgument(s[1]);
            }
            if (currentCommand.isNeedCity()) {
                City city = userInput.readCity();
                city.setOwner(user.getLogin());
                currentCommand.setCity(city);
            }*/
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
            inputAndOutput.output("Произошла непредвиденная ошибка", "Внимание!", null, Alert.AlertType.ERROR);
            e.printStackTrace();
            //System.exit(-1);
        }
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        datagramChannel.register(selector, SelectionKey.OP_READ);
        selector.select();
        WrapperForObjects answer = outputAnswer();
        if (answer == null) {
            inputAndOutput.output("Ошибка сериализации команды; команда не выполнена", "Ошибка", null, Alert.AlertType.ERROR);
            return false;
            //  System.exit(-1);
        }
        if (answer.getStatus() == Status.ERROR) {
            inputAndOutput.output((String) answer.getObject(), "Внимание!", null, Alert.AlertType.INFORMATION);
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
        try {
            sendCommand(command);
        } catch (Exception e) {
            inputAndOutput.output("Произошла непредвиденная ошибка", "Внимание!", null, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
        datagramChannel.register(selector, SelectionKey.OP_READ);
        selector.select();
        return outputAnswer();
    }
}
