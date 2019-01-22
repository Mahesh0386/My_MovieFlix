package main.java.io.bhannur.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "EntertainmentItem.findAll", query = "SELECT e FROM EntertainmentItem e"),

        })
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EntertainmentItem {

    @Column(unique =true)
    private String title;
    private int year;
    private String rated;
    private String runtime;
    private String genre;
    private String director;
    private String writer;
    private String actors;
    private String plot;
    private String language;
    private String country;
    private String awards;
    private String poster;
    private int metaScore;
    private double imdbRating;
    private int imdbVotes;
    @Id
    private String imdbId;
    private String type;
    @DateTimeFormat
    private String released;


}
