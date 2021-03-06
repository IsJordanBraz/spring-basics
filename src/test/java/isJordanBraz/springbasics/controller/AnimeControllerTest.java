package isJordanBraz.springbasics.controller;

import isJordanBraz.springbasics.domain.Anime;
import isJordanBraz.springbasics.dto.AnimeDto;
import isJordanBraz.springbasics.service.AnimeService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
class AnimeControllerTest {

    @InjectMocks
    private AnimeController animeController;
    @Mock
    private AnimeService animeServiceMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(animeServiceMock.listAll()).thenReturn(List.of(AnimeCreator.createValidAnime()));
        BDDMockito.when(animeServiceMock.findByIdOrThrow(ArgumentMatchers.anyLong())).thenReturn(AnimeCreator.createValidAnime());
        BDDMockito.when(animeServiceMock.findByName(ArgumentMatchers.anyString())).thenReturn(List.of(AnimeCreator.createValidAnime()));
        BDDMockito.when(animeServiceMock.save(ArgumentMatchers.any(AnimeDto.class))).thenReturn(AnimeCreator.createValidAnime());
        BDDMockito.when(animeServiceMock.update(ArgumentMatchers.any(AnimeDto.class), ArgumentMatchers.anyLong())).thenReturn(AnimeCreator.createValidUpdatedAnime());
        BDDMockito.doNothing().when(animeServiceMock).delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("ListAll Animes")
    void listAnimes_Success() {
        String expectedName = AnimeCreator.createValidAnime().getName();
        List<Anime> body = animeController.findAll().getBody();

        Assertions.assertThat(body).isNotEmpty().isNotNull().hasSize(1);
        Assertions.assertThat(body.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("find anime by Id")
    void findById_Success() {
        Long expectedId = AnimeCreator.createValidAnime().getId();
        Anime body = animeController.findById(1L).getBody();

        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("find anime by Name")
    void findByName_Success() {
        String expectedName = AnimeCreator.createValidAnime().getName();
        List<Anime> body = animeController.findByName("jordan").getBody();

        Assertions.assertThat(body).isNotEmpty().isNotNull().hasSize(1);
        Assertions.assertThat(body.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByName returns empty list when anime is not found")
    void findByName_NotFound() {
        BDDMockito.when(animeServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());
        List<Anime> body = animeController.findByName("jordan").getBody();
        Assertions.assertThat(body).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("save anime success")
    void saveAnime_Success() {
        Anime body = animeController.save(AnimeDtoCreator.createAnimeDto()).getBody();
        Assertions.assertThat(body).isNotNull().isEqualTo(AnimeCreator.createValidAnime());
    }

    @Test
    @DisplayName("update anime success")
    void updateAnime_Success() {
        Anime body = animeController.update(AnimeDtoCreator.createAnimeDto(), AnimeCreator.createValidUpdatedAnime().getId()).getBody();

        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getId()).isEqualTo(AnimeCreator.createValidUpdatedAnime().getId());
    }
    @Test
    @DisplayName("delete anime success")
    void deleteAnime_Success() {
        Assertions.assertThatCode(() -> animeController.delete(1L)).doesNotThrowAnyException();
    }
}