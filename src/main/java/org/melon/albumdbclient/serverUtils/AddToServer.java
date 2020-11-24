package org.melon.albumdbclient.serverUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.melon.albumdbclient.model.Album;

import java.io.IOException;

public class AddToServer {
    CloseableHttpClient httpClient = HttpClients.createDefault();

    public void addRecordToDatabase() throws IOException {

        HttpPost postRequest = new HttpPost("http://localhost:8080/server/api/database/add");
        postRequest.addHeader("Content-Type", "application/json");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(new Album("Bayer Ful", "Mega Mix 2000", "Hard polo", 2020));
        StringEntity input = new StringEntity(json);
        postRequest.setEntity(input);
        CloseableHttpResponse response = httpClient.execute(postRequest);
        response.close();

    }
}
