package sharedClasses.commands;


import sharedClasses.elementsOfCollection.City;
import sharedClasses.utils.*;


/**
 * Класс для команды help, которая выводит справку по доступным коммандам.
 */

public class Help extends Command {

    private static final long serialVersionUID = 147364832874L;

    /**
     * Конструктор, присваивающий имя и дополнительную информацию о команде.
     */
    public Help(User user, CommandsControl commandsControl) {
        super("help", "вывести справку по доступным командам", 0, false, user, commandsControl);
    }

    /**
     * Метод, исполняющий команду.
     *
     * @param ioForClient   объект, через который производится ввод/вывод.
     * @param priorityQueue хранимая коллекция.
     */
    public byte[] doCommand(IOInterface ioForClient, StorageInterface<City> priorityQueue) {
        Status status = Status.SUCCESSFUL;
        return Serialization.serializeData(new WrapperForObjects("HelpForCommands", DescriptionForObject.ANSWER, status));
    }
}
