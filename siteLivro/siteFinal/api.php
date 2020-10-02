<?php



if($_SERVER['REQUEST_METHOD'] == 'POST'){



    header('Content-type: application/json');



    // TOKEN

    $api_cpf = $_POST["api_cpf"];

    $api_token = $_POST["api_token"];

    $api_cd_usuario = $_POST["api_cd_usuario"];

    $api_nm_usuario = $_POST["api_nm_usuario"];

    $api_sexo = $_POST["api_sexo"];

    $api_dt_nasc = $_POST["api_dt_nasc"];

    $api_login = $_POST["api_login"];

    $api_senha = md5($_POST["api_senha"]);

    $api_email = $_POST["api_email"];

    $api_celular = $_POST["api_celular"];


    

        

        // Conexﾃ｣o com o BD



        require_once('dbConnect.php');



        // DEFINIR UTF8 PARA A CONEXﾃグ



        mysqli_set_charset($conn, $charset);



        $response = array();

        

        $sql = "INSERT INTO usuario(nm_usuario, cpf_usuario, sexo, data_nascimento,email, celular, login, senha)

         VALUES ('$api_nm_usuario', '$api_cpf', '$api_sexo', '$api_dt_nasc', '$api_email', '$api_celular', '$api_login', '$api_senha');"; 



        $stmt = mysqli_prepare($conn, $sql);



        mysqli_stmt_execute($stmt);

        mysqli_stmt_store_result($stmt);

        mysqli_stmt_bind_result($stmt, $id, $nm_usuario,$dt_nasc, $email, $login, $senha);



        //APRENSENTA OS DADOS DA CONSULTA

        //var_dump($stmt);

        $id=mysqli_stmt_insert_id($stmt);

        if(mysqli_stmt_affected_rows($stmt) > 0){

            

            $response["Cadastro"]= true;

            $response["NOME"]= $api_nm_usuario;

            $response["SEXO"]= $api_sexo;
            
            $responde["EMAIL"]= $api_email;
            
            $response["ID"]=$id;

            echo json_encode($response);



        }else{

            $response["Cadastro"] = false;

            $response["SQL"] = $sql;

            echo json_encode($response);

        }


}

else {
    
    $respones['auth_method'] = false;
    echo json_encode($response);
}

?>