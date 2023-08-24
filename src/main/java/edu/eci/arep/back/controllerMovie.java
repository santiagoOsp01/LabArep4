package edu.eci.arep.back;

import java.io.*;
import java.net.*;

/***
 * Clase encargada del manejo de nuestra api, la que va a consultar primero en el
 * cache si eta la pelicula y si no va a hacer una peticion a la api externa maneja json
 */
public class controllerMovie {

    private static final String GET_URL = "http://www.omdbapi.com/?apikey=f204c1de&t=";
    private static cache Cache;

    /***
     * funcion encargada de inicializar nuestro cache
     */
    public static void begin() {
        Cache = new cache();
    }

    /***
     * Función que primero busca si tiene la pelicula en el cache y
     * dado el caso de que no la tenga hace la peticion a la api externa
     * @throws Exception en caso de que la pelicula no exista en la api esterna
     * @param movie es el titulo de la pelicula
     * @return String
     */
    public static String getMovie(String movie) throws Exception {
        try {
            return Cache.getMovie(movie);
        } catch (Exception e) {
            URL url = new URL(GET_URL + movie);
            String response = makeRequest(url);
            Cache.addMovie(movie, response);
            return response;
        }
    }

    /***
     * Función que devuelve el cache de nuestro api
     * @return Cache
     */
    public static cache getMovieService() {
        return Cache;
    }

    /***
     * Función que revisa que la pelicula que buscamos este en el cache
     * @throws IOException en caso de que la pelicula no exista en la api esterna
     * @param obj la url a la cual haremos las peticiones
     */
    private static String makeRequest(URL obj) throws IOException {
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
            return response.toString();
        } else {
            System.out.println("GET request not worked");
            return "GET request not worked";
        }
    }

}
