package sharedClasses.commands;

import sharedClasses.elementsOfCollection.City;
import sharedClasses.utils.*;

/**
 * Класс для команды show, которая выводит в стандартный поток вывода все элементы коллекции в строковом представлении.
 */

public class Show extends Command {
    private static final long serialVersionUID = 147364832874L;

    /**
     * Конструктор, присваивающий имя и дополнительную информацию о команде.
     */
    public Show(User user, CommandsControl commandsControl) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении", 0, false, user, commandsControl);
    }

    /**
     * Метод, исполняющий команду.
     *
     * @param ioForClient   объект, через который производится ввод/вывод.
     * @param priorityQueue хранимая коллекция.
     */
    public byte[] doCommand(IOInterface ioForClient, StorageInterface<City> priorityQueue) {
        Status status = Status.WARN;
        if (!priorityQueue.getCollection().isEmpty()) status = Status.SUCCESSFUL;
        return Serialization.serializeData(new WrapperForObjects(priorityQueue.getArrayList(), DescriptionForObject.ANSWER, status));
    }
}