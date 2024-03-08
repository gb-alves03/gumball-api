package gumball.com.api.service;

import gumball.com.api.model.Character;
import gumball.com.api.model.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository repository;

    public List<Character> findAll() {
        return repository.findAll();
    }

    public Optional<Character> findById(final UUID id) {
        return repository.findById(id);
    }

    public Character save(final Character newCharacter) {
        return repository.save(newCharacter);
    }

    public void deleteById(final UUID id) {
        repository.deleteById(id);
    }

    public Optional<Character> update(final UUID id, final Character updateCharacter) {
        var characterOptional = repository.findById(id);

        if (characterOptional.isPresent()) {
            var characterDB = characterOptional.get();

            characterDB.setNome(updateCharacter.getNome());
            characterDB.setEspecie(updateCharacter.getEspecie());
            characterDB.setIdade(updateCharacter.getIdade());

            repository.save(characterDB);

            return Optional.of(characterDB);
        }

        return Optional.empty();
    }
}
