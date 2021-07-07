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
            "https://custom-predict.cognitiveservices.azure.com/customvision/v3.0/Prediction/b35a6382-1d43-4b37-a39a-8149ed2e7185/detect/iterations/Iteration2/url?Prediction-Key=0fe342767f344d9ab26f16068214cb49&Content-Type=application/json";
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
