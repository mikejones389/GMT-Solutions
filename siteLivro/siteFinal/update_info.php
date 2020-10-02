<?php
    $user_name =  $_POST["name"];
    $user_login =  $_POST["login"];
    $user_senha =  $_POST["senha"];

error_reporting(E_ALL);
ini_set('display_errors', 'On');

    $user = "gmtmarke_teste";
    $password ="@gmtifsp";
    $host = "162.241.39.192";
    $db_name = "gmtmarke_projeto_livraria";

    $con = mysqli_connect($host,$user,$password,$db_name);

    echo ".$user_name.",".$user_login.",".$user_senha.";

    $sql = "insert into usuario(nm_usuario,login,senha) values ('".$user_name."','".$user_login."','".$user_senha."');";
  if(mysqli_query($con,$sql)){
        echo "Data insertion success....";
         echo "Teste:".$user_name.",".$user_login.",".$user_senha."";
          echo "Teste:";
    }
    else{
        echo "Error while insertion....";
    }
    mysqli_close($con);

?>

