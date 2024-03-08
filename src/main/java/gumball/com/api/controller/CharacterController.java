package gumball.com.api.controller;


import gumball.com.api.controller.dto.CharacterDtoRequest;
import gumball.com.api.controller.mapper.CharacterMapper;
import gumball.com.api.controller.dto.CharacterDtoResponse;
import gumball.com.api.model.CharacterRepository;
import gumball.com.api.service.CharacterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/character")
public class CharacterController {

    @Autowired
    private CharacterService service;
    private CharacterMapper mapper;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid CharacterDtoRequest newCharacter) {
        log.info("Register executed");
        var character = mapper.toEntity(newCharacter);
        var response = mapper.toDto(service.save(character));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<CharacterDtoResponse>> findAll() {
        var characters = service.findAll();
        var response = mapper.toDto(characters);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDtoResponse> findById(@PathVariable("id") UUID id) {
        log.info("FindById executed");
        var characterOptional = service.findById(id);

        return characterOptional
                .map(character -> {
                    var dto = mapper.toDto(character);
                    return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterDtoResponse> update(@PathVariable("id") UUID id,
                                                       @RequestBody CharacterDtoRequest characterDto) {
        log.info("Update executed");
        var characterOptional = service.update(id, mapper.toEntity(characterDto));

        return characterOptional
                .map(character -> {
                    var dto = mapper.toDto(character);
                    return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable("id") UUID id) {
        log.info("DeleteById executed");
        service.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
