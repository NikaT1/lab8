package client.controllers;

import client.CityApplication;
import client.Client;
import client.clientUtils.CommandManager;
import client.clientUtils.InputAndOutput;
import client.clientUtils.LocalizationTool;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import sharedClasses.elementsOfCollection.City;
import sharedClasses.elementsOfCollection.Climate;
import sharedClasses.utils.Status;
import sharedClasses.utils.WrapperForObjects;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;

public class MainWindowController {
    public Label currentUserLabel;
    private CommandManager commandManager;
    private Client client;
    private CityApplication cityApplication;
    private PopUpWindowController popUpWindowController;
    private final InputAndOutput inputAndOutput = new InputAndOutput();
    private Shape prevCircle;
    private Color prevColor;
    private FileChooser fileChooser;
    private AuthorizationWindowController authController;

    @FXML
    private Button helpedButton;
    @FXML
    private Button changeUserButton;
    @FXML
    private Tab tableTab;
    @FXML
    private Button updateIdButton;
    @FXML
    private Button removeByIdButton;
    @FXML
    private Button infoButton;
    @FXML
    private Button groupCountingByMetersAboveSeaLevelButton;
    @FXML
    private Button executeScriptButton;
    @FXML
    private Button removeHeadButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button averageOfMetersAboveSeaLevelButton;
    @FXML
    private Button addIfMaxButton;
    @FXML
    private Button addIfMinButton;
    @FXML
    private Button addButton;
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
    TableColumn<City, String> creationDateColumn;
    @FXML
    TableColumn<City, Integer> areaColumn;
    @FXML
    TableColumn<City, Long> populationColumn;
    @FXML
    TableColumn<City, Long> metersAboveSeaLevelColumn;
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
    @FXML
    ComboBox<String> langButton;

    private String login = "";
    private final Random random = new Random();
    private final HashMap<String, Color> visualMap = new HashMap();
    private HashMap<Shape, Integer> moveMap = new HashMap();
    private HashMap<Integer, Text> idMap = new HashMap();
    private HashMap<Integer, Label> infoMap = new HashMap();
    private boolean isFirst = true;
    private HashMap<String, Locale> languages = new HashMap();
    private LocalizationTool localizationTool;
    private City prevChosen;

    public void setClient(Client client) {
        this.client = client;
    }

    public void setCityApplication(CityApplication cityApplication) {
        this.cityApplication = cityApplication;
    }

    public void setLocalizationTool(LocalizationTool localizationTool) {
        this.localizationTool = localizationTool;
    }

    public void init() {
        fillTable();
        setActionForTable();
        languages.put("English (CA)", new Locale("en", "CA"));
        languages.put("Shqiptare", new Locale("sq", "AL"));
        languages.put("Slovák", new Locale("sk", "SK"));
        languages.put("Русский", new Locale("ru", "RU"));
        langButton.setItems(FXCollections.observableArrayList(languages.keySet()));
        langButton.getSelectionModel().selectedItemProperty().addListener((someOptions, oldLang, newLang) -> {
                    localizationTool.setResource(ResourceBundle.getBundle("client.clientUtils.bundles.gui", languages.get(newLang)));
                    localizationTool.setNumberOfLang(langButton.getSelectionModel().getSelectedIndex());
                    if (popUpWindowController != null) popUpWindowController.changeLang();
                    changeLang();
                    inputAndOutput.setLocalizationTool(localizationTool);
                }
        );
        langButton.getSelectionModel().select(localizationTool.getNumberOfLang());
        fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
    }

    private void changeLang() {
        helpedButton.setText(localizationTool.getString("helpedButton"));
        changeUserButton.setText(localizationTool.getString("changeUserButton"));
        currentUserLabel.setText(localizationTool.getString("Hello") + login + "!");
        tableTab.setText(localizationTool.getString("tableTab"));
        mapTab.setText(localizationTool.getString("mapTab"));
        updateIdButton.setText(localizationTool.getString("updateIdButton"));
        removeByIdButton.setText(localizationTool.getString("removeByIdButton"));
        infoButton.setText(localizationTool.getString("infoButton"));
        groupCountingByMetersAboveSeaLevelButton.setText(localizationTool.getString("groupCountingByMetersAboveSeaLevelButton"));
        executeScriptButton.setText(localizationTool.getString("executeScriptButton"));
        removeHeadButton.setText(localizationTool.getString("removeHeadButton"));
        clearButton.setText(localizationTool.getString("clearButton"));
        averageOfMetersAboveSeaLevelButton.setText(localizationTool.getString("averageOfMetersAboveSeaLevelButton"));
        addIfMaxButton.setText(localizationTool.getString("addIfMaxButton"));
        addIfMinButton.setText(localizationTool.getString("addIfMinButton"));
        addButton.setText(localizationTool.getString("addButton"));
        idColumn.setText(localizationTool.getString("idLabel"));
        nameColumn.setText(localizationTool.getString("nameLabel"));
        xColumn.setText(localizationTool.getString("xLabel"));
        yColumn.setText(localizationTool.getString("yLabel"));
        areaColumn.setText(localizationTool.getString("areaLabel"));
        populationColumn.setText(localizationTool.getString("populationLabel"));
        climateColumn.setText(localizationTool.getString("climateLabel"));
        metersAboveSeaLevelColumn.setText(localizationTool.getString("metersAboveSeaLevelLabel"));
        agglomerationColumn.setText(localizationTool.getString("agglomerationLabel"));
        ageColumn.setText(localizationTool.getString("ageLabel"));
        creationDateColumn.setText(localizationTool.getString("creationDateColumn"));
        ownerColumn.setText(localizationTool.getString("ownerColumn"));
        fillTable();
        startVisualisation(false);
    }

    public void fillTable() {
        idColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getId()));
        nameColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getName()));
        xColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getCoordinates().getX()));
        yColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getCoordinates().getY()));
        creationDateColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(localizationTool.getDateString(data.getValue().getCreationDate())));
        areaColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getArea()));
        populationColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPopulation()));
        metersAboveSeaLevelColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getMetersAboveSeaLevel()));
        agglomerationColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getAgglomeration()));
        climateColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getClimate()));
        ageColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getGovernor().getAge()));
        ownerColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getOwner()));
        idColumn.setSortType(TableColumn.SortType.ASCENDING);
        ObservableList<City> list = commandManager.getCollectionForTable();
        if (list != null) tableView.setItems(list);
        else inputAndOutput.output("DBError", "Error", null, Alert.AlertType.ERROR);
        tableView.getSelectionModel().clearSelection();
        mapTab.setOnSelectionChanged(e -> {
            prevCircle = null;
            startVisualisation(true);
        });
    }

    public void startVisualisation(boolean isFirst) {
        mapPane.getChildren().clear();
        moveMap.clear();
        idMap.clear();
        infoMap.clear();
        for (City city : tableView.getItems()) {
            MessageFormat messageFormat = new MessageFormat(localizationTool.getResource().getString("StringObject"));
            String infoT = messageFormat.format(city.makeArray());
            Label info = new Label(infoT);
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
            circleObject.setOnMouseEntered(e -> info.setVisible(true));
            circleObject.setOnMouseExited(e -> info.setVisible(false));
            objectId.setFont(Font.font(size));
            objectId.setFill(visualMap.get(city.getOwner()).darker().darker());
            objectId.translateXProperty().bind(circleObject.translateXProperty().subtract(objectId.getLayoutBounds().getWidth() / 2));
            objectId.translateYProperty().bind(circleObject.translateYProperty().add(objectId.getLayoutBounds().getHeight() / 4));
            info.setVisible(false);
            info.setWrapText(true);
            info.setPrefWidth(230);
            info.setTextFill(visualMap.get(city.getOwner()).darker().darker());
            info.setStyle("-fx-border-radius: 100;" + "-fx-background-color: white; -fx-border-color: white; -fx-background-insets: 0, 0 1 1 0;");
            info.setPrefHeight(100);
            info.setFont(Font.font(size / 3.5));
            info.translateXProperty().bind(circleObject.translateXProperty().add(100));
            info.translateYProperty().bind(circleObject.translateYProperty().subtract(100));
            if (prevChosen != null && city.getId().equals(prevChosen.getId())) {
                prevCircle.setFill(prevColor.brighter().brighter());
            }
            mapPane.getChildren().add(circleObject);
            mapPane.getChildren().add(objectId);
            moveMap.put(circleObject, city.getId());
            infoMap.put(city.getId(), info);
            if (isFirst) {
                circleObject.setRadius(size * 1.5);
                Path path = new Path();
                path.getElements().add(new MoveTo(-400, -400));
                path.getElements().add(new HLineTo(city.getCoordinates().getX()));
                path.getElements().add(new VLineTo(city.getCoordinates().getY()));
                PathTransition pathTransition = new PathTransition();
                pathTransition.setDuration(Duration.millis(1500));
                pathTransition.setNode(circleObject);
                pathTransition.setPath(path);
                pathTransition.setOrientation(PathTransition.OrientationType.NONE);
                pathTransition.setAutoReverse(false);
                pathTransition.play();
            } else {
                objectId.translateXProperty().bind(circleObject.centerXProperty().subtract(objectId.getLayoutBounds().getWidth() / 2));
                objectId.translateYProperty().bind(circleObject.centerYProperty().add(objectId.getLayoutBounds().getHeight() / 4));
                info.translateXProperty().bind(circleObject.centerXProperty().add(100));
                info.translateYProperty().bind(circleObject.centerYProperty().subtract(100));
                ScaleTransition scaleTransition = new ScaleTransition();
                scaleTransition.setDuration(Duration.millis(1500));
                circleObject.setCenterX(city.getCoordinates().getX());
                circleObject.setCenterY(city.getCoordinates().getY());
                scaleTransition.setNode(circleObject);
                scaleTransition.setByY(0.5);
                scaleTransition.setByX(0.5);
                scaleTransition.setAutoReverse(false);
                scaleTransition.play();
            }
        }
        for (Integer id : infoMap.keySet()) {
            mapPane.getChildren().add(infoMap.get(id));
        }
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
        prevChosen = tableView.getSelectionModel().getSelectedItem();
        prevCircle = circle;
        prevColor = (Color) circle.getFill();
        circle.setFill(prevColor.brighter());
    }

    public void startUpdate() {
        Thread thread = new Thread(() -> {
            while (true) {
                ObservableList<City> list = updateTable();
                Platform.runLater(() -> {
                    changeCollection(list);
                    startVisualisation(false);
                });
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                }
            }
        });
        thread.start();
    }

    public ObservableList<City> updateTable() {
        return commandManager.getCollectionForTable();
    }

    private void setActionForTable() {
        tableView.setRowFactory(e -> {
            TableRow<City> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    City city = row.getItem();
                    if (city.getOwner().equals(login)) {
                        popUpWindowController.fillForUpdate(city);
                        popUpWindowController.makeUpdateWindow(localizationTool.getString("newInstructionLabel"), String.valueOf(city.getId()));
                    }
                }
            });
            return row;
        });
    }

    public void addButtonClicked() {
        popUpWindowController.makeAddWindow(localizationTool.getString("instructionLabel"));
    }

    public void changeCollection(ObservableList<City> list) {
        if (list != null) tableView.setItems(list);
        else inputAndOutput.output("DBError", "Error", null, Alert.AlertType.ERROR);
        tableView.getSelectionModel().clearSelection();
        //TableFilter.forTableView(tableView).apply();
        startVisualisation(false);
    }

    public void setPopUpWindowController(PopUpWindowController popUpWindowController) {
        this.popUpWindowController = popUpWindowController;
    }

    public void addIfMinButtonClicked() {
        popUpWindowController.makeAddIfMinWindow(localizationTool.getString("instructionLabel"));
    }

    public void averageOfMetersAboveSeaLevelButtonClicked() {
        WrapperForObjects wrapObject = commandManager.getInfoAboutCollection("average_of_meters_above_sea_level");
        outputResultWithArgs(wrapObject, "AverageCommand");
        changeCollection(updateTable());
    }

    public void addIfMaxButtonClicked() {
        popUpWindowController.makeAddIfMaxWindow(localizationTool.getString("instructionLabel"));
    }

    public void removeHeadButtonClicked() {
        WrapperForObjects wrapObject = commandManager.getInfoAboutCollection("remove_head");
        outputResult(wrapObject);
        changeCollection(updateTable());
    }

    public void clearButtonClicked() {
        WrapperForObjects wrapObject = commandManager.getInfoAboutCollection("clear");
        outputResult(wrapObject);
        changeCollection(updateTable());
    }

    public void executeScriptButtonClicked() {
        Stage stage = new Stage();
        File scriptFile = fileChooser.showOpenDialog(stage);
        if (scriptFile == null)
            return;
        try {
            client.executeScript(scriptFile);
            inputAndOutput.output("ScriptOk", "Result", null, Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            inputAndOutput.output("ScriptError", "Error", null, Alert.AlertType.ERROR);
        }
        changeCollection(updateTable());
    }

    public void groupCountingByMetersAboveSeaLevelButtonClicked() {
        WrapperForObjects wrapObject = commandManager.getInfoAboutCollection("group_counting_by_meters_above_sea_level");
        outputLongResultWithArgs(wrapObject, "GroupCommand");
        changeCollection(updateTable());
    }

    public void infoButtonClicked() {
        WrapperForObjects wrapObject = commandManager.getInfoAboutCollection("info");
        outputResultWithArgs(wrapObject, "InfoCommand");
        changeCollection(updateTable());
    }

    private void outputResultWithArgs(WrapperForObjects wrapObject, String s) {
        inputAndOutput.outputWithArgs(s, "Result", null, (String[]) wrapObject.getObject(), Alert.AlertType.INFORMATION, false);
    }

    private void outputLongResultWithArgs(WrapperForObjects wrapObject, String s) {
        inputAndOutput.outputWithArgs(s, "Result", null, (String[]) wrapObject.getObject(), Alert.AlertType.INFORMATION, true);
    }

    public void removeByIdButtonClicked() {
        String arg;
        if (!tableView.getSelectionModel().isEmpty()) {
            City city = tableView.getSelectionModel().getSelectedItem();
            arg = String.valueOf(city.getId());
        } else {
            Optional<String> optArg = commandManager.askArg("GetId");
            if (optArg.isPresent()) arg = optArg.get();
            else arg = null;
        }
        if (arg != null) {
            WrapperForObjects wrapObject = commandManager.getInfoAboutCollectionWithArg("remove_by_id", arg);
            outputResult(wrapObject);
            changeCollection(updateTable());
        }
    }

    public void updateIdButtonClicked() {
        String arg;
        if (!tableView.getSelectionModel().isEmpty()) {
            City city = tableView.getSelectionModel().getSelectedItem();
            popUpWindowController.fillForUpdate(city);
            arg = String.valueOf(city.getId());
        } else {
            Optional<String> optArg = commandManager.askArg("GetId");
            if (optArg.isPresent()) arg = optArg.get();
            else arg = null;
        }
        if (arg != null) {
            popUpWindowController.makeUpdateWindow(localizationTool.getString("newInstructionLabel"), arg);
        }
    }

    public void outputResult(WrapperForObjects wrapObject) {
        if (wrapObject.getStatus() == Status.ERROR)
            inputAndOutput.output((String) wrapObject.getObject(), "Error", null, Alert.AlertType.ERROR);
        else if (wrapObject.getStatus() == Status.WARN)
            inputAndOutput.output((String) wrapObject.getObject(), "Warn", null, Alert.AlertType.INFORMATION);
        else
            inputAndOutput.output((String) wrapObject.getObject(), "Result", null, Alert.AlertType.INFORMATION);
    }

    public void helpedButtonClicked() {
        WrapperForObjects wrapObject = commandManager.getInfoAboutCollection("help");
        inputAndOutput.longOutput((String) wrapObject.getObject(), "Commands", null, Alert.AlertType.INFORMATION);
    }

    public void changeButtonClicked() {
        try {
            cityApplication.returnAuthWindow();
        } catch (IOException e) {
            inputAndOutput.output("AuthError", "Error", null, Alert.AlertType.ERROR);
        }
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public void changeHello(String login) {
        this.login = login;
        currentUserLabel.setText(localizationTool.getString("Hello") + login + "!");
        currentUserLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20");
    }

    public void setAuthorizationWindow(AuthorizationWindowController authController) {
        this.authController = authController;
    }
}