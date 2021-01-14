package org.melon.albumdbclient.actions;

import org.json.JSONException;
import org.melon.albumdbclient.model.Album;
import org.melon.albumdbclient.utils.ConsoleInputProvider;
import org.melon.albumdbclient.utils.Messages;
import org.melon.albumdbclient.utils.ServerUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class DeleteRecord {

    private ServerUtils serverUtils;
    private PrintToConsole printToConsole;

    public DeleteRecord() {
        this.printToConsole = new PrintToConsole();
        this.serverUtils = new ServerUtils();
    }

    public void deleteRecordFromDb() throws IOException, JSONException, URISyntaxException {

        String userChoice;
        int albumId;
        List<Album> albums;
        do {
            System.out.println("Podaj numer ID albumu który chcesz usunąć:");
            albumId = ConsoleInputProvider.readIntFromUserHandlingEmptyInput();
            albums = serverUtils.findAlbumsByField("id", String.valueOf(albumId));
            if (!albums.isEmpty()) {
                printToConsole.printAlbumsDbListOnConsole(albums);
                System.out.println("Czy na pewno chcesz usunąć ten album z kolecji ? \n" +
                        "wprowadź 'T/t' aby usunąć lub 'N/n aby przejść dalej");
                userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
                if (userChoice.toLowerCase().equals("t")) {
                    serverUtils.deleteRecordById(albumId);
                }
            } else {
                System.out.println("\n Brak albumu o podanym numerze ID w kolekcji\n");
            }
            Messages.showEndingChooseMessage("spróbować usunąć kolejny album");
            userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
        } while (userChoice.toLowerCase().equals("t"));
    }
}
