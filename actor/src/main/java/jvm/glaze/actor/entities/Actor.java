package jvm.glaze.actor.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "actor")
@Data
@NoArgsConstructor
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "birth_day", nullable = false)
    private LocalDate birthDay;

    public Actor(String fullName, String country, LocalDate birthDay) {
        this.fullName = fullName;
        this.country = country;
        this.birthDay = birthDay;
    }

}
