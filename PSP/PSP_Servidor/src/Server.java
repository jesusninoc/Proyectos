import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Server {
    public Server(String[] args) throws IOException {
        while (true || args.length == 1) {

            String rutaCertificado = args[0];
            int puerto = 5556;

            System.setProperty("javax.net.ssl.keyStore", rutaCertificado);
            System.setProperty("javax.net.ssl.keyStorePassword", "admin1234");

            SSLServerSocketFactory sfact = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            SSLServerSocket servidorSSL = (SSLServerSocket) sfact
                    .createServerSocket(puerto);
            SSLSocket clienteConectado = null;
            DataInputStream flujoEntrada = null;//FLUJO DE ENTRADA DE CLIENTE
            DataOutputStream flujoSalida = null;//FLUJO DE SALIDA AL CLIENTE


            //System.out.println("Esperando al cliente ");
            clienteConectado = (SSLSocket) servidorSSL.accept();
            flujoEntrada = new DataInputStream(clienteConectado.getInputStream());

            // EL CLIENTE ME ENVIA UN MENSAJE

            String recibido = flujoEntrada.readUTF();
            System.out.println(recibido);
            flujoSalida = new DataOutputStream(clienteConectado.getOutputStream());

            // ENVIO UN SALUDO AL CLIENTE
            flujoSalida.writeUTF("Informacion enviada");

            // CERRAR STREAMS Y SOCKETS
            flujoEntrada.close();
            flujoSalida.close();
            clienteConectado.close();
            servidorSSL.close();

        }
    }
}
