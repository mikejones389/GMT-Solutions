
<?php
$db = mysqli_connect('35.198.9.166:3306', 'root','@gmtifsp', 'projeto_livraria');
if (!$db)
{
echo 'Não deu para conectar ao Banco de Dados';
exit;
}
?>