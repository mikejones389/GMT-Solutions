<?php
// Desenvovildo por André Luis
// Contato : djinn22001@yahoo.com.br / stopa190@hotmail.com
//  www.ajaxdesign.webege.com      

session_start();

$codigo_captcha = $_SESSION['captcha'];

$fontes = array(0 => 'fontes/amanbold.ttf',
1 => 'fontes/ChalkboardBold.ttf',
2 => 'fontes/Minya.ttf',
3 => 'fontes/staypuft.ttf');
$fonte = $fontes[rand(0,3)];

$bgNUM =  rand(1,5);
$imagem = @imagecreatefrompng('bg'.$bgNUM.'.png');

$colorir = imagecolorallocate($imagem,  rand(1, 100) ,  rand(1,100) ,  rand(1,155) );
imagettftext($imagem, 20, $rotate, 18, 30, $colorir, $fonte, $codigo_captcha);

header('Content-type: image/png');
header("Pragma: no-cache");
header("Cache-Control: public,max-age=0");
imagepng($imagem);

?>