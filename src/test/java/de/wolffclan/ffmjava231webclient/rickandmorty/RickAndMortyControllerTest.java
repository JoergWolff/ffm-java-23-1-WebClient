package de.wolffclan.ffmjava231webclient.rickandmorty;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RickAndMortyControllerTest {

    @Autowired
    MockMvc mockMvc;

    private static MockWebServer mockWebServer;

    @BeforeAll
    static void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @DynamicPropertySource
    static void setUriDynamic(DynamicPropertyRegistry registry) {
        registry.add("rickandmorty_api_url", () -> mockWebServer.url("/").toString());
    }

    @Test
    void getCharacter() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setHeader("Content-Type", "application/json")
                .setBody(
                        """
                                {
                                "results":
                                [
                                    {
                                        "id": 19,
                                        "name": "Antenna Rick",
                                        "species": "Human",
                                        "status": "unknown"
                                    }
                                ]
                                }
                                """
                )
        );

        mockMvc.perform(MockMvcRequestBuilders.get("/api/characters"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                [
                                  {
                                     "id": 19,
                                     "name": "Antenna Rick",
                                     "species": "Human",
                                     "status": "unknown"
                                   }
                                ]
                                 """
                ));
    }

    @Test
    void getCharacterById() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setHeader("Content-Type", "application/json")
                .setBody(
                        """
                                  {
                                     "id": 19,
                                     "name": "Antenna Rick",
                                     "species": "Human",
                                     "status": "unknown"
                                   }
                                """
                )
        );

        mockMvc.perform(MockMvcRequestBuilders.get("/api/characters/19"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                  {
                                     "id": 19,
                                     "name": "Antenna Rick",
                                     "species": "Human",
                                     "status": "unknown"
                                   }
                                 """
                ));
    }
}