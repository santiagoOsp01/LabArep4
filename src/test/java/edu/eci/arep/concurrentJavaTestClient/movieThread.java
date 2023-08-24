package edu.eci.arep.concurrentJavaTestClient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.concurrent.atomic.AtomicBoolean;
import edu.eci.arep.back.controllerMovie;

/***
 * Clase que extiend de Thread para poder tener varias peticiones concurrentemente
 */
public class movieThread extends Thread {

    private String movie;
    private String response;

    /***
     * Constructor de la clase RequestThread
     * @param movie recibe una URL a la que hara la consulta
     */
    public movieThread(String movie) {
        super();
        this.movie = movie;
    }
    @Override
    public void run() {
        try {
            response = controllerMovie.getMovie(movie);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
