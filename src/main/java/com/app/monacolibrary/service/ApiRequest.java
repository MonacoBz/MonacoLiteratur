package com.app.monacolibrary.service;

import com.app.monacolibrary.models.DatosAutor;
import com.app.monacolibrary.models.DatosLibro;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
@Service
public class ApiRequest {

    public DatosLibro realizaSolicitud(String url) throws JsonProcessingException {
        String json = obtenJson(url);
        return obtenLibro(json);

    }

    private String obtenJson(String url){
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response.body();
    }

    private DatosLibro obtenLibro(String json){
        DatosLibro libro = null;
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(json).path("results");
            libro = mapper.convertValue(
                    rootNode.get(0),
                    new TypeReference<DatosLibro>() {});
        }catch (JsonProcessingException error){
            System.out.println(error.getMessage());
        }

        return libro;
    }
}
