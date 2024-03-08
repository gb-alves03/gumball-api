package gumball.com.api.controller.mapper;

import gumball.com.api.controller.dto.CharacterDtoRequest;
import gumball.com.api.controller.dto.CharacterDtoResponse;
import gumball.com.api.model.Character;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CharacterMapper {

    public Character toEntity(final CharacterDtoRequest dto) {
        return Character.builder()
                .nome(dto.nome())
                .especie(dto.especie())
                .idade(dto.idade())
                .createdOn(dto.createdOn())
                .build();
    }

    public List<CharacterDtoResponse> toDto(final List<Character> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public CharacterDtoResponse toDto(final Character entity) {
        return CharacterDtoResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .especie(entity.getEspecie())
                .idade(entity.getIdade())
                .createdOn(entity.getCreatedOn())
                .build();
    }
}
