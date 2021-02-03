package isJordanBraz.springbasics.service;

import isJordanBraz.springbasics.domain.Anime;
import isJordanBraz.springbasics.exception.AnimeNotFoundException;
import isJordanBraz.springbasics.repository.AnimeRepository;
import isJordanBraz.springbasics.util.AnimeCreator;
import isJordanBraz.springbasics.util.AnimeDtoCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class AnimeServiceTest {
    @InjectMocks
    private AnimeService animeService;
    @Mock
    private AnimeRepository animeRepositoryMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(animeRepositoryMock.findAll()).thenReturn(List.of(AnimeCreator.createValidAnime()));
        BDDMockito.when(animeRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(AnimeCreator.createValidAnime()));
        BDDMockito.when(animeRepositoryMock.findByName(ArgumentMatchers.anyString())).thenReturn(List.of(AnimeCreator.createValidAnime()));
        BDDMockito.when(animeRepositoryMock.save(ArgumentMatchers.any(Anime.class))).thenReturn(AnimeCreator.createValidAnime());
        BDDMockito.doNothing().when(animeRepositoryMock).delete(ArgumentMatchers.any(Anime.class));
    }

    @Test
    @DisplayName("ListAll Animes")
    void listAnimes_Success() {
        String expectedName = AnimeCreator.createValidAnime().getName();
        List<Anime> body = animeService.listAll();

        Assertions.assertThat(body).isNotEmpty().isNotNull().hasSize(1);
        Assertions.assertThat(body.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("find anime by Id")
    void findById_Success() {
        Long expectedId = AnimeCreator.createValidAnime().getId();
        Anime body = animeService.findByIdOrThrow(1L);

        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("find anime by Id not found")
    void findById_NotFound() {
        BDDMockito.when(animeRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());
        Assertions.assertThatExceptionOfType(AnimeNotFoundException.class)
                .isThrownBy(() -> animeService.findByIdOrThrow(1L));
    }

    @Test
    @DisplayName("find anime by Name")
    void findByName_Success() {
        String expectedName = AnimeCreator.createValidAnime().getName();
        List<Anime> body = animeService.findByName("jordan");

        Assertions.assertThat(body).isNotEmpty().isNotNull().hasSize(1);
        Assertions.assertThat(body.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByName returns empty list when anime is not found")
    void findByName_NotFound() {
        BDDMockito.when(animeRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());
        List<Anime> body = animeService.findByName("jordan");
        Assertions.assertThat(body).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("save anime success")
    void saveAnime_Success() {
        Anime body = animeService.save(AnimeDtoCreator.createAnimeDto());
        Assertions.assertThat(body).isNotNull().isEqualTo(AnimeCreator.createValidAnime());
    }

    @Test
    @DisplayName("update anime success")
    void updateAnime_Success() {
        Anime body = animeService.update(AnimeDtoCreator.createAnimeDto(), AnimeCreator.createValidUpdatedAnime().getId());

        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getId()).isEqualTo(AnimeCreator.createValidUpdatedAnime().getId());
    }
    @Test
    @DisplayName("delete anime success")
    void deleteAnime_Success() {
        Assertions.assertThatCode(() -> animeService.delete(1L)).doesNotThrowAnyException();
    }
}