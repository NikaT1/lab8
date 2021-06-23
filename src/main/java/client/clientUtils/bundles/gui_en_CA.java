package client.clientUtils.bundles;

import java.util.ListResourceBundle;

public class gui_en_CA extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private static final Object[][] contents = {
            {"ProgName", "City DB"},
            {"logBut", "Sing in"},
            {"regBut", "Sing up"},
            {"authLabel", "Identification:"},
            {"passLabel", "Password"},
            {"logLabel", "Login"},
            {"buttonAdd", "Add"},
            {"buttonBack", "Cancel"},
            {"instructionLabel", "	Adding an item"},
            {"nameLabel", "Name"},
            {"xLabel", "X"},
            {"yLabel", "Y"},
            {"areaLabel", "Area"},
            {"populationLabel", "Population"},
            {"climateLabel", "Climate"},
            {"establishmentDateLabel", "Establishment date"},
            {"metersAboveSeaLevelLabel", "Meters above sea level"},
            {"agglomerationLabel", "Agglomeration"},
            {"ageLabel", "Age of the governor"},
            {"idLabel", "Id"},
            {"no", "no"},
            {"yes", "yes"},
            {"Arguments", "Arguments"},
            {"SerialError", "Command serialization error; The command is not executed"},
            {"FatalError", "There was an unforeseen error"},
            {"ConnectError", "Connection is not installed"},
            {"TryToConnect", "Retry connection?"},
            {"DBError", "	Error connecting to database	"},
            {"ScriptError", "Error during the execution of the script"},
            {"IDError", "Invalid format of ID"},
            {"WrongId", "Item with this id does not exist"},
            {"OkUpdate", "Element update successfully completed"},
            {"NotUpdate", "Element update not implemented"},
            {"OkAdd", "Adding an item successfully completed"},
            {"NotAdd", "Adding an item not implemented"},
            {"Empty", "Collection is empty"},
            {"OkRemove", "Deleting an item successfully completed"},
            {"NotRemove", "Removal of the item not implemented"},
            {"OkRemoveAll", "Deleting user's elements successfully completed"},
            {"AverageCommand", "The average height value above sea level for all elements of the collection: {0}"},
            {"InfoCommand", "Type: {0} \n" +
                    "Initialization date: {1} \n" +
                    "Number of elements:{2}"},
            {"GroupCommand", "Elements grouped by value of meters above sea level: \n {0}"},
            {"Error", "Error"},
            {"GetId", "Enter ID:"},
            {"newInstructionLabel", "Changing element"},
            {"Hello", "Welcome, "},
            {"Commands", "Available commands:"},
            {"AuthError", "Error occurred while trying to start the authorization window"},
            {"Result", "Result of command:"},
            {"Warn", "Attention:"},
            {"WrongPop", "Population entered incorrectly"},
            {"WrongCoord", "The coordinate introduced incorrectly"},
            {"WrongAge", "Age is entered incorrectly"},
            {"WrongClimate", "The climate is introduced incorrectly"},
            {"WrongAgl", "The size of the agglomeration is entered incorrectly"},
            {"WrongDate", "Date introduced incorrectly"},
            {"WrongMet", "Meters above sea level introduced incorrectly"},
            {"HelpForCommands", "Add : add a new item to the collection \n" +
                    "remove head : remove the first element of the collection \n" +
                    "Group counting by meters above sea level : grind the elements of the collection of the METERSABOVESELELEVEL field, output the number of elements in each group \n" +
                    "Add if min : add a new item to the collection if its value is less than that of the smallest item of this collection \n" +
                    "Execute script : execute the script from the file. \n" +
                    "Clear : clear Collection \n" +
                    "Update : update the value of the element with specified ID\n" +
                    "remove : delete an item from the collection by its ID \n" +
                    "Add if max : add a new item to the collection, if its value exceeds the value of the largest element of this collection \n" +
                    "Average of meters above sea level : output the average value of the METERSABOVESELEVEL field for all elements of the collection \n" +
                    "Info : displays information about the collection (type,initialization date,number of elements,etc.)"},
            {"helpedButton", "help"},
            {"changeUserButton", "change user"},
            {"currentUserLabel", "Welcome,	"},
            {"tableTab", "Table"},
            {"mapTab", "Visualization"},
            {"updateIdButton", "update"},
            {"removeByIdButton", "remove"},
            {"infoButton", "info"},
            {"groupCountingByMetersAboveSeaLevelButton", "group counting by meters above sea level"},
            {"executeScriptButton", "execute script"},
            {"removeHeadButton", "remove head"},
            {"clearButton", "clear"},
            {"averageOfMetersAboveSeaLevelButton", "average of meters above sea level"},
            {"addIfMaxButton", "add if max"},
            {"addIfMinButton", "add if min	"},
            {"addButton", "add"},
            {"creationDateColumn", "Creation date"},
            {"ownerColumn", "Owner"}
    };
}
