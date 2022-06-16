package edu.escuelaing.arsw.app.HttpServer;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class HttpServer {

    private static HttpServer _instance = new HttpServer();

    public HttpServer() {
    }

    public static HttpServer getInstance(){
        return _instance;
    }


    public static void start(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        while(true) {
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
            String inputLine, outputLine, component = null;
            while ((inputLine = in.readLine()) != null) {
                if(inputLine.matches("(GET)+.*")){
                    component = inputLine.split(" ")[1];
                }
                if (!in.ready()) {
                    break;
                }
            }
            if(component.matches(".*(.html)")) {
                StringBuffer stringBuffer = new StringBuffer();
                System.out.println(component);
                try (BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir")+component))) {
                    String nameFile = null;
                    stringBuffer.append("HTTP/1.1 200 OK\r\n");
                    stringBuffer.append("Content-Type: text/html\r\n");
                    stringBuffer.append("\r\n");
                    while ((nameFile = reader.readLine()) != null) {
                        stringBuffer.append(nameFile);
                    }
                }catch (Exception e){
                    System.out.println(e);
                    outputLine = "HTTP/1.1 404 OK\r\n"
                            + "Content-Type: text/html\r\n"
                            + "\r\n"
                            + "<!DOCTYPE html>"
                            + "<html>"
                            + "<head>"
                            + "<meta charset=\"UTF-8\">"
                            + "<title>Title of the document</title>\n" + "</head>"
                            + "<body>"
                            + "No se encontro el recurso"
                            + "</body>"
                            + "</html>" ;
                    stringBuffer =  new StringBuffer();
                    stringBuffer.append(outputLine);

                }


                out.println();
                out.println(stringBuffer.toString());
            }

            out.close();
            in.close();
        }
    }



}