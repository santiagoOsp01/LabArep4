package edu.eci.arep.front;

import java.util.HashMap;
import java.util.Map;

public class lambda {

    public static Map<String,servicio> servicios = new HashMap<>();

    public static void main( String[] args ) throws Exception {
        get("/hello", str -> "Hola " + str);
        get("/author", str -> "Santiago");

        serverHttp.start(args);
        servicio s  = buscar("/hello");

    }

    public static servicio buscar(String nombre) {
        return servicios.get(nombre);
    }

    public static void get(String str, servicio service){
        servicios.put(str, service);
    }


}
