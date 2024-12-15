package org.example.javasystematiskprojekt1;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.javasystematiskprojekt1.database.MovieRepository;
import org.example.javasystematiskprojekt1.model.Movie;

import java.util.List;

/**
 * The MovieResource class represents the view class of the MVC(Model-View-Controller) Design Pattern.
 * This is where the user gets to interact with the server. In our Jakarta REST project,
 * this is the resource class to manage movies, which can be accessed by a specific URL path.
 * This class is available on the base URL, accessible through the path "/movies". The resource class contains
 * methods that handle HTTP requests such as GET, POST, PUT, DELETE, etc., and receive HTTP responses.
 *
 * @author Suhana
 * @version 1.0
 */


@Path("/movies") // Annotation defines mapping of the class to the URL path "/movies".
public class MovieResource {

    @Inject // Annotation is a part of Java CDI framework.
    private MovieRepository movieRepository;
    // The container is responsible for instantiating and providing the required instance of the dependency, MovieRepository.

    /**
     * This method getMovie() retrieves a list of all movies from the 'MovieRepository'.
     * It's publicly accessible.
     *
     * @return A Response object containing the list of movies in JSON format and a 200 status
     * or a 404 status with an appropriate message if no movies are found.
     */

    @GET // Annotation that the method handles HTTP GET requests.
    @Produces(MediaType.APPLICATION_JSON) // Annotation that response data is in JSON format.
    public Response getMovie() {
        /*
        Declaration of the 'movies' list to hold Movie entities. We assign the value by calling the method
        'findAllMovies()' from the 'movieRepository'.
         */
        List<Movie> movies = movieRepository.findAllMovies();
        // Check for null
        if (movies == null) {
            return Response.status(Response.Status.NOT_FOUND) // Return 404 response
                    .entity("Empty repository!") // Attach a message
                    .build(); // Build the response object
        }
        return Response.ok(movies).build(); // Return 200 response
    }

    /**
     * This method getMovieById() retrieves a Movie entity from the 'MovieRepository' by its id.
     * It's publicly accessible.
     * This method is mapped to the HTTP GET request with a dynamic path parameter movie id.
     *
     * @param id is the id of the movie to be retrieved.
     * @return A Response object containing either the required movie in JSON format and a 200 status
     * or a 404 status with an appropriate message if the movie is not found.
     */


    @GET // Annotation that the method handles HTTP GET requests.
    @Path("/{id}") // Annotation defines mapping of the method to the URL path "/movies/id".
    // {id} means it's a dynamic path parameter.
    @Consumes(MediaType.APPLICATION_JSON) // Annotation that the method consumes data in JSON format.
    @Produces(MediaType.APPLICATION_JSON) // Annotation that response data is in JSON format.
    public Response getMovieById(@PathParam("id") Long id) {
         /* Parameter for this method is the id of the Movie entity to be retrieved from the 'MovieRepository'.
          Annotation @PathParam reads dynamic parts of a URL and matches them
          to the id of the Movie entity to be retrieved.
          */
         /*
          Declaration of the variable 'movie' to hold a Movie entity. We assign the value by calling the method
          'findMovieById()' from the 'movieRepository'.
          */
        Movie movie = movieRepository.findMovieById(id);
        // Check for null
        if (movie == null) {
            return Response.status(Response.Status.NOT_FOUND) // Return 404 response
                    .entity("Movie not found!") // Attach a message
                    .build(); // Build the Response object
        }
        return Response.ok(movie).build(); // Return 202 message
    }

    /**
     * This method addMovie() adds a new Movie entity to the 'MovieRepository'.
     * It's publicly accessible.
     * This method is mapped to the HTTP POST request.
     *
     * @param movie is the Movie entity to be added to the 'MovieRepository'.
     * @return A Response object containing a 201 Created status with a success message.
     */

    @POST // Annotation that the method handles HTTP POST requests.
    @Consumes(MediaType.APPLICATION_JSON) // Annotation that the method consumes data in JSON format.
    @Produces(MediaType.TEXT_PLAIN) // Annotation that response data is in text format.
    public Response addMovie(Movie movie) {
        // Parameter for this method is a Movie object to be added to the movie repository.
        // The method 'createMovie()' is called from the 'movieRepository' to add the Movie object to the movie repository.
        movieRepository.createMovie(movie);
        return Response.status(Response.Status.CREATED) // Return 201 response
                .entity("Movie created successfully!") // Attach a message
                .build(); // Build the Response object
    }

    /**
     * This method updateMovieById() updates the existing Movie entity data by its id.
     * It's publicly accessible.
     * This method is mapped to the HTTP PUT request.
     *
     * @param id is the id of the movie to be updated.
     * @param updatedMovie is the updated Movie object containing the updated data.
     * @return A Response object with either a 200 OK status and a success message if the movie is updated,
     * or a 404 Not Found status with an appropriate message if the movie is not found.
     */

    @PUT // Annotation that the method handles HTTP PUT requests.
    @Path("/{id}") // Annotation defines mapping of the method to the URL path "/movies/id".
    // {id} means it's a dynamic path parameter.
    @Consumes(MediaType.APPLICATION_JSON) // Annotation that the method consumes data in JSON format.
    @Produces(MediaType.TEXT_PLAIN) // Annotation that response data is in text format.
    public Response updateMovieById(@PathParam("id") Long id, Movie updatedMovie) {
        /* First parameter for this method is the id of the Movie entity to be updated in the 'MovieRepository'.
         Annotation @PathParam reads dynamic parts of a URL and matches them to the id of the movie to be updated.
         Second parameter for this method is the updated Movie entity in the 'MovieRepository'.
         */
        /*
         Declaration of 'movies' List to hold Movie entities. We assign the value by calling the method
         'findAllMovies()' from the 'movieRepository'.
         */
        List<Movie> movies = movieRepository.findAllMovies();

        // Iterate through each Movie entity in the 'movies' List.
        for (Movie movie : movies) {
            // Check if the id of the existing movie matches the id of the movie to be updated.
            if (movie.getId().equals(id)) {
                // Update the data of the Movie entity with the field values of the updatedMovie entity.
                movie.setMovieTitle(updatedMovie.getMovieTitle());
                movie.setMovieDirector(updatedMovie.getMovieDirector());
                movie.setReleaseYear(updatedMovie.getReleaseYear());
                movie.setMovieGenre(updatedMovie.getMovieGenre());
                movie.setMoviePlot(updatedMovie.getMoviePlot());
                /*
                Call the method 'updateMovieById() from the 'movieRepository' to save the updated Movie
                entity in the 'MovieRepository'.
                 */
                movieRepository.updateMovieById(movie);
                return Response.ok("Movie information updated successfully!") // Return 200 response
                        .build(); // Build the Response object
            }
        }
        return Response.status(Response.Status.NOT_FOUND) // Return 404 response
                .entity("Movie not found!") // Attach a message
                .build(); // Build the Response object
    }

    /**
     * This method deleteMovieById() handles deleting a 'Movie' entity from the 'MovieRepository' by id,
     * checks if the movie exists, and then performs the deletion task.
     * it's publicly accessible.
     *
     * @param id is the id of the movie to be deleted.
     * @return A Response object with either a 200 OK status and a success message if the movie is deleted,
     * or a 404 Not Found status with an appropriate message if the movie is not found.
     */

    @DELETE // Annotation that the method handles HTTP DELETE requests.
    @Path("/{id}") // Annotation defines mapping of the method to the URL path "/movies/id".
    // {id} means it's a dynamic path parameter.
    @Consumes(MediaType.APPLICATION_JSON) // Annotation that the method consumes data in JSON format.
    @Produces(MediaType.TEXT_PLAIN) // Annotation that response data is in text format.
    public Response deleteMovieById(@PathParam("id") Long id) {
        /* Parameter for this method is the id of the Movie entity to be deleted from the 'MovieRepository'.
         Annotation @PathParam reads dynamic parts of a URL and matches them to the id of the movie to be deleted.
         */
        /*
        Declaration of the variable 'movieToDelete' to hold a Movie entity. We assign the value by calling the method
        'findMovieById()' from the 'movieRepository'.
         */
        Movie movieToDelete = movieRepository.findMovieById(id);
        // Check for null
        if (movieToDelete == null) {
            return Response.status(Response.Status.NOT_FOUND) // Return 404 response
                    .entity("Invalid ID!") // Attach a message
                    .build(); // Build the Response object
        }
         /*
          Call the method 'deleteMovieById() from the 'movieRepository' to delete the selected Movie
          entity from the 'MovieRepository'.
          */
        movieRepository.deleteMovieById(id);
        return Response.ok("Movie deleted successfully!") // Return 200 response
                .build(); // Build the Response object
    }

}