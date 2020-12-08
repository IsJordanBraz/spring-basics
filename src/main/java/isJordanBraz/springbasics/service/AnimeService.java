package isJordanBraz.springbasics.service;

import isJordanBraz.springbasics.domain.Anime;
import isJordanBraz.springbasics.dto.AnimeDto;
import isJordanBraz.springbasics.repository.AnimeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeService {

    private final AnimeRepository repository;

    public AnimeService(AnimeRepository repository) {
        this.repository = repository;
    }

    public List<Anime> listAll() {
        return repository.findAll();
    }

    public Anime findById(Long id) {
        return repository.findById(id)
                .orElseThrow();
    }

    public Anime save(AnimeDto animeDto) {
        return repository.save(animeDto.toAnime(animeDto));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Anime update(AnimeDto animeDto, Long id) {
        return repository.findById(id)
                .map(anime -> {
                    anime.setId(id);
                    anime.setName(animeDto.getName());
                    return repository.save(anime);
                })
                .orElseThrow();
    }
}
