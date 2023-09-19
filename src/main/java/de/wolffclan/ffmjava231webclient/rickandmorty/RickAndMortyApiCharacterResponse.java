package de.wolffclan.ffmjava231webclient.rickandmorty;

public record RickAndMortyApiCharacterResponse(
        int id,
        String name,
        String species,
        String status
) {
}
