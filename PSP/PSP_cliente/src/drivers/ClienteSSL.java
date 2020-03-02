package drivers;

import org.apache.commons.net.ftp.FTP;

import java.io.*;
import javax.net.ssl.*;

public class ClienteSSL {

    public ClienteSSL(String rutaAlmacen) throws IOException {
        String Host = "80.102.82.61";
        int puerto = 5556;//puerto remoto

        // Propiedades JSSE)
        System.setProperty("javax.net.ssl.trustStore", rutaAlmacen); //C:\Users\extre\Desktop
        System.setProperty("javax.net.ssl.trustStorePassword", "admin1234");

        System.out.println("PROGRAMA CLIENTE INICIADO....");

        SSLSocketFactory sfact = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket Cliente = (SSLSocket) sfact.createSocket(Host, puerto);

        // CREO FLUJO DE SALIDA AL SERVIDOR
        DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());

        // ENVIO UN SALUDO AL SERVIDOR
        flujoSalida.writeUTF(drivers.Cliente.ARCHIVO);

        // CREO FLUJO DE ENTRADA AL SERVIDOR
        DataInputStream flujoEntrada = new DataInputStream(Cliente.getInputStream());

        // EL servidor ME ENVIA UN MENSAJE
        System.out.println(flujoEntrada.readUTF());
        System.out.println("Por favor, espere a la finalizacion del programa");

        // CERRAR STREAMS Y SOCKETS
        flujoEntrada.close();
        flujoSalida.close();
        Cliente.close();
    }// main
}//..ClienteSSLv