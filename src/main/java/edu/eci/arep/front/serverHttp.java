package edu.eci.arep.front;

import java.net.*;
import java.io.*;

import edu.eci.arep.fileController.file;
import edu.eci.arep.fileController.imgController;
import edu.eci.arep.fileController.textController;


/***
 * Clase encargada de montar nuestro servidor http
 */
public class serverHttp {
    private static file fileController;


    /***
     * funcion encargada de inicializar nuestro servidor http que recibe diferentes tipos de archivos y la que se va
     * a encargar de mantener en constante uso hasta que se decida apagar el servicio
     * de lo contrario seguira funcionando con la excepcion que ocurra un error
     * @throws Exception en caso de que suceda un error
     * @param args donde se le pueden pasar argumentos
     */
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        while (true) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
                break;
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine = in.readLine();
            String path = inputLine.split(" ")[1];
            URI filePath = new URI("/target/classes/public" + path);
            System.out.println(inputLine);

            try {
                manageFile(filePath, clientSocket);
            }catch (Exception e){}

            in.close();
        }
        serverSocket.close();
    }

    /***
     * funcion encargada de manejar los archivos que tenga que manejar el servidor
     * ya si sean un formato de texto o de imagenes
     * @param filePath es el path de nuestro archivo
     * @param clientSocket es el socket donde esta el cliente
     * @throws Exception en caso de que suceda un error
     */
    private static void manageFile(URI filePath, Socket clientSocket) throws Exception {
        File file = new File(System.getProperty("user.dir") + filePath);
        String fileType = getFileType(filePath);
        if (!file.exists()) {
            fileController = new textController(clientSocket, "html", new URI("/target/classes/public" + "/error101.html"));
        } else if (filePath.getPath().endsWith(".jpg") || filePath.getPath().endsWith(".png") || filePath.getPath().endsWith(".jpeg") || filePath.getPath().endsWith(".gif")){
            fileController = new imgController(clientSocket, fileType, filePath);
        } else if (filePath.getPath().endsWith(".html") || filePath.getPath().endsWith(".css") || filePath.getPath().endsWith(".js")) {
            fileController = new textController(clientSocket, fileType, filePath);
        }else {
            fileController = new textController(clientSocket, "html", new URI("/target/classes/public" + "/error102.html"));
        }
        fileController.sendfile();
    }

    /***
     * Función que devuelve el tipo del archivo
     * @param path es el titulo de la pelicula
     * @return String
     */
    private static String getFileType(URI path){
        String format = "";
        try {
            format = path.getPath().split("\\.")[1];
        } catch (ArrayIndexOutOfBoundsException e){}
        return format;
    }


}
