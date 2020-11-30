package org.melon.albumdbclient.actions;

import org.melon.albumdbclient.model.Album;
import org.melon.albumdbclient.utils.ConsoleInputProvider;
import org.melon.albumdbclient.utils.Messages;

import java.util.List;

public class EditRecord {

   private List<Album> albums;
   private PrintToConsole printToConsole;

    public EditRecord() {
        this.albums = albums;
        this.printToConsole = new PrintToConsole();
    }


    public void editAlbumFields() {
        String selectedAlbum;
        String userChoice;
        List<Album> albumsToEdit;

        do {
            System.out.println("Wpisz tytuł albumu, którego elementy chciałbyś edytować: ");
            selectedAlbum = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
//            isAlbumInCollectionValidation(albums, selectedAlbum);
//
//            if (isAlbumInCollectionValidation(albums, selectedAlbum)) {
//                albumsToEdit = createListOfAlbumsToEdit(albums, selectedAlbum);
                {
                    System.out.println("Wyszukano rekord: ");


                    System.out.println("Wybierz pozycję do zmiany: ");
                    userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
                    do {
                        switch (userChoice) {
//                            case "Tytuł":
//                                System.out.println("Podaj nowy tytuł albumu:");
//                                String oldTitle = albumToEdit.getTitle();
//                                String newAlbumTitle = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
//                                changeAlbumName(albumToEdit, newAlbumTitle);
//                                Messages.showMessageEndOfFieldEdit(oldTitle, newAlbumTitle);
//                                break;
//
//                            case "Wykonawca":
//                                System.out.println("Podaj nowego wykonawcę albumu:");
//                                String oldBandName = albumToEdit.getBand();
//                                String newBandName = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
//                                changeBandName(albumToEdit, newBandName);
//                                Messages.showMessageEndOfFieldEdit(oldBandName, newBandName);
//                                break;
//
//                            case "Gatunek":
//                                System.out.println("Podaj nowy gatunek muzyki na albumie:");
//                                String oldGenre = albumToEdit.getGenre();
//                                String newGenreName = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
//                                changeGenre(albumToEdit, newGenreName);
//                                Messages.showMessageEndOfFieldEdit(oldGenre, newGenreName);
//                                break;
//
//                            case "Rok Wydania":
//                                System.out.println("Podaj nowy rok wydania albumu:");
//                                int oldReleaseYear = albumToEdit.getReleaseYear();
//                                int newReleaseYear = ConsoleInputProvider.readIntFromUserHandlingEmptyInput();
//                                changeReleaseYear(albumToEdit, newReleaseYear);
//                                Messages.showMessageEndOfFieldEdit(String.valueOf(oldReleaseYear), String.valueOf(newReleaseYear));
//                                break;

                            default:
                                System.out.println("Nazwa pozycji do zmiany nie była poprawna, podaj właściwą nazwę lub wprowadź " +
                                        "'NEXT/next' aby przejść do kolejnego rekordu lub wyjść jeśli nie będzie ich wiecej: ");
                                break;
                        }


                        userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
                    } while (!userChoice.toLowerCase().equals("next"));
                }
//            } else {
//                System.out.println("Nie ma takiego albumu lub błąd pisowni");
//            }


            Messages.showEndingChooseMessage("aby wyszukać i edytować kolejne albumy");
            userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
        } while (userChoice.toLowerCase().equals("t"));
    }

//    private boolean isAlbumInCollectionValidation(List<Album> albums, final String title) {
//        boolean isAlbumInCollection;
//        return isAlbumInCollection = albums.stream().anyMatch(h ->
//                h.getTitle().equals(title));
//    }

//    private List<Album> createListOfAlbumsToEdit(List<Album> albums, String selectedTitle) {
//        List<Album> albumsToEdit = new LinkedList<>();
//        for (Album album : albums) {
//            if (album.getTitle().equals(selectedTitle)) {
//                albumsToEdit.add(album);
//            }
//        }
//        return albumsToEdit;
//    }

    private void changeBandName(Album albumToChange, String newBandName) {
        albumToChange.setBand(newBandName);
    }

    private void changeAlbumName(Album albumToChange, String newAlbumName) {
        albumToChange.setTitle(newAlbumName);
    }

    private void changeGenre(Album albumToChange, String newGenreName) {
        albumToChange.setGenre(newGenreName);
    }

    private void changeReleaseYear(Album albumToChange, int newReleaseYear) {
        albumToChange.setReleaseYear(newReleaseYear);
    }

}
