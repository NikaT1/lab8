package sharedClasses.commands;


import sharedClasses.elementsOfCollection.City;
import sharedClasses.utils.*;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для команды update, которая обновляет значение элемента коллекции по его id.
 */

public class UpdateId extends Command {
    private static final long serialVersionUID = 147364832874L;

    /**
     * Конструктор, присваивающий имя и дополнительную информацию о команде.
     */
    public UpdateId(User user, CommandsControl commandsControl) {
        super("update id {element}", "обновить значение элемента коллекции, id которого равен заданному", 1, true, user, commandsControl);
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
                int id = Integer.parseInt(getArgument());
                List<City> cities = priorityQueue.getCollection().stream().
                        filter(city -> city.getId() == id).
                        collect(Collectors.toList());
                if (cities.size() > 0) {
                    City city = getCity();
                    if (priorityQueue.update(cities.get(0), city, id, getUser())) {
                        result = "OkUpdate";
                        status = Status.SUCCESSFUL;
                    } else result = "NotUpdate";
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
