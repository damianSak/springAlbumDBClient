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


//    private boolean isAlbumInCollectionValidation(List<Album> albums, final String title) {
//        boolean isAlbumInCollection;
//        return isAlbumInCollection = albums.stream().anyMatch(h ->
//                h.getTitle().equals(title));
//    }

//    private List<Album> createListOfAlbumsToDelete(List<Album> albums, String selectedTitle) {
//        List<Album> albumsToDelete = new LinkedList<>();
//        for (Album album : albums) {
//            if (album.getTitle().equals(selectedTitle)) {
//                albumsToDelete.add(album);
//            }
//        }
//        return albumsToDelete;
//    }

    public void deleteRecordFromDb() throws IOException, URISyntaxException {
        String albumTitle;
        String userChoice;
        int albumId;
        List<Album> albumsToRemove = new LinkedList<>();

        do {
            System.out.println("Podaj czy chcesz usunąć album po tytule( wprowadź 'tytuł' " +
                    "czy numerze ID z bazy (wprowadź 'id'");
            userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
            if(userChoice.toLowerCase().equals("tytuł")){
                System.out.println("Wpisz numer ID albumu do usunięcia : ");
                 albumId = ConsoleInputProvider.readIntFromUserHandlingEmptyInput();
                serverUtils.deleteRecordFromDbById(albumId);
            }else{
                System.out.println("Wpisz nazwę albumu do usunięcia : ");
                albumTitle = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
                serverUtils.deleteRecordFromDbByTitle(albumTitle);
            }

//            {
//                System.out.println("Znaleziona pozycja w liście albumów: \n");
//                System.out.println(printToConsole.printHeading());
//
//                System.out.println("\n" + "Czy na pewno chcesz usunąć ten album z listy ? Wybierz T/t by usunąć lub N/n aby " +
//                        "zobaczyć kolejną znalezioną pozycję lub wyjść jeśli nie ma ich więcej: ");
//                userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
//
//            }

//            System.out.println("Nie ma takiego albumu, błąd pisowni lub nie wczytano właściwej listy albumów\n");

            Messages.showEndingChooseMessage("spróbować usunąć kolejny album");
            userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
        } while (userChoice.toLowerCase().equals("t"));
    }
}
