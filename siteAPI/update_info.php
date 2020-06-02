<?php
    $user_name =  $_POST["name"];
    //$user_dt_nasc = (date) $_POST["dt_nasc"];
    //$user_name =  "teste";
    //$user_dt_nasc ;
    //$user_idade = (int) $_POST["idade"];
    $user_login = $_POST["login"];
    $user_senha = $_POST["senha"];
    //$user_login = "teste";
    //$user_senha = "teste";
    $user = "gmtmarke_teste";
    $password ="@gmtifsp";
    $host = "162.241.39.192";
    $db_name = "gmtmarke_projeto_livraria";

    $con = mysqli_connect($host,$user,$password,$db_name);

    $sql = "insert into usuario_app(nm_usuario,login,senha) values ('".$user_name."','".$user_login."','".$user_senha."');";
  // $sql = "insert into usuario_app values ('teste1',null,'teste1','teste1');"; 
   if(mysqli_query($con,$sql)){
    
        echo "Data insertion success....";
    }
    else{
        echo "Error while insertion....";
    }
    mysqli_close($con);

?>
