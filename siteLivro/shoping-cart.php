<?php require_once "i_topo.php";
		$_SESSION['sessaoTotal'] = 0;
		$lista = array();
		if (!empty($_SESSION['listaItensCarrinhoSelecionados']) ){
		$lista = $_SESSION['listaItensCarrinhoSelecionados'];
	}
?>
	<!-- breadcrumb -->
	<div class="container">
		<div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
			<a href="index.php" class="stext-109 cl8 hov-cl1 trans-04">
				Home
				<i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
			</a>
			<span class="stext-109 cl4">
				Carrinho de Compra
			</span>
		</div>
	</div>
	<!-- Shoping Cart -->
	<form class="bg0 p-t-75 p-b-85">
		<div class="container">
			<div class="row">
				<div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
					<div class="m-l-25 m-r--38 m-lr-0-xl">
						<div class="wrap-table-shopping-cart">
							<table class="table-shopping-cart">
								<tr class="table_head">
									<th class="column-1">Produtos</th>
									<th class="column-2"></th>
									<!--<th class="column-3">Price</th>-->
									<th class="column-4">Quantidade</th>
									<th class="column-5">Total</th>
								</tr>
<?php

		$codigos = "";
	foreach ($lista as $item) {
		$codigos = $codigos .  $item. ", ";
	}
		$codigos = substr($codigos,0, -2);
	if (empty($codigos) ){
		$codigos=0;
	}
		$query = "select * from livro where cd_livro in (".$codigos.")";
		$result = mysqli_query($db,$query);
		$num_results = mysqli_num_rows($result);
	for ($i=0; $i <$num_results; $i++)
		{
		$row = mysqli_fetch_array($result);
?>

								<tr class="table_row">
									<td class="column-1">
										<div class="how-itemcart1">
											<img src="images/<?php  echo $row['link_img']; ?>" alt="IMG-PRODUCT">
										</div>
									</td>
									<td class="column-2"><?php  echo $row['nm_livro']; ?></td>
									<!--<td class="column-3">?php  echo $row['preco_venda']; ?></td>-->
									<td class="column-4">
										<div class="wrap-num-product flex-w m-l-auto m-r-0">
											<div class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m" onclick="carrinhoSubValor(<?php  echo $row['cd_livro']; ?>,<?php  echo $row['preco_venda']; ?>);">
												<i class="fs-16 zmdi zmdi-minus"></i>
											</div>
											<input class="mtext-104 cl3 txt-center num-product" type="number" name="num-product<?php  echo $row['cd_livro']; ?>" id="num-product<?php  echo $row['cd_livro']; ?>" value="0">
												<?php 
													$codigoFinal="valor".$row['cd_livro'];
												?>
											<div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m" onclick="carrinhoAdValor(<?php  echo $row['cd_livro']; ?>,<?php  echo $row['preco_venda']; ?>);">
												<i class="fs-16 zmdi zmdi-plus"></i>
											</div>
										</div>
									</td>
									<td class="column-5" id="valor<?php  echo $row['cd_livro']; ?>"></td>
								</tr>

								<?php
									}
								?>

							</table>
						</div>
						<div class="flex-w flex-sb-m bor15 p-t-18 p-b-15 p-lr-40 p-lr-15-sm">
							<div class="flex-w flex-m m-r-20 m-tb-5">
								<input class="stext-104 cl2 plh4 size-117 bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="coupon" placeholder="Código do Cupom">		
								<div class="flex-c-m stext-101 cl2 size-118 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-5">
									Aplicar
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50">
					<div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
						<h4 class="mtext-109 cl2 p-b-30">
							Total
						</h4>
						<div class="flex-w flex-t bor12 p-b-13">
							<div class="size-208">
								<span class="stext-110 cl2">
									Subtotal:
								</span>
							</div>
							<div class="size-209">
								<span class="mtext-110 cl2">
								<input type="text" readonly="readonly"  id="subTotal"value="0">
								</span>
							</div>
						</div>
						<div class="p-t-15">	
							<div class="bor8 bg0 m-b-22">
								<input class="stext-111 cl8 plh3 size-111 p-lr-15" type="text" id="cep" name="cep" placeholder="CEP">
							</div>
							<div class="size-208">
								<span class="stext-110 cl2">
									Frete: <input type="text" readonly="readonly"  id="frete" name="frete" value="0" >
								</span>
							</div>
							<div class="size-208">
								<span class="stext-110 cl2">
								</span>
							</div>
							<div class="size-209">
								<span class="mtext-110 cl2">
									&nbsp;
								</span>
							</div>
						</div>
						<div class="flex-w">
							<div class="flex-c-m stext-101 cl2 size-115 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer">
								<a href="#" onClick="calcularFrete();"> Calcular Frete</a>
							</div>
						</div>
						<div class="size-209">
							<span class="mtext-110 cl2">
								&nbsp;
							</span>
						</div>
						<div class="panel panel-info">
                        <h4 class="mtext-109 cl2 p-b-30">
							Pagamento
						</h4>
                        <div class="panel-body">
                            <div class="form-group">
                                <div class="col-md-12"><strong>Cartão:</strong></div>
                                <div class="col-md-12">
                                    <select id="CreditCardType" name="CreditCardType" class="form-control">
                                        <option value="5">Crédito</option>
                                        <option value="6">Débito</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12"><strong>Número do Cartão de Crédito/Débito:</strong></div>
                                <div class="col-md-12"><input type="tel"  class="form-control" id="cc" name="cc" maxlength="19" /></div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12"><strong>Código de Segurança:</strong></div>
                                <div class="col-md-12"><input type="text" class="form-control" name="car_code" maxlength="3" /></div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12">
                                    <strong>Data de Expiração</strong>
                                </div>
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                    <select class="form-control" name="">
                                        <option value="">Mês</option>
                                        <option value="01">01</option>
                                        <option value="02">02</option>
                                        <option value="03">03</option>
                                        <option value="04">04</option>
                                        <option value="05">05</option>
                                        <option value="06">06</option>
                                        <option value="07">07</option>
                                        <option value="08">08</option>
                                        <option value="09">09</option>
                                        <option value="10">10</option>
                                        <option value="11">11</option>
                                        <option value="12">12</option>
                                </select>
                                </div>
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                    <select class="form-control" name="">
                                        <option value="">Ano</option>
                                        <option value="2019">2019</option>
                                        <option value="2020">2020</option>
                                        <option value="2021">2021</option>
                                        <option value="2022">2022</option>
                                        <option value="2023">2023</option>
                                        <option value="2024">2024</option>
                                        <option value="2025">2025</option>
                                	</select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12">
                                    <span>Pague seguro usando seu cartão.</span>
                                </div>
                                <div class="col-md-12">
                                    <ul class="cards">
                                        <li >
											<a href="#" class="m-all-1">
												<img src="images/icons/icon-pay-02.png" alt="ICON-PAY">
											</a>
												Visa
										</li>
                                        <li class="mastercard hand">
											<a href="#" class="m-all-1">
												<img src="images/icons/icon-pay-03.png" alt="ICON-PAY">
											</a>
												MasterCard
										</li>
                                        <li class="amex hand">
											<a href="#" class="m-all-1">
												<img src="images/icons/icon-pay-01.png" alt="ICON-PAY">
											</a>	
												PayPal
										</li>
                                    </ul>
                                    <div class="clearfix"></div>
                                </div>
                            </div>
                        </div>
                    </div>
						<div class="flex-w flex-t p-t-27 p-b-33">
							<div class="size-208">
								<span class="mtext-101 cl2">
									Total:
								</span>
							</div>
							<div class="size-209 p-t-1">
								<input type="text" readonly="readonly"  id="somaTotal"value="0">
							</div>
						</div>
						<button class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer">
							Finalizar Compra
						</button>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>	

	<?php
		require_once "i_footer.php";
	?>

<!--===============================================================================================-->	
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
	<script>
		$(".js-select2").each(function(){
			$(this).select2({
				minimumResultsForSearch: 20,
				dropdownParent: $(this).next('.dropDownSelect2')
			});
		})
	</script>
<!--===============================================================================================-->
	<script src="vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script>
		$('.js-pscroll').each(function(){
			$(this).css('position','relative');
			$(this).css('overflow','hidden');
			var ps = new PerfectScrollbar(this, {
				wheelSpeed: 1,
				scrollingThreshold: 1000,
				wheelPropagation: false,
			});

			$(window).on('resize', function(){
				ps.update();
			})
		});
	</script>
<!--===============================================================================================-->
	<script src="js/main.js"></script>

	<script>

		function calcularFrete(){
	
				cep=document.getElementById("cep").value;

				valor = 1;
				if(cep == "13142134"){
					valor = "10.99";
				}else if(cep == "13068211") {
					valor = "15.99";
				}else{
					valor = "20.00";
				}

				document.getElementById("frete").value=valor;
				calculaValorTotal(valor);
		}

		function calculaValorTotal(valor){
			
			subTotal=document.getElementById("subTotal").value;
			total = parseFloat(subTotal) + parseFloat(valor);
			document.getElementById("somaTotal").value=total;
			}

		function carrinhoAdValor(codigo,valor){
				input= "num-product"+codigo;
				qtd=document.getElementById(input).value;
				
					qtd++;
				
				soma= valor*qtd;
				codigoSoma= "valor"+codigo;
				document.getElementById(codigoSoma).innerHTML=soma;
				calculaAdTotal(valor,qtd, soma);
		}

		function carrinhoSubValor(codigo,valor){
				input= "num-product"+codigo;
				qtd=document.getElementById(input).value;

					if (qtd > 0) {
						qtd--;
					}
									
				soma= valor*qtd;
				codigoSoma= "valor"+codigo;
				document.getElementById(codigoSoma).innerHTML=soma;
				calculaSubTotal(valor,qtd, soma);
		}

		function calculaAdTotal(valor,qtd, soma){
				
				temp = document.getElementById("subTotal").value;

				if (qtd > 0){
					qtdLivroAnterior = (qtd - 1) * valor; 
				}else{
					qtdLivroAnterior = 0;
				}	
					total = (parseFloat(soma) - qtdLivroAnterior) + parseFloat(temp) ;
					document.getElementById("subTotal").value=total;
		}

		function calculaSubTotal(valor,qtd, soma){
				
				temp = document.getElementById("subTotal").value;

				if (qtd > 0){
					qtdLivroAnterior = (qtd - 1) * valor; 
				}else{
					qtdLivroAnterior = 0;
				}	
					total = parseFloat(temp) - (parseFloat(soma) - qtdLivroAnterior)  ;
					document.getElementById("subTotal").value=total;
		}

		function mascara(o,f){
    		
				v_obj=o
    			v_fun=f
    			setTimeout("execmascara()",1)
		}

		function execmascara(){
    
				v_obj.value=v_fun(v_obj.value)
		}

		function mcc(v){

				  v = v.replace(/\D/g,""); // Permite apenas dígitos
				  v = v.replace(/(\d{4})/g, "$1."); // Coloca um ponto a cada 4 caracteres
				  v = v.replace(/\.$/, ""); // Remove o ponto se estiver sobrando
				  v = v.substring(0, 19)// Limita o tamanho
				  return v;
		}

		function id( el ){
	
				return document.getElementById( el );
		}

		window.onload = function(){
			id('cc').onkeypress = function(){
			mascara( this, mcc );
			}
		}
	</script>

</body>
</html>