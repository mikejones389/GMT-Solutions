<?php
$db = mysqli_connect('localhost:3307', 'root','', 'projeto_livraria');
if (!$db)
{
echo 'Não deu para conectar ao Banco de Dados';
exit;
}

$query = "select * from estoque";
$result = mysqli_query($db,$query);
$num_results = mysqli_num_rows($result);
echo '<p>Número de alunos encontrados: '.$num_results.'</p>';
for ($i=0; $i <$num_results; $i++)
{
$row = mysqli_fetch_array($result);
echo '<br />ps_estoque: ';
echo stripslashes($row[0]);
echo '<br />qnt_livro: ';
echo stripslashes($row[1]);
echo '</p>';
}
mysqli_close($db);
?>