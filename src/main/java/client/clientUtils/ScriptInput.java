package client.clientUtils;

import sharedClasses.elementsOfCollection.City;
import sharedClasses.elementsOfCollection.Climate;
import sharedClasses.elementsOfCollection.Coordinates;
import sharedClasses.elementsOfCollection.Human;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Scanner;

public class ScriptInput {

    Scanner scanner;

    public ScriptInput(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Метод, считывающий значение поля name.
     *
     * @return значение поля name.
     */
    private String readName() {
        String name;
        name = readField();
        if (name.equals("")) throw new NumberFormatException();
        return name;
    }

    public String readField() {
        return scanner.nextLine();
    }

    /**
     * Метод, считывающий значение поля area.
     *
     * @return значение поля area.
     */
    private int readArea() throws NumberFormatException {
        int area;
        area = Integer.parseInt(readField());
        if (area <= 0) throw new NumberFormatException();
        return area;
    }

    /**
     * Метод, считывающий значение поля population.
     *
     * @return значение поля population.
     */
    private long readPopulation() throws NumberFormatException {
        long population;
        population = Long.parseLong(readField());
        if (population <= 0) throw new NumberFormatException();
        return population;
    }

    /**
     * Метод, считывающий значение поля metersAboveSeaLevel.
     *
     * @return значение поля metersAboveSeaLevel.
     */
    private Long readMetersAboveSeaLevel() throws NumberFormatException {
        long metersAboveSeaLevel;
        String s = readField();
        if (s.equals("")) {
            return null;
        }
        metersAboveSeaLevel = Long.parseLong(s);
        return metersAboveSeaLevel;
    }

    /**
     * Метод, считывающий значение поля establishmentDate.
     *
     * @return значение поля establishmentDate.
     */
    private LocalDate readEstablishmentDate() {
        LocalDate establishmentDate;
        String s = readField();
        if (s.equals("")) {
            return null;
        }
        establishmentDate = LocalDate.parse(s);
        return establishmentDate;
    }

    /**
     * Метод, считывающий значение поля agglomeration.
     *
     * @return значение поля agglomeration.
     */
    private Integer readAgglomeration() throws NumberFormatException {
        int agglomeration;
        String s = readField();
        if (s.equals("")) {
            return null;
        }
        agglomeration = Integer.parseInt(s);
        return agglomeration;
    }

    /**
     * Метод, считывающий значение поля climate.
     *
     * @return значение поля climate.
     */
    private Climate readClimate() throws NumberFormatException {
        return Climate.valueOf(readField());
    }

    /**
     * Метод, считывающий значение поля governor.
     *
     * @return значение поля governor.
     */
    private Human readGovernor() throws NumberFormatException {
        int age;
        String s = readField();
        if (s.equals("")) {
            return new Human(null);
        }
        age = Integer.parseInt(s);
        if (age <= 0) throw new NumberFormatException();
        return new Human(age);
    }

    /**
     * Метод, считывающий и создающий объект класса City.
     *
     * @return новый объект класса City.
     */
    public City readCity() throws NumberFormatException {
        City city = new City();
        city.setName(readName());
        city.setCoordinates(readCoordinates());
        city.setCreationDate(LocalDate.now());
        city.setArea(readArea());
        city.setPopulation(readPopulation());
        city.setMetersAboveSeaLevel(readMetersAboveSeaLevel());
        city.setEstablishmentDate(readEstablishmentDate());
        city.setAgglomeration(readAgglomeration());
        city.setClimate(readClimate());
        city.setGovernor(readGovernor());
        return city;
    }

    /**
     * Метод, считывающий значение поля coordinates.
     *
     * @return значение поля coordinates.
     */
    public Coordinates readCoordinates() throws NumberFormatException {
        float x;
        int y;
        x = Float.parseFloat(readField());
        y = Integer.parseInt(readField());
        if (y <= -989 || x <= -724) throw new NumberFormatException();
        return new Coordinates(x, y);
    }
}
