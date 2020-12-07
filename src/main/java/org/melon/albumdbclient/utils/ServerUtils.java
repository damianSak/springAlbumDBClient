package org.melon.albumdbclient.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.melon.albumdbclient.actions.PrintToConsole;
import org.melon.albumdbclient.model.Album;
import org.melon.albumdbclient.model.AlbumDbResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

public class ServerUtils {
    final String adressPath = "http://localhost:8080/albumsdb/api/";


    CloseableHttpClient httpClient = HttpClients.createDefault();
    PrintToConsole printToConsole = new PrintToConsole();


    public List<String> findAllAlbums() throws IOException {
        List<String> AlbumsFromDb = new LinkedList<>();
        HttpGet request = new HttpGet("http://localhost:8080/server/api/database/find_all");
        CloseableHttpResponse response = httpClient.execute(request);
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        }

        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = br.readLine()) != null) {
                    AlbumsFromDb.add(line);

                }
            }
        } finally {
            response.close();
        }
        return AlbumsFromDb;
    }

    public void findAlbumById(int albumID) throws IOException {
        HttpGet request = new HttpGet("http://localhost:8080/server/api/database/find/id/" + albumID);
        CloseableHttpResponse response = httpClient.execute(request);
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        }
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } finally {
            response.close();
        }
    }

    public void findAlbumsByBandName(String band) throws IOException {
        HttpGet request = new HttpGet("http://localhost:8080/server/api/database/find/band/" + band);
        CloseableHttpResponse response = httpClient.execute(request);
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        }
        try {
            HttpEntity entity = response.getEntity();
//            if(entity !=null) {
//                BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//                String line;
//                while ((line = br.readLine()) != null) {
//                    System.out.println(line);
//                }
//            }
        } finally {
            response.close();
        }
    }

    public void findAlbumsByTitle(String title) throws IOException {
        HttpGet request = new HttpGet("http://localhost:8080/server/api/database/find/title/" + title);
        CloseableHttpResponse response = httpClient.execute(request);
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        }
        try {
            HttpEntity entity = response.getEntity();
//            if(entity !=null) {
//                BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//                String line;
//                while ((line = br.readLine()) != null) {
//                    System.out.println(line);
//                }
//            }
        } finally {
            response.close();
        }
    }

    public void findAlbumsByReleaseYear(int releaseYear) throws IOException {
        HttpGet request = new HttpGet("http://localhost:8080/server/api/database/find/releaseYear/" + releaseYear);
        CloseableHttpResponse response = httpClient.execute(request);
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        }
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } finally {
            response.close();
        }
    }

    public void findAlbumsByGenre(String genre) throws IOException {
        HttpGet request = new HttpGet("http://localhost:8080/server/api/database/find/genre/" + genre);
        CloseableHttpResponse response = httpClient.execute(request);
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        }
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } finally {
            response.close();
        }
    }

    public void addRecordToDatabase(String band, String title, String genre, int releaseYear) throws IOException, JSONException {

        HttpPost postRequest = new HttpPost(adressPath + "add");
        postRequest.addHeader("Content-Type", "application/json");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(new Album(band, title, genre, releaseYear));
        StringEntity input = new StringEntity(json);
        postRequest.setEntity(input);
        CloseableHttpResponse response = httpClient.execute(postRequest);
        String serverResponse = EntityUtils.toString(response.getEntity());

        if (response.getStatusLine().getStatusCode() != 200) {
//            printMessageFromResponse(serverResponse);
            parseJsonToGson(serverResponse);


        } else {
//           printMessageFromResponse(serverResponse);
            parseJsonToGson(serverResponse);
            System.out.println(printToConsole.printHeading());
            StringUtils.printSingleRecord(band, title, genre, releaseYear);
            System.out.println(printToConsole.printEnding());
        }
        response.close();
    }

    public void updateWholeAlbum(int id, String band, String title, String genre, int releaseYear) throws IOException {
        HttpPut putRequest = new HttpPut(adressPath + id);
        putRequest.setHeader("Accept", "application/json");
        putRequest.setHeader("Content-Type", "application/json");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(new Album(band, title, genre, releaseYear));
        StringEntity input = new StringEntity(json);
        putRequest.setEntity(input);
        System.out.println("executing request" + putRequest.getRequestLine());
        CloseableHttpResponse response = httpClient.execute(putRequest);
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        }
        response.close();
    }

    public void deleteRecordFromDbById(int id) throws IOException, URISyntaxException {

        HttpDelete deleteRequest = new HttpDelete(adressPath + String.valueOf(id));
        CloseableHttpResponse response = httpClient.execute(deleteRequest);
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
//                HttpResponse deleteResponse = httpClient.execute(deleteRequest);

            }
        } finally {
            response.close();
        }
    }

    public void deleteRecordFromDbByTitle(String title) throws IOException, URISyntaxException {
        HttpDelete deleteRequest = new HttpDelete(adressPath + title);
        System.out.println(deleteRequest.getURI());
        CloseableHttpResponse response = httpClient.execute(deleteRequest);
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {


            } else {
                System.out.println("Brak albumu w bazie danych");
            }
        } finally {
            response.close();
        }
    }

    private void printMessageFromResponse(String jsonStr) throws JSONException {
        String jsonString = jsonStr;
        JSONObject jsonObj = new JSONObject(jsonString);
        String message = jsonObj.getString("message");
        System.out.println(message);
    }

    private void parseJsonToGson(String jsonStr){
        Gson gson = new Gson();
        AlbumDbResponse response = gson.fromJson(jsonStr,AlbumDbResponse.class);
        System.out.println(response.getMessage());
    }

}
