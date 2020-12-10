package org.melon.albumdbclient.actions;

import org.json.JSONException;
import org.melon.albumdbclient.utils.ConsoleInputProvider;
import org.melon.albumdbclient.utils.Messages;
import org.melon.albumdbclient.utils.ServerUtils;

import java.io.IOException;

public class DeleteRecord {

    private ServerUtils serverUtils;

    public DeleteRecord() {

        this.serverUtils = new ServerUtils();
    }

    public void deleteRecordFromDb() throws IOException, JSONException {

        String userChoice;
        int albumId;

        do {
            System.out.println("Podaj numer ID albumu który chcesz usunąć:");
            albumId = ConsoleInputProvider.readIntFromUserHandlingEmptyInput();
            serverUtils.deleteRecordById(albumId);
            Messages.showEndingChooseMessage("spróbować usunąć kolejny album");
            userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
        } while (userChoice.toLowerCase().equals("t"));
    }
}
