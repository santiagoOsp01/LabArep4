package edu.eci.arep.fileController;

import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.Scanner;

/***
 * Clase encargada de los archivos de imagenes
 */
public class imgController extends file{

    /***
     * atributo que contiene el encabezado
     */
    public String header;


    /***
     * constructor de la clase imgController recibe
     * @param clientSocket es el nuestro socket
     * @param fileType es el formato del archivo
     * @param filePath es el path de nuestro archivo
     */
    public imgController(Socket clientSocket, String fileType, URI filePath){
        this.clientSocket = clientSocket;
        this.fileType = fileType;
        this.filePath = filePath;
        this.header = "HTTP/1.1 200 OK \r\n" +
                "Content-Type: image/" + fileType + " \r\n" +
                "\r\n";
    }


    /***
     * metodo encargado de enviar y leer los archivos
     * @throws IOException en caso de que suceda un error
     */
    @Override
    public void sendfile() throws IOException {
        OutputStream out = clientSocket.getOutputStream();
        System.out.println(filePath);
        BufferedImage img = ImageIO.read(new File(System.getProperty("user.dir") + filePath));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(img, fileType, byteArrayOutputStream);

        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
        out.write(header.getBytes());
        out.write(byteArrayOutputStream.toByteArray());

        out.close();
        clientSocket.close();
    }

}
