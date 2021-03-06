package jvm.glaze.actor.service;

import jvm.glaze.actor.entities.Actor;
import jvm.glaze.actor.entities.dtos.ActorDTO;
import jvm.glaze.actor.entities.models.ActorRequest;
import jvm.glaze.actor.repository.ActorRepository;
import jvm.glaze.clients.movie.MovieClient;
import jvm.glaze.clients.movie.MovieResponse;
import jvm.glaze.shared.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class ActorService {
    private final MovieClient movieClient;
    private final ActorRepository actorRepository;

    public ActorDTO save(ActorRequest request) {
        Actor newActor = new Actor(request.fullName(), request.country(), LocalDate.of(1996, 10, 11));
        Actor savedActor = actorRepository.save(newActor);
        return new ActorDTO(savedActor.getId(), savedActor.getFullName(), savedActor.getCountry());
    }

    public MovieResponse addActorToMovie(String actorId, String movieId) {
        MovieResponse response = movieClient.addActorToMovie(actorId, movieId);

        return response;
    }

    public void deleteById(String id) {
        boolean exists = actorRepository.existsById(id);
        if(!exists) {
            throw new ResourceNotFoundException("Can not delete an actor that does not exists");
        }

        actorRepository.deleteById(id);
    }

}
