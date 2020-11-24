package org.melon.albumdbclient.serverUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.melon.albumdbclient.model.Album;

import java.io.IOException;

public class UpdateOnServer {
    CloseableHttpClient httpClient = HttpClients.createDefault();

    public void updateWholeAlbum(int id) throws IOException {
        HttpPut putRequest = new HttpPut("http://localhost:8080/server/api/database/update/"+id);
        putRequest.setHeader("Accept", "application/json");
        putRequest.setHeader("Content-Type", "application/json");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(new Album("Bayer Ful", "Mega Mix 2000", "Hard polo", 2020));
        StringEntity input = new StringEntity(json);
        putRequest.setEntity(input);
        System.out.println("executing request"+putRequest.getRequestLine());
        CloseableHttpResponse response = httpClient.execute(putRequest);
//        if (response.getStatusLine().getStatusCode() != 200) {
//            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
//        }
        response.close();
    }
}



