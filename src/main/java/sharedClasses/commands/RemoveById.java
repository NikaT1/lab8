package sharedClasses.commands;


import sharedClasses.elementsOfCollection.City;
import sharedClasses.utils.*;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для команды remove_by_id, которая удаляет элемент из коллекции по его id.
 */

public class RemoveById extends Command {
    private static final long serialVersionUID = 147364832874L;

    /**
     * Конструктор, присваивающий имя и дополнительную информацию о команде.
     */
    public RemoveById(User user, CommandsControl commandsControl) {
        super("remove_by_id id", "удалить элемент из коллекции по его id", 1, false, user, commandsControl);
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
                int id = Integer.parseInt(this.getArgument());
                List<City> cities = priorityQueue.getCollection().stream().
                        filter(city -> city.getId() == id).
                        collect(Collectors.toList());
                if (cities.size() > 0) {
                    boolean flag = priorityQueue.remove(cities.get(0), getUser());
                    if (flag) {
                        result = "OkRemove";
                        status = Status.SUCCESSFUL;
                    } else result = "NotRemove";
                } else result = "WrongId";
            } catch (NumberFormatException e) {
                result = "IDError";
                status = Status.ERROR;
            } catch (SQLException e) {
                result = "DBError";
                status = Status.ERROR;
            }
        }
        return Serialization.serializeData(new WrapperForObjects(result, DescriptionForObject.ANSWER, status));
    }
}
