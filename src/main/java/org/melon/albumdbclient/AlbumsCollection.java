package org.melon.albumdbclient;

import org.melon.albumdbclient.actions.*;
import org.melon.albumdbclient.model.Album;
import org.melon.albumdbclient.utils.ConsoleInputProvider;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class AlbumsCollection  {

    private File chosenDb;
    private List<Album> albums;
    private LoadDb loadDb;
    private ReadAlbum readAlbum;
    private AddRecord addRecord;
    private PrintToConsole printToConsole;
    private EditRecord editRecord;
    private DeleteRecord deleteRecord;
    private SaveDb saveDb;

//    private static final String LOAD = "wczytaj";
//    private static final String LOAD_DB = "wczytaj bazę";
    private static final String ADD = "dodaj";
    private static final String ADD_ALBUM = "dodaj album";
    private static final String PRINT = "drukuj";
    private static final String PRINT_DB = "drukuj bazę";
    private static final String EDIT = "edytuj";
    private static final String EDIT_ELEMENTS = "edytuj elementy";
    private static final String DELETE = "usuń";
    private static final String DELETE_FROM_DB = "usuń z bazy";
//    private static final String SAVE = "zapisz";
//    private static final String SAVE_TO_FILE = "zapisz do pliku";
    private static final String EXIT = "zakończ";

    public AlbumsCollection() {
        this(new LinkedList<>());
    }

    public AlbumsCollection(List<Album> albums) {
        initializeActions(albums);
    }

    private void initializeActions(List<Album> albums) {
        this.albums = albums;
//        this.loadDb = new LoadDb();
//        this.readAlbum = new ReadAlbum();
        this.addRecord = new AddRecord(albums);
        this.printToConsole = new PrintToConsole();
        this.editRecord = new EditRecord(albums);
        this.deleteRecord = new DeleteRecord(albums);
//        this.saveDb = new SaveDb();
    }

    public void start() throws IOException{
        String line;

        do {
            welcomMenu();
            line = ConsoleInputProvider.readStringFromUserHandlingEmptyInput().toLowerCase();

//            if (line.equals(LOAD) || line.equals(LOAD_DB)) {
//                loadDB();
             if (line.equals(ADD) || line.equals(ADD_ALBUM)) {
                addRecord();
            } else if (line.equals(PRINT) || line.equals(PRINT_DB)) {
                printToConsole();
            } else if (line.equals(EDIT) || line.equals(EDIT_ELEMENTS)) {
                editRecord();
            } else if (line.equals(DELETE) || line.equals(DELETE_FROM_DB)) {
                deleteRecord();
//            } else if (line.equals(SAVE) || line.equals(SAVE_TO_FILE)) {
//                saveDb();
            } else if (line.equals(EXIT)) {
                ConsoleInputProvider.closeScanner();
                break;
            } else {
                System.out.println("To nie jest poprawnie wybrana opcja z MENU, wpisz właściwą komendę: ");
            }
        } while ( !line.toLowerCase().equals(EXIT));
    }


    private void endMessage() {

       ConsoleInputProvider.waitForPresedEnter();
    }

    private void welcomMenu() {
        System.out.println("---------------------------");
//        System.out.println("1. Wczytaj bazę płyt");
        System.out.println("1. Dodaj album do bazy");
        System.out.println("2. Drukuj elementy bazy na konsoli");
        System.out.println("3. Edytuj elementy bazy");
        System.out.println("4. Usuń album z bazy");
//        System.out.println("6. Zapisz bazę do pliku tekstowego");
        System.out.println("5. Zakończ");
        System.out.println("---------------------------");
        System.out.println("Wybierz opcję:");
    }

//    private void loadDB() {
//        chosenDb = loadDb.loadDbFromFile();
//        albums = readAlbum.readAlbumsDbToList(chosenDb, StringUtils.countChar(printToConsole.printHeading(), '\n') + 1);
//        endMessage();
//    }

    private void addRecord() throws IOException {
        addRecord.addRecordToDb();
    }

    private void printToConsole() {
        printToConsole.printAlbumsDbOnConsole(albums);
        endMessage();
    }

    private void editRecord() {
        editRecord.editAlbumFields(albums);
    }

    private void deleteRecord() {
        deleteRecord.deleteRecordFromDb(albums);
    }

//    private void saveDb() {
//        saveDb.saveDbToFile(albums);
//
//    }

}
