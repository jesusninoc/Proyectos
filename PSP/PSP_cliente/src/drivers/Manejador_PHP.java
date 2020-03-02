package drivers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Manejador_PHP {

    public Manejador_PHP() {
    }
    private String json;

    private String passFTP;

    public void agregar(String user, String pass, String correo) throws IOException {

        try {
            String data = "user=" + user + "&password=" + pass + "&correo=" + correo;
            URL url = new URL("http://localhost/php/agregar.php");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.getOutputStream().write(data.getBytes("UTF-8"));

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));


            reader.close();// cerrar flujo

        } catch (MalformedURLException me) {
            System.err.println("MalformedURLException: " + me);
        } catch (IOException ioe) {
            System.err.println("IOException:  " + ioe);
        }
    }

    public boolean validar(String user, String pass) throws IOException {
        boolean validacion = false;
        try {
            String data = "user=" + user + "&password=" + pass;
            URL url = new URL("http://localhost/php/validar.php");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.getOutputStream().write(data.getBytes("UTF-8"));

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String linea = reader.readLine();
            if (linea.length() > 2) {
                validacion = true;
                passFTP = linea.substring(linea.indexOf("passFTP") + 10, linea.length() - 3); //sacar de base de datos el pass del FTP (ya que el user es el mismo)
                linea = null;
            }

            reader.close();// cerrar flujo

        } catch (MalformedURLException me) {
            System.err.println("MalformedURLException: " + me);
        } catch (IOException ioe) {
            System.err.println("IOException:  " + ioe);
        }
        return validacion;
    }


    public String getPassFTP() {
        return passFTP;
    }

    public void setPassFTP(String passFTP) {
        this.passFTP = passFTP;
    }
}
