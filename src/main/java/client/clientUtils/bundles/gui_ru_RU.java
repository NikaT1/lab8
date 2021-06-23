package client.clientUtils.bundles;

import java.util.ListResourceBundle;

public class gui_ru_RU extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private static final Object[][] contents = {
            {"ProgName", "City DB"},
            {"buttonAdd", "добавить"},
            {"buttonBack", "отменить"},
            {"instructionLabel", "Добавление элемента"},
            {"nameLabel", "Имя"},
            {"xLabel", "X"},
            {"yLabel", "Y"},
            {"areaLabel", "Территория"},
            {"populationLabel", "Население"},
            {"HelpMessage", "Reference:\n" +
                    "At the bottom of the interface there are buttons for interacting with the base. To perform actions that change the contents of the database, you must first select a row of the table. You can also double-click on the required row of the table to change the object.\n" +
                    "The \"Clear\" command removes from the database only those objects that belong to you.\n" +
                    "You can go to the \"Visualization\" section to see a visual representation of the database objects. Double clicking on the object will enable you to edit it."},
            {"InfoMessage", "Collection information:\n" +
                    " Access date: {0}\n" +
                    " Type: {1}\n" +
                    " Size: {2}"},
            {"climateLabel", "Климат"},
            {"establishmentDateLabel", "Дата основания"},
            {"metersAboveSeaLevelLabel", "Высота над уровнем моря"},
            {"agglomerationLabel", "Размер агломерации"},
            {"ageLabel", "Возраст губернатора"},
            {"idLabel", "ID"},
            {"no", "нет"},
            {"yes", "да"},
            {"Arguments", "Аргументы"},
            {"DBError", "Ошибка при подключении к БД"},
            {"Error", "Ошибка"},
            {"GetId", "Введите id:"},
            {"newInstructionLabel", "Изменение элемента"},
            {"Hello", "С возвращением, "},
            {"Commands", "Доступные команды:"},
            {"AuthError", "Возникла ошибка при попытке запуска окна авторизации"},
            {"Result", "Результат команды:"},
            {"Warn", "Внимание:"},
            {"UpdateSuccess", "Changing the object is successful!"},
            {"UpdateError", "Error when changing the object."},
            {"IdColumn", "Identifier"},
            {"NameColumn", "Name"},
            {"XColumn", "X."},
            {"YColumn", "Y."},
            {"SalaryColumn", "Salary"},
            {"EndDateColumn", "Date of termination of the contract"},
            {"CreationDateColumn", "Date of adding to the database"},
            {"PositionColumn", "Position"},
            {"StatusColumn", "Status"},
            {"OrgColumn", "Organization"},
            {"OrgTypeColumn", "Type of organization"},
            {"AnnualTurnoverColumn", "Annual revenue"},
            {"StreetColumn", "Street"},
            {"PostalCodeColumn", "Index"},
            {"UserColumn", "User"},
            {"RefreshButton", "Refresh"},
            {"AddButton", "Add"},
            {"UpdateButton", "Change"},
            {"RemoveButton", "Delete"},
            {"RemoveGreaterButton", "Remove large"},
            {"RemoveLowerButton", "Remove smaller"},
            {"ClearButton", "Clear"},
            {"HelpButton", "Help"},
            {"InfoButton", "Information"},
            {"CountByStatusButton", "Count by status"},
            {"PrintUniqueOrgsButton", "Unique organizations"},
            {"SaveButton", "Save to file"},
            {"AddIfMinButton", "Add if less"},
            {"ExecuteScriptButton", "Run script"},
            {"VisualMapTab", "Visualization"},
            {"DataTableTab", "Table"},
            {"PortUnavailableError", "The server is currently unavailable."},
            {"ConnectionError", "Connection to the server failed."},
            {"AddressError", "The connection address is not found."},
            {"FatalConnectionError", "Natasha, are you sleeping? For 6 o'clock in the morning, Natasha. Get up, we dropped everything there. We dropped everything at all, Natasha, honestly."},
            {"LoginLabel", "Authorization"},
            {"LoginFieldText", "Enter login"},
            {"PwdFieldText", "Enter password"},
            {"LoginButton", "Sign in"},
            {"RegisterButton", "Register"},
            {"TryLater", "Try to execute this action later."},
            {"DoneButton", "Ready"},
            {"NameLabel", "Name"},
            {"XLabel", "X."},
            {"YLabel", "Y."},
            {"SalaryLabel", "Salary"},
            {"EndDateLabel", "Date of termination of the contract"},
            {"PositionLabel", "Position"},
            {"StatusLabel", "Status"},
            {"OrgLabel", "Organization"},
            {"OrgTypeLabel", "Type of organization"},
            {"AnnTurnoverLabel", "Annual revenue"},
            {"StreetLabel", "Street"},
            {"PostalCodeLabel", "Index"},
            {"NamePrompt", "Enter your name"},
            {"XPrompt", "X."},
            {"YPrompt", "Y."},
            {"SalaryPrompt", "Enter the salary"},
            {"EndDatePrompt", "Specify the Date Termination of the Contract"},
            {"PositionPrompt", "Choose a position"},
            {"StatusPrompt", "Select Status"},
            {"OrgPrompt", "Enter the name of the organization"},
            {"OrgTypePrompt", "Select the type of organization"},
            {"AnnTurnoverPrompt", "Enter the annual revenue"},
            {"StreetPrompt", "Enter the street"},
            {"PostalCodePrompt", "Enter the index"},
            {"ObjectNotChosen", "First select an object!"},
            {"HelpForCommands", "add : добавить новый элемент в коллекцию\n" +
                    "удалить первый : удалить первый элемент коллекции\n" +
                    "сгруппировать : сгруппировать элементы коллекции по значению поля metersAboveSeaLevel, вывести количество элементов в каждой группе\n" +
                    "добавить если меньше : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции\n" +
                    "исполнить скрипт : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                    "очистить : очистить коллекцию\n" +
                    "обновить : обновить значение элемента коллекции, id которого равен заданному\n" +
                    "удалить : удалить элемент из коллекции по его id\n" +
                    "добавить если меньше : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                    "справка : вывести справку по доступным командам\n" +
                    "средняя высота над уровнем моря : вывести среднее значение поля metersAboveSeaLevel для всех элементов коллекции\n" +
                    "информация : вывести информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)"},
            {"WorkerDisplay", "Name: {0}\n" +
                    "Salary: {1}\n" +
                    "Contract end date: {2}\n" +
                    "Position: {3}\n" +
                    "Status: {4}\n" +
                    "Organization: {5}\n" +
                    "A type: {6}"}
    };
}
