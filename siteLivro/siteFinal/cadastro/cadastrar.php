<?php
// Desenvovildo por Andr� Luis
// Contato : djinn22001@yahoo.com.br / stopa190@hotmail.com
//  www.ajaxdesign.webege.com      

session_start();

$hora = gmdate("H:i:s");
$data = gmdate("d/m/y");
$ip = $_SERVER['REMOTE_ADDR'];
$captcha = strtoupper($_POST['captcha']);

if($_SERVER['REQUEST_METHOD'] == "GET"){
$arquivo = fopen("cadastro.log","a+");
$registro = "$hora - $data : Solicita��o incorreta de $ip.\r\n";
$registro .= "$hora - $data : Tentativa de acesso direto (GET).\r\n";
fputs ($arquivo,$registro); 
fclose($arquivo); 
header('HTTP/1.1 400 Bad Request');
exit('HTTP/1.1 400 Solicita��o Incorreta'); } 

if(!eregi("^([A-Z0-9]{5,7})$",$captcha) || $captcha != $_SESSION['captcha']){
$arquivo = fopen("cadastro.log","a+");
$registro = "$hora - $data : Solicita��o incorreta de $ip.\r\n";
$registro .= "$hora - $data : Captcha $captcha com valor incorreto.\r\n";
fputs ($arquivo,$registro); 
fclose($arquivo); 
header('HTTP/1.1 400 Bad Request');
exit('HTTP/1.1 400 Solicita��o Incorreta'); }

$nome = $_POST['nome'];
$login = $_POST['login'];
$senha= $_POST['senha'];
$email = $_POST['email'];
$telefone = $_POST['telefone'];

if(empty($nome) || empty($login) || empty($senha) || empty($email) || empty($telefone)) {
$arquivo = fopen("cadastro.log","a+");
$registro = "$hora - $data : Solicita��o incorreta de $ip.\r\n";
$registro .= "$hora - $data : Campos do formul�rio em branco.\r\n";
fputs ($arquivo,$registro); 
fclose($arquivo); 
header('HTTP/1.1 400 Bad Request');
exit('HTTP/1.1 400 Solicita��o Incorreta'); }

if(!eregi("^[a-zA-Z0-9.@_-]{4,15}$",$login) || !eregi("^[a-zA-Z0-9.@_-]{4,15}$",$senha)){
$arquivo = fopen("cadastro.log","a+");
$registro = "$hora - $data : Solicita��o incorreta de $ip.\r\n";
$registro .= "$hora - $data : Login ($login) ou Senha ($senha) com valor incorreto.\r\n";
fputs ($arquivo,$registro); 
fclose($arquivo); 
header('HTTP/1.1 400 Bad Request');
exit('HTTP/1.1 400 Solicita��o Incorreta'); }

if(!eregi("^([(]{1}[0-9]{2}[)]{1}[0-9]{4}[-]{1}[0-9]{4})$",$telefone) || !eregi("^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,3})$",$email)){
$arquivo = fopen("cadastro.log","a+");
$registro = "$hora - $data : Solicita��o incorreta de $ip.\r\n";
$registro .= "$hora - $data : Email ($email) ou Telefone ($telefone) com valor incorreto.\r\n";
fputs ($arquivo,$registro); 
fclose($arquivo); 
header('HTTP/1.1 400 Bad Request');
exit('HTTP/1.1 400 Solicita��o Incorreta'); }

//Fun��o para verificar caracteres 
function verificar($string){
$permitido = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz���������������� ";
for ($i=0; $i<strlen($string); $i++) {
if (strpos($permitido, substr($string, $i, 1)) == FALSE) return TRUE; }}

$cadastro = $_POST["cadastro"];

($cadastro == "cpf"){
$cpf = $_POST['cpf'];
$sexo = $_POST['sexo'];
if(!eregi("^([0-9]{3}\.){2}[0-9]{3}-[0-9]{2}$",$cpf) || !eregi("^([MF]{1})$",$sexo)){
$arquivo = fopen("cadastro.log","a+");
$registro = "$hora - $data : Solicita��o incorreta de $ip.\r\n";
$registro .= "$hora - $data : CPF ($cpf) ou Sexo ($sexo) com valor incorreto.\r\n";
fputs ($arquivo,$registro); 
fclose($arquivo); 
header('HTTP/1.1 400 Bad Request');
exit('HTTP/1.1 400 Solicita��o Incorreta'); }}

//$cnpj = $_POST['cnpj'];
//$empresa = $_POST['empresa'];
//if($cadastro == "cnpj"){
//if(!eregi("^[0-9]{2}[.]{1}[0-9]{3}[.]{1}[0-9]{3}[/]{1}[0-9]{4}[-]{1}[0-9]{2}$",$cnpj)){
//$arquivo = fopen("cadastro.log","a+");
//$registro = "$hora - $data : Solicita��o incorreta de $ip.\r\n";
//$registro .= "$hora - $data : CNPJ ($cnpj) com valor incorreto.\r\n";
//fputs ($arquivo,$registro); 
//fclose($arquivo); 
//header('HTTP/1.1 400 Bad Request');
//exit('HTTP/1.1 400 Solicita��o Incorreta'); }}	

//if(verificar($empresa) == TRUE){
//$arquivo = fopen("cadastro.log","a+");
//$registro = "$hora - $data : Solicita��o incorreta de $ip.\r\n";
//$registro .= "$hora - $data : Nome da empresa ($empresa) com valor incorreto.\r\n";
//fputs ($arquivo,$registro); 
//fclose($arquivo); 
//header('HTTP/1.1 400 Bad Request');
//exit('HTTP/1.1 400 Solicita��o Incorreta'); 
//   }		
   
// Inclui o arquivo para conex�o ao banco de dados
include "includes/mysql.php";
$md5_senha = MD5($senha);
$sql = "INSERT INTO `gmtmarke_projeto_livraria`.`usuario` (
`cd_usuario` ,
`nm_usuario` ,
`login` ,
`senha` ,
`email` ,
`celular` ,
`cpf_usuario` ,
`sexo` ,
`status`
)
VALUES (
NULL , '$nome', '$login', '$senha', '$email', '$telefone', '$cpf', '$sexo', '$cadastro'
);";
// Realiza a consulta ou registra no log eventuais erros
$inserir = mysql_query($sql) OR log_evento("Falha ao inserir os dados do cadastro");

// Imprime a mensagem de sucesso
echo("<div align='center'><img src='imagens/disponivel.png' width='14' height='16'/> <span class='titulo1'>Cadastro efetuado com sucesso.</span></div><br /><br />");
?>