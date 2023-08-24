package edu.eci.arep.concurrentJavaTestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import edu.eci.arep.back.controllerMovie;

/***
 * Clase principal para el test de concurrencia del back desarollado
 */
public class ConcurrentTest {

    private static AtomicBoolean getOk = new AtomicBoolean(true);

    /***
     * Función main que ejecuta el test de concurrencia donde se lanza 100 hilos
     * @param args un String[] donde puede recibir parametros
     */
    public static void main(String[] args) throws InterruptedException {
        controllerMovie.begin();
        long start = System.currentTimeMillis();

        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            threads.add(new movieThread("it"));
        }

        for (Thread t : threads) {t.start();}
        for (Thread t : threads) {t.join();}
        long end = (System.currentTimeMillis() - start);
        System.out.println("Tiempo del test : " + end+ " ms");
    }

}

