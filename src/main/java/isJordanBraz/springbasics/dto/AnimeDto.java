package isJordanBraz.springbasics.dto;

import isJordanBraz.springbasics.domain.Anime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimeDto {

    @NotEmpty
    @NotNull(message = "name cannot be null")
    private String name;

    public Anime toAnime(AnimeDto dto) {
        return new Anime(null,dto.getName());
    }
    public Anime toAnime(AnimeDto dto, Long id) { return new Anime(id,dto.getName()); }
}
