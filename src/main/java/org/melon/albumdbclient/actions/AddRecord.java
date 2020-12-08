package org.melon.albumdbclient.actions;

import org.json.JSONException;
import org.melon.albumdbclient.utils.ConsoleInputProvider;
import org.melon.albumdbclient.utils.Messages;
import org.melon.albumdbclient.utils.ServerUtils;

import java.io.IOException;
import java.util.Calendar;

public class AddRecord {

    private ServerUtils serverUtils;

    public AddRecord() {
        this.serverUtils = new ServerUtils();
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

    public void addRecordToDb() throws IOException, JSONException {

        int id;
        String band;
        String title;
        String genre;
        String choose;
        int releaseDate;
        int actualYear = Calendar.getInstance().get(Calendar.YEAR);

        do {
            band = readStringFromUserHandlingEmptyInput("Podaj nazwę zespołu:",
                    "Nie podano żadnej nazwy zespołu");

            title = readStringFromUserHandlingEmptyInput("Podaj tytuł płyty: ",
                    "Nie podano rzadnej nazwy płyty");

//           isAlbumAlreadyInCollection = isAlbumAlreadyInCollectionValidation(band, title);
            genre = readStringFromUserHandlingEmptyInput("Podaj gatunek wykonywanej muzyki: ",
                    "Nie podano rzadnego gatunku");

            releaseDate = readIntFromUserHandlingEmptyInput("Podaj rok wydania albumu: ",
                    "Nie podano roku wydania płyty lub data wykracza poza możliwy relany historyczny zakres "
                    , 1887, actualYear);

            id = readIntFromUserHandlingEmptyInput("Podaj rok wydania albumu: ",
                    "Nie podano roku wydania płyty lub data wykracza poza możliwy relany historyczny zakres "
                    , 1887, actualYear);
                serverUtils.addRecordToDatabase(id, band, title, genre, releaseDate);


            Messages.showEndingChooseMessage("dodać kolejną nową pozycję");
            choose = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
        }
        while (choose.toLowerCase().equals("t"));
    }

}

