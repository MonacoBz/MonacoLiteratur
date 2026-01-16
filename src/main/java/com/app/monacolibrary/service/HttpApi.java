package com.app.monacolibrary.service;

import com.app.monacolibrary.models.DatosAutor;
import com.app.monacolibrary.models.DatosLibro;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class HttpApi {

    public void realizaSolicitud(String url) throws JsonProcessingException {
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
        String json = response.body();
        ObjectMapper mapper = new ObjectMapper();
        DatosLibro datosLibro;
        DatosAutor libros;
        JsonNode rootNode = mapper.readTree(json).path("results");
        List<DatosLibro> listaLibros = mapper.convertValue(rootNode, new TypeReference<List<DatosLibro>>() {});
        listaLibros.forEach(System.out::println);
    }
}
