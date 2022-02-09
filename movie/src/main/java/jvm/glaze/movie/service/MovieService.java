package jvm.glaze.movie.service;

import jvm.glaze.movie.entities.Movie;
import jvm.glaze.movie.entities.dtos.CreatedMovieDTO;
import jvm.glaze.movie.entities.dtos.MovieDTO;
import jvm.glaze.movie.entities.models.MovieRequest;
import jvm.glaze.movie.repository.MovieRepository;
import jvm.glaze.shared.exception.ResourceNotFoundException;
import jvm.glaze.shared.dtos.DeleteResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SynchronousSink;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class MovieService {
    private final MovieRepository repository;

    public Mono<CreatedMovieDTO> save(MovieRequest request) {
        return repository.save(new Movie(request.title(), request.description(), LocalDate.now()))
                .map(it -> new CreatedMovieDTO(it.getId(), it.getTitle()))
                .log("Movie saved");
    }

    public Mono<MovieDTO> findById(String id) {
        return repository.findById(id)
                .map(it -> new MovieDTO(it.getId(), it.getTitle(), it.getDescription(), it.getReleaseDate()))
                .log("Movie found")
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Could not find movie with id " + id)))
                .log("Movie not found");
    }

    public Mono<DeleteResponse> deleteById(String movieId) {
        return repository.existsById(movieId)
                .handle((Boolean bool, SynchronousSink<Boolean> sink) -> {
                    if(!bool){
                        sink.error(new ResourceNotFoundException("Can not delete a movie that does not exists."));
                    }else{
                        sink.next(bool);
                    }
                })
                .flatMap(it -> repository.deleteById(movieId))
                .log("Deleted movie!")
                .map(it -> new DeleteResponse()) // required mapper in order to get the right type
                .switchIfEmpty(Mono.just(new DeleteResponse("Movie deleted successfully!", LocalDateTime.now())));
    }
}
