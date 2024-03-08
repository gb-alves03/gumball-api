package gumball.com.api.controller.mapper;

import gumball.com.api.model.Character;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class CharacterMapperTest {

    CharacterMapper mapper = new CharacterMapper();

    @Test
    void toEntityNullTest() {
        Assertions.assertThrows(RuntimeException.class,
                () -> mapper.toEntity(null));
    }


    @Test
    void toDtoNullTest() {
        Assertions.assertThrows(RuntimeException.class,
                () -> mapper.toDto((Character) null));
    }

    @Test
    void toDtoListNullTest() {
        Assertions.assertThrows(RuntimeException.class,
                () -> mapper.toDto((List<Character>) null));
    }
}
