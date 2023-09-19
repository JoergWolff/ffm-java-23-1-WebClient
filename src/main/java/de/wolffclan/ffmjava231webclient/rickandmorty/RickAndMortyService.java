package de.wolffclan.ffmjava231webclient.rickandmorty;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class RickAndMortyService {
    private final WebClient webClient;

    public RickAndMortyService(@Value("${rickandmorty_api_url}") String url){
        webClient = WebClient.create(url);
    }

    public List<RickAndMortyApiCharacterResponse> getCharacters(){

        ResponseEntity<RickAndMortyApiResponse> response =
                webClient
                        .get()
                        .uri("/character")
                        .retrieve()
                        .toEntity(RickAndMortyApiResponse.class)
                        .block();
        assert response != null;
        return Objects.requireNonNull(response.getBody()).results();
    }

    public RickAndMortyApiCharacterResponse getCharacterById(String id){
        ResponseEntity<RickAndMortyApiCharacterResponse> response =
                webClient
                        .get()
                        .uri("/character/" + id)
                        .retrieve()
                        .toEntity(RickAndMortyApiCharacterResponse.class)
                        .block();

        assert response != null;
        return response.getBody();
    }

    public List<RickAndMortyApiCharacterResponse> getCharacterByStatus(String status){
        ResponseEntity<RickAndMortyApiCharacterResponse> response =
                webClient
                        .get()
                        .uri("/character?status=" + status)
                        .retrieve()
                        .toEntity(RickAndMortyApiCharacterResponse.class)
                        .block();

        assert response != null;
        return Collections.singletonList(response.getBody());
    }

}
