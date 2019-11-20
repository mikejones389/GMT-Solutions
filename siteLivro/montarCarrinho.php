<?php

if(!isset($_SESSION)){
	session_start();
}

 $livro = $_GET['livro'];

 //$listaItensCarrinhoSelecionados = array();




if (empty($_SESSION['listaItensCarrinhoSelecionados']) ){
	$_SESSION['listaItensCarrinhoSelecionados'] = array();

}

if (!in_array($_GET['livro'] , $_SESSION['listaItensCarrinhoSelecionados'])){
	array_push($_SESSION['listaItensCarrinhoSelecionados'] , $_GET['livro']);	
}





header("Location: shoping-cart.php");
		
?>
	

	