package de.wolffclan.ffmjava231webclient.rickandmorty;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RickAndMortyController {

    private final RickAndMortyService service;

    @GetMapping("/test")
    public String test() {
        return "Test";
    }

    @GetMapping("/characters")
    List<RickAndMortyApiCharacterResponse> getCharacter() {
        return service.getCharacters();
    }

    @GetMapping("/characters/{id}")
    RickAndMortyApiCharacterResponse getCharacterById(@PathVariable String id){
        return service.getCharacterById(id);
    }

    @GetMapping("/characters/status")
    public List<RickAndMortyApiCharacterResponse> getCharacterByStatus(@RequestParam String status){
        return service.getCharacterByStatus(status);
    }
}
