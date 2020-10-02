<?php


// CARREGA OS DADOS DO BANCO DE DADOS 
require_once "../conexaoBD.php"; 
$query = "select * from livro where cd_livro = 12";

//valecho $query;
$result = mysqli_query($db,$query);
$num_results = mysqli_num_rows($result);


for ($i=0; $i <$num_results; $i++)
		{
		$row = mysqli_fetch_array($result);
		$nomeLivro = 	$row['nm_livro'];
		$precoLivro = 	$row['preco_venda'];

}
echo utf8_encode($nomeLivro);


?>
                      