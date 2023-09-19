package de.wolffclan.ffmjava231webclient.rickandmorty;

import java.util.List;

public record RickAndMortyApiResponse(
        RickAndMortyApiInfoResponse info,
        List<RickAndMortyApiCharacterResponse> results
) {
}
