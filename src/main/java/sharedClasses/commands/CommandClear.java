package sharedClasses.commands;

import sharedClasses.elementsOfCollection.City;
import sharedClasses.utils.*;

import java.sql.SQLException;

/**
 * Класс для команды clear, которая очищает коллекцию.
 */

public class CommandClear extends Command {
    private static final long serialVersionUID = 147364832874L;

    /**
     * Конструктор, присваивающий имя и дополнительную информацию о команде.
     */
    public CommandClear(User user, CommandsControl commandsControl) {
        super("clear", "очистить коллекцию", 0, false, user, commandsControl);
    }

    /**
     * Метод, исполняющий команду.
     *
     * @param ioForClient   объект, через который производится ввод/вывод.
     * @param priorityQueue хранимая коллекция.
     */
    public byte[] doCommand(IOInterface ioForClient, StorageInterface<City> priorityQueue) {
        String s = "Элементы пользователя успешно удалены";
        Status status = Status.ERROR;
        synchronized (priorityQueue.getCollection()) {
            try {
                priorityQueue.clear(getUser());
                status = Status.SUCCESSFUL;
            } catch (SQLException e) {
                s = "Элементы пользователя не удалены, так как возникла проблема с подключением к БД";
            }
        }
        return Serialization.serializeData(new WrapperForObjects(s, DescriptionForObject.ANSWER, status));
    }
}
