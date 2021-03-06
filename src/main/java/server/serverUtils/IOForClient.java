package server.serverUtils;

import sharedClasses.utils.IOInterface;
import sharedClasses.utils.Serialization;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class IOForClient implements IOInterface {
    /**
     * Ввод пользователя.
     */
    private int port;
    private InetAddress addr;

    private DatagramSocket datagramSocket;
    /**
     * Флаг, отвечающий за вид взаимодействия с пользователем.
     */
    private boolean printMessages;

    public IOForClient(boolean printMessages) {
        this.printMessages = printMessages;
    }

    /**
     * Метод, устанавливающий вид взаимодействия с пользователем.
     *
     * @param printMessages флаг, отвечающий за вид взаимодействия с пользователем.
     */
    public void setPrintMessages(boolean printMessages) {
        this.printMessages = printMessages;
    }

    public void setDatagramSocket(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    public byte[] input(byte[] bytes) throws IOException {
        DatagramPacket datagramPacket = new DatagramPacket(bytes, 100000);
        datagramSocket.receive(datagramPacket);
        port = datagramPacket.getPort();
        addr = datagramPacket.getAddress();
        return bytes;
    }

    /**
     * Метод, отвечающий передачу информации пользователю.
     *
     * @param commandResult информация для вывода.
     */
    public boolean output(byte[] commandResult) {
        boolean flag = true;
        try {
            DatagramPacket result = new DatagramPacket(commandResult, commandResult.length, addr, port);
            datagramSocket.send(result);
        } catch (IOException e) {
            flag = false;
        }
        return flag;
    }

    public void output(String s) {
        byte[] bytes = Serialization.serializeData(s);
        DatagramPacket result = null;
        if (bytes != null) {
            result = new DatagramPacket(bytes, bytes.length, addr, port);
        }
        try {
            datagramSocket.send(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean getPrintMessages() {
        return printMessages;
    }

}
