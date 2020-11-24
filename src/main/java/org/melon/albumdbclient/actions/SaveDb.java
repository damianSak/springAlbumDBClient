package org.melon.albumdbclient.actions;

import org.melon.albumdbclient.model.Album;
import org.melon.albumdbclient.utils.ConsoleInputProvider;
import org.melon.albumdbclient.utils.Messages;
import org.melon.albumdbclient.utils.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import static java.lang.String.format;

public class SaveDb {

   private LoadDb loadDb;
   private PrintToConsole printToConsole;

    private final String dbPath = StringUtils.selectDbPatch();
    private static final String NEW = "nowa";
    private static final String NEW_DB = "nowa baza";
    private static final String ADD = "dodaj";

    public SaveDb() {
        loadDb = new LoadDb();
        printToConsole = new PrintToConsole();
    }


    public void saveDbToNewFile(List<Album> albums, File dbName) {
        try {
            FileWriter writer = new FileWriter(dbName);
            writer.write(printToConsole.printHeading());
            writer.write("\n");
            writeDbToFile(writer, albums);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void saveDbToExistingFile(List<Album> albums, File dbFileName) {
        try {
            List<String> linesToReprint = readLinesFromExistingFileToReprint(dbFileName);
            FileWriter writer = new FileWriter(dbFileName);

            if (linesToReprint.size() < 4) {
                saveDbToNewFile(albums, dbFileName);
            } else {
                for (String line : linesToReprint) {
                    writer.write(line + "\n");
                }
                writeDbToFile(writer, albums);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readLinesFromExistingFileToReprint(File dbFileName) {
        List<String> linesToReprint = new LinkedList<>();
        try {
            List<String> readedLinesFromExistingDb = Files.readAllLines(Path.of(dbFileName.getPath()));
            linesToReprint = readedLinesFromExistingDb.subList(0, readedLinesFromExistingDb.size() - 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linesToReprint;
    }

    private void writeDbToFile(FileWriter writer, List<Album> albums) {
        try {
            for (Album album : albums) {
                writer.write(format("%-25s|%-19s|%-17s| %-13s\n",
                        album.getTitle(), album.getBand(), album.getGenre(), album.getReleaseYear()));
            }
            writer.write(printToConsole.printEnding());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveDbToFile(List<Album> albums) {
        String userChoice;
        File dbFile;
        String dbName;

        do {
            System.out.println("Wybierz, czy chcesz zapisać aktualną bazę do nowego pliku czy dopisać dane do już istniejącej bazy");
            System.out.println("Nowa baza/ Dodaj do istniejącej bazy:");
            userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();

            if (userChoice.toLowerCase().equals(NEW) || userChoice.toLowerCase().equals(NEW_DB)) {
                do {
                    System.out.println("Podaj nazwę pliku do zapisu: ");
                    dbName = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
                    dbFile = new File(dbPath + dbName + ".txt");
                    if (dbFile.exists()) {
                        System.out.println("Plik z podaną nazwa juz istniene, podaj inną nazwę: ");
                    }
                } while (dbFile.exists());

                saveDbToNewFile(albums, dbFile);
                System.out.println("Utworzono nową bazę danych o nazwię " + "' " + dbName + " '");
                Messages.showEndingChooseMessage("zapisać wybraną bazę jeszcze raz");


            } else if (userChoice.toLowerCase().equals(ADD)) {
                loadDb.loadDBdir();
                System.out.println("Wybierz bazę do której mają być dodane elementy: ");
                do {
                    dbName = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
                    dbFile = new File(dbPath + dbName + ".txt");
                    if (!dbFile.exists()) {
                        System.out.println("Niepoprawna nazwa pliku, podaj właściwą: ");
                    }
                } while (!dbFile.exists());

                saveDbToExistingFile(albums, dbFile);
                System.out.println("Aktualna baza danych została dodana do " + "' " + dbName + " '");
                Messages.showEndingChooseMessage("zapisać wybraną bazę jeszcze raz");
            } else {
                System.out.println("Nie wybrano właściwej opcji zapisu");
                Messages.showEndingChooseMessage("wprowadzić właściwą opcję do zapisu");
            }
            userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
        } while (!userChoice.toLowerCase().equals("n"));
    }
}

