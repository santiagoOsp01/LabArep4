package edu.eci.arep.front;

import java.net.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.*;
import edu.eci.arep.back.controllerMovie;

/***
 * Clase encargada de montar nuestro servidor http
 */
public class serverHttp {


    /***
     * funcion encargada de inicializar nuestro servido y la que se va
     * a mantener en constante uso hasta que se decida apagar el servicio
     * de lo contrario seguira funcionando con la excepcion que ocurra un error
     * @throws Exception en caso de que suceda un error
     */
    public static void main(String[] args) throws Exception{
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
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            boolean firstLine = true;
            String path = null;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if (firstLine){
                    firstLine = false;
                    path = inputLine.split(" ")[1];
                }
                if (!in.ready()) {
                    break;
                }
            }
            outputLine = "";
            if (path.startsWith("/movie")){
                outputLine = getTextFile(path, clientSocket.getOutputStream());
            } else {
                outputLine = getTextFile(path, clientSocket.getOutputStream());
            }
            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }



    public static String getTextFile(String path, OutputStream out) throws URISyntaxException, IOException {
        URI requestedUir = null;
        requestedUir = new URI("target/classes/public" + path);
        String header = "";
        Path file = Paths.get(requestedUir.getPath());
        PrintWriter printWriter = new PrintWriter(out, true);
        BufferedReader reader = Files.newBufferedReader(file);
        String line = null;
        header = "HTTP/1.1 200 OK \r\n" +
                "Content-Type: text/html \r\n" +
                "Access-Control-Allow-Headers: *"+
                "\r\n";
        while ((line = reader.readLine()) != null){
            System.out.println(line);
            printWriter.println(line);
            header += line + "r\n";
        }
        return header;
    }


}
