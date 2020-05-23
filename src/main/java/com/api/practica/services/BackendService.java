package com.api.practica.services;

import com.api.practica.dtos.CollectionPaginatedDto;
import com.api.practica.exceptions.CustomException;
import com.api.practica.meli.ProductoMeliDto;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
            throw new CustomException("Error get request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public <T> List<T> getArray(String endpoint, String key, Class<T[]> aClass) throws IOException {
        JSONObject jsonResult =  this.get(endpoint);
        JSONArray resultsJson = jsonResult.getJSONArray(key);
        ObjectMapper mapper = new ObjectMapper();
        List<T> resultObject = Arrays.asList(mapper.readValue(resultsJson.toString(), aClass));
        return resultObject;
    }

    public  <T> List<T> getCollectionPaginatedDto(String endpoint, Class<T> classType) throws IOException {
        JSONObject jsonResult =  this.get(endpoint);
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, classType);
        return mapper.readValue(jsonResult.toString(), javaType);
    }
}
