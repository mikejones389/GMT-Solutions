<?
// Desenvovildo por Andr� Luis
// Contato : djinn22001@yahoo.com.br / stopa190@hotmail.com
//  www.ajaxdesign.webege.com      

//Configura��o do Banco de dados
$SQLHOST = '162.241.39.192' ; //Endere�o do servidor
$SQLLOGIN = 'gmtmarket' ; //Login do usuario
$SQLPASS = '@gmtifsp' ; //Senha
$DATABASE = 'gmtmarke_projeto_livraria' ; //Nome do banco de dados

// Fun��o para registrar os erros do banco de dados
function log_evento($mensagem){
$hora = gmdate("H:i:s");
$data = gmdate("d/m/y");	
$arquivo = fopen("cadastro.log","a+");
$registro = "$hora - $data : $mensagem.\r\n";
fputs ($arquivo,$registro); 
fclose($arquivo); 
exit("<div align='center'><img src='imagens/indisponivel.png' width='14' height='16'/> <span class='titulo1'>O sistema est� temporariamente indispon�vel.Tenta novamente mais tarde.</span></div></div><div><img src='imagens/bg_bottom.jpg' width='759' height='46'/></div></div></body></html>");}

//Conecta ao servidor MySQL
$conectar = @mysql_connect("$SQLHOST","$SQLLOGIN","$SQLPASS") OR log_evento("Falha ao conectar no servidor MySQL");
$db = @mysql_select_db($DATABASE,$conectar) OR log_evento("Falha ao selecionar o banco de dados");

?>