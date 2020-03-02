package drivers;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;

public class Manejador_FTP {

    private static String SERVER = "localhost"; //82.213.200.119
    private FTPClient cliente;
    boolean login = false;
    public static String DIRECTORIO_SERVER = "server";

    public Manejador_FTP() throws IOException {
    }

    public void ftpConectar(String user, String pass) throws IOException {
        cliente = new FTPClient();
        String servFTP = SERVER;

        cliente.connect(servFTP, 21);
        login = cliente.login(user, pass);
        if (login)
            System.out.println("Login ftp correcto");
        else {
            System.out.println("Login Incorrecto");
            cliente.disconnect();
            System.exit(1);
        }

    }

    public void ftpDescargar(String directorio, String rutaElemento) {
        try {

            if (login) {
                //cliente.changeWorkingDirectory(directorio);
                cliente.setFileType(FTP.BINARY_FILE_TYPE);

                //Stream de entrada con el fichero a descargar
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(directorio + "/" + rutaElemento));

                if (cliente.retrieveFile(rutaElemento, out)) {

                    String comando = "Expand-Archive " + directorio + "\\" + rutaElemento + " " + directorio + "\n";
                    ProcessBuilder pb = new ProcessBuilder("powershell.exe", "/c", comando);
                    Thread borrar = new Thread(borrarZip(rutaElemento, directorio));

                    pb.start();
                    borrar.start();

                } else {
                    System.out.println("No se ha podido descargar...");
                }

                out.close(); //cerrar flujo
                cliente.logout(); //logout del usuario
                cliente.disconnect(); // desconexion del servidor
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ftpDesconectar() throws IOException {
        boolean logout = cliente.logout();
        if (logout)
            System.out.println("Logout del servidor FTP . . . ");
        else
            System.out.println("Error al hacer  Logout ... ");
        cliente.disconnect();
        System.out.println("Desconectado . . . ");
    }

    private Runnable borrarZip(String rutaElemento, String directorio) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    String nombreArchivo = "";

                    for (int i = 0; i < rutaElemento.length(); i++) {
                        if (rutaElemento.charAt(i) != '.') {
                            nombreArchivo += rutaElemento.charAt(i);
                        } else {
                            break;
                        }
                    }

                    while (true) {
                        File file = new File(directorio + "\\" + nombreArchivo);
                        if (file.isDirectory()) {
                            Thread.sleep(5000);
                            ProcessBuilder pb1 = new ProcessBuilder("powershell.exe", "/c",
                                    "rm " + directorio + "/" + rutaElemento);
                            pb1.start();
                            System.exit(0);
                            break;
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        return r;
    }
}
