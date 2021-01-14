package org.melon.albumdbclient.actions;

import org.json.JSONException;
import org.melon.albumdbclient.model.Album;
import org.melon.albumdbclient.utils.ConsoleInputProvider;
import org.melon.albumdbclient.utils.Messages;
import org.melon.albumdbclient.utils.ServerUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class EditRecord {

    private ServerUtils serverUtils;
    private PrintToConsole printToConsole;

    public EditRecord() {
        this.serverUtils = new ServerUtils();
        this.printToConsole = new PrintToConsole();
    }

    public void editAlbumFields() throws IOException, URISyntaxException, JSONException {
        int selectedAlbumID;
        String userChoice;
        List<Album> albumsToEdit;

        do {
            System.out.println("Wpisz ID albumu, którego elementy chciałbyś edytować: ");
            selectedAlbumID = ConsoleInputProvider.readIntFromUserHandlingEmptyInput();
            albumsToEdit = serverUtils.findAlbumsByField("id", String.valueOf(selectedAlbumID));
            if (albumsToEdit.size() != 0) {
                printToConsole.printAlbumsDbListOnConsole(albumsToEdit);
                Album albumToUpdate = albumsToEdit.get(0);
                System.out.println("\nAktualna nazwa zespołu to : " + albumToUpdate.getBand());
                System.out.println("Wprowadź nową nazwę zespołu lub wciśnij ENTER aby pozostawić obecną i przejść dalej:");
                userChoice = ConsoleInputProvider.readStringFromUserWithEmptyInput();
                if (!userChoice.toLowerCase().equals("")) {
                    albumToUpdate.setBand(userChoice);
                    System.out.println("Zmieniono nazwę zespołu na: " + userChoice + "\n");
                }
                System.out.println("Aktualna nazwa albumu to : " + albumToUpdate.getTitle());
                System.out.println("Wprowadź nową nazwę albumu lub wprowadź 'N/n' i wciśnij ENTER aby pozostawić obecną i przejść dalej:");
                userChoice = ConsoleInputProvider.readStringFromUserWithEmptyInput();
                if (!userChoice.toLowerCase().equals("")) {
                    albumToUpdate.setTitle(userChoice);
                    System.out.println("Zmieniono nazwę albumu na: " + userChoice + "\n");
                }
                System.out.println("Aktualny gatunek muzyki to : " + albumToUpdate.getGenre());
                System.out.println("Wprowadź nową nazwę gatunku muzyki lub wprowadź 'N/n' i wciśnij ENTER aby pozostawić obecną i przejść dalej:");
                userChoice = ConsoleInputProvider.readStringFromUserWithEmptyInput();
                if (!userChoice.toLowerCase().equals("")) {
                    albumToUpdate.setGenre(userChoice);
                    System.out.println("Zmieniono gatunek muzyki na: " + userChoice + "\n");
                }
                System.out.println("Aktualny rok wydania albumu to : " + albumToUpdate.getReleaseYear());
                System.out.println("Wprowadź nową nazwę zespołu lub wprowadź 'N/n' i wciśnij ENTER aby pozostawić obecną i przejść dalej:");
                userChoice = ConsoleInputProvider.readStringFromUserWithEmptyInput();
                if (!userChoice.toLowerCase().equals("")) {
                    albumToUpdate.setReleaseYear(Integer.parseInt(userChoice));
                    System.out.println("Zmieniono rok wydania albumu na: " + userChoice + "\n");
                }

                serverUtils.updateWholeAlbum(selectedAlbumID, albumToUpdate.getBand(), albumToUpdate.getTitle(),
                        albumToUpdate.getGenre(), albumToUpdate.getReleaseYear());
                System.out.println("Zaktualiowano rekord do poniższych wartości:");
                printToConsole.printSingleAlbumsOnConsole(albumToUpdate);
            } else {
                System.out.println("Albumu o podanym ID nie ma w bazie danych");
            }

            Messages.showEndingChooseMessage("aby wyszukać i edytować kolejne albumy");
            userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
        } while (userChoice.toLowerCase().equals("t"));
    }

}
