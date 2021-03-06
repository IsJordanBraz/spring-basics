package isJordanBraz.springbasics.controller;

import isJordanBraz.springbasics.domain.Anime;
import isJordanBraz.springbasics.dto.AnimeDto;
import isJordanBraz.springbasics.service.AnimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Anime> findById(@PathVariable Long id) {
        return ResponseEntity.ok(animeService.findByIdOrThrow(id));
    }

    @GetMapping("/find")
    public ResponseEntity<List<Anime>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(animeService.findByName(name));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody @Valid AnimeDto animeDto) {
        return ResponseEntity.ok(animeService.save(animeDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        animeService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Anime> update(@RequestBody @Valid AnimeDto animeDto, @PathVariable Long id) {
        return ResponseEntity.ok(animeService.update(animeDto, id));
    }
}
