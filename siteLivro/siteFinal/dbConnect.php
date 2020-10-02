<?php

    define('HOST', '162.241.39.192');
    define('USER', 'gmtmarke_teste');
    define('PASS', '@gmtifsp');
    define('DB', 'gmtmarke_projeto_livraria');

    $charset = 'utf8';
    
    $conn = mysqli_connect(HOST, USER, PASS, DB) or dir('FALHOU...');


?>