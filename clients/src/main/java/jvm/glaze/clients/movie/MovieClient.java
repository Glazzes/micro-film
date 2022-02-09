package jvm.glaze.clients.movie;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "movie")
public interface MovieClient {

    @PostMapping("/api/v1/movie/{movieId}/actor/{actorId}")
    MovieResponse addActorToMovie(@PathVariable String actorId, @PathVariable String movieId);

}
