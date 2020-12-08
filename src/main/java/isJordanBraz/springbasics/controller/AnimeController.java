package isJordanBraz.springbasics.controller;

import isJordanBraz.springbasics.domain.Anime;
import isJordanBraz.springbasics.dto.AnimeDto;
import isJordanBraz.springbasics.service.AnimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("api/v1/animes")
@RequiredArgsConstructor
public class AnimeController {

    private final AnimeService animeService;

    @GetMapping
    public ResponseEntity<List<Anime>> findAll() {
        return ResponseEntity.ok(animeService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anime> findById(@PathVariable Long id) {
        return ResponseEntity.ok(animeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody AnimeDto animeDto) {
        return ResponseEntity.ok(animeService.save(animeDto));
    }

    @DeleteMapping("/{id}")
    public void update(@PathVariable Long id) {
        animeService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Anime> save(@RequestBody AnimeDto animeDto, @PathVariable Long id) {
        return ResponseEntity.ok(animeService.update(animeDto, id));
    }
}
