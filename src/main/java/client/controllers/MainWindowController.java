package client.controllers;

import client.CityApplication;
import client.Client;
import client.clientUtils.CommandManager;
import client.clientUtils.InputAndOutput;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import sharedClasses.elementsOfCollection.City;
import sharedClasses.elementsOfCollection.Climate;
import sharedClasses.utils.Status;
import sharedClasses.utils.WrapperForObjects;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class MainWindowController {
    public Label currentUserLabel;
    private CommandManager commandManager;
    private Client client;
    private CityApplication cityApplication;
    private PopUpWindowController popUpWindowController;
    private final InputAndOutput inputAndOutput = new InputAndOutput();
    private Circle prevCircle;
    private Color prevColor;
    private FileChooser fileChooser;

    @FXML
    AnchorPane mapPane;
    @FXML
    TableColumn<City, Integer> idColumn;
    @FXML
    TableColumn<City, String> nameColumn;
    @FXML
    TableColumn<City, Float> xColumn;
    @FXML
    TableColumn<City, Integer> yColumn;
    @FXML
    TableColumn<City, LocalDate> creationDateColumn;
    @FXML
    TableColumn<City, Integer> areaColumn;
    @FXML
    TableColumn<City, Long> populationColumn;
    @FXML
    TableColumn<City, Long> metersAboveSeaLevelColumn;
    @FXML
    TableColumn<City, Date> establishmentDateColumn;
    @FXML
    TableColumn<City, Integer> agglomerationColumn;
    @FXML
    TableColumn<City, Climate> climateColumn;
    @FXML
    TableColumn<City, Integer> ageColumn;
    @FXML
    TableColumn<City, String> ownerColumn;
    @FXML
    TableView<City> tableView;
    @FXML
    Tab mapTab;

    private Random random = new Random();
    private HashMap<String, Color> visualMap = new HashMap();
    private HashMap<Shape, Integer> moveMap = new HashMap();
    private HashMap<Integer, Text> idMap = new HashMap();
    private HashMap<Integer, Label> infoMap = new HashMap();
    private boolean isFirst = true;

    public void setClient(Client client) {
        this.client = client;
    }

    public void setCityApplication(CityApplication cityApplication) {
        this.cityApplication = cityApplication;
    }

    public void fillTable() {
        idColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getId()));
        nameColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getName()));
        xColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getCoordinates().getX()));
        yColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getCoordinates().getY()));
        creationDateColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getCreationDate()));
        areaColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getArea()));
        populationColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPopulation()));
        metersAboveSeaLevelColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getMetersAboveSeaLevel()));
        establishmentDateColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getNotStringEstablishmentDate()));
        agglomerationColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getAgglomeration()));
        climateColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getClimate()));
        ageColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getGovernor().getAge()));
        ownerColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getOwner()));
        idColumn.setSortType(TableColumn.SortType.ASCENDING);
        populationColumn.setSortable(false);
        ObservableList<City> list = commandManager.getCollectionForTable();
        if (list != null) tableView.setItems(list);
        else inputAndOutput.output("Ошибка при подключении к БД", "Ошибка", null, Alert.AlertType.ERROR);
        tableView.getSelectionModel().clearSelection();
        mapTab.setOnSelectionChanged(e -> {
            mapPane.getChildren().clear();
            isFirst = true;
            startVisualisation();
        });
        //mapPane.setOnMouseClicked(e -> {if (prevCircle != null) prevCircle.setFill(prevColor);});
    }

    public void startVisualisation() {
        for (City city : tableView.getItems()) {
            Label info = new Label(city.toString());
            info.setVisible(false);
            if (!visualMap.containsKey(city.getOwner())) {
                visualMap.put(city.getOwner(), Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble()));
            }
            double size = Math.min(200, city.getArea() * 2) / 6;
            Circle circleObject = new Circle(size, visualMap.get(city.getOwner()));
            circleObject.setEffect(new BoxBlur(5, 5, 1));
            Text objectId = new Text(String.valueOf(city.getId()));
            idMap.put(city.getId(), objectId);
            circleObject.setOnMousePressed(mouseEvent -> {
                if (mouseEvent.getClickCount() == 1) {
                    shapeOnMouseClicked(mouseEvent);
                } else if (mouseEvent.getClickCount() == 2) {
                    shapeOnMouseClicked(mouseEvent);
                    updateIdButtonClicked();
                }
            });
            circleObject.setOnMouseEntered(e -> {
                info.setVisible(true);
            });
            circleObject.setOnMouseExited(e -> info.setVisible(false));
            objectId.setFont(Font.font(size));
            objectId.setStyle("-fx-font-weight: bold");
            objectId.setFill(visualMap.get(city.getOwner()).darker());
            objectId.translateXProperty().bind(circleObject.centerXProperty().subtract(objectId.getLayoutBounds().getWidth() / 2));
            objectId.translateYProperty().bind(circleObject.centerYProperty().add(objectId.getLayoutBounds().getHeight() / 4));
            info.setVisible(false);
            info.setWrapText(true);
            info.setPrefWidth(230);
            info.setTextFill(visualMap.get(city.getOwner()).darker().darker());
            info.setStyle("-fx-border-radius: 100;" + "-fx-background-color: white; -fx-border-color: white; -fx-background-insets: 0, 0 1 1 0;");
            info.setPrefHeight(100);
            info.setFont(Font.font(size / 3.5));
            info.translateXProperty().bind(circleObject.centerXProperty().add(100));
            info.translateYProperty().bind(circleObject.centerYProperty().subtract(100));
            mapPane.getChildren().add(circleObject);
            mapPane.getChildren().add(objectId);
            moveMap.put(circleObject, city.getId());
            infoMap.put(city.getId(), info);
            ScaleTransition scaleTransition = new ScaleTransition();
            scaleTransition.setDuration(Duration.millis(1500));
            circleObject.setCenterX(city.getCoordinates().getX());
            circleObject.setCenterY(city.getCoordinates().getY());
            scaleTransition.setNode(circleObject);
            //if (isFirst) {
            scaleTransition.setByY(2);
            scaleTransition.setByX(2);
                /*} else {
                    circleObject.setRadius(size * 1.5);
                    scaleTransition.setByY(1.33);
                    scaleTransition.setByX(1.33);
                }*/
            scaleTransition.setAutoReverse(false);
            scaleTransition.play();
        }
        for (Integer id : infoMap.keySet()) {
            mapPane.getChildren().add(infoMap.get(id));
        }
        isFirst = false;
    }

    public void startUpdate() {
        Thread thread = new Thread(() -> {
            while (true) {
                ObservableList<City> list = updateTable();
                Platform.runLater(() -> {
                    changeCollection(list);
                    startVisualisation();
                });
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
            }
        });
        thread.start();
    }

    private void shapeOnMouseClicked(MouseEvent event) {
        Circle circle = (Circle) event.getSource();
        long id = moveMap.get(circle);
        for (City city : tableView.getItems()) {
            if (city.getId() == id) {
                tableView.getSelectionModel().select(city);
                break;
            }
        }
        if (prevCircle != null) {
            prevCircle.setFill(prevColor);
        }
        prevCircle = circle;
        prevColor = (Color) circle.getFill();
        circle.setFill(prevColor.brighter());
    }

    public ObservableList<City> updateTable() {
        return commandManager.getCollectionForTable();
    }

    public void addButtonClicked() {
        popUpWindowController.makeAddWindow("Добавление объекта:");
    }

    public void changeCollection(ObservableList<City> list) {
        if (list != null) tableView.setItems(list);
        else inputAndOutput.output("Ошибка при подключении к БД", "Ошибка", null, Alert.AlertType.ERROR);
        tableView.getSelectionModel().clearSelection();
        mapPane.getChildren().clear();
    }

    public void setPopUpWindowController(PopUpWindowController popUpWindowController) {
        this.popUpWindowController = popUpWindowController;
    }

    public void addIfMinButtonClicked() {
        popUpWindowController.makeAddIfMinWindow("Добавление объекта:");
    }

    public void averageOfMetersAboveSeaLevelButtonClicked() {
        WrapperForObjects wrapObject = commandManager.getInfoAboutCollection("average_of_meters_above_sea_level");
        updateTable();
        outputResult(wrapObject);
    }

    public void addIfMaxButtonClicked() {
        popUpWindowController.makeAddIfMaxWindow("Добавление объекта:");
    }

    public void removeHeadButtonClicked() {
        WrapperForObjects wrapObject = commandManager.getInfoAboutCollection("remove_head");
        updateTable();
        outputResult(wrapObject);
    }

    public void clearButtonClicked() {
        WrapperForObjects wrapObject = commandManager.getInfoAboutCollection("clear");
        updateTable();
        outputResult(wrapObject);
    }

    public void executeScriptButtonClicked() {
        WrapperForObjects wrapObject = commandManager.getInfoAboutCollection("execute_script");
        updateTable();
        outputResult(wrapObject);
    }

    public void groupCountingByMetersAboveSeaLevelButtonClicked() {
        WrapperForObjects wrapObject = commandManager.getInfoAboutCollection("group_counting_by_meters_above_sea_level");
        updateTable();
        outputLongResult(wrapObject);
    }

    public void infoButtonClicked() {
        WrapperForObjects wrapObject = commandManager.getInfoAboutCollection("info");
        updateTable();
        outputResult(wrapObject);
    }

    public void removeByIdButtonClicked() {
        String arg;
        if (!tableView.getSelectionModel().isEmpty()) {
            City city = tableView.getSelectionModel().getSelectedItem();
            arg = String.valueOf(city.getId());
        } else {
            Optional<String> optArg = commandManager.askArg("Введите id:");
            if (optArg.isPresent()) arg = optArg.get();
            else arg = null;
        }
        if (arg != null) {
            WrapperForObjects wrapObject = commandManager.getInfoAboutCollectionWithArg("remove_by_id", arg);
            updateTable();
            outputResult(wrapObject);
        }
    }

    public void updateIdButtonClicked() {
        String arg;
        if (!tableView.getSelectionModel().isEmpty()) {
            City city = tableView.getSelectionModel().getSelectedItem();
            arg = String.valueOf(city.getId());
        } else {
            Optional<String> optArg = commandManager.askArg("Введите id:");
            if (optArg.isPresent()) arg = optArg.get();
            else arg = null;
        }
        if (arg != null) {
            popUpWindowController.makeUpdateWindow("Добавление объекта:", arg);
        }
    }

    public void outputLongResult(WrapperForObjects wrapObject) {
        if (wrapObject.getStatus() == Status.ERROR)
            inputAndOutput.longOutput((String) wrapObject.getObject(), "Ошибка:", null, Alert.AlertType.ERROR);
        else if (wrapObject.getStatus() == Status.WARN)
            inputAndOutput.longOutput((String) wrapObject.getObject(), "Внимание:", null, Alert.AlertType.INFORMATION);
        else
            inputAndOutput.longOutput((String) wrapObject.getObject(), "Результат команды:", null, Alert.AlertType.INFORMATION);
    }

    public void outputResult(WrapperForObjects wrapObject) {
        if (wrapObject.getStatus() == Status.ERROR)
            inputAndOutput.output((String) wrapObject.getObject(), "Ошибка:", null, Alert.AlertType.ERROR);
        else if (wrapObject.getStatus() == Status.WARN)
            inputAndOutput.output((String) wrapObject.getObject(), "Внимание:", null, Alert.AlertType.INFORMATION);
        else
            inputAndOutput.output((String) wrapObject.getObject(), "Результат команды:", null, Alert.AlertType.INFORMATION);
    }

    public void helpedButtonClicked() {
        WrapperForObjects wrapObject = commandManager.getInfoAboutCollection("help");
        inputAndOutput.longOutput((String) wrapObject.getObject(), "Доступные команды:", null, Alert.AlertType.INFORMATION);
    }

    public void changeButtonClicked() {
        try {
            cityApplication.returnAuthWindow();
        } catch (IOException e) {
            inputAndOutput.output("Возникла ошибка при попытке запуска окна авторизации", "Ошибка:", null, Alert.AlertType.ERROR);
        }
    }

    public void langButtonClicked() {
        //commandManager.getInfoAboutCollection("removeHead");
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public void changeHello(String login) {
        currentUserLabel.setText("С возвращением, " + login + "!");
        currentUserLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20");
    }
}