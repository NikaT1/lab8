package client.clientUtils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import sharedClasses.utils.IOInterface;

import java.util.Optional;

/**
 * Класс, осуществляющий ввод/вывод.
 */

public class InputAndOutput implements IOInterface {
    /**
     * Ввод пользователя.
     */
    private boolean printMessages;

    /**
     * Конструктор.
     *
     * @param printMessages флаг, отвечающий за вид взаимодействия с пользователем.
     */
    public InputAndOutput(boolean printMessages) {
        this.printMessages = printMessages;
    }
    public InputAndOutput(){}

    /**
     * Метод, устанавливающий вид взаимодействия с пользователем.
     *
     * @param printMessages флаг, отвечающий за вид взаимодействия с пользователем.
     */
    public void setPrintMessages(boolean printMessages) {
        this.printMessages = printMessages;
    }

    public boolean readAnswer(String text, String title, String header, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        ButtonType yes = new ButtonType("да");
        ButtonType no = new ButtonType("нет");
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(yes, no);
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.isPresent()) {
            if (answer.get() == yes) return true;
            else return false;
        }
        return false;
    }

    public void output(String text, String title, String header, Alert.AlertType type) {
        Alert alert = new Alert(type);
        if (type== Alert.AlertType.ERROR) alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public void longOutput(String text, String title, String header, Alert.AlertType type) {
        Alert alert = new Alert(type);
        if (type== Alert.AlertType.ERROR) alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        TextArea area = new TextArea(text);
        area.setWrapText(true);
        area.setEditable(false);
        alert.getDialogPane().setContent(area);
        alert.setResizable(true);
        alert.showAndWait();
    }

    public boolean getPrintMessages() {
        return printMessages;
    }
}
