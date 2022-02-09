package jvm.glaze.movie.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Document(collection = "movies")
public class Movie {
    @Id
    private String id;
    private String title;
    private String description;
    private LocalDate releaseDate;
    private Set<String> actorIds = new HashSet<>();

    public Movie(String title, String description, LocalDate releaseDate) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
    }
}
