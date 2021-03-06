package client.controllers;

import client.clientUtils.CommandManager;
import client.clientUtils.InputAndOutput;
import client.clientUtils.LocalizationTool;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sharedClasses.elementsOfCollection.City;
import sharedClasses.elementsOfCollection.Climate;
import sharedClasses.elementsOfCollection.Coordinates;
import sharedClasses.elementsOfCollection.Human;
import sharedClasses.utils.WrapperForObjects;

import java.time.LocalDate;

public class PopUpWindowController {
    private Stage popUpStage;

    @FXML
    private ComboBox<Climate> climateBox;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonBack;
    @FXML
    private Label instructionLabel;
    @FXML
    private TextField name;
    @FXML
    private TextField x;
    @FXML
    private TextField y;
    @FXML
    private TextField area;
    @FXML
    private TextField population;
    @FXML
    private DatePicker establishmentDate;
    @FXML
    private TextField metersAboveSeaLevel;
    @FXML
    private TextField agglomeration;
    @FXML
    private TextField age;
    @FXML
    private Label nameLabel;
    @FXML
    private Label xLabel;
    @FXML
    private Label yLabel;
    @FXML
    private Label areaLabel;
    @FXML
    private Label populationLabel;
    @FXML
    private Label establishmentDateLabel;
    @FXML
    private Label metersAboveSeaLevelLabel;
    @FXML
    private Label agglomerationLabel;
    @FXML
    private Label ageLabel;
    @FXML
    private Label climateLabel;
    @FXML
    private GridPane gridPane;
    private boolean flag;
    private CommandManager commandManager;
    private final InputAndOutput inputAndOutput = new InputAndOutput();
    private MainWindowController parentController;
    private LocalizationTool localizationTool;

    public void makeAddWindow(String s) {
        instructionLabel.setText(s);
        buttonAdd.setOnAction(e -> buttonAddClicked());
        popUpStage.show();
    }

    public void makeAddIfMinWindow(String s) {
        instructionLabel.setText(s);
        buttonAdd.setOnAction(e -> buttonAddIfMinClicked());
        popUpStage.show();
    }


    public void makeUpdateWindow(String s, String arg) {
        instructionLabel.setText(s);
        buttonAdd.setOnAction(e -> buttonUpdateClicked(arg));
        popUpStage.show();
    }

    public void makeAddIfMaxWindow(String s) {
        instructionLabel.setText(s);
        buttonAdd.setOnAction(e -> buttonAddIfMaxClicked());
        popUpStage.show();
    }

    private String readName() {
        String s = name.getText();
        if (s == null || s.equals("")) {
            inputAndOutput.output("?????? ?????????????? ??????????????", "Warn", null, Alert.AlertType.ERROR);
            name.clear();
            flag = false;
        }
        return s;
    }

    private int readArea() {
        int s = 1;
        try {
            s = Integer.parseInt(area.getText());
            if (s <= 0) {
                inputAndOutput.output("???????????? ???????????????????? ???????????? ??????????????", "Warn", null, Alert.AlertType.ERROR);
                area.clear();
                flag = false;
            }
        } catch (NumberFormatException ex) {
            inputAndOutput.output("???????????? ???????????????????? ???????????? ??????????????", "Warn", null, Alert.AlertType.ERROR);
            area.clear();
            flag = false;
        }
        if (flag) return s;
        else return 1;
    }

    private long readPopulation() {
        long s = 1;
        try {
            s = Long.parseLong(population.getText());
            if (s <= 0) {
                inputAndOutput.output("WrongPop", "Warn", null, Alert.AlertType.ERROR);
                population.clear();
                flag = false;
            }
        } catch (NumberFormatException e) {
            inputAndOutput.output("WrongPop", "Warn", null, Alert.AlertType.ERROR);
            population.clear();
            flag = false;
        }
        if (flag) return s;
        else return 1;
    }

    private Long readMetersAboveSeaLevel() throws NumberFormatException {
        Long s = null;
        String text = metersAboveSeaLevel.getText();
        if (text.equals("")) {
            return null;
        }
        try {
            s = Long.parseLong(text);
        } catch (NumberFormatException e) {
            inputAndOutput.output("", "Warn", null, Alert.AlertType.ERROR);
            metersAboveSeaLevel.clear();
            flag = false;
        }
        return s;
    }

    private LocalDate readEstablishmentDate() {
        LocalDate s;
        String text = establishmentDate.getAccessibleText();
        if (text == null || text.equals("")) {
            return null;
        }
        s = LocalDate.parse(text);
        return s;
    }

    private Integer readAgglomeration() {
        Integer s = null;
        String text = agglomeration.getText();
        if (text.equals("")) {
            return null;
        }
        try {
            s = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            inputAndOutput.output("WrongAgl", "Warn", null, Alert.AlertType.ERROR);
            agglomeration.clear();
            flag = false;
        }
        return s;
    }

    private Climate readClimate() {
        Climate s = null;
        try {
            s = climateBox.getValue();
        } catch (IllegalArgumentException ex) {
            inputAndOutput.output("WrongClimate", "Warn", null, Alert.AlertType.ERROR);
            flag = false;
        }
        return s;
    }

    private Human readGovernor() {
        Integer s = null;
        String text = age.getText();
        if (text.equals("")) {
            return new Human(null);
        }
        try {
            s = Integer.parseInt(text);
            if (s <= 0) {
                inputAndOutput.output("WrongAge", "Warn", null, Alert.AlertType.ERROR);
                age.clear();
                flag = false;
            }
        } catch (NumberFormatException ex) {
            inputAndOutput.output("WrongAge", "Warn", null, Alert.AlertType.ERROR);
            age.clear();
            flag = false;
        }
        if (flag) return new Human(s);
        else return new Human(1);
    }

    public Coordinates readCoordinates() throws NumberFormatException {
        Float sx = null;
        Integer sy = null;
        try {
            sx = Float.parseFloat(x.getText());
            if (sx <= -724) {
                inputAndOutput.output("WrongCoord", "Warn", null, Alert.AlertType.ERROR);
                x.clear();
                flag = false;
            }
        } catch (NumberFormatException e) {
            inputAndOutput.output("WrongCoord", "Warn", null, Alert.AlertType.ERROR);
            x.clear();
            flag = false;
        }
        try {
            sy = Integer.parseInt(y.getText());
            if (sy <= -989) {
                inputAndOutput.output("WrongCoord", "Warn", null, Alert.AlertType.ERROR);
                y.clear();
                flag = false;
            }
        } catch (NumberFormatException ex) {
            inputAndOutput.output("WrongCoord", "Warn", null, Alert.AlertType.ERROR);
            y.clear();
            flag = false;
        }
        if (flag) return new Coordinates(sx, sy);
        else return new Coordinates(0f, 0);
    }

    public void buttonAddClicked() {
        City city = processingFields();
        if (flag) {
            WrapperForObjects wrapObject = commandManager.addToCollection(city, "add");
            parentController.outputResult(wrapObject);
            closeWindow();
            parentController.changeCollection(parentController.updateTable());
            parentController.startVisualisation(false);
        }
    }

    public void buttonAddIfMinClicked() {
        City city = processingFields();
        if (flag) {
            closeWindow();
            WrapperForObjects wrapObject = commandManager.addToCollection(city, "add_if_min");
            parentController.outputResult(wrapObject);
            parentController.changeCollection(parentController.updateTable());
            parentController.startVisualisation(false);
        }
    }

    public void buttonAddIfMaxClicked() {
        City city = processingFields();
        if (flag) {
            closeWindow();
            WrapperForObjects wrapObject = commandManager.addToCollection(city, "add_if_max");
            parentController.outputResult(wrapObject);
            parentController.changeCollection(parentController.updateTable());
            parentController.startVisualisation(false);
        }
    }

    public void buttonUpdateClicked(String arg) {
        City city = processingFields();
        if (flag) {
            closeWindow();
            WrapperForObjects wrapObject = commandManager.getInfoAboutCollectionWithArgs("update", arg, city);
            parentController.outputResult(wrapObject);
            parentController.changeCollection(parentController.updateTable());
            parentController.startVisualisation(false);
        }
    }

    public City processingFields() {
        City city = new City();
        flag = true;
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

    public void setPopUpStage(Stage popUpStage) {
        this.popUpStage = popUpStage;
        climateBox.getItems().addAll(FXCollections.observableArrayList(Climate.values()));
        buttonBack.setOnAction(e -> popUpStage.close());
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public void setParentController(MainWindowController controller) {
        parentController = controller;
    }

    public void closeWindow() {
        name.clear();
        x.clear();
        y.clear();
        area.clear();
        population.clear();
        establishmentDate.valueProperty().setValue(null);
        climateBox.getSelectionModel().clearSelection();
        metersAboveSeaLevel.clear();
        agglomeration.clear();
        age.clear();
        popUpStage.close();
    }


    public void changeLang() {
        buttonAdd.setText(localizationTool.getString("buttonAdd"));
        buttonBack.setText(localizationTool.getString("buttonBack"));
        instructionLabel.setText(localizationTool.getString("instructionLabel"));
        nameLabel.setText(localizationTool.getString("nameLabel"));
        xLabel.setText(localizationTool.getString("xLabel"));
        yLabel.setText(localizationTool.getString("yLabel"));
        areaLabel.setText(localizationTool.getString("areaLabel"));
        populationLabel.setText(localizationTool.getString("populationLabel"));
        establishmentDateLabel.setText(localizationTool.getString("establishmentDateLabel"));
        climateLabel.setText(localizationTool.getString("climateLabel"));
        metersAboveSeaLevelLabel.setText(localizationTool.getString("metersAboveSeaLevelLabel"));
        agglomerationLabel.setText(localizationTool.getString("agglomerationLabel"));
        ageLabel.setText(localizationTool.getString("ageLabel"));
    }

    public void setLocalizationTool(LocalizationTool localizationTool) {
        this.localizationTool = localizationTool;
    }

    public void fillForUpdate(City city) {
        name.setText(city.getName());
        x.setText(String.valueOf(city.getCoordinates().getX()));
        y.setText(String.valueOf(city.getCoordinates().getY()));
        area.setText(String.valueOf(city.getArea()));
        population.setText(String.valueOf(city.getPopulation()));
        if (city.getEstablishmentDate() != null)
            establishmentDate.setValue(LocalDate.parse(String.valueOf(city.getEstablishmentDate())));
        climateBox.setValue(city.getClimate());
        metersAboveSeaLevel.setText(String.valueOf(city.getMetersAboveSeaLevel()));
        agglomeration.setText(String.valueOf(city.getAgglomeration()));
        age.setText(String.valueOf(city.getGovernor().getAge()));
    }
}
