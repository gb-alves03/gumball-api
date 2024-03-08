package gumball.com.api.controller;

import gumball.com.api.controller.dto.CharacterDtoRequest;
import gumball.com.api.controller.dto.CharacterDtoResponse;
import gumball.com.api.controller.mapper.CharacterMapper;
import gumball.com.api.model.Character;
import gumball.com.api.model.CharacterRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CharacterControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CharacterRepository repository;

    @Autowired
    private CharacterMapper mapper;

    private String URL;

    @BeforeEach
    void setUp() {
        URL = "http://localhost:" + port + "/character";

        var charater = Character.builder()
                .id(UUID.randomUUID())
                .nome("Gumball")
                .especie("Gato")
                .idade(12)
                .createdOn(Instant.now())
                .build();

        var character1 = Character.builder()
                .id(UUID.randomUUID())
                .nome("Darwin")
                .especie("Peixe")
                .idade(10)
                .createdOn(Instant.now())
                .build();

        repository.save(charater);
        repository.save(character1);
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void findAllTest() {
        // Given

        // When
       var response = restTemplate.getForEntity(URL, CharacterDtoResponse[].class);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(repository.count(), response.getBody().length);
    }

    @Test
    void findByIdTest() {
        // Given
        var entity = repository.findAll().get(0);
        var id = entity.getId();
        var dto = mapper.toDto(entity);

        // When
        var response = restTemplate.getForEntity(URL + "{id}", CharacterDtoResponse.class, id);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void deleteTest() {
        // Given
        var id = repository.findAll().get(0).getId();

        // When
        var response = restTemplate.exchange(URL + "{id}",
                HttpMethod.DELETE, null, Void.class, id);

        // Then
        var entity = repository.findById(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertFalse(entity.isPresent());
    }

    @Test
    void saveTest() {
        // Given
        var newCharacter =
                CharacterDtoRequest.builder()
                        .nome("Nicole")
                        .especie("Gato")
                        .idade(38)
                        .createdOn(Instant.now())
                        .build();
        // When
        var response = restTemplate.postForEntity(URL, newCharacter, CharacterDtoResponse.class);

        // Then
        var dto = response.getBody();
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newCharacter.nome(), dto.nome());
        assertEquals(newCharacter.especie(), dto.especie());
        assertEquals(newCharacter.idade(), dto.idade());
        assertEquals(newCharacter.createdOn(), dto.createdOn());
        assertNotNull(dto.id());
    }


}
