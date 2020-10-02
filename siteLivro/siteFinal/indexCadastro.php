<!DOCTYPE html>
<html lang="pt-br">
<head>
	<title>Cadastro GMT Store</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
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
	<link rel="stylesheet" type="text/css" href="cssLogin/util.css">
	<link rel="stylesheet" type="text/css" href="cssLogin/main.css">
<!--===============================================================================================-->
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
			
				<form action="apiCadastrar.php" class="login100-form validate-form flex-sb flex-w" method="post">
					<span class="login100-form-title p-b-32">
						Novo Cadastro
					</span>
					<span class="txt1 p-b-11">
						Nome Completo
					</span>
					<div class="wrap-input100 validate-input m-b-36" data-validate = "Nome is required">
						<input class="input100" type="text" name="api_nm_usuario" >
						<span class="focus-input100"></span>
					</div>
					<span class="txt1 p-b-11">
						CPF
					</span>
					<div class="wrap-input100 validate-input m-b-36" data-validate = "Nome
					 is required">
						<input class="input100" type="number" name="api_cpf" >
						<span class="focus-input100"></span>
					</div>
					<span class="txt1 p-b-11">
						Genero
					</span>
					<br></br>
					<select class="wrap-input100 validate-input m-b-36" name="api_sexo" id="sexo">
                        <option value="sexo">selecione</option>
                        <option value="Masculino">Masculino</option>
                        <option value="Feminino">Feminino</option>
                        <option value="Outros">Outros</option>
                      </select>
                      <span class="txt1 p-b-11">
						Data de Nascimento
					</span>
					<div class="wrap-input100 validate-input m-b-36" data-validate = "Nome is required">
					    <input oninput="mascara(this, 'data')" class="input100" type="text" name="api_dt_nasc"
					    " placeholder="Ex.: dd/mm/aaaa" autocomplete="off" name="customer['birthdate']">
					</div>
					
					<span class="txt1 p-b-11">
						E-mail 
					</span>
					<div class="wrap-input100 validate-input m-b-36" data-validate = "Nome is required">
						<input class="input100" type="text" name="api_email" >
						<span class="focus-input100"></span>
					</div>				
					<span class="txt1 p-b-11">
						Celular
					</span>
					<div class="wrap-input100 validate-input m-b-36" data-validate = "Nome is required">
						<input class="input100" type="number" name="api_celular" >
						<span class="focus-input100"></span>
					</div>	
					<span class="txt1 p-b-11">
						Login
					</span>
					<div class="wrap-input100 validate-input m-b-36" data-validate = "Nome is required">
						<input class="input100" type="text" name="api_login" >
						<span class="focus-input100"></span>
					</div>
					<span class="txt1 p-b-11">
						Senha 
					</span>
					<div class="wrap-input100 validate-input m-b-12" data-validate = "Password is required">
						<span class="btn-show-pass">
							<i class="fa fa-eye"></i>
						</span>
						<input class="input100" type="password" name="api_senha" >
						<span class="focus-input100"></span>
					</div>
					<div class="flex-sb-m w-full p-b-48">
						<div class="contact100-form-checkbox">
							<input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
						</div>
					</div>
					<div class="container-login100-form-btn">
						<button type="submit" class="login100-form-btn" name="api_token" value="cadastrar">
						    	Cadastrar
						</button>
						
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="dropDownSelect1"></div>
	
<!--===============================================================================================-->
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/daterangepicker/moment.min.js"></script>
	<script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="js/main.js"></script>
	<script>
	function mascara(i,t){
   
   var v = i.value;
   
   if(isNaN(v[v.length-1])){
      i.value = v.substring(0, v.length-1);
      return;
   }
   
   if(t == "data"){
      i.setAttribute("maxlength", "10");
      if (v.length == 2 || v.length == 5) i.value += "/";
   }

   if(t == "cpf"){
      i.setAttribute("maxlength", "14");
      if (v.length == 3 || v.length == 7) i.value += ".";
      if (v.length == 11) i.value += "-";
   }

   if(t == "cnpj"){
      i.setAttribute("maxlength", "18");
      if (v.length == 2 || v.length == 6) i.value += ".";
      if (v.length == 10) i.value += "/";
      if (v.length == 15) i.value += "-";
   }

   if(t == "cep"){
      i.setAttribute("maxlength", "9");
      if (v.length == 5) i.value += "-";
   }

   if(t == "tel"){
      if(v[0] == 9){
         i.setAttribute("maxlength", "10");
         if (v.length == 5) i.value += "-";
      }else{
         i.setAttribute("maxlength", "9");
         if (v.length == 4) i.value += "-";
      }
   }
}
</script>
	
	
	

</body>
</html>