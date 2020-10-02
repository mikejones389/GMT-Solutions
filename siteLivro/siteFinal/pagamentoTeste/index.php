<?php
include_once("configuracoes.php");

//Nesse checkout estamos usando o método GET somente a título de aprendizagem,
//mas para a segurança da sua aplicação use o método POST para passar os dados da venda para o checkout.

//$id_transacao = $_POST["id_transacao"];
//$descricao = $_POST["descricao"];

// id_transacao == cd_livro
//$id_transacao = $_GET["livro"];

$livro = $_GET["livro"]; 

// CARREGA OS DADOS DO BANCO DE DADOS 
require_once "../conexaoBD.php"; 
$query = "select * from livro where cd_livro = ".$livro;

//valecho $query;
$result = mysqli_query($db,$query);
$num_results = mysqli_num_rows($result);


for ($i=0; $i <$num_results; $i++)
		{
		$row = mysqli_fetch_array($result);
		$nomeLivro = 	utf8_encode($row['nm_livro']);
		$precoLivro = 	$row['preco_venda'];

}



//Lembre-se: a moeda deve estar formatada sem vírgula e com o ponto separando somente os centavos.
//R$ 0,50 ficaria assim 0.50
//R$ 10,00 ficaria assim 10.00
//R$ 100,00 ficaria assim 100.00
//R$ 1.000,00 ficaria assim 1000.00

$total = $precoLivro;
$total_tela = $precoLivro;



$id_transacao = "1";
$descricao = $row['nm_livro'];


//Lembre-se: a moeda deve estar formatada sem vírgula e com o ponto separando somente os centavos.
//R$ 0,50 ficaria assim 0.50
//R$ 10,00 ficaria assim 10.00
//R$ 100,00 ficaria assim 100.00
//R$ 1.000,00 ficaria assim 1000.00

//$total = "20.00";
//$total_tela = "R$ 20,00";

//------------------------------------
?>
    <!DOCTYPE html>
    <html lang="pt">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Checkout transparente PagSeguro em PHP">
        <meta name="author" content="Noveweb Soluções Digitais">
        <title>Checkout</title>
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900" rel="stylesheet" type="text/css">
        <link href="icons/icomoon/styles.min.css" rel="stylesheet" type="text/css">
        <link href="icons/fontawesome/styles.min.css" rel="stylesheet" type="text/css">
        <link href="css/checkout.css" rel="stylesheet" type="text/css">
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="js/blockui.min.js"></script>
        <script type="text/javascript" src="js/sweet_alert.min.js"></script>
    </head>

    <body>
        <div class="page-content">
            <div class="content-wrapper">
                <div class="content d-flex justify-content-center align-items-center">
                    <form id="form_login" name="form_login" class="checkout-form" action="" autocomplete="off" enctype="application/x-www-form-urlencoded" method="post" onsubmit="return false;">
                        <div class="header mb-4 text-center checkout-logo">
                            <img src="images/gmtstore1.png" class="img-fluid" alt="GMT Store" />
                        </div>
                        <div class="d-sm-flex justify-content-sm-center d-flex justify-content-center d-xl-block mb-3">
                             <strong class="font-weight-semibold">Livro: </strong><?php echo $nomeLivro; ?>
                                <br>
                            <div class="badge badge-pill bg-grey-300 text-12 font-weight-light valor_cobrado" >
                               
                                
                                Valor cobrado: <strong class="font-weight-semibold" id="valorCobrado"><?php echo $total_tela; ?></strong>
                                 
                            </div>
                        </div>
                        <div class="card mb-0 shadow-0 mt-3 card_sucesso hidden">
                            <div class="card-body card_sucesso_txt">
                                Pagamento concluído com sucesso.
                            </div>
                        </div>
                        <div class="card mb-0 shadow-0 mt-3 card_pagamento">
                            <div class="card-heading">
                                <ul class="nav nav-tabs nav-justified nav-tabs-solid border-0 shadow-0 tabs_checkout">
                                    <li class="nav-item"><a id="tab_cartao" href="#tab-cartao" class="nav-link tab_checkout pt-2 pb-2 active show" data-toggle="tab">CRÉDITO</a></li>
                                    <li class="nav-item"><a id="tab_boleto" href="#tab-boleto" class="nav-link tab_checkout pt-2 pb-2" data-toggle="tab">BOLETO</a></li>
                                    <li class="nav-item"><a id="tab_debito" href="#tab-debito" class="nav-link tab_checkout pt-2 pb-2" data-toggle="tab">DÉBITO</a></li>
                                     <li class="nav-item"><a id="tab_end" href="#tab-end" class="nav-link tab_checkout pt-2 pb-2" data-toggle="tab">ENDEREÇO</a></li>
                                </ul>
                            </div>
                            <div class="card-body">

                                <div class="tab-content">

                                    <div class="tab-pane active show" id="tab-cartao">

                                        <div class="row">
                                            <div class="col-xl-6 col-md-6 col-sm-6">
                                                <div class="form-group">
                                                    <input class="form-control" id="cartao_nome" placeholder="Nome do titular" type="text" value="" />
                                                </div>
                                            </div>
                                            <div class="col-xl-2 col-md-2 col-sm-4">
                                                <div class="form-group">
                                                    <input class="form-control" id="cartao_celular" placeholder="Celular do titular" type="text" value="" />
                                                </div>
                                            </div>
                                            <div class="col-xl-2 col-md-2 col-sm-3 col-8">
                                                <div class="form-group">
                                                    <input class="form-control" id="cartao_cpf" placeholder="CPF do titular" type="text" value="" />
                                                </div>
                                            </div>
                                            <div class="col-xl-2 col-md-2 col-sm-3 col-4">
                                                <div class="form-group">
                                                    <input class="form-control" id="cartao_data_nascimento" placeholder="Nascimento" type="text" value="" />
                                                </div>
                                            </div>
                                            <div class="col-xl-7 col-md-7 col-sm-7 col-6">
                                                <div class="form-group">
                                                    <input autocomplete="off" class="form-control" id="cartao_numero" placeholder="Número do Cartão" type="text" value="" />
                                                </div>
                                            </div>
                                            <div class="col-xl-3 col-md-3 col-sm-3 col-3">
                                                <div class="form-group">
                                                    <input autocomplete="off" class="form-control" id="cartao_validade" placeholder="Validade" type="text" value="" />
                                                </div>
                                            </div>
                                            <div class="col-xl-2 col-md-2 col-sm-2 col-3">
                                                <div class="form-group">
                                                    <input autocomplete="off" class="form-control" id="cartao_cvv" placeholder="CVV" type="text" value="" />
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <select id="cartao_bandeira" class="form-control">
                                                        <option>Visa</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <select id="cartao_parcelas" class="form-control hidden">
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="tab-boleto">
                                        <p>A confirmação do pagamento via Boleto bancário é automática e ocorre <strong>entre 48 e 72 horas</strong>. </p>
                                    </div>
                                    <div class="tab-pane" id="tab-debito">
                                        <p>O pagamento via débito em conta está disponível para os bancos: Bradesco, Banco do Brasil, Itaú e Banrisul. Clique no banco desejado e em seguida clique em PAGAR.</p>
                                        <ul id="bancos">
                                            <li><img class="img-banco" id="bradesco" src="img/bradesco.jpg" alt="Bradesco" title="Bradesco" /></li>
                                            <li><img class="img-banco selecionado" id="bancodobrasil" src="img/bancodobrasil.jpg" alt="Banco do Brasil" title="Banco do Brasil" /></li>
                                            <li><img class="img-banco" id="itau" src="img/itau.jpg" alt="Itaú" title="Itaú" /></li>
                                            <li><img class="img-banco" id="banrisul" src="img/banrisul.jpg" alt="Banrisul" title="Banrisul" /></li>
                                        </ul>
                                    </div>
                                     <div class="tab-pane" id="tab-end">
                                        <div class="container">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label for="exampleInputEmail1">Digite Seu CEP</label>
                                                    <input type="text" class="form-control" id="cep" placeholder="Seu cep" onblur="pesquisacep(this.value);">
                                                    <small class="form-text text-muted"><a href="http://www.consultaenderecos.com.br/busca-endereco" target="_blank">Não sei meu CEP</a></small>
                                                </div>
                                                <button onclick="calculo();" class="btn btn-success">Calcular</button>
                                            </div>
                                            <div class="col-md-4" id="retorno">

                                            </div>
                                        </div>
                                        </div>                                
                                        <!-- Inicio do formulario -->
                                        <form method="get" action=".">
                                            <!--<label>Cep:
                                            <input name="cep" type="text" id="cep" value="" size="10" maxlength="9"
                                                onblur="pesquisacep(this.value);" /></label><br />-->
                                            <br></br>     
                                            <label>Rua:
                                            <input name="rua" type="text" id="rua" size="60" /></label><br />
                                            <label>Bairro:
                                            <input name="bairro" type="text" id="bairro" size="40" /></label><br />
                                            <label>Cidade:
                                            <input name="cidade" type="text" id="cidade" size="40" /></label><br />
                                            <label>Estado:
                                            <input name="uf" type="text" id="uf" size="2" /></label><br />
                                            <label>Número:
                                            <input name="numero" type="text" id="numero" size="8" /></label><br />
                                        </form>
                                        
                                    </div>
                                </div>
                                <div>
                                <input type="hidden" id="id_transacao" value="<?php echo $id_transacao; ?>" />
                                <input type="hidden" id="total" value="<?php echo $total; ?>" />
                                <input type="hidden" id="descricao" value="Aqui vai a descrição do item comprado." />
                                <input type="hidden" id="forma_de_pagamento" value="Cartão" />
                                <input type="hidden" id="banco" value="bancodobrasil" />
                                <button type="submit" id="botao_pagar" class="btn bg-green btn-block"><span>Pagar</span></button>
                            </div>
                        </div>
                        <div class="footer mt-3 text-muted text-center">
                            <div id="div_logos_bandeiras"></div>
                            <div class="mt-2 c-both">
                                &copy;
                                <?php echo date("Y"); ?>. <a class="text-grey" href="https://gmtmarketplace.com.br">GMT Solutions</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
         <script>
      function calculo(){
        var cep = $("#cep").val();
        $.post('calcula.php',{cep:cep},function(data){
          $("#retorno").html(data);
        });
        //var retorno = parseInt(document.getElementById('retorno').innerHTML);
        //var valorCobrado = parseFloat(document.getElementById('valorCobradoJS').innerHTML);

        //document.getElementById('valorCobrado').innerHTML = (retorno + valorCobrado);
         //       alert(retorno + valorCobrado);
      }
    </script>
        <script>
    
    function limpa_formulário_cep() {
            //Limpa valores do formulário de cep.
            document.getElementById('rua').value=("");
            document.getElementById('bairro').value=("");
            document.getElementById('cidade').value=("");
            document.getElementById('uf').value=("");
    }

    function meu_callback(conteudo) {
        if (!("erro" in conteudo)) {
            //Atualiza os campos com os valores.
            document.getElementById('rua').value=(conteudo.logradouro);
            document.getElementById('bairro').value=(conteudo.bairro);
            document.getElementById('cidade').value=(conteudo.localidade);
            document.getElementById('uf').value=(conteudo.uf);
        } //end if.
        else {
            //CEP não Encontrado.
            limpa_formulário_cep();
            alert("CEP não encontrado.");
        }
    }
        
    function pesquisacep(valor) {

        //Nova variável "cep" somente com dígitos.
        var cep = valor.replace(/\D/g, '');

        //Verifica se campo cep possui valor informado.
        if (cep != "") {

            //Expressão regular para validar o CEP.
            var validacep = /^[0-9]{8}$/;

            //Valida o formato do CEP.
            if(validacep.test(cep)) {

                //Preenche os campos com "..." enquanto consulta webservice.
                document.getElementById('rua').value="...";
                document.getElementById('bairro').value="...";
                document.getElementById('cidade').value="...";
                document.getElementById('uf').value="...";

                //Cria um elemento javascript.
                var script = document.createElement('script');

                //Sincroniza com o callback.
                script.src = 'https://viacep.com.br/ws/'+ cep + '/json/?callback=meu_callback';

                //Insere script no documento e carrega o conteúdo.
                document.body.appendChild(script);

            } //end if.
            else {
                //cep é inválido.
                limpa_formulário_cep();
                alert("Formato de CEP inválido.");
            }
        } //end if.
        else {
            //cep sem valor, limpa formulário.
            limpa_formulário_cep();
        }
    };
    
    </script>
    
    

    
    
        <script type="text/javascript" src="<?php echo $pagseguro_url_js; ?>"></script>
        <script type="text/javascript" src="js/numeral.min.js"></script>
        <script type="text/javascript" src="js/jquery.inputmask.bundle.min.js"></script>
        <script type="text/javascript" src="js/pagseguro.js"></script>
    </body>

    </html>
