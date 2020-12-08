package org.melon.albumdbclient.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.melon.albumdbclient.model.AlbumsListResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;

public class ServerUtils {



    final String adressPath = "http://localhost:8080/albumsdb/api/";
    final String adressFindPath = adressPath + "find/";


    CloseableHttpClient httpClient = HttpClients.createDefault();
    PrintToConsole printToConsole = new PrintToConsole();


    public List<String> findAllAlbums() throws IOException {
        List<String> AlbumsFromDb = new LinkedList<>();
        HttpGet request = new HttpGet(adressPath + "find_all");
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

//    public void findAlbumById(int albumID) throws IOException {
//        HttpGet request = new HttpGet(adressFindPath + "id");
//        CloseableHttpResponse response = httpClient.execute(request);
//        if (response.getStatusLine().getStatusCode() != 200) {
//            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
//        }
//        try {
//            HttpEntity entity = response.getEntity();
//            if (entity != null) {
//                BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//                String line;
//                while ((line = br.readLine()) != null) {
//                    System.out.println(line);
//                }
//            }
//        } finally {
//            response.close();
//        }
//    }

//    public void findAlbumsByBandName(String band) throws IOException {
//        HttpGet request = new HttpGet("http://localhost:8080/server/api/database/find/band/" + band);
//        CloseableHttpResponse response = httpClient.execute(request);
//        if (response.getStatusLine().getStatusCode() != 200) {
//            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
//        }
//        try {
//            HttpEntity entity = response.getEntity();
////            if(entity !=null) {
////                BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
////                String line;
////                while ((line = br.readLine()) != null) {
////                    System.out.println(line);
////                }
////            }
//        } finally {
//            response.close();
//        }
//    }
//
//    public void findAlbumsByTitle(String title) throws IOException {
//        HttpGet request = new HttpGet("http://localhost:8080/server/api/database/find/title/" + title);
//        CloseableHttpResponse response = httpClient.execute(request);
//        if (response.getStatusLine().getStatusCode() != 200) {
//            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
//        }
//        try {
//            HttpEntity entity = response.getEntity();
////            if(entity !=null) {
////                BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
////                String line;
////                while ((line = br.readLine()) != null) {
////                    System.out.println(line);
////                }
////            }
//        } finally {
//            response.close();
//        }
//    }

    public void findAlbumsByField(String field, String searchedParametr) throws IOException,URISyntaxException,JSONException {
        String url2Decode = adressFindPath + field + "/" + searchedParametr;
        String decodedURL = URLDecoder.decode(url2Decode, "UTF-8");
        URL url = new URL(decodedURL);
        URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
        String decodedURLAsString = uri.toASCIIString();
        HttpGet request = new HttpGet(decodedURLAsString);
        CloseableHttpResponse response = httpClient.execute(request);
        String serverResponse = EntityUtils.toString(response.getEntity());


        if (response.getStatusLine().getStatusCode() == 200) {
            printMessageFromResponse(serverResponse);
            List<Album> albumsToPrintOnConsole = readListFromResponse(serverResponse);
            printToConsole.printAlbumsDbOnConsole(albumsToPrintOnConsole);

        }else{
            System.out.println("Nic nie znaleziono");
        }

        response.close();

    }

//    public void findAlbumsByReleaseYear(int releaseYear) throws IOException {
//        HttpGet request = new HttpGet("http://localhost:8080/server/api/database/find/releaseYear/" + releaseYear);
//        CloseableHttpResponse response = httpClient.execute(request);
//        if (response.getStatusLine().getStatusCode() != 200) {
//            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
//        }
//        try {
//            HttpEntity entity = response.getEntity();
//            if (entity != null) {
//                BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//                String line;
//                while ((line = br.readLine()) != null) {
//                    System.out.println(line);
//                }
//            }
//        } finally {
//            response.close();
//        }
//    }
//
//    public void findAlbumsByGenre(String genre) throws IOException {
//        HttpGet request = new HttpGet("http://localhost:8080/server/api/database/find/genre/" + genre);
//        CloseableHttpResponse response = httpClient.execute(request);
//        if (response.getStatusLine().getStatusCode() != 200) {
//            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
//        }
//        try {
//            HttpEntity entity = response.getEntity();
//            if (entity != null) {
//                BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//                String line;
//                while ((line = br.readLine()) != null) {
//                    System.out.println(line);
//                }
//            }
//        } finally {
//            response.close();
//        }
//    }

    public void addRecordToDatabase(int id, String band, String title, String genre, int releaseYear) throws IOException, JSONException {

        HttpPost postRequest = new HttpPost(adressPath + "add");
        postRequest.addHeader("Content-Type", "application/json");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(new Album(id, band, title, genre, releaseYear));
        StringEntity input = new StringEntity(json);

        postRequest.setEntity(input);

        CloseableHttpResponse response = httpClient.execute(postRequest);
        String serverResponse = EntityUtils.toString(response.getEntity());

        if (response.getStatusLine().getStatusCode() != 200) {
            printMessageFromResponse(serverResponse);
        } else {
            printMessageFromResponse(serverResponse);

            System.out.println(printToConsole.printHeading());
            StringUtils.printSingleRecord(id,band, title, genre, releaseYear);
            System.out.println(printToConsole.printEnding());
        }
        response.close();
    }

    public void updateWholeAlbum(int id, String band, String title, String genre, int releaseYear) throws IOException {
        HttpPut putRequest = new HttpPut(adressPath + id);
        putRequest.setHeader("Accept", "application/json");
        putRequest.setHeader("Content-Type", "application/json");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(new Album(id,band, title, genre, releaseYear));
        StringEntity input = new StringEntity(json);
        putRequest.setEntity(input);
        CloseableHttpResponse response = httpClient.execute(putRequest);
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        }
        response.close();
    }

    public void deleteRecordById(int id) throws IOException, URISyntaxException {

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

    private void printMessageFromResponse(String jsonStr) throws JSONException {
        JSONObject jsonObj = new JSONObject(jsonStr);
        String message = jsonObj.getString("message");
        System.out.println(message);
    }

    private void parseJsonToGson(String jsonStr) {
        Gson gson = new Gson();
        AlbumsListResponse response = gson.fromJson(jsonStr, AlbumsListResponse.class);
        System.out.println(response.getMessage());
    }

    private List<Album> readListFromResponse(String jsonStr) throws JsonProcessingException {
        Gson gson = new Gson();

        AlbumsListResponse response = gson.fromJson(jsonStr,AlbumsListResponse.class);
        List<Album> albumList = response.getAlbumList();
//        Type listType = new TypeToken<List<Album>>(){}.getType();
//
//         List<Album> albumList = gson.fromJson(jsonStr,listType);
//       Collection<AlbumsListResponse> readValues = new ObjectMapper().readValue(jsonStr, new TypeReference<Collection<AlbumsListResponse>>() {});
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
//        AlbumsListResponse[] albums = objectMapper.readValue(jsonStr,AlbumsListResponse[].class);
//        List<AlbumsListResponse>albumsListResponses = new ArrayList<>(Arrays.asList(albums));

         return albumList;
    }

}
