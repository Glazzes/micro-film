package jvm.glaze.actor.repository;

import jvm.glaze.actor.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, String> {}
