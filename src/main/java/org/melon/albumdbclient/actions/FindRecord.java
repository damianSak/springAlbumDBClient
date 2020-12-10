package org.melon.albumdbclient.actions;

import org.json.JSONException;
import org.melon.albumdbclient.model.Album;
import org.melon.albumdbclient.utils.ConsoleInputProvider;
import org.melon.albumdbclient.utils.Messages;
import org.melon.albumdbclient.utils.ServerUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public class FindRecord {

    ServerUtils serverUtils;
    PrintToConsole printToConsole;

    public FindRecord() {
        this.serverUtils = new ServerUtils();
        this.printToConsole = new PrintToConsole();
    }

    private static final String ID = "id";
    private static final String BAND = "band";
    private static final String TITLE = "title";
    private static final String GENRE = "genre";
    private static final String RELEASE_YEAR = "releaseYear";

    public void findRecord() throws IOException, URISyntaxException, JSONException {

        String searchedParameter;
        String userChoice;
        List<Album>albumsToPrint = new ArrayList<>();

        do {
            System.out.println("Wpisz pole, po jakim chcesz odnaleźć albumy\n" +
                    "Wprowadź liczbę: \n1. ID \n2. WYKONAWCA \n3. TYTUŁ \n4. GATUNEK \n5. ROK WYDANIA: ");

            userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();

            switch (userChoice) {

                case "1":
                    System.out.println("Podaj ID albumu, który chcesz znaleźć:");
                    searchedParameter = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
                   albumsToPrint= serverUtils.findAlbumsByField(ID, searchedParameter);

                    break;

                case "2":
                    System.out.println("Podaj nazwę zespołu, którego albumy chcesz znaleźć:");
                    searchedParameter = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
                    albumsToPrint= serverUtils.findAlbumsByField(BAND, searchedParameter);
                    break;

                case "3":
                    System.out.println("Podaj tytuł albumu, który  chcesz znaleźć:");
                    searchedParameter = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
                    albumsToPrint= serverUtils.findAlbumsByField(TITLE, searchedParameter);
                    break;

                case "4":
                    System.out.println("Podaj gatunek muzyki, z którą albumy chcesz znaleźć:");
                    searchedParameter = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
                   albumsToPrint = serverUtils.findAlbumsByField(GENRE, searchedParameter);
                    break;

                case "5":
                    System.out.println("Podaj rok, dla którego albumy chcesz znaleźć:");
                    searchedParameter = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
                    albumsToPrint=  serverUtils.findAlbumsByField(RELEASE_YEAR, searchedParameter);
                    break;

                default:
                    System.out.println("Wprowadzona nazwa pozycji do wyszukiwania nie była poprawna");
                    break;
            }
            if(!albumsToPrint.isEmpty()) {
                printToConsole.printAlbumsDbListOnConsole(albumsToPrint);
            }else{
                System.out.println("\nNie znaleziono żadnych albumów\n");
            }
            albumsToPrint.clear();

            Messages.showEndingChooseMessage("aby ponownie wprowadzi nazwę pola do wyszukiwania albumów ");
            userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
        } while (!userChoice.toLowerCase().equals("n"));
    }

}
