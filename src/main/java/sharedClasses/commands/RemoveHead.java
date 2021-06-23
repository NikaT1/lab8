package sharedClasses.commands;


import sharedClasses.elementsOfCollection.City;
import sharedClasses.utils.*;

import java.sql.SQLException;

/**
 * Класс для команды remove_head, которая выводит и удаляет первый элемент из коллекции.
 */

public class RemoveHead extends Command {
    private static final long serialVersionUID = 147364832874L;

    /**
     * Конструктор, присваивающий имя и дополнительную информацию о команде.
     */
    public RemoveHead(User user, CommandsControl commandsControl) {
        super("remove_head", "вывести первый элемент коллекции и удалить его", 0, false, user, commandsControl);
    }

    /**
     * Метод, исполняющий команду.
     *
     * @param ioForClient   объект, через который производится ввод/вывод.
     * @param priorityQueue хранимая коллекция.
     */
    public byte[] doCommand(IOInterface ioForClient, StorageInterface<City> priorityQueue) {
        String result;
        Status status = Status.WARN;
        synchronized (priorityQueue.getCollection()) {
            try {
                if (priorityQueue.getCollection().isEmpty()) result = "Empty";
                else {
                    City city = priorityQueue.pollFromQueue();
                    boolean flag = priorityQueue.remove(city, getUser());
                    if (flag) {
                        result = "OkRemove";
                        status = Status.SUCCESSFUL;
                    } else {
                        result = "NotRemove";
                        priorityQueue.getCollection().add(city);
                    }
                }
            } catch (SQLException e) {
                result = "IDError";
                status = Status.ERROR;
            }
        }
        return Serialization.serializeData(new WrapperForObjects(result, DescriptionForObject.ANSWER, status));
    }
}
