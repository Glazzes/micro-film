package jvm.glaze.movie.entities.dtos;

import java.time.LocalDate;

public record MovieDTO(String id, String title, String description, LocalDate releaseDate) {}
