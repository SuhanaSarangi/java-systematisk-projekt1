package org.example.javasystematiskprojekt1;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

/**
 * When we start our application on GlassFish, along with the HelloApplication class, we also get HelloResource class
 * auto-generated for us. This is a template class to demonstrate how we can have different REST-ful endpoints
 * mapped by different classes. For ex, this class is mapped to the path "/hello-world".
 *
 *@author auto-generated
 *@version 1.0
 */

@Path("/hello-world") // Annotation defines mapping of the class to the URL path "/api/hello-world".
public class HelloResource {
    @GET // Annotation that the method handles HTTP GET requests.
    @Produces("text/plain") // Annotation that response is a text.
    public String hello() {
        return "This is my Overall Java Project #3!";
    }
}