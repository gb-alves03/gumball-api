package gumball.com.api.service;

import gumball.com.api.model.Character;
import gumball.com.api.model.CharacterRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CharacterServiceTest {

    @Mock
    private CharacterRepository repository;
    @Mock
    private Character character;
    @InjectMocks
    private CharacterService service;

    @Test
    void findAllTest() {
        // Given
        final Character character1 = Mockito.mock(Character.class);
        final List<Character> expected = List.of(character, character1);

        OngoingStubbing<List<Character>> thenReturn = Mockito
                .when(repository.findAll())
                .thenReturn(expected);

        // When
        var actual = service.findAll();

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void findById() {
        // Given
        var id = UUID.randomUUID();
        Optional<Character> expected = Optional.of(character);

        Mockito
                .when(repository.findById(id))
                .thenReturn(expected);

        // When
        var actual = service.findById(id);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void deleteByIdTest() {
        // Given
        var id = UUID.randomUUID();

        // When
        service.deleteById(id);

        // Then
        Mockito
                .verify(repository, Mockito.times(1))
                .deleteById(id);
    }

    @Test
    void saveTest() {
        // Given
        Mockito.when(repository.save(character))
                .thenReturn(character);

        // When
        var actual = service.save(character);

        // Then
        Mockito
                .verify(repository, Mockito.times(1))
                .save(character);
        assertEquals(character, actual);
    }
}
