package sharedClasses.commands;


import sharedClasses.elementsOfCollection.City;
import sharedClasses.utils.*;

import javax.xml.transform.Result;
import java.sql.SQLException;
import java.text.ParseException;
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
            city.setOwner(getUser().getLogin());
            synchronized (priorityQueue.getCollection()) {
                priorityQueue.addToCollection(city, getUser());
            }
            result.append("В коллекцию добавлен новый элемент: ").append(city.toString());
            status = Status.SUCCESSFUL;
        } catch (IllegalStateException e) {
            result.append("Ошибка: в коллекции слишком много элементов; объект коллекции не создан");
        } catch (NoSuchElementException e) {
            result.append("В скрипте не указаны значения для создания элемента коллекции. Команда add не выполнена");
        } catch (ClassNotFoundException | SQLException | ParseException e) {
            result.append("Возникла ошибка при попытке соединения с БД, новый объект не добавлен");
        }
        return Serialization.serializeData(new WrapperForObjects(priorityQueue.getArrayList(), DescriptionForObject.ANSWER, status));
    }
}
