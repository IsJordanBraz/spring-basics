package isJordanBraz.springbasics.dto;

import isJordanBraz.springbasics.domain.Anime;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AnimeDto {

    @NotEmpty
    @NotNull(message = "name cannot be null")
    private String name;

    public Anime toAnime(AnimeDto dto) {
        return new Anime(null,dto.getName());
    }
    public Anime toAnime(AnimeDto dto, Long id) { return new Anime(id,dto.getName()); }
}
