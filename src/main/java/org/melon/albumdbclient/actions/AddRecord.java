package org.melon.albumdbclient.actions;

import org.melon.albumdbclient.model.Album;
import org.melon.albumdbclient.utils.ConsoleInputProvider;
import org.melon.albumdbclient.utils.Messages;
import org.melon.albumdbclient.utils.ServerUtils;
import org.melon.albumdbclient.utils.StringUtils;

import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class AddRecord {

    private List<Album> albums;
    private PrintToConsole printToConsole;
    private ServerUtils serverUtils;

    public AddRecord(List<Album> albums) {
        this.albums = albums;
        this.printToConsole = new PrintToConsole();
        this.serverUtils = new ServerUtils();
    }

    public AddRecord() {
        this.albums = albums;
        this.printToConsole = new PrintToConsole();
        this.serverUtils = new ServerUtils();
    }

    public boolean isAlbumAlreadyInCollectionValidation( String band, String title) throws IOException {
        List<String> test = serverUtils.findAllAlbums();
        List<Album>mock = new LinkedList<>();
        boolean isAlbumAlreadyInCollection;
        System.out.println("-------------------------------");
        test = serverUtils.findAllAlbums();
        for (String t : test) {
            System.out.println(t);
        }

        System.out.println("-------------------------------");
        return isAlbumAlreadyInCollection = mock.stream().anyMatch(h -> h.getBand().equals(band) &&
                h.getTitle().equals(title));

    }

    private void addAlbumToCollection(List<Album> albums, String band, String title, String genre, int releaseDate) {
        albums.add(new Album(band, title, genre, releaseDate));
    }


    private int readIntFromUserHandlingEmptyInput(String mainMessage, String exceptionMessage, int lowerConstraint, int upperConstraint) {
        int number;

        do {
            System.out.println(mainMessage);
            number = ConsoleInputProvider.readIntFromUserHandlingEmptyInput();
            if (number < lowerConstraint || number > upperConstraint)
                System.out.println(exceptionMessage);
        } while (number < lowerConstraint || number > upperConstraint);
        return number;
    }

    private String readStringFromUserHandlingEmptyInput(String mainMessage, String exceptionMessage) {
        String result;
        do {
            System.out.println(mainMessage);
            result = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
            if (result.isEmpty()) {
                System.out.println(exceptionMessage);
            }
        } while (result.isEmpty());

        return result;
    }

    public void addRecordToDb() throws IOException {

        String band;
        String title;
        String genre;
        String choose;
        int releaseDate;
        boolean isAlbumAlreadyInCollection;

        int actualYear = Calendar.getInstance().get(Calendar.YEAR);

        do {
            band = readStringFromUserHandlingEmptyInput("Podaj nazwę zespołu:",
                    "Nie podano żadnej nazwy zespołu");

            title = readStringFromUserHandlingEmptyInput("Podaj tytuł płyty: ",
                    "Nie podano rzadnej nazwy płyty");

           isAlbumAlreadyInCollection = isAlbumAlreadyInCollectionValidation(band, title);

            if (!isAlbumAlreadyInCollection) {
                genre = readStringFromUserHandlingEmptyInput("Podaj gatunek wykonywanej muzyki: ",
                        "Nie podano rzadnego gatunku");

                releaseDate = readIntFromUserHandlingEmptyInput("Podaj rok wydania albumu: ",
                        "Nie podano roku wydania płyty lub data wykracza poza możliwy relany historyczny zakres "
                        , 1887, actualYear);

                serverUtils.addRecordToDatabase(band, title, genre, releaseDate);

                System.out.println("Dodano do kolekcji album: ");
                System.out.println(printToConsole.printHeading());
                StringUtils.printSingleRecord(band, title, genre, releaseDate);
                System.out.println(printToConsole.printEnding());

            } else {
                System.out.println("Wprowadzony album z podanym wykonawcą już istnieje na tej liście \n");
            }
            Messages.showEndingChooseMessage("dodać kolejną nową pozycję");
            choose = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
        }
        while (choose.toLowerCase().equals("t"));
    }

}

