package edu.eci.arep.fileController;

import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.*;

public class textController implements fileController {

    private Socket clientSocket;
    private String fileType;
    private URI filePath;

    public textController(Socket clientSocket, String fileType, URI filePath){
        this.clientSocket = clientSocket;
        this.fileType = fileType;
        try {
            this.filePath = new URI("." + filePath);
        }catch (URISyntaxException e){
            this.filePath = filePath;
        }
    }


    @Override
    public void sendResponse() throws IOException {
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

    @Override
    public String readFile(String path) {
        StringBuilder textFile = new StringBuilder();
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                textFile.append(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return textFile.toString();
    }

    private String getMimeType(){
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
