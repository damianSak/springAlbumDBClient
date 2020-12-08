package org.melon.albumdbclient.actions;

import org.melon.albumdbclient.model.Album;
import org.melon.albumdbclient.utils.ConsoleInputProvider;
import org.melon.albumdbclient.utils.Messages;
import org.melon.albumdbclient.utils.ServerUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

public class DeleteRecord {

    private PrintToConsole printToConsole;
    private ServerUtils serverUtils;

    public DeleteRecord() {

        this.printToConsole = new PrintToConsole();
        this.serverUtils = new ServerUtils();
    }

    public void deleteRecordFromDb() throws IOException, URISyntaxException {

        String userChoice;
        int albumId;
        List<Album> albumsToRemove = new LinkedList<>();

        do {
            System.out.println("Podaj numer ID albumu który chcesz usunąć:");
            albumId = ConsoleInputProvider.readIntFromUserHandlingEmptyInput();
            serverUtils.deleteRecordById(albumId);
            Messages.showEndingChooseMessage("spróbować usunąć kolejny album");
            userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
        } while (userChoice.toLowerCase().equals("t"));
    }
}
