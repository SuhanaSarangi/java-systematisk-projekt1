package org.example.javasystematiskprojekt1.model;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import jakarta.persistence.*;

/**
 * The Movie class represents the model class of the MVC(Model-View-Controller) Design Pattern.
 * Its purpose is to contain the data handled by the project. In this project, it's the 'Movie' entity
 * with attributes such as movie title, director, release year, genre and plot. This class has JPA
 * annotations that map it to a database table. The JSON-B annotation sets the order of properties
 * during display and/or usage in JSON format.
 *
 * @author Suhana
 * @version 1.0
 */


@Entity // Annotation makes the class a JPA entity.
@Table(name = "movieData") // Annotation maps the entity to a database table.
@JsonbPropertyOrder({"id", "movieTitle", "movieDirector", "releaseYear", "movieGenre", "moviePlot"}) // Ordered
public class Movie {

    @Id // Primary key (Unique Identifier) is assigned to this field of the entity class.
    @GeneratedValue(strategy = GenerationType.AUTO) // id value will be generated automatically.
    @Column(name = "ID", nullable = false) // id needs to have a value.
    private Long id; // The Long wrapper class is the recommended data type in JPA contexts. Advantage: Nullability.

    /*
    Fields in the class correspond to columns in the database table. In-memory
    database means it dissolves as soon as the application ends.
    Below are initialised class fields with the corresponding required data types.
    The access modifiers are set to private in order to prevent direct access outside the class.
    Through getters and setters they can however be accessed.
    */


    private String movieTitle;
    private String movieDirector;
    private int releaseYear;
    private String movieGenre;
    private String moviePlot;

    /**
     * A no-argument constructor for the Movie class. It creates a Movie instance without
     * requiring any parameters. This type constructors are used in JPA frameworks.
     */

    public Movie() {
    }

    /**
     * This method getId() gets the unique identifier of a Movie entity.
     *
     * @return The movie id(Long).
     */

    public Long getId() {
        return id;
    }

    /**
     * This method setId() sets the unique identifier of a Movie entity.
     *
     * @param id The movie ID.
     */

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method getMovieTitle() gets the title of a Movie entity.
     *
     * @return The movie title(String).
     */

    public String getMovieTitle() {
        return movieTitle;
    }

    /**
     * This method setMovieTitle()sSets the title of a Movie entity.
     *
     * @param movieTitle The movie title.
     */

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    /**
     * This method getMovieGenre() gets the genre of a Movie entity.
     *
     * @return The movie genre(String).
     */

    public String getMovieGenre() {
        return movieGenre;
    }

    /**
     * This method setMovieGenre() sets the genre of a Movie entity.
     *
     * @param movieGenre The movie genre.
     */

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    /**
     * This method getReleaseYear() gets the release year of a Movie entity.
     *
     * @return The movie release year(int).
     */

    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * This method setReleaseYear() sets the release year of a Movie entity.
     *
     * @param releaseYear The movie release year.
     */

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * This method getMoviePlot() gets the plot of a Movie entity.
     *
     * @return The movie plot(String).
     */

    public String getMoviePlot() {
        return moviePlot;
    }

    /**
     * This method setMoviePlot() sets the plot of a Movie entity.
     *
     * @param moviePlot The movie plot.
     */

    public void setMoviePlot(String moviePlot) {
        this.moviePlot = moviePlot;
    }

    /**
     * This method getMovieDirector() gets the director of a Movie entity.
     *
     * @return The movie director(String).
     */

    public String getMovieDirector() {
        return movieDirector;
    }

    /**
     * This method setMovieDirector() sets the director of a Movie entity.
     *
     * @param movieDirector The movie director.
     */

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }
}
