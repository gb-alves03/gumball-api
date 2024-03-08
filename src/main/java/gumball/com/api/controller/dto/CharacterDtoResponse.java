package gumball.com.api.controller.dto;

import gumball.com.api.model.Character;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Builder
public record CharacterDtoResponse(
        UUID id,
        String nome,
        String especie,
        int idade,
        Instant createdOn
) {
    public CharacterDtoResponse(Character character) {
        this(character.getId(), character.getNome(), character.getEspecie(), character.getIdade(), character.getCreatedOn());
    }
}
