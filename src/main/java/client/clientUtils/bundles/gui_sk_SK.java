package client.clientUtils.bundles;

import java.util.ListResourceBundle;

public class gui_sk_SK extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private static final Object[][] contents = {
            {"ProgName", "Mesto DB"},
            {"logBut", "Prihlásiť sa"},
            {"regBut", "Registrovať"},
            {"authLabel", "Identifikácia:"},
            {"passLabel", "Heslo"},
            {"logLabel", "Prihlásiť sa"},
            {"buttonAdd", "Pridať"},
            {"buttonBack", "Zrušiť"},
            {"instructionLabel", "Pridanie položky"},
            {"nameLabel", "názov"},
            {"xLabel", "X"},
            {"yLabel", "Y"},
            {"areaLabel", "Územie"},
            {"populationLabel", "Populácia"},
            {"climateLabel", "Podnebie"},
            {"establishmentDateLabel", "Dátum nadácie"},
            {"metersAboveSeaLevelLabel", "Výška nad morom"},
            {"agglomerationLabel", "Veľkosť aglomerácie"},
            {"ageLabel", "Vek guvernéra"},
            {"idLabel", "Id"},
            {"no", "nie"},
            {"yes", "Áno"},
            {"Arguments", "Argumenty"},
            {"SerialError", "Chyba príkazu serializácie; Príkaz nie je vykonaný"},
            {"FatalError", "Tam bola nepredvídaná chyba"},
            {"ConnectError", "Spojenie nie je nainštalované"},
            {"TryToConnect", "Zopakujte?"},
            {"DBError", "Chyba pripojenie k databáze"},
            {"ScriptError", "Chyba pri vykonávaní skriptu"},
            {"IDError", "Neplatné FOMAT ID"},
            {"WrongId", "ID dátová položka neexistuje"},
            {"OkUpdate", "Aktualizácia prvku úspešne dokončená"},
            {"NotUpdate", "Aktualizácia prvku nie je implementovaná"},
            {"OkAdd", "Pridanie položky úspešne dokončenej"},
            {"NotAdd", "Pridanie položky, ktorá nie je implementovaná"},
            {"Empty", "Zbierka prázdnych"},
            {"OkRemove", "Vymazanie položky úspešne dokončenej"},
            {"NotRemove", "Odstránenie položky, ktorá nie je implementovaná"},
            {"OkRemoveAll", "Vymazanie prvkov používateľa úspešne dokončené"},
            {"AverageCommand", "Priemerná výška hodnota nad hladinou mora pre všetky prvky kolekcie: {0}"},
            {"InfoCommand", "Typ: {0} \n" +
                    "Dátum inicializácie: {1}\n" +
                    "Počet prvkov: {2}"},
            {"GroupCommand", "Prvky zoskupené vo výške nad hladinou mora: \n {0}"},
            {"Error", "Chyba"},
            {"GetId", "Zadajte ID:"},
            {"newInstructionLabel", "Zmeniť prvok"},
            {"Hello", "Vitaj späť,"},
            {"Commands", "K dispozícii príkazy:"},
            {"AuthError", "Pri pokuse o spustenie okna autorizácie sa vyskytla chyba"},
            {"Result", "Výsledný tím:"},
            {"Warn", "POZOR:"},
            {"WrongPop", "Nesprávne zadané obyvateľstvo"},
            {"WrongCoord", "Koordinate zaviedla nesprávne"},
            {"WrongAge", "Vek je zadaný nesprávne"},
            {"WrongClimate", "Klimatizácia sa zavádza nesprávne"},
            {"WrongAgl", "Veľkosť aglomerácie sa zadá nesprávne"},
            {"WrongDate", "Dátum zavedený nesprávne"},
            {"WrongMet", "Výška nad morom sa zaviedla nesprávne"},
            {"HelpForCommands", "Pridať: Pridajte novú položku do kolekcie \n" +
                    "Odstráňte prvý: Odstráňte prvý prvok kolekcie \n" +
                    "Skupina: Brúsiť prvky zberu m meterMaboveseseleleleveleho poľa,výstup počtu prvkov v každej skupine \n" +
                    "Pridať,ak je menej: Pridajte novú položku do zberu,ak je jeho hodnota nižšia ako hodnota najmenšej položky tejto kolekcie \n" +
                    "Postupujte podľa skriptu: Počítajte a vykonajte skript zo zadaného súboru. \n" +
                    "CLEAR: CLEAR COLLECTION \n" +
                    "Obnoviť: Aktualizujte hodnotu zberného prvku,ktorej ID je špecifikované \n" +
                    "Vymazať: Odstráňte položku z kolekcie svojím ID \n" +
                    "Pridajte,či je menej: Pridajte novú položku do kolekcie,ak jeho hodnota presahuje hodnotu najväčšieho prvku tejto zbierky \n" +
                    "Pomoc: Odoberte certifikát prístupných príkazov \n" +
                    "Priemerná výška nad úrovňou mora: výstup Priemerná hodnota meracových metrov pre všetky prvky kolekcie \n" +
                    "Informácie: Zobrazí informácie o kolekcii(Typ,dátum inicializácie,Počet prvkov atď.)"},
            {"helpedButton", "pomoc"},
            {"changeUserButton", "zmeniť používateľa"},
            {"currentUserLabel", "Vitaj späť,"},
            {"tableTab", "stôl"},
            {"mapTab", "vizualizácia"},
            {"updateIdButton", "zmena"},
            {"removeByIdButton", "Vymazať"},
            {"infoButton", "referencia"},
            {"groupCountingByMetersAboveSeaLevelButton", "grupuar në lartësi mbi nivelin e detit"},
            {"executeScriptButton", "Vykonajte skript"},
            {"removeHeadButton", "fshini së pari"},
            {"clearButton", "jasný"},
            {"averageOfMetersAboveSeaLevelButton", "lartësia e mesme mbi nivelin e detit"},
            {"addIfMaxButton", "pridajte, či je viac"},
            {"addIfMinButton", "pridať, ak je menej"},
            {"addButton", "pridať"},
            {"creationDateColumn", "Dátum vytvorenia"},
            {"ownerColumn", "Majiteľ"}
    };
}
