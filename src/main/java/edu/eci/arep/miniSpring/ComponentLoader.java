package edu.eci.arep.miniSpring;

import edu.eci.arep.miniSpring.anotations.Componente;
import edu.eci.arep.miniSpring.anotations.GetMapping;
import edu.eci.arep.miniSpring.anotations.PostMapping;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.*;
import java.util.*;

/**
 * clase que se encarga de caragr las clase que tengan la antocion componente
 */
public class ComponentLoader {

    /**
     * atributo que guarda los metodos get
     */
    public static Map<String, Method> getServicios = new HashMap<>();
    /**
     * atributo que guarda los metodos post
     */
    public static Map<String, Method> postServicios = new HashMap<>();
    /**
     * atributo que guarda el camino donde estan los componentes
     */
    public static String path = "edu/eci/arep/ejemploAppSpring";


    /***
     * funcion encargada de ejecutar nuestros metodos
     * @param method si es post o get
     * @param param los parametros de la funcion
     * @throws IllegalAccessException en caso de que suceda un error
     * @throws InvocationTargetException en caso de que suceda un error
     * @return String
     */
    public static String ejecutar(Method method, String param) throws IllegalAccessException, InvocationTargetException {
        return (String) method.invoke(null, param);
    }

    /***
     * funcion encargada de buscar los metodos get post
     * @param nombre si es post o get
     * @param accion los parametros de la funcion
     * @return Method
     */
    public static Method search(String nombre, String accion) {
        if (accion.equals("GET")){
            return getServicios.get(nombre);
        } else if (accion.equals("POST")) {
            return postServicios.get(nombre);
        }
        return null;
    }

    /***
     * funcion encargada caragr los componentes
     * @throws ClassNotFoundException en caso de que suceda un error
     */
    public static void loadComponents() throws ClassNotFoundException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URL packageURL = classLoader.getResource(path);
        if (packageURL != null) {
            String packagePath = packageURL.getPath();
            if (packagePath != null) {
                File packageDir = new File(packagePath);
                if (packageDir.isDirectory()) {
                    File[] files = packageDir.listFiles();
                    assert files != null;
                    saveComponents(files);
                }
            }
        }
    }

    /***
     * funcion encargada de guardar los metodos de los componentes
     * @throws ClassNotFoundException en caso de que suceda un error
     */
    private static void saveComponents(File[] files) throws ClassNotFoundException {
        for (File file : files) {
            String className = file.getName();
            if (className.endsWith(".class")) {
                className = path + "/" + className.substring(0, className.length() - 6);
                Class<?> clazz = Class.forName(className.replace("/", "."));
                if(clazz.isAnnotationPresent(Componente.class)){
                    Method[] methods = clazz.getDeclaredMethods();
                    for(Method m : methods){
                        if(m.isAnnotationPresent(GetMapping.class)){
                            String accion = m.getAnnotation(GetMapping.class).value();
                            getServicios.put(accion, m);
                        } else if(m.isAnnotationPresent(PostMapping.class)){
                            String accion = m.getAnnotation(PostMapping.class).value();
                            postServicios.put(accion, m);
                        }
                    }
                }
            }
        }
    }


}
