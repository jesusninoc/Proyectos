<?PHP
session_start();
if ($_SERVER["REQUEST_METHOD"] == "POST") {

    $usuario = $_POST['user'];
    $pass = $_POST['password'];
    $correo = $_POST['correo'];

    $conection = mysqli_connect("localhost", "root", "") or die("Error en la conexion a base de datos");
    mysqli_select_db($conection, "gestor");



    $peticion = "INSERT INTO login(user, password, correo) VALUES('$usuario','$pass', '$correo')";
    $consulta = mysqli_query($conection, $peticion);
    $convertToJson = array();


    session_destroy();
    mysqli_close($conection);
}