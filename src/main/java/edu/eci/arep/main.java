package edu.eci.arep;

import edu.eci.arep.back.controllerMovie;
import edu.eci.arep.front.serverHttp;


/***
 * Clase encargada del manejo de nuestor servidor http aqui es donde lo inicializamos
 * y donde si no se corre esta clase no servira nuestor servidor
 */
public class main {

    /***
     * Función main que ejecuta los pasos para poner en marcha el servidor
     * @throws Exception en caso de fallo
     * @param args un String[] donde puede recibir parametros
     */
    public static void main(String[] args) throws Exception {
        controllerMovie.begin();
        serverHttp ServerHttp = new serverHttp();
        ServerHttp.begin();
    }
}
