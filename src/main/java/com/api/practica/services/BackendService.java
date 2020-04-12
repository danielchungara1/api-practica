package com.api.practica.services;

import com.api.practica.exceptions.CustomException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BackendService {
    public JSONObject get(String endPoint) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(endPoint);
        CloseableHttpResponse response = httpClient.execute(request);
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();

            // return it as a String
            String body = EntityUtils.toString(entity);

            // Org.Json
            JSONObject jsonObject = new JSONObject(body);

            return jsonObject;

        }
        else{
            throw new CustomException("Error al obtener productos de Mercado Libre.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
