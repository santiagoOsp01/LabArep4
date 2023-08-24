package edu.eci.arep.front;

import java.net.*;
import java.io.*;
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
    public void begin() throws Exception{
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
            outputLine = "HTTP/1.1 200 OK \r\n";
            if (path.startsWith("/movie")){
                outputLine += getMovie(path);
            } else {
                outputLine += getIndex();
            }
            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }


    /***
     * Función que agrega la informacion de la pelicula de forma ordenada al http
     * @throws Exception en caso de que la pelicula no este
     * @param path la url la cual extraeremos el nombre de la pelicula para asi hacer
     * uso de nuestra api que nos dara la informacion de la pelicula
     */
    private static String getMovie(String path) throws Exception {
        String response = controllerMovie.getMovie(path.split("=")[1]);
        JSONObject object = new JSONObject(response);
        if (response.contains("False")){
            return null;
        }
        return  "Content-Type: text/json \r\n" +
                "\r\n" +
                "<div>" +
                "<h2>"+ object.get("Title") + "</h2>" +
                "<p> Year: " + object.get("Year") + "</p>" +
                "<img src=\"" + object.get("Poster") + "\"/>" +
                "<p> Plot: " + object.get("Plot") + "</p>" +
                "<p> Rated: " + object.get("Rated") + "</p>" +
                "<p> Released: " + object.get("Released") + "</p>" +
                "<p> Runtime: " + object.get("Runtime") + "</p>" +
                "<p> Genre: " + object.get("Genre") + "</p>" +
                "<p> Director: " + object.get("Director") + "</p>" +
                "<p> Writer: " + object.get("Writer") + "</p>" +
                "<p> Actors: " + object.get("Actors") + "</p>" +
                "<p> Language: " + object.get("Language") + "</p>" +
                "<p> Country: " + object.get("Country") + "</p>" +
                "<p> Awards: " + object.get("Awards") + "</p>" +
                "<p> Ratings: " + object.get("Ratings") + "</p>" +
                "<p> Metascore: " + object.get("Metascore") + "</p>" +
                "<p> Type: " + object.get("Type") + "</p>" +
                "<p> BoxOffice: " + object.get("BoxOffice") + "</p>" +
                "<p> Production: " + object.get("Production") + "</p>" +
                "<p> Website: " + object.get("Website") + "</p>" +
                "<p> Response: " + object.get("Response") + "</p>" +
                "</div>\n";
    }

    /***
     * funcion encargada de colocar el index de la pagina en nuestro html en el servidor
     * el cual contiene javascript que tiene una funcion que llena la informacion del nombre que pusimos a buscar
     */
    private static String getIndex(){
        return "Content-Type: text/html \r\n"
                + "\r\n <!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <title>Movie Library</title>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <h1>Movie Library</h1>\n" +
                "        <form action=\"/hello\">\n" +
                "            <label for=\"name\">Name:</label><br>\n" +
                "            <input type=\"text\" id=\"name\" name=\"name\" value=\"it\"><br><br>\n" +
                "            <input type=\"button\" value=\"Search\" onclick=\"loadGetMsg()\">\n" +
                "        </form> \n" +
                "        <hr>" +
                "        <div id=\"getrespmsg\"></div>\n" +
                "\n" +
                "        <script>\n" +
                "            function loadGetMsg() {\n" +
                "                let nameVar = document.getElementById(\"name\").value;\n" +
                "                const xhttp = new XMLHttpRequest();\n" +
                "                xhttp.onload = function() {\n" +
                "                    document.getElementById(\"getrespmsg\").innerHTML =\n" +
                "                    this.responseText;\n" +
                "                }\n" +
                "                xhttp.open(\"GET\", \"/movie?name=\"+nameVar);\n" +
                "                xhttp.send();\n" +
                "            }\n" +
                "        </script>\n" +
                "\n" +
                "    </body>\n" +
                "</html>";
    }

}
