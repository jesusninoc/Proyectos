<?PHP
session_start();
if ($_SERVER["REQUEST_METHOD"] == "POST") {

    $usuario = $_POST['user'];
    $passw = $_POST['pass'];

    $conection = mysqli_connect("localhost", "root", "") or die("Error en la conexion a base de datos");
    mysqli_select_db($conection, "login");



    $peticion = "select * from login where user = '$usuario' and pass = '$passw';";
    $consulta = mysqli_query($conection, $peticion);

    while ($row = mysqli_fetch_array($consulta)) {

        echo $row['puerto'];


    }

    session_destroy();
    mysqli_close($conection);
}