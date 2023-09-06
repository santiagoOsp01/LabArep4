package edu.eci.arep.functLambda;

import edu.eci.arep.front.serverHttp;
import edu.eci.arep.functLambda.servicio;

import java.util.HashMap;
import java.util.Map;

/***
 * Clase encargada de iniciar el servidor http y manejo de
 * las funciones lamda
 */
public class lambda {

    /***
     * atributo que contiene los sevicios get de las funciones lambda
     */
    public static Map<String, servicio> getServicios = new HashMap<>();
    /***
     * atributo que contiene los sevicios post de las funciones lambda
     */
    public static Map<String,servicio> postServicios = new HashMap<>();

    /***
     * funcion encargada de cargar las funciones lambda y inicialisar el servidor
     * @throws Exception en caso de que suceda un error
     * @param args donde se le pueden pasar argumentos
     */
    public static void main( String[] args ) throws Exception {
        get("/hello", str -> "Hola con get " + str);
        post("/hello", str -> "Hola con post " + str);
        get("/coseno", str -> "" + Math.cos(Double.parseDouble(str)));
        post("/coseno", str -> "" + Math.cos(Double.parseDouble(str)));
        get("/seno", str -> "" + Math.sin(Double.parseDouble(str)));
        post("/seno", str -> "" + Math.sin(Double.parseDouble(str)));

        serverHttp.start(args);
    }

    /***
     * funcion encargada de cargar las funciones lambda y inicialisar el servidor
     * @param accion es el verbo que puede ser POST O GET
     * @param nombre es el nombre de la funcion
     * @return servicio
     */
    public static servicio search(String nombre, String accion) {
        if (accion.equals("GET")){
            return getServicios.get(nombre);
        } else if (accion.equals("POST")) {
            return postServicios.get(nombre);
        }
        return null;
    }

    /***
     * funcion que guarda las funciones en un hashmap
     * @param str es el nombre de la funcion
     * @param service es el servicio que se esta guardando
     */
    public static void get(String str, servicio service){
        getServicios.put(str, service);
    }

    /***
     * funcion que guarda las funciones en un hashmap
     * @param str es el nombre de la funcion
     * @param service es el servicio que se esta guardando
     */
    public static void post(String str, servicio service){
        postServicios.put(str, service);
    }


}
