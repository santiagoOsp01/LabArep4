package edu.eci.arep.fileController;

import java.io.*;
import java.net.*;
import java.util.*;

public interface fileController {


    void sendResponse() throws IOException;

    String readFile(String path);

}
