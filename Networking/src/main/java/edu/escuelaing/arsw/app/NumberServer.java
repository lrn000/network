package edu.escuelaing.arsw.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class NumberServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, outputLine;
        String fun= "cos";
        while ((inputLine = in.readLine()) != null) {
            System.out.println("MEensaje recibido" + inputLine);
           if(inputLine.contains("fun:")){
                if(inputLine.contains("cos")){
                    fun="cos";
                }if(inputLine.contains("sin")) {
                   fun="sin";
                }if(inputLine.contains("tan")){
                   fun="tan";
                }
            }else {
               Double val =  Double.valueOf(inputLine);
               Double respuesta=0.0;
               if(fun=="cos"){
                   respuesta=Math.cos(Double.parseDouble(inputLine));

               }else if(fun=="sin"){
                   Math.sin(Double.parseDouble(inputLine));
               }else if(fun=="tan"){
                   Math.tan(Double.parseDouble(inputLine));
               }
               outputLine = "Respuesta"+ " " + "Coseno de " + inputLine + " es: " + respuesta;
               out.println(outputLine);
           }
            /*if (outputLine.equals("Respuestas: Bye."))
                break;*/
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }

}
