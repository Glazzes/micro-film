package jvm.glaze.actor.controller;

import jvm.glaze.actor.entities.models.ActorRequest;
import jvm.glaze.actor.service.ActorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/actor")
@AllArgsConstructor
public class ActorController {
    private final ActorService actorService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ActorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(actorService.save(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        actorService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

}
