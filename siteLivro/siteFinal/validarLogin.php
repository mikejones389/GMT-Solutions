<?php

if(!isset($_SESSION)){
    session_start();
}



$email = $_POST['username'];
$senha = $_POST['pass'];






require_once "conexaoBD.php";



$query = "select * from usuario where login = '". $email. "'";


$result = mysqli_query($db,$query);
$num_results = mysqli_num_rows($result);
$row = mysqli_fetch_array($result);


//echo stripslashes($row['senha']);

if (substr(md5($senha),0,15) == $row['senha']){
    
    $_SESSION['cd_usuario'] = $row['cd_usuario'];
    $_SESSION['nm_usuario'] = $row['nm_usuario'];






    header("Location: product.php");

}else{
        header("Location: indexLogin.php?erro=1");

}


mysqli_close($db);
?>