package miniSpring;


import edu.eci.arep.miniSpring.ComponentLoader;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static junit.framework.Assert.assertEquals;
public class springTest {

    @Before
    public void init() {
        try {
            ComponentLoader.loadComponents();
        } catch (ClassNotFoundException e) {}
    }

    @Test
    public void when_gradeNotRegister_then_ReturnNotFound() throws Exception {
        //Arrange
        String expectedValue = "La calificion no esta agregada";
        Method s = ComponentLoader.search("/hello", "GET");
        //ACT
        String response = ComponentLoader.ejecutar(s,"jorge");
        //ASSERT
        assertEquals(expectedValue, response);
    }

    @Test
    public void when_RegisterGrade_then_notError() throws Exception {
        //Arrange
        String expectedValue1 = "La calificacion fue agregada";
        Method s = ComponentLoader.search("/hello", "POST");
        //ACT
        String response = ComponentLoader.ejecutar(s,"john&5");
        //ASSERT
        assertEquals(expectedValue1, response);
    }

    @Test
    public void when_gradeRegister_then_Returgrade() throws Exception {
        //Arrange
        String expectedValue2 = "Hola  john su calificacion fue 5";
        Method s = ComponentLoader.search("/hello", "POST");
        ComponentLoader.ejecutar(s,"john&5");

        Method s1 = ComponentLoader.search("/hello", "GET");
        //ACT
        String response = ComponentLoader.ejecutar(s1,"john");
        //ASSERT
        assertEquals(expectedValue2, response);
    }

}
