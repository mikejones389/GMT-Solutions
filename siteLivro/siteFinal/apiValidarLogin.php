<?php
if(!isset($_SESSION)){
    session_start();
}
if($_SERVER['REQUEST_METHOD'] == 'POST'){

    header('Content-type: application/json');

    // TOKEN
    
    $api_email = $_POST["api_email"];
    $api_senha = md5($_POST["api_senha"]);
    
    // Conexﾃ｣o com o BD

    require_once('dbConnect.php');

    // DEFINIR UTF8 PARA A CONEXﾃグ

    mysqli_set_charset($conn, $charset);

    $response = array();
    
    $sql = "SELECT cd_usuario, login, senha FROM usuario WHERE email like '$api_email' and senha like '$api_senha';"; 

    $stmt = mysqli_prepare($conn, $sql);

    mysqli_stmt_execute($stmt);
    mysqli_stmt_store_result($stmt);
    mysqli_stmt_bind_result($stmt, $id, $login, $senha);

    //APRENSENTA OS DADOS DA CONSULTA
    //var_dump($stmt);

    if(mysqli_stmt_num_rows($stmt) > 0){

        while(mysqli_stmt_fetch($stmt)){
            array_push($response, array("id" => $id, "login" => $login, "senha" => $senha));
        }
        $response["Login"]= true;
        $response["ID"]= $id;
        //echo json_encode($response);
        
        $_SESSION['cd_usuario'] = $row['cd_usuario'];
        $_SESSION['nm_usuario'] = $row['nm_usuario']; 
        header("Location: product.php");


    }else{
        $response["Login"] = false;
        $response["SQL"] = $sql;
        //echo json_encode($response);
        
        header("Location: indexLogin.php?erro=1");
    }
}
else {
    $respones['auth_method'] = false;
    header("Location: indexLogin.php?erro=1");
}
    
mysqli_close($db);
?>