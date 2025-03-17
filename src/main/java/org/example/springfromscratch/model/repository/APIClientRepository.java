package org.example.springfromscratch.model.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;

import java.net.http.HttpClient;

public interface APIClientRepository {
    final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
    final HttpClient httpClient = HttpClient.newHttpClient();
    final ObjectMapper objectMapper = new ObjectMapper();
}
