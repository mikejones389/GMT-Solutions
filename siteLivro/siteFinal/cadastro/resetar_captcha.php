<?php
// Desenvovildo por André Luis
// Contato : djinn22001@yahoo.com.br / stopa190@hotmail.com
//  www.ajaxdesign.webege.com      

require 'includes/gerar_captcha.php';
session_start();
$_SESSION['captcha'] = $codigo_captcha; 
?>