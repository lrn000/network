package edu.escuelaing.arsw.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class URLReader {

    public static void main(String[] args){
        URL ayURL= null;
        try {
            ayURL = new URL("https://campusvirtual.escuelaing.edu.co/moodle/pluginfile.php/208595/mod_resource/content/0/NamesNetworkClientService.pdf");
            System.out.println("Protoco"+ ayURL.getProtocol());
            System.out.println("Authority"+ ayURL.getAuthority());
            System.out.println("Host"+ ayURL.getHost());
            System.out.println("Port"+ ayURL.getPort());
            System.out.println("Path"+ ayURL.getPath());
            System.out.println("Query"+ ayURL.getQuery());
            System.out.println("File"+ ayURL.getFile());
            System.out.println("Ref"+ ayURL.getRef());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }



    }

}
