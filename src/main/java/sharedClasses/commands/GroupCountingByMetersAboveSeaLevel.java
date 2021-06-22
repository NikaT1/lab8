package sharedClasses.commands;

import sharedClasses.elementsOfCollection.City;
import sharedClasses.utils.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Класс для команды group_counting_by_meters_above_sea_level, которая группирует элементы коллекции по значению
 * поля metersAboveSeaLevel и выводит количество элементов в каждой группе.
 */

public class GroupCountingByMetersAboveSeaLevel extends Command {

    private static final long serialVersionUID = 147364832874L;

    /**
     * Конструктор, присваивающий имя и дополнительную информацию о команде.
     */
    public GroupCountingByMetersAboveSeaLevel(User user, CommandsControl commandsControl) {
        super("group_counting_by_meters_above_sea_level", "сгруппировать элементы коллекции по значению поля metersAboveSeaLevel, вывести количество элементов в каждой группе", 0, false, user, commandsControl);
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
        synchronized (priorityQueue.getCollection()) {
            if (priorityQueue.getCollection().isEmpty()) result.append("Коллекция пуста" + '\n');
            else {
                Map<Object, List<City>> groups = priorityQueue.getCollection().stream().
                        filter(city -> city.getMetersAboveSeaLevel() != null).
                        collect(Collectors.groupingBy(City::getMetersAboveSeaLevel));
                groups.forEach((meters, cities) -> result.append("Группа ").append(meters).append(" (м):").append('\n').append(print(cities)).append('\n'));
                status = Status.SUCCESSFUL;
            }
            result.delete(result.length() - 2, result.length());
        }
        return Serialization.serializeData(new WrapperForObjects(result.toString(), DescriptionForObject.ANSWER, status));
    }

    public String print(List<City> cities) {
        StringBuilder result = new StringBuilder();
        for (City city : cities) {
            result.append(city.toString()).append('\n');
        }
        return result.toString();
    }
}
