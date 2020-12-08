package isJordanBraz.springbasics.dto;

import isJordanBraz.springbasics.domain.Anime;
import lombok.Data;

@Data
public class AnimeDto {
    private String name;

    public Anime toAnime(AnimeDto dto) {
        return new Anime(null,dto.getName());
    }
    public Anime toAnime(AnimeDto dto, Long id) { return new Anime(id,dto.getName()); }
}
