package edu.eci.arep.fileController;

import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.*;

/***
 * Clase encargada de los archivos de texto
 */
public class textController extends file {


    /***
     * constructor de la clase textController recibe
     * @param clientSocket es el nuestro socket
     * @param fileType es el formato del archivo
     * @param filePath es el path de nuestro archivo
     */
    public textController(Socket clientSocket, String fileType, URI filePath){
        this.clientSocket = clientSocket;
        this.fileType = fileType;
        try {
            this.filePath = new URI("." + filePath);
        }catch (URISyntaxException e){
            this.filePath = filePath;
        }
    }


    /***
     * metodo encargado de enviar los archivos
     * @throws IOException en caso de que suceda un error
     */
    @Override
    public void sendfile() throws IOException {
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        String outputLine;

        outputLine = "HTTP/1.1 200 OK \r\n" +
                "Content-Type: " + getMimeType() + " \r\n" +
                "\r\n";
        outputLine += readFile(filePath.getPath());

        out.println(outputLine);

        out.close();
        clientSocket.close();
    }

    /***
     * metodo encargado de enviar los archivos
     * @param path es el direccion de nuestro archivo
     * @return String
     */
    public String readFile(String path) {
        StringBuilder outputLine = new StringBuilder();
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                outputLine.append(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return outputLine.toString();
    }

    /***
     * metodo encargado de devolver le MimeType
     * @return String
     */
    public String getMimeType(){
        switch (fileType){
            case "js":
                return "text/javascript";
            case "css":
                return "text/css";
            case "html":
                return "text/html";
        }
        return "";
    }

}
