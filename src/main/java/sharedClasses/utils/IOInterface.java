package sharedClasses.utils;

import javafx.scene.control.Alert;

public interface IOInterface {

    default void output(String s) {
    }

    default void output(String text, String title, String header, Alert.AlertType type) {
    }

    boolean getPrintMessages();

    void setPrintMessages(boolean printMessages);
}
