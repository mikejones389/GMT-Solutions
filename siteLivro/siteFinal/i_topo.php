	<?php
		if(! isset($_SESSION)){
			session_start();
		}
		//echo substr(md5("123"),0,15);
		//exit;
	?>

<!DOCTYPE html>
<html lang="pt-br">
<head>
	<title>GMT Store</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="images/icons/favicon.png"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/linearicons-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/slick/slick.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/MagnificPopup/magnific-popup.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->
</head>
<body class="animsition">
	<!-- Header -->
	<header class="header-v4">
		<!-- Header desktop -->
		<div class="container-menu-desktop">
			<!-- Topbar -->
			<div class="top-bar">
				<div class="content-topbar flex-sb-m h-full container">
					<div class="left-top-bar">
					</div>
					<div class="right-top-bar flex-w h-full">
						<a target="_blank" href="FAQ.pdf" class="flex-c-m trans-04 p-lr-25">
							Ajuda & FAQs
						</a>

						<?php
							if (empty($_SESSION)){
						?>
						<a href="indexLogin.php" class="flex-c-m trans-04 p-lr-25">
							Minha conta 
						</a>
						<?php
							}else {
						?>

						<a href="sairSessao.php" class="flex-c-m trans-04 p-lr-25">
							Bem vindo, <?php echo $_SESSION['nm_usuario'];?>. Clique aqui para Sair.
						</a>
										
						<?php
							}
						?>
						
						<a href="#" class="flex-c-m trans-04 p-lr-25">
							BRL
						</a>
					</div>
				</div>
			</div>
			<div class="wrap-menu-desktop how-shadow1">
				<nav class="limiter-menu-desktop container">	
					<!-- Logo desktop -->		
					<a href="#" class="logo">
						<img src="images/icons/gmtstore.png" alt="IMG-LOGO">
					</a>
					<!-- Menu desktop -->
					<div class="menu-desktop">
						<ul class="main-menu">
							<li>
								<a href="product.php?genero=0">Shop</a>
							</li>
							<!--<li class="label1" data-label1="hot">

							<?php
								if (empty($_SESSION)){
							?>			
							
								<a href="indexLogin.php"    >Carrinho</a>
							
							<?php
								}else{
							?>			
							
								<a href="shoping-cart.php"    >Carrinho</a>
							


							<?php
								}
							?>			
							
							
							</li>-->
							<li>
								<a href="about.php">Sobre</a>
							</li>
							<li>
								<a href="contact.php">Contato</a>
							</li>
						</ul>
					</div>	
			<!-- Icon header -->
			<div class="wrap-icon-header flex-w flex-r-m">
						
				<?php
					if (empty($_SESSION)){
				?>

				<a href="indexLogin.php" class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti js-show-cart2" data-notify="0">
					<i class="zmdi zmdi-favorite-outline"></i>
				</a>

				<?php
					}else {
						require_once "conexaoBD.php";
							$query = "SELECT * FROM item_desejado i inner join livro l on i.cd_livro=l.cd_livro where i.cd_usuario=".$_SESSION['cd_usuario'];
							$result = mysqli_query($db,$query);
							$total = 0;
							$num_results = mysqli_num_rows($result);
				?>

				<a href="#" class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti js-show-cart2" data-notify="<?php echo $num_results; ?>">
					<i class="zmdi zmdi-favorite-outline"></i>
				</a>

				<?php
					}
				?>

					</div>
				</nav>
			</div>	
		</div>		
	</header>
	
	<!-- wishlist -->
	<div class="wrap-header-cart js-panel-cart2">
		<div class="s-full js-hide-cart2"></div>
			<div class="header-cart flex-col-l p-l-65 p-r-25">
				<div class="header-cart-title flex-w flex-sb-m p-b-8">
					<span class="mtext-103 cl2">
					
						<?php
							if (empty($_SESSION)){

						?>
							Usuário não logado			
						<?php
							}else {
						?>
							Seu carrinho, <?php echo $_SESSION['nm_usuario']; ?>			
						<?php
							}
						?>

					</span>

				<div class="fs-35 lh-10 cl2 p-lr-5 pointer hov-cl1 trans-04 js-hide-cart2">
					<i class="zmdi zmdi-close"></i>
				</div>
			</div>
			<div class="header-cart-content flex-w js-pscroll">
				<ul class="header-cart-wrapitem w-full">
			
					<?php
						require_once "conexaoBD.php";
						$listaItensDesejosSelecionados = array();
						$query = "SELECT * FROM item_desejado i inner join livro l on i.cd_livro=l.cd_livro where i.cd_usuario=".$_SESSION['cd_usuario'];
						$result = mysqli_query($db,$query);
						$total = 0;
						$num_results = mysqli_num_rows($result);
					for ($i=0; $i <$num_results; $i++)
						{
							$row = mysqli_fetch_array($result);
							array_push($listaItensDesejosSelecionados, $row['cd_livro']);
					?>

					<li class="header-cart-item flex-w flex-t m-b-12">
						<div class="header-cart-item-img">
							<img src="images/<?php  echo $row['link_img']; ?>" alt="IMG-PRODUCT">
						</div>
						<div class="header-cart-item-txt p-t-8">
							<a href="#" class="header-cart-item-name m-b-18 hov-cl1 trans-04">
								<?php  echo $row['nm_livro']; ?>
							</a>
							<span class="header-cart-item-info">
								<?php  echo $row['preco_venda']; 
									$total += $row['preco_venda'];
								?>
							</span>
						</div>
					</li>

					<?php
						}
					?>

				</ul>
				<div class="w-full">
					<div class="header-cart-total w-full p-tb-40">
						Total: <?php echo $total; ?> 
					</div>
					<!--<div class="header-cart-buttons flex-w w-full">
						<a href="shoping-cart.php" class="flex-c-m stext-101 cl0 size-107 bg3 bor2 hov-btn3 p-lr-15 trans-04 m-r-8 m-b-10">
							View Cart
						</a>

						<a href="shoping-cart.php" class="flex-c-m stext-101 cl0 size-107 bg3 bor2 hov-btn3 p-lr-15 trans-04 m-b-10">
							Check Out
						</a>
					</div>-->
				</div>
			</div>
		</div>
	</div>
	





	