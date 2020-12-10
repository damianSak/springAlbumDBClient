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
                System.out.println("Wprowadź nową nazwę zespołu lub wprowadź 'N/n' i wciśnij ENTER aby pozostawić obecną i przejść dalej:");
                userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
                if (!userChoice.toLowerCase().equals("n")) {
                    albumToUpdate.setBand(userChoice);
                    System.out.println("Zmieniono nazwę zespołu na: " + userChoice);
                }
                System.out.println("\nAktualna nazwa albumu to : " + albumToUpdate.getTitle());
                System.out.println("Wprowadź nową nazwę albumu lub wprowadź 'N/n' i wciśnij ENTER aby pozostawić obecną i przejść dalej:");
                userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
                if (!userChoice.toLowerCase().equals("n")) {
                    albumToUpdate.setTitle(userChoice);
                    System.out.println("Zmieniono nazwę albumu na: " + userChoice);
                }
                System.out.println("\nAktualny gatunek muzyki to : " + albumToUpdate.getGenre());
                System.out.println("Wprowadź nową nazwę gatunku muzyki lub wprowadź 'N/n' i wciśnij ENTER aby pozostawić obecną i przejść dalej:");
                userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
                if (!userChoice.toLowerCase().equals("n")) {
                    albumToUpdate.setGenre(userChoice);
                    System.out.println("Zmieniono gatunek muzyki na: " + userChoice);
                }
                System.out.println("\nAktualny rok wydania albumu to : " + albumToUpdate.getReleaseYear());
                System.out.println("Wprowadź nową nazwę zespołu lub wprowadź 'N/n' i wciśnij ENTER aby pozostawić obecną i przejść dalej:");
                userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
                if (!userChoice.toLowerCase().equals("n")) {
                    albumToUpdate.setReleaseYear(Integer.parseInt(userChoice));
                    System.out.println("Zmieniono rok wydania albumu na: " + userChoice);
                }
//                System.out.println("Wybierz pozycję do zmiany: ");
//                userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
//                do {
//                    switch (userChoice) {
////                            case "Tytuł":
////                                System.out.println("Podaj nowy tytuł albumu:");
////                                String oldTitle = albumToEdit.getTitle();
////                                String newAlbumTitle = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
////                                changeAlbumName(albumToEdit, newAlbumTitle);
////                                Messages.showMessageEndOfFieldEdit(oldTitle, newAlbumTitle);
////                                break;
////
////                            case "Wykonawca":
////                                System.out.println("Podaj nowego wykonawcę albumu:");
////                                String oldBandName = albumToEdit.getBand();
////                                String newBandName = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
////                                changeBandName(albumToEdit, newBandName);
////                                Messages.showMessageEndOfFieldEdit(oldBandName, newBandName);
////                                break;
////
////                            case "Gatunek":
////                                System.out.println("Podaj nowy gatunek muzyki na albumie:");
////                                String oldGenre = albumToEdit.getGenre();
////                                String newGenreName = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
////                                changeGenre(albumToEdit, newGenreName);
////                                Messages.showMessageEndOfFieldEdit(oldGenre, newGenreName);
////                                break;
////
////                            case "Rok Wydania":
////                                System.out.println("Podaj nowy rok wydania albumu:");
////                                int oldReleaseYear = albumToEdit.getReleaseYear();
////                                int newReleaseYear = ConsoleInputProvider.readIntFromUserHandlingEmptyInput();
////                                changeReleaseYear(albumToEdit, newReleaseYear);
////                                Messages.showMessageEndOfFieldEdit(String.valueOf(oldReleaseYear), String.valueOf(newReleaseYear));
////                                break;
//
//                        default:
//                            System.out.println("Nazwa pozycji do zmiany nie była poprawna, podaj właściwą nazwę lub wprowadź " +
//                                    "'NEXT/next' aby przejść do kolejnego rekordu lub wyjść jeśli nie będzie ich wiecej: ");
//                            break;
//                    }
                serverUtils.updateWholeAlbum(selectedAlbumID, albumToUpdate.getBand(), albumToUpdate.getTitle(),
                        albumToUpdate.getGenre(), albumToUpdate.getReleaseYear());
                System.out.println("Zaktualiowano rekord do poniższych wartości:");
                printToConsole.printSingleAlbumsOnConsole(albumToUpdate);
            } else {
                System.out.println("Albumu o podanym ID nie ma w bazie danych");
//            while (!userChoice.toLowerCase().equals("next")) ;
            }
////            } else {
////                System.out.println("Nie ma takiego albumu lub błąd pisowni");
////            }


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

//    private void changeBandName(Album albumToChange, String newBandName) {
//        albumToChange.setBand(newBandName);
//    }
//
//    private void changeAlbumName(Album albumToChange, String newAlbumName) {
//        albumToChange.setTitle(newAlbumName);
//    }
//
//    private void changeGenre(Album albumToChange, String newGenreName) {
//        albumToChange.setGenre(newGenreName);
//    }
//
//    private void changeReleaseYear(Album albumToChange, int newReleaseYear) {
//        albumToChange.setReleaseYear(newReleaseYear);
//    }

}
