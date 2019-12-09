<?php require_once "i_topo.php"; ?>

	<!-- Title page -->
	<section class="bg-img1 txt-center p-lr-15 p-tb-92" style="background-image: url('images/contato.png');">
		<h2 class="ltext-105 cl0 txt-center">
			Sobre
		</h2>
	</section>	
	<!-- Content page -->
	<section class="bg0 p-t-75 p-b-120">
		<div class="container">
			<div class="row p-b-148">
				<div class="col-md-7 col-lg-8">
					<div class="p-t-7 p-r-85 p-r-15-lg p-r-0-md">
						<h3 class="mtext-111 cl2 p-b-16">
							Nossa História
						</h3>
						<p class="stext-113 cl6 p-b-26">
							A GMT Group foi fundada em 2018 com um grupo de amigos do IFSP Campinas com o objetivo de impactar 
							o setor livreiro. No inicio de 2019, houve uma reformulação da compasição da equipe, que é atualmente 
							formada pelos Confudadores Gustavo Souza da Mota, Tiago Freitas Faustino e prlo Sócio-Proprietário 
							Mike Jones Cabrera da Silva.
						</p>
					</div>
				</div>

				<div class="col-11 col-md-5 col-lg-4 m-lr-auto">
					<div class="how-bor1 ">
						<div class="hov-img0">
							<img src="images/about-01.jpg" alt="IMG">
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="order-md-2 col-md-7 col-lg-8 p-b-30">
					<div class="p-t-7 p-l-85 p-l-15-lg p-l-0-md">
						<h3 class="mtext-111 cl2 p-b-16">
							Nossa Missão
						</h3>
						<p class="stext-113 cl6 p-b-26">
							A GMT Group tem como missão incluir autores autonômos no mercado livreiro, dando visibilidade
							para a venda de livros, pois esse mercado é constituido por um oligopólio de poucas editoras 
							que vem ditando as regras do mercado. 
						</p>
						<div class="bor16 p-l-29 p-b-9 m-t-22">
							<p class="stext-114 cl6 p-r-40 p-b-11">
								Creativity is just connecting things. When you ask creative people how they did something, they feel a little guilty because they didn't really do it, they just saw something. It seemed obvious to them after a while.
							</p>
							<span class="stext-111 cl8">
								- Steve Job’s 
							</span>
						</div>
					</div>
				</div>
				<div class="order-md-1 col-11 col-md-5 col-lg-4 m-lr-auto p-b-30">
					<div class="how-bor2">
						<div class="hov-img0">
							<img src="images/about-02.jpg" alt="IMG">
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>	
	<!-- Footer -->
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
	
</body>
</html>