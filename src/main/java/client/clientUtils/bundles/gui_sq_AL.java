package client.clientUtils.bundles;

import java.util.ListResourceBundle;

public class gui_sq_AL extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private static final Object[][] contents = {
            {"ProgName", "Qyteti db."},
            {"logBut", "Identifikohu"},
            {"regBut", "Regjistrohem"},
            {"authLabel", "Identifikimi:"},
            {"passLabel", "Fjalëkalim"},
            {"logLabel", "Login"},
            {"buttonAdd", "Shtoj"},
            {"buttonBack", "Anuloj"},
            {"instructionLabel", "Duke shtuar një artikull"},
            {"nameLabel", "Emër"},
            {"xLabel", "X"},
            {"yLabel", "Y"},
            {"areaLabel", "Territor"},
            {"populationLabel", "Popullatë"},
            {"climateLabel", "Klima"},
            {"establishmentDateLabel", "Data e themelimit"},
            {"metersAboveSeaLevelLabel", "Lartësia mbi nivelin e detit"},
            {"agglomerationLabel", "Aglomeration madhësia"},
            {"ageLabel", "Mosha e Guvernatorit"},
            {"idLabel", "Id"},
            {"no", "jo"},
            {"yes", "po"},
            {"Arguments", "Argumente"},
            {"SerialError", "Gabimi i serializimit të komandës; Komanda nuk ekzekutohet"},
            {"FatalError", "Kishte një gabim të paparashikuar"},
            {"ConnectError", "Lidhja nuk është e instaluar"},
            {"TryToConnect", "Për të përsëritur?"},
            {"DBError", "Gabim në lidhje me bazën e të dhënave"},
            {"ScriptError", "Gabim në ekzekutimin e skriptit"},
            {"IDError", "ID e pavlefshme fomat"},
            {"WrongId", "ID i të dhënave nuk ekziston"},
            {"OkUpdate", "Përditësimi i elementit përfundoi me sukses"},
            {"NotUpdate", "Përditësimi i elementit nuk zbatohet"},
            {"OkAdd", "Duke shtuar një artikull të përfunduar me sukses"},
            {"NotAdd", "Duke shtuar një artikull që nuk zbatohet"},
            {"Empty", "Mbledhja e bosh"},
            {"OkRemove", "Fshirja e një artikulli të përfunduar me sukses"},
            {"NotRemove", "Heqja e sendit që nuk zbatohet"},
            {"OkRemoveAll", "Fshirja e elementeve të përdoruesve të përfunduar me sukses"},
            {"AverageCommand", "Vlera mesatare e lartësisë mbi nivelin e detit për të gjitha elementet e mbledhjes: {0}"},
            {"InfoCommand", "Lloji: {0} \n" +
                    "Data e inicializimit: {1} \n" +
                    "Numri i elementeve: {2}"},
            {"GroupCommand", "Elementet e grupuara në lartësi mbi nivelin e detit: {0}"},
            {"Error", "Gabim"},
            {"GetId", "Shkruani ID:"},
            {"newInstructionLabel", "Ndryshoni elementin"},
            {"Hello", "Mirëse u ktheve, "},
            {"Commands", "Komandat e disponueshme:"},
            {"AuthError", "Ndodhi një gabim gjatë përpjekjes për të filluar dritaren e autorizimit"},
            {"Result", "Ekipi i rezultatit:"},
            {"Warn", "ujdes:"},
            {"WrongPop", "Popullsia hyri në mënyrë të gabuar"},
            {"WrongCoord", "Koordinata u prezantua gabimisht"},
            {"WrongAge", "Mosha futet gabimisht"},
            {"WrongClimate", "Klima është futur gabimisht"},
            {"WrongAgl", "Madhësia e agglomerimit futet gabimisht"},
            {"WrongDate", "Data e prezantuar gabimisht"},
            {"WrongMet", "Lartësia mbi nivelin e detit u prezantua gabimisht"},
            {"HelpForCommands", "Shto: Shto një artikull të ri në mbledhjen \n" +
                    "Hiq të parën: Hiq elementin e parë të mbledhjes \n" +
                    "Grupi: bluaj elementet e mbledhjes së fushës mesiaboveselelevel,prodhojnë numrin e elementeve në secilin grup \n" +
                    "Shto nëse më pak: Shto një artikull të ri në mbledhjen nëse vlera e tij është më e vogël se ajo e artikullit më të vogël të këtij koleksioni \n" +
                    "Ndiqni skenarin: numëroni dhe ekzekutoni skenarin nga skedari i specifikuar. \n" +
                    "Qartë: mbledhja e qartë \n" +
                    "Refresh: Përditësoni vlerën e elementit të grumbullimit,ID e të cilave është specifikuar \n" +
                    "Fshij: Fshini një artikull nga koleksioni nga ID e saj \n" +
                    "Shto nëse më pak: Shto një artikull të ri në mbledhjen,nëse vlera e saj tejkalon vlerën e elementit më të madh të këtij koleksioni \n" +
                    "Ndihmoni: të tërhiqni një certifikatë të komandave të arritshme \n" +
                    "Lartësia mesatare mbi nivelin e detit: nxjerrjen e vlerës mesatare të fushës me matës për të gjitha elementet e mbledhjes \n" +
                    "Informacion: Shfaq informacion në lidhje me mbledhjen(lloji,data e inicializimit,numri i elementeve,etj)"},
            {"helpedButton", "ndihmoj"},
            {"changeUserButton", "ndryshoni përdoruesin"},
            {"currentUserLabel", "Mirëse u ktheve, "},
            {"tableTab", "tryezë"},
            {"mapTab", "vizualizim"},
            {"updateIdButton", "ndryshoj"},
            {"removeByIdButton", "fshij"},
            {"infoButton", "referim"},
            {"groupCountingByMetersAboveSeaLevelButton", "grupuar në lartësi mbi nivelin e detit"},
            {"executeScriptButton", "ekzekutoni skenarin"},
            {"removeHeadButton", "fshini së pari"},
            {"clearButton", "qartë"},
            {"averageOfMetersAboveSeaLevelButton", "lartësia e mesme mbi nivelin e detit"},
            {"addIfMaxButton", "shtoni nëse më shumë"},
            {"addIfMinButton", "shtoni nëse më pak"},
            {"addButton", "shtoj"},
            {"creationDateColumn", "Data e krijimit"},
            {"ownerColumn", "Pronar"}
    };
}
