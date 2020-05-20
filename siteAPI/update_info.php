<?php
    $user_id = (int) $_POST["id"];
    $user_name =  $_POST["name"];
    $user_idade = (int) $_POST["idade"];
    $user = "gmtmarke_teste";
    $password ="@gmtifsp";
    $host = "162.241.39.192";
    $db_name = "gmtmarke_projeto_livraria";

    $con = mysqli_connect($host,$user,$password,$db_name);

    $sql = "insert into usuario_app values ('".$user_id."','".$user_name."','".$user_idade."');";
    if(mysqli_query($con,$sql)){
        echo "Data insertion success....";
    }
    else{
        echo "Error while insertion....";
    }
    mysqli_close($con);

?>
