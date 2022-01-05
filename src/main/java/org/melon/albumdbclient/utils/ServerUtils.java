package org.melon.albumdbclient.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.melon.albumdbclient.model.Album;
import org.melon.albumdbclient.model.AlbumDbResponse;
import org.melon.albumdbclient.model.AlbumsListResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ServerUtils {

    private final  String adressPath = "http://localhost:8080/albumsdb/api/";
    private final  String adressFindPath = adressPath + "find/";
    private final  String adressUpdatePath = adressPath + "update/";
    private final  String adressDeletePath = adressPath + "delete/";

    private CloseableHttpClient httpClient;

    public ServerUtils() {

        this.httpClient = HttpClients.createDefault();
    }

    public List<Album> findAllAlbums() throws IOException, JSONException {
        List<Album> albumsFromDb = new LinkedList<>();
        HttpGet request = new HttpGet(adressPath + "find_all");
        CloseableHttpResponse response = httpClient.execute(request);
        String serverResponse = EntityUtils.toString(response.getEntity());
        if (response.getStatusLine().getStatusCode() == 200) {
            printMessageFromResponse(serverResponse);
            albumsFromDb = readListFromResponse(serverResponse);
        } else {
            System.out.println("Jakiś błąd");
        }
        response.close();
        return albumsFromDb;
    }

    public List<Album> findAlbumsByField(String field, String searchedParametr) throws IOException,
            URISyntaxException, JSONException {
        String url2Decode = adressFindPath + field + "/" + searchedParametr;

        String decodedURLAsString = decodeURLAsString(url2Decode);

        HttpGet request = new HttpGet(decodedURLAsString);
        CloseableHttpResponse response = httpClient.execute(request);
        String serverResponse = EntityUtils.toString(response.getEntity());
        List<Album> albumsList = new ArrayList<>();
        if (response.getStatusLine().getStatusCode() == 200) {
            printMessageFromResponse(serverResponse);
            albumsList = readListFromResponse(serverResponse);

        } else {
            System.out.println("jakiś błąd");
        }
        response.close();
        return albumsList;
    }

    public void addRecordToDatabase(String band, String title, String genre, int releaseYear)
            throws IOException, JSONException {
        HttpPost postRequest = new HttpPost(adressPath + "add");
        postRequest.addHeader("Content-Type", "application/json");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(new Album(band, title, genre, releaseYear));
        StringEntity input = new StringEntity(json, Charset.forName("UTF-8"));

        postRequest.setEntity(input);

        CloseableHttpResponse response = httpClient.execute(postRequest);
        String serverResponse = EntityUtils.toString(response.getEntity());

        if (response.getStatusLine().getStatusCode() != 200) {
            printMessageFromResponse(serverResponse);
        } else {
            printMessageFromResponse(serverResponse);

            Album albumFromResponse = readAlbumFromResponse(serverResponse);

            StringUtils.printSingleRecordWithHeadingAndEnding(albumFromResponse.getId(), albumFromResponse.getTitle(),
                    albumFromResponse.getBand(), albumFromResponse.getGenre(), albumFromResponse.getReleaseYear());

        }
        response.close();
    }

    public void updateWholeAlbum(int id, String band, String title, String genre, int releaseYear)
            throws IOException, JSONException {
        HttpPut putRequest = new HttpPut(adressUpdatePath + id);
        putRequest.setHeader("Accept", "application/json");
        putRequest.setHeader("Content-Type", "application/json");

        StringEntity input = mapObjectToJson(new Album(band, title, genre, releaseYear));

        putRequest.setEntity(input);
        CloseableHttpResponse response = httpClient.execute(putRequest);
        String serverResponse = EntityUtils.toString(response.getEntity());
        if (response.getStatusLine().getStatusCode() != 200) {
            printMessageFromResponse(serverResponse);
        } else {

        }
        response.close();
    }

    public void deleteRecordById(int id) throws IOException, JSONException {
        HttpDelete deleteRequest = new HttpDelete(adressDeletePath + id);
        CloseableHttpResponse response = httpClient.execute(deleteRequest);
        String serverResponse = EntityUtils.toString(response.getEntity());

        if (response.getStatusLine().getStatusCode() != 200) {
            printMessageFromResponse(serverResponse);
        } else {
            printMessageFromResponse(serverResponse);
            Album albumFromResponse = readAlbumFromResponse(serverResponse);
            StringUtils.printSingleRecordWithHeadingAndEnding(albumFromResponse.getId(), albumFromResponse.getTitle(),
                    albumFromResponse.getBand(), albumFromResponse.getGenre(), albumFromResponse.getReleaseYear());
        }
        response.close();

    }

    private void printMessageFromResponse(String jsonStr) throws JSONException {
        JSONObject jsonObj = new JSONObject(jsonStr);
        String message = jsonObj.getString("message");
        System.out.println(message);
    }

    private Album readAlbumFromResponse(String jsonStr) {
        Gson gson = new Gson();
        AlbumDbResponse response = gson.fromJson(jsonStr, AlbumDbResponse.class);
        Album album = response.getAlbum();
        return album;
    }

    private List<Album> readListFromResponse(String jsonStr) {
        Gson gson = new Gson();
        AlbumsListResponse response = gson.fromJson(jsonStr, AlbumsListResponse.class);
        List<Album> albumList = response.getAlbumList();
        return albumList;
    }

    private StringEntity mapObjectToJson(Album album) throws UnsupportedEncodingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(album);
        StringEntity input = new StringEntity(json);
        return input;
    }

    private String decodeURLAsString(String urlToDecode)
            throws UnsupportedEncodingException, MalformedURLException, URISyntaxException {
        String decodedURL = URLDecoder.decode(urlToDecode, "UTF-8");
        URL url = new URL(decodedURL);
        URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(),
                url.getQuery(), url.getRef());
        String decodedURLAsString = uri.toASCIIString();
        return decodedURLAsString;
    }

}
