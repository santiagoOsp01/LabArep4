package edu.eci.arep.back;

import java.util.concurrent.ConcurrentHashMap;

/***
 * Clase encargada de la memoria cache de nuestro api
 */
public class cache {

    private final ConcurrentHashMap<String, String> Cache;


    /***
     * constructor de la clase cache no recibe parametros
     */
    public cache(){
        this.Cache = new ConcurrentHashMap<>();
    }


    /***
     * Función que devuelve el ConcurrentHashMap que estamos usando como cache
     * @return Cache
     */
    public ConcurrentHashMap<String, String> getCache() {
        return Cache;
    }

    /***
     * Función que revisa que la pelicula que buscamos este en el cache
     * @throws Exception en caso de que la pelicula no este en el cache
     * @param movie la cantidad de peticiones a realizar
     * @return String
     */
    public String getMovie(String movie) throws Exception {
        if (Cache.containsKey(movie)) {
            return Cache.get(movie);
        } else {
            throw new Exception("Not found");
        }
    }

    /***
     * Función que agrega al cache el titulo y informacion de la pelicula
     * @param movie es el titulo de la pelicula
     * @param data es la informacion de la pelicula
     */
    public void addMovie(String movie, String data){
        if (!Cache.containsKey(movie)) {
            Cache.put(movie, data);
        }
    }

}
