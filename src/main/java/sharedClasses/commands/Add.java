package sharedClasses.commands;


import sharedClasses.elementsOfCollection.City;
import sharedClasses.utils.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.NoSuchElementException;

/**
 * Класс для команды add, которая добавляет новый элемент в коллекцию.
 */

public class Add extends Command {
    private static final long serialVersionUID = 147364832874L;

    /**
     * Конструктор, присваивающий имя и дополнительную информацию о команде.
     */
    public Add(User user, CommandsControl commandsControl) {
        super("add", "добавить новый элемент в коллекцию", 0, true, user, commandsControl);
    }

    /**
     * Метод, исполняющий команду.
     *
     * @param ioForClient   объект, через который производится ввод/вывод.
     * @param priorityQueue хранимая коллекция.
     */
    public byte[] doCommand(IOInterface ioForClient, StorageInterface<City> priorityQueue) {
        StringBuilder result = new StringBuilder();
        Status status = Status.ERROR;
        try {
            City city = this.getCity();
            city.setCreationDate(LocalDate.now());
            city.setOwner(getUser().getLogin());
            synchronized (priorityQueue.getCollection()) {
                priorityQueue.addToCollection(city, getUser());
            }
            result.append("OkAdd");
            status = Status.SUCCESSFUL;
        } catch (NoSuchElementException e) {
            result.append("ScriptError");
        } catch (ClassNotFoundException | SQLException | ParseException e) {
            result.append("DBError");
        }
        return Serialization.serializeData(new WrapperForObjects(result.toString(), DescriptionForObject.ANSWER, status));
    }
}
