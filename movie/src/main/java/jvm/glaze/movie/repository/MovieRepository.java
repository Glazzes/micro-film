package jvm.glaze.movie.repository;

import jvm.glaze.movie.entities.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {
    Flux<Movie> findByActorIdsContaining(String actorId);
}
