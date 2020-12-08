package isJordanBraz.springbasics.service;

import isJordanBraz.springbasics.domain.Anime;
import isJordanBraz.springbasics.dto.AnimeDto;
import isJordanBraz.springbasics.exception.AnimeNotFoundException;
import isJordanBraz.springbasics.repository.AnimeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Anime> findByName(String name) {
        return repository.findByName(name);
    }

    public Anime findByIdOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AnimeNotFoundException("Anime not found for ID: " + id));
    }

    @Transactional
    public Anime save(AnimeDto animeDto) {
        return repository.save(animeDto.toAnime(animeDto));
    }

    public void delete(Long id) {
        repository.delete(findByIdOrThrow(id));
    }

    @Transactional
    public Anime update(AnimeDto animeDto, Long id) {
        return repository.findById(id)
                .map(anime -> {
                    anime.setName(animeDto.getName());
                    return repository.save(anime);
                })
                .orElseThrow(() -> new AnimeNotFoundException("Anime not found for ID: " + id));
    }

}
