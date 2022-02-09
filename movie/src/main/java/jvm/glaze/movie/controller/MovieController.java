package jvm.glaze.movie.controller;

import jvm.glaze.movie.entities.dtos.CreatedMovieDTO;
import jvm.glaze.movie.entities.dtos.MovieDTO;
import jvm.glaze.movie.entities.models.MovieRequest;
import jvm.glaze.movie.service.MovieService;
import jvm.glaze.shared.dtos.DeleteResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/movie")
@AllArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/greetings")
    public Mono<String> greet() {
        return Mono.just("Welcome to the movie api");
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<MovieDTO>> findById(@PathVariable String id) {
        return movieService.findById(id)
                .map(it -> ResponseEntity.ok().body(it));
    }

    @PostMapping
    public Mono<ResponseEntity<CreatedMovieDTO>> save(@RequestBody MovieRequest movieRequest) {
        return movieService.save(movieRequest)
                .map(it -> ResponseEntity.status(HttpStatus.CREATED).body(it));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<DeleteResponse>> deleteById(@PathVariable String id) {
        return movieService.deleteById(id)
                .map(it -> ResponseEntity.ok().body(it));
    }

}
