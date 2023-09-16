package edu.eci.arep.ejemploAppSpring;

import edu.eci.arep.miniSpring.anotations.Componente;
import edu.eci.arep.miniSpring.anotations.GetMapping;
import edu.eci.arep.miniSpring.anotations.PostMapping;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * clase que es nuestra controller del endpoint /hello
 */
@Componente
public class ControlerHello {

    /**
     * atributo que funciona como persistencia
     */
    private static final Map<String, String> persistence = new HashMap<>();

    /***
     * funcion get que nos trae la calificacion de las personas
     * @param name el nombre de la persona
     * @return String
     */
    @GetMapping("/hello")
    public static String getPerson(String name) {
        if (persistence.containsKey(name)) {
            return "Hola  " + name +" " + "su calificacion fue " + persistence.get(name);
        }
        return "La calificion no esta agregada";
    }

    /***
     * funcion get que nos trae la calificacion de las personas
     * @param arg los parametros que se utilizaran
     * @return String
     */
    @PostMapping("/hello")
    public static String postPerson(String arg){
        persistence.put(arg.split("&")[0], arg.split("&")[1]);
        return "La calificacion fue agregada";
    }

}
