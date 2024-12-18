package org.example.javasystematiskprojekt1.database;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.example.javasystematiskprojekt1.model.Movie;

import java.util.List;

/**
 * The MovieRepository class represents the controller class of the MVC(Model-View-Controller) Design Pattern.
 * This is where the database operations are done that are not visible. This class provides CRUD operations
 * for the Movie entity. The EntityManager acts as a bridge between database and the applications. It is used
 * to save an entity in the database. We get EntityManager from an EntityManagerFactory.
 *
 * @author Suhana
 * @version 1.0
 */

@ApplicationScoped // Annotation of CDI bean, makes the class a singleton. Class can be injected into other classes.
@Transactional // Annotation ensures that the methods within the class are executed in a transactional context.
// The transaction will be committed if a method executes successfully and rolled back if an exception is thrown.
public class MovieRepository {
    @PersistenceContext // Annotation injects an EntityManager instance, which is used to interact with the database
    private EntityManager entityManager;

    /**
     * This method findAllMovies() Retrieves a list of all movies from the database.
     * It's publicly accessible.
     *
     * @return A list of Movie objects.
     */

    public List<Movie> findAllMovies() {
        // A native SQL query to select all records from the table 'movieData'.
        String sql = "select * from movieData";
        // Create a native query using 'EntityManager'.
        Query query = entityManager.createNativeQuery(sql, Movie.class);
        // Create a 'movies' List of all the movie data as a result of the query.
        List<Movie> movies = query.getResultList();
        return movies; // Return 'movies' List
    }

    /**
     * This method findMovieById() finds a movie in the Derby database by id.
     * It's publicly accessible.
     *
     * @param id The id of the movie to find.
     * @return A Movie object if found, or null if not found.
     */

    public Movie findMovieById(Long id) {
        // Use of the EntityManager to find a Movie object by its id.
        Movie movieFound = entityManager.find(Movie.class, id);
        return movieFound; // Return the Movie object desired or null if not found.
    }

    /**
     * This method createMovie() creates a new Movie entity in the database.
     * It's publicly accessible.
     *
     * @param movie A Movie object to be created.
     */

    public void createMovie(Movie movie) {
        // Use of the EntityManager to persist(save) a Movie entity to the database.
        entityManager.persist(movie);
    }

    /**
     * This method updateMovieById() updates the existing data of a movie in the database.
     * It's publicly accessible.
     *
     * @param movie A Movie object with updated details.
     */

    public void updateMovieById(Movie movie) {
        // Use of the EntityManager to merge(update) a Movie object in the database.
        entityManager.merge(movie);
    }

    /**
     * This method deleteMovieById() deletes a movie by its ID from the database.
     * It's publicly accessible.
     *
     * @param id The ID of the movie to be deleted.
     */

    public void deleteMovieById(Long id) {
        // Use of the EntityManager to find a Movie object by its ID.
        Movie movieToDelete = entityManager.find(Movie.class, id);
        // Use of the EntityManager to remove the found Movie object from the database.
        entityManager.remove(movieToDelete);
    }
}
