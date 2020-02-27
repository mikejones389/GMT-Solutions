
<?php
$db = mysqli_connect('localhost:3306', 'root','', 'projeto_livraria');
if (!$db)
{
echo 'Não deu para conectar ao Banco de Dados';
exit;
}
?>