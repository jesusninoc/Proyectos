<?PHP
session_start();
if ($_SERVER["REQUEST_METHOD"] == "POST") {

    $usuario = $_POST['user'];
    $pass = $_POST['password'];

    $conection = mysqli_connect("localhost", "root", "") or die("Error en la conexion a base de datos");
    mysqli_select_db($conection, "gestor");



    $peticion = "select * from login where user = '$usuario' and password = '$pass';";
    $consulta = mysqli_query($conection, $peticion);
    $convertToJson = array();

    while ($row = mysqli_fetch_array($consulta)) {

        $rowArray['user'] = $row['user'];
        $rowArray['password'] = $row['password'];
        $rowArray['passFTP'] = $row['passFTP'];

        array_push($convertToJson, $rowArray);
    }

    echo json_encode($convertToJson);



    session_destroy();
    mysqli_close($conection);
}

?>