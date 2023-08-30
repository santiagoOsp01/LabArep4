package edu.eci.arep.front;

import java.net.*;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import edu.eci.arep.fileController.fileController;
import edu.eci.arep.fileController.imgController;
import edu.eci.arep.fileController.textController;
import org.json.*;

/***
 * Clase encargada de montar nuestro servidor http
 */
public class serverHttp {
    private static fileController responseInterface;
    private static final List<String> supportedImgFormats = Arrays.asList("jpg", "png", "jpeg");

    private static final List<String> supportedTextFormats = Arrays.asList("html", "css", "js");


    /***
     * funcion encargada de inicializar nuestro servido y la que se va
     * a mantener en constante uso hasta que se decida apagar el servicio
     * de lo contrario seguira funcionando con la excepcion que ocurra un error
     * @throws Exception en caso de que suceda un error
     */
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine = in.readLine();
            String path = inputLine.split(" ")[1];
            URI resourcePath = new URI("/target/classes/public" + path);
            System.out.println("Received: " + inputLine);

            try {
                sendResponse(resourcePath, clientSocket);
            }catch (Exception e){}


            in.close();
        }
        serverSocket.close();
    }

    private static void sendResponse(URI resourcePath, Socket clientSocket) throws IOException, URISyntaxException {
        char lastChar = resourcePath.getPath().charAt(resourcePath.getPath().length() - 1);
        String fileType = getFileType(resourcePath);
        if (!fileExists(resourcePath)) {
            responseInterface = new textController(clientSocket, "html", new URI("/target/classes/public" + "/error.html"));
        } else if (isImage(resourcePath)) {
            responseInterface = new imgController(clientSocket, fileType, resourcePath);
        } else if (isText(resourcePath)) {
            responseInterface = new textController(clientSocket, fileType, resourcePath);
        }
        responseInterface.sendResponse();
    }

    private static String getFileType(URI path){
        String fileFormat = "";
        try {
            fileFormat = path.getPath().split("\\.")[1];
        } catch (ArrayIndexOutOfBoundsException ignored){}
        return fileFormat;
    }

    private static boolean isImage(URI path){
        String fileFormat = path.getPath().split("\\.")[1];
        return supportedImgFormats.contains(fileFormat);
    }

    private static boolean isText(URI path){
        String fileFormat = path.getPath().split("\\.")[1];
        return supportedTextFormats.contains(fileFormat);
    }

    private static boolean fileExists(URI path) {
        File file = new File(System.getProperty("user.dir") + path);
        return file.exists();
    }



}
