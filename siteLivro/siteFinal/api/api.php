<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){

    header('Content-type: application/json');

    // TOKEN
    $api_token = $_POST["api_token"];
    
    // USUARIO
    $api_cd_usuario = $_POST["api_cd_usuario"];
    $api_nm_usuario = $_POST["api_nm_usuario"];
    $api_sexo = $_POST["api_sexo"];
    $api_login = $_POST["api_login"];
    $api_senha = md5($_POST["api_senha"]);
    $api_avatar = $_POST["api_avatar"];
        
    // PROJETO
    $api_cd_projeto = $_POST["api_cd_projeto"];
    $api_cd_usuario = $_POST["api_cd_usuario"];
    $api_titulo = $_POST["api_titulo"];
    $api_genero = $_POST["api_genero"];
    $api_historia = $_POST["api_historia"];
    $api_valor_total = $_POST["api_valor_total"];
    $api_valor_arrecadado = $_POST["api_valor_arrecadado"];
    
    // BUSCAR
    $api_palavra_chave = $_POST["api_palavra_chave"];
    
    // METODOS CRUD PARA O BANCO
    
    // METODOS PARA USUARIO
    if($api_token == 'validarLogin'){

        // Conexﾃ｣o com o BD

        require_once('dbConnect.php');

        // DEFINIR UTF8 PARA A CONEXﾃグ

        mysqli_set_charset($conn, $charset);

        $response = array();
        
        $sql = "SELECT cd_usuario, login, senha FROM usuario WHERE login like '$api_login' and senha like '$api_senha';"; 

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
            echo json_encode($response);

        }else{
            $response["Login"] = false;
            $response["SQL"] = $sql;
            echo json_encode($response);
        }
    }
    
    else if($api_token == 'cadastrar'){
        
        // Conexﾃ｣o com o BD

        require_once('dbConnect.php');

        // DEFINIR UTF8 PARA A CONEXﾃグ

        mysqli_set_charset($conn, $charset);

        $response = array();
        
        $sql = "INSERT INTO usuario(nm_usuario, sexo, login, senha)
         VALUES ('$api_nm_usuario', '$api_sexo', '$api_login', '$api_senha');"; 

        $stmt = mysqli_prepare($conn, $sql);

        mysqli_stmt_execute($stmt);
        mysqli_stmt_store_result($stmt);
        mysqli_stmt_bind_result($stmt, $id, $nm_usuario, $login, $senha);

        //APRENSENTA OS DADOS DA CONSULTA
        //var_dump($stmt);
        
        $id=mysqli_stmt_insert_id($stmt);
        $erro=mysqli_stmt_error($stmt);
        if(mysqli_stmt_affected_rows($stmt) > 0){
            
            $response["Cadastro"]= true;
            $response["NOME"]= $api_nm_usuario;
            $response["SEXO"]= $api_sexo;
            $response["ID"]=$id;
            echo json_encode($response);

        }else{
            $response["Cadastro"] = false;
            $response["SQL"] = $sql;
            $response["ERRO"] = $erro;
            echo json_encode($response);
        }
        
    }
    
    else if($api_token == 'consultarUsuario'){

        //CONEXÃO COM O BDD
        
        require_once('dbConnect.php');
        
        //DEFINIR UTF8 PARA A CONEXÃO
         
        mysqli_set_charset($conn, $charset);
        
        $response = array();
        
        $sql = "SELECT cd_usuario, nm_usuario, sexo, login, senha, avatar FROM usuario WHERE cd_usuario = $api_cd_usuario;";
        
        $stmt = mysqli_prepare($conn, $sql);
        
        mysqli_stmt_execute($stmt);
        mysqli_stmt_store_result($stmt);
        mysqli_stmt_bind_result($stmt, $cd_usuario, $nm_usuario, $sexo, $login, $senha, $avatar);
        
        //APRESENTA OS DADOS DA CONSULTA
        //var_dump($stmt);
        
        
        if(mysqli_stmt_num_rows($stmt) > 0){
            
            while(mysqli_stmt_fetch($stmt)){
                array_push($response, array("cd_usuario" => $cd_usuario, "nm_usuario" => $nm_usuario, "sexo" => $sexo, "login" => $login, "senha" => $senha, "avatar" => $avatar));
            }
            $response["RESULTADO"] = true;
            $response["ID"] = $cd_usuario;
            $response["nm_usuario"] = $nm_usuario;
            $response["SEXO"] = $sexo;
            $response["avatar"] = $avatar;
            echo json_encode($response);
            
        }
        else{
            $response["RESULTADO"] = false;
            $response["SQL"] = $sql;
            echo json_encode($response);
        }
    }
    
    else if($api_token == 'salvarAvatar'){
        
        // Conexﾃ｣o com o BD

        require_once('dbConnect.php');

        // DEFINIR UTF8 PARA A CONEXﾃグ

        mysqli_set_charset($conn, $charset);

        $response = array();
        
        $sql = "UPDATE `usuario` SET `avatar` = '$api_avatar' WHERE `usuario`.`cd_usuario` = '$api_cd_usuario';"; 

        $stmt = mysqli_prepare($conn, $sql);

        mysqli_stmt_execute($stmt);
        mysqli_stmt_store_result($stmt);
        mysqli_stmt_bind_result($stmt, $id, $nm_usuario, $login, $senha);

        //APRENSENTA OS DADOS DA CONSULTA
        //var_dump($stmt);
        
        $id=mysqli_stmt_insert_id($stmt);
        $erro=mysqli_stmt_error($stmt);
        if(mysqli_stmt_affected_rows($stmt) > 0){
            
            $response["Atualizado"]= true;
            $response["ID"]=$id;
            $response["avatar"] = $api_avatar;
            echo json_encode($response);

        }else{
            $response["Atualizado"] = false;
            $response["SQL"] = $sql;
            $response["ERRO"] = $erro;
            $response["avatar"] = $api_avatar;
            echo json_encode($response);
        }
    }
    
    // METODOS PARA PROJETO
    else if($api_token == 'salvarProjeto'){
        
        //Conexão com o BD
        
        require_once('dbConnect.php');
        
        //DEFINIR UTF8 PARA CONEXÃO
        
        mysqli_set_charset($conn, $charset);
        
        $response = array();
        
        $sql = "INSERT INTO projeto(cd_usuario, titulo, genero, historia, valor_total)
                    VALUES('$api_cd_usuario', '$api_titulo', '$api_genero', '$api_historia', '$api_valor_total');";
                    
        $stmt = mysqli_prepare($conn, $sql);
        
        mysqli_stmt_execute($stmt);
        mysqli_stmt_store_result($stmt);
        mysqli_stmt_bind_result($stmt, $cd_projeto, $cd_usuario, $titulo, $genero, $historia, $valor_total);
        
        //APRESENTA OS DADOS DA CONSULTA
        //var_dump($stmt);
        $cd_projeto=mysqli_stmt_insert_id($stmt);
        if(mysqli_stmt_affected_rows($stmt) > 0){
            
            $response["Cadastro"]= true;
            $response["CD_PROJETO"]=$cd_projeto;
            $response["CD_USUARIO"]=$api_cd_usuario;
            $response["titulo"]=$api_titulo;
            $response["genero"]=$api_genero;
            $response["historia"]=$api_historia;
            $response["valorTotal"]=$api_valor_total+";"+$valor_total;
            echo json_encode($response);
        }
        else{
            $response["Cadastro"]=false;
            $response["SQL"]= $sql;
            echo json_encode($response);
        }
        
    }
    
    else if($api_token == 'listarMeusProjetos'){
        
        //CONEXÃO COM O BDD
        
        require_once('dbConnect.php');
        
        //DEFINIR UTF8 PARA A CONEXÃO
        
        mysqli_set_charset($conn, $charset);
        
        $response = array();
        
        $sql = "SELECT p.cd_projeto, p.titulo, p.genero, p.historia, p.cd_usuario, p.valor_total, p.valor_arrecadado, u.cd_usuario, u.nm_usuario, u.avatar FROM projeto AS p INNER JOIN usuario AS u where p.cd_usuario = $api_cd_usuario and u.cd_usuario = $api_cd_usuario ORDER BY cd_projeto DESC;";
        
        //$sql = "SELECT titulo, genero, historia, cd_usuario from projeto where cd_usuario = $api_cd_usuario;";
        
        $stmt = mysqli_prepare($conn, $sql);
        
        mysqli_stmt_execute($stmt);
        mysqli_stmt_store_result($stmt);
        mysqli_stmt_bind_result($stmt, $cd_projeto, $titulo, $genero, $historia, $cd_usuario , $valor_total, $valor_arrecadado, $id_usuario, $nm_usuario, $avatar);
        
        //APRESENTAR OS DADOS DA CONSULTA
        //var_dump($stmt);
        
        $length = mysqli_stmt_num_rows($stmt);
        $response["length"] = $length;
        
        if($length > 0){
            //$length = mysqli_stmt_num_rows($stmt);
            //$response["length"] = $length;
            $i=1;
            while(mysqli_stmt_fetch($stmt)){
                array_push($response, array("cd_projeto" => $cd_projeto, "titulo" => $titulo, "genero" => $genero, "historia" => $historia, "cd_usuario" => $cd_usuario, "id_usuario" => $id_usuario, "nm_usuario" => $nm_usuario, "avatar" => $avatar, "valor_total" => $valor_total , "valor_arrecadado" => $valor_arrecadado));
                
                //RECEBENDO INFORMAÇÕES POR LINHA DO RESPONSE
                
                $response["cd_projeto$i"] = $cd_projeto;
                $response["nm_usuario$i"] = $nm_usuario;
                $response["cd_usuario$i"] = $cd_usuario;
                $response["titulo$i"] = $titulo;
                $response["genero$i"] = $genero;
                $response["historia$i"] = $historia;
                $response["ID$i"] = $api_cd_usuario;
                $response["avatar$i"] = $avatar;
                $response["valor_total$i"] = $valor_total;
                $response["valor_arrecadado$i"] = $valor_arrecadado;
                $i++;
            }
            $response["RESULTADO"] = true;
            echo json_encode($response);
        }
        else{
            $response["RESULTADO"] = false;
            $response["SQL"] = $sql;
            echo json_encode($response);
        }
    }
    
    else if($api_token == 'consultarMeuProjeto'){

        //CONEXÃO COM O BDD
        
        require_once('dbConnect.php');
        
        //DEFINIR UTF8 PARA A CONEXÃO
         
        mysqli_set_charset($conn, $charset);
        
        $response = array();
        
        $sql = "SELECT titulo, historia, genero FROM projeto WHERE cd_projeto = $api_cd_projeto;";
        
        $stmt = mysqli_prepare($conn, $sql);
        
        mysqli_stmt_execute($stmt);
        mysqli_stmt_store_result($stmt);
        mysqli_stmt_bind_result($stmt, $titulo, $historia, $genero);
        
        //APRESENTA OS DADOS DA CONSULTA
        //var_dump($stmt);
        
        
        if(mysqli_stmt_num_rows($stmt) > 0){
            
            while(mysqli_stmt_fetch($stmt)){
                array_push($response, array("titulo" => $titulo, "historia" => $historia, "genero" => $genero));
            }
            $response["RESULTADO"] = true;
            $response["titulo"] = $titulo;
            $response["historia"] = $historia;
            $response["genero"] = $genero;
            echo json_encode($response);
            
        }
        else{
            $response["RESULTADO"] = false;
            $response["SQL"] = $sql;
            echo json_encode($response);
        }
    }
    
    else if($api_token == 'atualizarProjeto'){
        
        // Conexao com o BD
        
        require_once('dbConnect.php');
        
        // DEFINIR UTF8 PARA CONEXAO
        
        mysqli_set_charset($conn, $charset);
        
        $response = array();
        
        $sql = "UPDATE `projeto` SET `titulo` = '$api_titulo', `genero` = '$api_genero', `historia` = '$api_historia' WHERE `projeto`.`cd_projeto` = $api_cd_projeto;";
        
        $stmt = mysqli_prepare($conn, $sql);
        
        mysqli_stmt_execute($stmt);
        mysqli_stmt_store_result($stmt);
        mysqli_stmt_bind_result($stmt);
        $erro=mysqli_stmt_error($stmt);
        
        // APRESENTA OS DADOS DA CONSULTA
        //var_dump($stmt);
        
        if(mysqli_stmt_affected_rows($stmt) > 0){
            
            $response["Atualizar"]= true;
            echo json_encode($response);
        
        }
        else{
            $response["Atualizar"]= false;
            $response["SQL"]= $sql;
            $response["ERRO"]= $erro;
            echo json_encode($response);
        }
        
    }
    
    else if($api_token == 'deletarProjeto'){
        
        // Conexao com o BD
        
        require_once('dbConnect.php');
        
        // DEFINIR UTF8 PARA CONEXAO
        
        mysqli_set_charset($conn, $charset);
        
        $response = array();
        
        $sql = "DELETE FROM `projeto` WHERE `cd_projeto` = $api_cd_projeto;";
        
        $stmt = mysqli_prepare($conn, $sql);
        
        mysqli_stmt_execute($stmt);
        mysqli_stmt_store_result($stmt);
        mysqli_stmt_bind_result($stmt);
        $erro=mysqli_stmt_error($stmt);
        
        // APRESENTA OS DADOS DA CONSULTA
        //var_dump($stmt);
        
        if(mysqli_stmt_affected_rows($stmt) > 0){
            
            $response["Atualizar"]= true;
            echo json_encode($response);
        
        }
        else{
            $response["Atualizar"]= false;
            $response["SQL"]= $sql;
            $response["ERRO"]= $erro;
            echo json_encode($response);
        }
        
    }
    
    else if($api_token == 'listarTodosProjetos'){
        
        //CONEXÃO COM O BDD
        
        require_once('dbConnect.php');
        
        //DEFINIR UTF8 PARA A CONEXÃO
        
        mysqli_set_charset($conn, $charset);
        
        $response = array();
        
        $sql = "SELECT p.cd_projeto, p.titulo, p.genero, p.historia, p.cd_usuario, p.valor_total, p.valor_arrecadado, u.cd_usuario, u.nm_usuario, u.avatar FROM projeto AS p INNER JOIN usuario AS u where p.cd_usuario = u.cd_usuario ORDER BY cd_projeto DESC;";
    
        
        $stmt = mysqli_prepare($conn, $sql);
        
        mysqli_stmt_execute($stmt);
        mysqli_stmt_store_result($stmt);
        mysqli_stmt_bind_result($stmt, $cd_projeto, $titulo, $genero, $historia, $cd_usuario , $valor_total, $valor_arrecadado, $id_usuario, $nm_usuario, $avatar);
        
        //APRESENTAR OS DADOS DA CONSULTA
        //var_dump($stmt);
        
        if(mysqli_stmt_num_rows($stmt) > 0){
            $length = mysqli_stmt_num_rows($stmt);
            $response["length"] = $length;
            $i=1;
            while(mysqli_stmt_fetch($stmt)){
                array_push($response, array("cd_projeto" => $cd_projeto, "titulo" => $titulo, "genero" => $genero, "historia" => $historia, "cd_usuario" => $cd_usuario, "id_usuario" => $id_usuario, "nm_usuario" => $nm_usuario, "avatar" => $avatar, "valor_total" => $valor_total , "valor_arrecadado" => $valor_arrecadado));
                
                //RECEBENDO INFORMAÇÕES POR LINHA DO RESPONSE
                
                $response["cd_projeto$i"] = $cd_projeto;
                $response["nm_usuario$i"] = $nm_usuario;
                $response["cd_usuario$i"] = $cd_usuario;
                $response["titulo$i"] = $titulo;
                $response["genero$i"] = $genero;
                $response["historia$i"] = $historia;
                $response["ID$i"] = $api_cd_usuario;
                $response["avatar$i"] = $avatar;
                $response["valor_total$i"] = $valor_total;
                $response["valor_arrecadado$i"] = $valor_arrecadado;
                $i++;
            }
            $response["RESULTADO"] = true;
            echo json_encode($response);
        }
        else{
            $response["RESULTADO"] = false;
            $response["SQL"] = $sql;
            echo json_encode($response);
        }
    }
    
    else if($api_token == 'consultarProjeto'){

        //CONEXÃO COM O BDD
        
        require_once('dbConnect.php');
        
        //DEFINIR UTF8 PARA A CONEXÃO
         
        mysqli_set_charset($conn, $charset);
        
        $response = array();
        
        $sql = "SELECT p.cd_projeto, p.titulo, p.genero, p.historia, p.valor_total, p.valor_arrecadado, u.cd_usuario, u.nm_usuario, u.avatar FROM `projeto` AS p INNER JOIN usuario AS u WHERE cd_projeto = $api_cd_projeto AND u.cd_usuario = p.cd_usuario";
        
        $stmt = mysqli_prepare($conn, $sql);
        
        mysqli_stmt_execute($stmt);
        mysqli_stmt_store_result($stmt);
        mysqli_stmt_bind_result($stmt, $cd_projeto, $titulo, $genero, $historia, $valor_total, $valor_arrecadado, $cd_usuario, $nm_usuario, $avatar);
        
        //APRESENTA OS DADOS DA CONSULTA
        //var_dump($stmt);
        
        
        if(mysqli_stmt_num_rows($stmt) > 0){
            
            while(mysqli_stmt_fetch($stmt)){
                array_push($response, array("cd_projeto" => $cd_projeto, "titulo" => $titulo, "genero" => $genero, "historia" => $historia, "valor_total" => $valor_total, "valor_arrecadado" => $valor_arrecadado, "cd_usuario" => $cd_usuario, "nm_usuario" => $nm_usuario, "avatar" => $avatar));
            }
            $response["RESULTADO"] = true;
            $response["cd_projeto"] = $cd_projeto;
            $response["titulo"] = $titulo;
            $response["genero"] = $genero;
            $response["historia"] = $historia;
            $response["valor_total"] = $valor_total;
            $response["valor_arrecadado"] = $valor_arrecadado;
            $response["cd_usuario"] = $cd_usuario;
            $response["nm_usuario"] = $nm_usuario;
            $response["avatar"] = $avatar;
            echo json_encode($response);
            
        }
        else{
            $response["RESULTADO"] = false;
            $response["SQL"] = $sql;
            echo json_encode($response);
        }
    }
    
    else if($api_token == 'doarValor'){

        //CONEXÃO COM O BDD
        
        require_once('dbConnect.php');
        
        //DEFINIR UTF8 PARA A CONEXÃO
         
        mysqli_set_charset($conn, $charset);
        
        $response = array();
        
        $sql = "UPDATE `projeto` SET `valor_arrecadado` = '$api_valor_arrecadado' WHERE `projeto`.`cd_projeto` = $api_cd_projeto;";
        
        $stmt = mysqli_prepare($conn, $sql);
        
        mysqli_stmt_execute($stmt);
        mysqli_stmt_store_result($stmt);
        mysqli_stmt_bind_result($stmt, $cd_projeto, $valor_arrecadado);
        
        //APRESENTA OS DADOS DA CONSULTA
        //var_dump($stmt);
        
        
        if(mysqli_stmt_affected_rows($stmt) > 0){
            
            
            $response["RESULTADO"] = true;
            $response["cd_projeto"] = $cd_projeto;
            $response["valor_arrecadado"] = $valor_arrecadado;
            echo json_encode($response);
            
        }
        else{
            $response["RESULTADO"] = false;
            $response["SQL"] = $sql;
            echo json_encode($response);
        }
    }
    
    else if($api_token == 'buscarPalavraChave'){
        
        //CONEXÃO COM O BDD
        
        require_once('dbConnect.php');
        
        //DEFINIR UTF8 PARA A CONEXÃO
        
        mysqli_set_charset($conn, $charset);
        
        $response = array();
        
        $sql = "SELECT p.cd_projeto, p.titulo, p.genero, p.historia, p.cd_usuario, p.valor_total, p.valor_arrecadado, u.cd_usuario, u.nm_usuario, u.avatar 
                FROM projeto AS p INNER JOIN usuario AS u
                WHERE p.cd_usuario = u.cd_usuario AND (p.titulo LIKE '%$api_palavra_chave%' OR p.genero LIKE '%$api_palavra_chave%' OR u.nm_usuario LIKE '%$api_palavra_chave%')
                ORDER BY cd_projeto DESC;";
    
        
        $stmt = mysqli_prepare($conn, $sql);
        
        mysqli_stmt_execute($stmt);
        mysqli_stmt_store_result($stmt);
        mysqli_stmt_bind_result($stmt, $cd_projeto, $titulo, $genero, $historia, $cd_usuario , $valor_total, $valor_arrecadado, $id_usuario, $nm_usuario, $avatar);
        
        //APRESENTAR OS DADOS DA CONSULTA
        //var_dump($stmt);
        
        if(mysqli_stmt_num_rows($stmt) > 0){
            $length = mysqli_stmt_num_rows($stmt);
            $response["length"] = $length;
            $i=1;
            while(mysqli_stmt_fetch($stmt)){
                array_push($response, array("cd_projeto" => $cd_projeto, "titulo" => $titulo, "genero" => $genero, "historia" => $historia, "cd_usuario" => $cd_usuario, "id_usuario" => $id_usuario, "nm_usuario" => $nm_usuario, "avatar" => $avatar, "valor_total" => $valor_total , "valor_arrecadado" => $valor_arrecadado));
                
                //RECEBENDO INFORMAÇÕES POR LINHA DO RESPONSE
                
                $response["cd_projeto$i"] = $cd_projeto;
                $response["nm_usuario$i"] = $nm_usuario;
                $response["cd_usuario$i"] = $cd_usuario;
                $response["titulo$i"] = $titulo;
                $response["genero$i"] = $genero;
                $response["historia$i"] = $historia;
                $response["ID$i"] = $api_cd_usuario;
                $response["avatar$i"] = $avatar;
                $response["valor_total$i"] = $valor_total;
                $response["valor_arrecadado$i"] = $valor_arrecadado;
                $i++;
            }
            $response["RESULTADO"] = true;
            echo json_encode($response);
        }
        else{
            $response["RESULTADO"] = false;
            $response["SQL"] = $sql;
            echo json_encode($response);
        }
    }
    
    else {
        $response['auth_token'] = false;
        echo json_encode($response);
    }

}

else {
    $response['auth_method'] = false;
    echo json_encode($response);
}

?>