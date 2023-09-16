package edu.eci.arep.miniSpring.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación para los metodos get
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface GetMapping {

    /**
     * Valor asociado a la anotación.
     * @return String con el valor.
     */
    String value();
}
