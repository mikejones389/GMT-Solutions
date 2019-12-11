<?php require_once "i_topo.php"; ?>
					
	<!-- Product -->
	<div class="bg0 m-t-23 p-b-140">
		<div class="container">
			<div class="flex-w flex-sb-m p-b-52">
				<div class="flex-w flex-l-m filter-tope-group m-tb-10">
					<a href="product.php?genero=0" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6"> Todos os produtos </a>
					&nbsp;&nbsp;&nbsp;
					<a href="product.php?genero=1" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6"> Ficção </a>
					&nbsp;&nbsp;&nbsp;
					<a href="product.php?genero=2" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6"> Romance </a>
					&nbsp;&nbsp;&nbsp;
					<a href="product.php?genero=4" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6"> Literatura brasileira </a>	
				</div>
				<form method="GET"  class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
					<input class="plh3" type="text" name="search" id="search" placeholder="Search..."   >
						<button >
							<i class="zmdi zmdi-search" ></i>
							</a>
						</button>				
				</form>
			</div>
			<div class="row isotope-grid">
			
<?php

	$busca = $_GET['search'];
	if($busca == null){
    	$query = "select * from livro ";
	}
	else{
		$query = "select * from livro where nm_livro like '%".$busca."%' ";
	}

	$result = mysqli_query($db,$query);
	$num_results = mysqli_num_rows($result);

	if($num_results == 0){
    	$query = "select * from livro ";
	    $result = mysqli_query($db,$query);
	    $num_results = mysqli_num_rows($result);
	}
	for ($i=0; $i <$num_results; $i++)
	{
	$row = mysqli_fetch_array($result);
	
?>
            
                <div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item women">
                    <!-- Block2 -->
                    <div class="block2">
					<div class="block2-pic hov-img0">
							<img src="images/<?php  echo $row['link_img']; ?>" alt="IMG-PRODUCT">
							<?php
								if (empty($_SESSION)){
							?>			
								<a href="indexLogin.php"    class="block2-btn flex-c-m stext-103 cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04 js-addcart-detail">Carrinho</a>
							<?php
								}else{
							?>			
							<a href="montarCarrinho.php?livro=<?php echo $row['cd_livro'];?>" class="block2-btn flex-c-m stext-103 cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04 js-addcart-detail">
								Carrinho
							</a>
							<?php
								}
							?>			
						</div>
                        <div class="block2-txt flex-w flex-t p-t-14">
                            <div class="block2-txt-child1 flex-col-l ">
                                <a class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                	<?php  echo $row['nm_livro']; ?>
                                </a>
                                <span class="stext-105 cl3">
                                	<?php  echo $row['preco_venda']; ?>
                                </span>
                            </div>
                            <div class="block2-txt-child2 flex-r p-t-3">
                                <form method="post" action="cadastroDesejo.php" name="frmListaDesejo" id="frmListaDesejo"> 
                                    <input type="hidden" name="cdLivro" id="cdLivro" value="<?php echo $row['cd_livro'];?>">
                                    <input type="hidden" name="cd_usuario" id="cd_usuario" value="<?php echo $_SESSION['cd_usuario'];?>">
                                    <?php	
                                        if(in_array($row['cd_livro'],$listaItensDesejosSelecionados)){
                                            ?>
                                            <input type="hidden" name="acao" id="acao" value="deletar">
                                            <button type="submit" name="enviar" >  <img src="images/icons/icon-heart-02.png"></button>
                                            <!--<button type="submit" name="enviar" >  <img src="images/icons/icon-cart-01.png"></button>-->
                                            <?php
                                        }else{
                                            ?>
                                            <input type="hidden" name="acao" id="acao" value="adcionar">
                                            <button type="submit" name="enviar" > <img src="images/icons/icon-heart-01.png"></button>
                                            <?php
										}  
                                    ?>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

<?php 
	}
		mysqli_close($db);
?>

			</div>
		</div>
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
	<script src="vendor/daterangepicker/moment.min.js"></script>
	<script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="vendor/slick/slick.min.js"></script>
	<script src="js/slick-custom.js"></script>
<!--===============================================================================================-->
	<script src="vendor/parallax100/parallax100.js"></script>
	<script>
        $('.parallax100').parallax100();
	</script>
<!--===============================================================================================-->
	<script src="vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
	<script>
		$('.gallery-lb').each(function() { // the containers for all your galleries
			$(this).magnificPopup({
		        delegate: 'a', // the selector for gallery item
		        type: 'image',
		        gallery: {
		        	enabled:true
		        },
		        mainClass: 'mfp-fade'
		    });
		});
	</script>
<!--===============================================================================================-->
	<script src="vendor/isotope/isotope.pkgd.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/sweetalert/sweetalert.min.js"></script>
	<script>
		$('.js-addwish-b2, .js-addwish-detail').on('click', function(e){
			e.preventDefault();
		});

		$('.js-addwish-b2').each(function(){
			var nameProduct = $(this).parent().parent().find('.js-name-b2').html();		
			$(this).on('click', function(){
				swal(nameProduct, "is added to wishlist !", "success");
				$(this).addClass('js-addedwish-b2');
				$(this).off('click');
			});
		});

		$('.js-addwish-detail').each(function(){
			var nameProduct = $(this).parent().parent().parent().find('.js-name-detail').html();

			$(this).on('click', function(){
				swal(nameProduct, "is added to wishlist !", "success");

				$(this).addClass('js-addedwish-detail');
				$(this).off('click');
			});
		});

		/*---------------------------------------------*/

		$('.js-addcart-detail').each(function(){
			var nameProduct = $(this).parent().parent().parent().parent().find('.js-name-detail').html();
			$(this).on('click', function(){
				swal(nameProduct, "is added to cart !", "success");
			});
		});
	
	</script>
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

</body>
</html>