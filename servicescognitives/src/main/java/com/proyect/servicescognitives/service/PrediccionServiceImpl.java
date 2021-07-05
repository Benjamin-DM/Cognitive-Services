package com.proyect.servicescognitives.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyect.servicescognitives.config.HttpClientConfig;
import com.proyect.servicescognitives.model.PrediccionBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class PrediccionServiceImpl extends HttpClientConfig implements PrediccionService {

    @Autowired
    private ObjectMapper objectMapper;

    String URL =
            "https://image-rec365.cognitiveservices.azure.com/customvision/v3.0/Prediction/2f67f0bb-203a-4ac5-b093-435d97213a3d/classify/iterations/Iteration1/url?Prediction-Key=e8c2262268e145868c5af0ace80d07c4&Content-Type=application/json";
    @Override
    public HttpResponse<String> predecir(PrediccionBody prediccionBody) throws IOException, InterruptedException{
        String json = objectMapper.writeValueAsString(prediccionBody);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create(URL))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/json")
                .build();
        //HttpResponse<String> response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }
}
