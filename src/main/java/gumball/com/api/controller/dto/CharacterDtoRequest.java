package gumball.com.api.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder

public record CharacterDtoRequest(
        @NotBlank
        String nome,

        @NotBlank
        String especie,

        int idade,
        Instant createdOn
) {
}
