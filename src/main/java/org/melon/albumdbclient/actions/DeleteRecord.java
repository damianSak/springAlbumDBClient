package org.melon.albumdbclient.actions;

import org.melon.albumdbclient.model.Album;
import org.melon.albumdbclient.utils.ConsoleInputProvider;
import org.melon.albumdbclient.utils.Messages;
import org.melon.albumdbclient.utils.StringUtils;

import java.util.LinkedList;
import java.util.List;

public class DeleteRecord {

   private List<Album> albums;

   private PrintToConsole printToConsole;

    public DeleteRecord(List<Album> albums) {
        this.albums = albums;
        this.printToConsole = new PrintToConsole();
    }


    private boolean isAlbumInCollectionValidation(List<Album> albums, final String title) {
        boolean isAlbumInCollection;
        return isAlbumInCollection = albums.stream().anyMatch(h ->
                h.getTitle().equals(title));
    }

    private List<Album> createListOfAlbumsToDelete(List<Album> albums, String selectedTitle) {
        List<Album> albumsToDelete = new LinkedList<>();
        for (Album album : albums) {
            if (album.getTitle().equals(selectedTitle)) {
                albumsToDelete.add(album);
            }
        }
        return albumsToDelete;
    }

    public void deleteRecordFromDb(List<Album> albums) {
        String albumTitle;
        String userChoice;
        List<Album> albumsToDelete;
        List<Album> albumsToRemove = new LinkedList<>();

        do {
            System.out.println("Wpisz nazwę poszukiwanego albumu: ");
            albumTitle = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
            if (isAlbumInCollectionValidation(albums, albumTitle)) {
                albumsToDelete = createListOfAlbumsToDelete(albums, albumTitle);
                for (Album findedAlbum : albumsToDelete) {
                    System.out.println("Znaleziona pozycja w liście albumów: \n");
                    System.out.println(printToConsole.printHeading());
                    StringUtils.printSingleRecord(findedAlbum.getTitle(), findedAlbum.getBand(), findedAlbum.getGenre(), findedAlbum.getReleaseYear());
                    System.out.println("\n" + "Czy na pewno chcesz usunąć ten album z listy ? Wybierz T/t by usunąć lub N/n aby " +
                            "zobaczyć kolejną znalezioną pozycję lub wyjść jeśli nie ma ich więcej: ");
                    userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
                    if (userChoice.toLowerCase().equals("t")) {
                        albumsToRemove.add(findedAlbum);
                    }
                }
                albums.removeAll(albumsToRemove);
                albumsToRemove.clear();
            } else {
                System.out.println("Nie ma takiego albumu, błąd pisowni lub nie wczytano właściwej listy albumów\n");
            }
            Messages.showEndingChooseMessage("spróbować usunąć kolejny album");
            userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
        } while (userChoice.toLowerCase().equals("t"));
    }
}
