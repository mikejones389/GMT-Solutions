
<?php

    $cep_origem = "07095090";     // Seu CEP , ou CEP da Loja
    $cep_destino = $_POST['cep']; // CEP do cliente, que irá vim via POST



    /* DADOS DO PRODUTO A SER ENVIADO */
    $peso          = 1;
    $valor         = 100;
    $tipo_do_frete = '40010'; //Sedex: 40010   |  Pac: 41106
    $altura        = 6;
    $largura       = 20;
    $comprimento   = 20;


    $url = "http://ws.correios.com.br/calculador/CalcPrecoPrazo.aspx?";
    $url .= "nCdEmpresa=";
    $url .= "&sDsSenha=";
    $url .= "&sCepOrigem=" . $cep_origem;
    $url .= "&sCepDestino=" . $cep_destino;
    $url .= "&nVlPeso=" . $peso;
    $url .= "&nVlLargura=" . $largura;
    $url .= "&nVlAltura=" . $altura;
    $url .= "&nCdFormato=1";
    $url .= "&nVlComprimento=" . $comprimento;
    $url .= "&sCdMaoProria=n";
    $url .= "&nVlValorDeclarado=" . $valor;
    $url .= "&sCdAvisoRecebimento=n";
    $url .= "&nCdServico=" . $tipo_do_frete;
    $url .= "&nVlDiametro=0";
    $url .= "&StrRetorno=xml";


    $xml = simplexml_load_file($url);

    $frete =  $xml->cServico;

    echo "<p>Valor PAC: R$ ".$frete->Valor."<br />Prazo: ".$frete->PrazoEntrega." dias</p>";
    echo "<p>Valor: R$ ".$frete->Valor."<br /></p>";

    
    //session_start();
    //$_SESSION['valor'] = $frete->Valor;
     //header('Location: https://gmtmarketplace.com.br/pagamentoTeste/index.php?livro=5');
    

 ?>
