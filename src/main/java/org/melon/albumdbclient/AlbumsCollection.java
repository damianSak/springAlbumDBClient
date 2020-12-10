package org.melon.albumdbclient;

import org.json.JSONException;
import org.melon.albumdbclient.actions.*;
import org.melon.albumdbclient.utils.ConsoleInputProvider;

import java.io.IOException;
import java.net.URISyntaxException;


public class AlbumsCollection {

    private AddRecord addRecord;
    private PrintToConsole printToConsole;
    private EditRecord editRecord;
    private DeleteRecord deleteRecord;
    private FindRecord findRecord;

    private static final String FIND = "wyszukaj";
    private static final String FIND_NUMBER = "1";
    private static final String ADD = "dodaj";
    private static final String ADD_NUMBER = "2";
    private static final String PRINT = "drukuj";
    private static final String PRINT_DB = "3";
    private static final String EDIT = "edytuj";
    private static final String EDIT_NUMBER = "4";
    private static final String DELETE = "usuń";
    private static final String DELETE_NUMBER = "5";
    private static final String EXIT = "zakończ";
    private static final String EXIT_NUMBER = "6";

    public AlbumsCollection() {
        initializeActions();
    }

    private void initializeActions() {

        this.addRecord = new AddRecord();
        this.printToConsole = new PrintToConsole();
        this.editRecord = new EditRecord();
        this.deleteRecord = new DeleteRecord();
        this.findRecord = new FindRecord();
    }

    public void start() throws IOException, URISyntaxException, JSONException {
        String line;

        do {
            welcomMenu();
            line = ConsoleInputProvider.readStringFromUserHandlingEmptyInput().toLowerCase();

            if (line.equals(ADD) || line.equals(ADD_NUMBER)) {
                addRecord();
            } else if (line.equals(PRINT) || line.equals(PRINT_DB)) {
                printToConsole();
            } else if (line.equals(EDIT) || line.equals(EDIT_NUMBER)) {
                editRecord();
            } else if (line.equals(DELETE) || line.equals(DELETE_NUMBER)) {
                deleteRecord();
            } else if (line.equals(FIND)||line.equals(FIND_NUMBER)) {
                findRecord();
            } else if (line.equals(EXIT)||line.equals(EXIT_NUMBER)) {
                ConsoleInputProvider.closeScanner();
                break;
            } else {
                System.out.println("To nie jest poprawnie wybrana opcja z MENU, wpisz właściwą komendę: ");
            }
        } while (!line.toLowerCase().equals(EXIT));
    }


    private void endMessage() {

        ConsoleInputProvider.waitForPresedEnter();
    }

    private void welcomMenu() {
        System.out.println("---------------------------");
        System.out.println("1. Wyszukaj albumy w bazie");
        System.out.println("2. Dodaj album do bazy");
        System.out.println("3. Drukuj elementy bazy na konsoli");
        System.out.println("4. Edytuj elementy bazy");
        System.out.println("5. Usuń album z bazy");
        System.out.println("6. Zakończ");
        System.out.println("---------------------------");
        System.out.println("Wybierz opcję:");
    }

    private void addRecord() throws IOException, JSONException {
        addRecord.addRecordToDb();
    }

    private void printToConsole() throws IOException,JSONException{
        printToConsole.printAllAlbumsFromDbOnconsle();
        endMessage();
    }

    private void editRecord() throws IOException,URISyntaxException,JSONException  {
        editRecord.editAlbumFields();
    }

    private void deleteRecord() throws IOException, JSONException {
        deleteRecord.deleteRecordFromDb();
    }
    private void findRecord() throws IOException,URISyntaxException,JSONException{
        findRecord.findRecord();
    }

}
