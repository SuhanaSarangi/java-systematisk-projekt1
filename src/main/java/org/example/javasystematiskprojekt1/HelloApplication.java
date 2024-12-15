package org.example.javasystematiskprojekt1;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * HelloApplication class is the auto-generated starting default class, where our Jakarta REST application is launched.
 * This project is deployed on the external server, GlassFish, which runs on port 8080, when started.
 * GlassFish generates the URL: "http:localhost:8080/project-name"
 * This class contains the annotation that provides a root path "/api" to our application.
 * It navigates us to the REST-ful endpoint, i.e; the base URL link
 * <a href="http://localhost:8080/projectname/api">, where clients can interact with the server.
 *
 * @author auto-generated
 * @version 1.0
 */

@ApplicationPath("/api") // Annotation defines mapping of the class to the URL path "/api".
// This is the root path of our application.
public class HelloApplication extends Application {
    // HelloApplication class inherits all the methods and attributes from the predefined Application class.

}