package org.melon.albumdbclient.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.melon.albumdbclient.model.Album;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class ServerUtils {
    CloseableHttpClient httpClient = HttpClients.createDefault();

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

    public void addRecordToDatabase(String band, String title, String genre, int releaseYear) throws IOException {

        HttpPost postRequest = new HttpPost("http://localhost:8080/server/api/database/add");
        postRequest.addHeader("Content-Type", "application/json");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(new Album(band, title, genre, releaseYear));
        StringEntity input = new StringEntity(json);
        postRequest.setEntity(input);
        CloseableHttpResponse response = httpClient.execute(postRequest);
//        if (response.getStatusLine().getStatusCode() != 200) {
//            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
//        }
        response.close();

    }

    public void updateWholeAlbum(int id, String band, String title, String genre, int releaseYear) throws IOException {
        HttpPut putRequest = new HttpPut("http://localhost:8080/server/api/database/update/" + id);
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

    public void deleteRecordFromDbById(int id) throws IOException {
        HttpDelete deleteRequest = new HttpDelete("http://localhost:8080/server/api/database/delete_by_id/" + id);
        CloseableHttpResponse response = httpClient.execute(deleteRequest);
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                HttpResponse deleteResponse = httpClient.execute(deleteRequest);

            }
        } finally {
            response.close();
        }
    }
}
