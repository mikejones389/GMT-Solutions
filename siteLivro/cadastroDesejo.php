<?php 
require_once "conexaoBD.php"; 

$listaDesejo = 0;
$usuario = $_POST['cd_usuario'];
$livro = $_POST['cdLivro'];


$sql="insert into item_desejado (cd_listaDesejo,cd_usuario,cd_livro) values ('$listaDesejo', '$usuario', '$livro')";


if (mysqli_query($db, $sql)) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($db);
}



?>

