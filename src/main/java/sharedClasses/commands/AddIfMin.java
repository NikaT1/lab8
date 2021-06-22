package sharedClasses.commands;

import sharedClasses.elementsOfCollection.City;
import sharedClasses.utils.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Comparator;

/**
 * Класс для команды add_if_min, которая добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего
 * элемента этой коллекции.
 */

public class AddIfMin extends Command {
    private static final long serialVersionUID = 147364832874L;

    /**
     * Конструктор, присваивающий имя и дополнительную информацию о команде.
     */
    public AddIfMin(User user, CommandsControl commandsControl) {
        super("add_if_min {element}", "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции", 0, true, user, commandsControl);
    }

    /**
     * Метод, исполняющий команду.
     *
     * @param ioForClient   объект, через который производится ввод/вывод.
     * @param priorityQueue хранимая коллекция.
     */
    public byte[] doCommand(IOInterface ioForClient, StorageInterface<City> priorityQueue) {
        StringBuilder result = new StringBuilder();
        Status status = Status.WARN;
        try {
            synchronized (priorityQueue.getCollection()) {
                City city = this.getCity();
                city.setOwner(getUser().getLogin());
                if (priorityQueue.getCollection().stream().min(Comparator.comparingInt(City::getArea)).isPresent()) {
                    City minCity = priorityQueue.getCollection().stream().min(Comparator.comparingInt(City::getArea)).get();
                    if (city.getArea() < minCity.getArea()) {
                        priorityQueue.addToCollection(city, getUser());
                        result.append("В коллекцию добавлен новый элемент: ").append(city.toString());
                        status = Status.SUCCESSFUL;
                    } else result.append("В коллекцию не добавлен элемент: ").append(city.toString());
                } else {
                    priorityQueue.addToCollection(city, getUser());
                    result.append("В коллекцию добавлен новый элемент: ").append(city.toString());
                    status = Status.SUCCESSFUL;
                }
            }
        } catch (ClassNotFoundException | SQLException | ParseException e) {
            result.append("Возникла ошибка при попытке соединения с БД, новый объект не добавлен");
            status = Status.ERROR;
        }
        return Serialization.serializeData(new WrapperForObjects(result.toString(), DescriptionForObject.ANSWER, status));
    }
}
