package edu.eci.arep.fileController;

import java.io.IOException;
import java.net.*;

/***
 * Clase abstracta de los archivos
 */
public abstract class file {

    /***
     * atributo abstracto que contiene el socket del cliente
     */
    public Socket clientSocket;
    /***
     * atributo abstracto que contiene el tipo del archivo
     */
    public String fileType;
    /***
     * atributo abstracto que contiene la ruta del archivo
     */
    public URI filePath;

    /***
     * metodo abstracto encargada de enviar los archivos
     * @throws IOException en caso de que suceda un error
     */
    public abstract void sendfile() throws IOException;



}
