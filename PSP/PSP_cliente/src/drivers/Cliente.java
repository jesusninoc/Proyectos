package drivers;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Cliente {

    private Manejador_FTP ftp;
    private Manejador_PHP php;
    public static final String ARCHIVO = "GestorV5fx.zip";

    private void instancias() throws IOException {
        ftp = new Manejador_FTP();
        php = new Manejador_PHP();
    }

    public Cliente(String[] args) throws IOException {
        instancias();
        Scanner sc = new Scanner(System.in);
        php = new Manejador_PHP();
        ftp = new Manejador_FTP();

        int seleccion = 0;

            System.out.println("1. Login");
            System.out.println("2. Registro");
            System.out.println("3. Salir");
            seleccion = sc.nextInt();
            switch (seleccion){
                case 1:
                    System.out.print(" - Usario: ");
                    String user = sc.next();
                    sc.nextLine();
                    System.out.print(" - Clave: ");
                    String pass = sc.nextLine();

                    boolean login = php.validar(user, sha512(pass, "maesez"));
                    if(login){

                        System.out.println("Conectando con el servidor FTP...");
                        ftp.ftpConectar("admin", php.getPassFTP());

                        System.out.print(" - Carpeta de descarga: ");
                        String downloadFolder = sc.next();
                        System.out.print(" - Ruta del certificado: ");
                        String rutaCertificado = sc.next();
                        sc.nextLine();
                        System.out.println("descargando el modulo....");

                        //downloadFolder = "C:\Users\extre\Desktop";
                        //rutaCertificado = "C:\Users\extre\Desktop\AlmacenCertificado";

                        ftp.ftpDescargar(downloadFolder, ARCHIVO);
                        new ClienteSSL(rutaCertificado);

                    }else  {
                        System.out.println("Usuario o clave erroneo");
                    }
                    break;


                case 2:
                    System.out.print(" - Correo: ");
                    String correoReg = sc.next();
                    sc.nextLine();
                    System.out.print(" - Usuario: ");
                    String userReg = sc.next();
                    sc.nextLine();
                    System.out.print(" - Clave: ");
                    String passReg = sc.nextLine();

                    php.agregar(userReg, sha512(passReg, "maesez"), correoReg);
                    break;
                case 3:
                    System.exit(0);
                    break;
            }

        }


    public String sha512(String passwordToHash, String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

}
