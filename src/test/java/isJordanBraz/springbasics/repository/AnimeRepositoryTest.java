package isJordanBraz.springbasics.repository;

import isJordanBraz.springbasics.domain.Anime;
import isJordanBraz.springbasics.exception.AnimeNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@DisplayName("Testes anime repository")
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    private Anime createAnime() {
        return new Anime(null,"jordan");
    }

    @Test
    @DisplayName("Should creates anime")
    void saveAnime_Success() {
        Anime animeToBeSaved = createAnime();
        Anime animeSaved = animeRepository.save(animeToBeSaved);
        Assertions.assertThat(animeSaved).isNotNull();
        Assertions.assertThat(animeSaved.getId()).isNotNull();
        Assertions.assertThat(animeSaved.getName()).isEqualTo(animeToBeSaved.getName());
    }

    @Test
    @DisplayName("Should update anime")
    void updateAnime_Success() {
        Anime animeToBeSaved = createAnime();
        Anime animeSaved = animeRepository.save(animeToBeSaved);
        animeSaved.setName("braz");
        Anime animeUpdated = animeRepository.save(animeSaved);
        Assertions.assertThat(animeUpdated).isNotNull();
        Assertions.assertThat(animeUpdated.getId()).isNotNull();
        Assertions.assertThat(animeUpdated.getName()).isEqualTo(animeSaved.getName());
    }

    @Test
    @DisplayName("Should delete anime")
    void deleteAnime_Success() {
        Anime animeToBeSaved = createAnime();
        Anime animeSaved = animeRepository.save(animeToBeSaved);
        animeRepository.delete(animeSaved);
        Optional<Anime> animeOptional = animeRepository.findById(animeSaved.getId());
        Assertions.assertThat(animeOptional).isEmpty();
    }

    @Test
    @DisplayName("Should find anime by name")
    void findAnimeByName_Success() {
        Anime animeToBeSaved = createAnime();
        Anime animeSaved = animeRepository.save(animeToBeSaved);
        List<Anime> animesByName = animeRepository.findByName(animeSaved.getName());
        Assertions.assertThat(animesByName).isNotEmpty().contains(animeSaved);
    }

    @Test
    @DisplayName("Should not find anime by name")
    void findAnimeByName_NotFound() {
        Anime animeToBeSaved = createAnime();
        List<Anime> animesByName = animeRepository.findByName(animeToBeSaved.getName());
        Assertions.assertThat(animesByName).isEmpty();
    }

    @Test
    @DisplayName("Save anime exception")
    void saveAnime_Exception() {
        Anime anime = new Anime();
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> animeRepository.save(anime));
    }


}