package dao;

import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class SistemaDAO {
	
	String caminho;
	String texto;
	
	public boolean gerarArq(String caminho, String texto) {
		try {
			FileWriter arq = new FileWriter(caminho);
			PrintWriter gravarArq = new PrintWriter(arq);
			gravarArq.println("# 									 AJUDA	 				 				   #");
			gravarArq.println("#                                                                               #");
			gravarArq.println("#  ESSE PROGRAMA TEM A FUN��O DE DISPONIBILIZAR ACESSO AO BANCO DE DADOS E SUAS #");
			gravarArq.println("# POSS�VEIS ALTERA��ES. BASEADO NA LINGUAGEM DE PROGRAMA��O JAVA, O SISTEMA TEM #");
			gravarArq.println("# FUNCIONALIDADES ACESS�VEIS POR MEIO DA BARRA DE TAREFAS LOCALIZADO NA REGI�O  #");
			gravarArq.println("# SUPERIOR DA TELA.                                                             #");
			gravarArq.println("#                                                                               #");
			gravarArq.println("#  O MENU:                                                                      #");
			gravarArq.println("#  NO MENU PODEMOS LOCALIZAR QUATRO T�PICOS DE TRABALHOS, NA QUAL CADA UM DELES #");
			gravarArq.println("# POSSUI SUA DETERMINADA FUN��O. A SEGUIR EXPLICAREMOS A FUN��O DOS T�PICOS, NA #");
			gravarArq.println("# QUAL EST�O DIVIDOS EM:                                                        #");
			gravarArq.println("#                                                                               #");
			gravarArq.println("#  1- CADASTRO                                                                  #");
			gravarArq.println("#  O CADASTRO � UM T�PICO ESPEC�FICO PARA REALIZAR QUALQUER TIPO DE CADASTRO NO #");
			gravarArq.println("# BANCO DE DADOS. ESTE T�PICO POSSUI SUBT�PICOS, SENDO ESTES:                   #");
			gravarArq.println("#                                                                               #");
			gravarArq.println("#  1.1- CADASTRAR LIVRO                                                         #");
			gravarArq.println("#  ESSE T�PICO REALIZA A FUN��O DE CADASTRAR UM LIVRO NO BANCO DE DADOS. BASTA  #");
			gravarArq.println("# PREENCHER TODOS OS CAMPOS CORRETAMENTES E CLICAR EM CONCLUIR QUE O LIVRO SER� #");
			gravarArq.println("# INCLUSO NO BANCO DE DADOS. VALE RESSALTAR QUE PARA O CADASTRO DO LIVRO �      #");
			gravarArq.println("# OBRIGAT�RIO A SELE��O DE UM FORNECEDOR PARA QUE O CADASTRO SEJA CONCLUIDO COM #");
			gravarArq.println("# PERFEI��O.                                                                    #");
			gravarArq.println("#                                                                               #");
			gravarArq.println("#  1.2- CADASTRAR FORNECEDOR                                                    #");
			gravarArq.println("#  ESSE T�PICO REALIZA A FUN��O DE CADASTRAR UM FORNECEDOR NO BANCO DE DADOS. � #");
			gravarArq.println("# S� PREENCHER TODOS OS CAMPOS CORRETAMENTE E CLICAR EM CONCLUIR QUE O TODOS OS #");
			gravarArq.println("# DADOS DO FORNECEDOR SER�O SALVOS.                                             #");
			gravarArq.println("#                                                                               #");
			gravarArq.println("#  2- COMPRAS                                                                   #");
			gravarArq.println("#  NESSE T�PICO, POSSUI APENAS UM SUBT�PICO, SENDO ELE O GERAR COMPRA, E ESTE,  #");
			gravarArq.println("# TEM A FUN��O DE INSERIR NO BANCO DE DADOS MA COMPRA REALIZADA. PARA TAL A��O  #");
			gravarArq.println("# BASTA QUE SEJA SELECIONADO UM FORNECEDOR EXISTENTE E UM LIVRO EXISTENTE. CASO #");
			gravarArq.println("# N�O EXISTA NENHUM LIVRO OU FORNECEDOR CADASTRADO NO BANCO, OBRIGATORIAMENTE   #");
			gravarArq.println("# EXIGE-SE QUE SEJA CADASTRADO UM FORNECEDOR E UM LIVRO RESPECTIVAMENTE, E POR  #");
			gravarArq.println("# FIM, SEJA GERADO UMA COMPRA.                                                  #");
			gravarArq.println("#  NO FINAL DE UMA COMPRA, � EMITIDO UMA NOTA FISCAL, E ESTE ARQUIVO � SALVO NA #");
			gravarArq.println("# PR�PRIA PASTA DO PROGRAMA.                                                    #");
			gravarArq.println("#                                                                               #");
			gravarArq.println("#  3-LISTAR                                                                     #");
			gravarArq.println("#  ESSE T�PICO TEM A FUN��O DE APRESENTAR EM UMA TABELA ALGUMAS INFORMA��ES QUE #");
			gravarArq.println("# ES�O SALVAS NO BANCO DE DADOS REFERENTES A CADA SUBTOPICO, SENDO ELES:        #");
			gravarArq.println("#   - LISTAR LIVROS                                                             #");
			gravarArq.println("#   LISTA TODOS OS LIVROS CADASTRADOS NO BANCO DE DADOS, ORDENADOS PELO C�DIGO  #");
			gravarArq.println("#  DE IDENTIFICA��O. AS COLUNAS DA TABELA S�O: C�DIGO; T�TULO; QUANTIDADE.      #");
			gravarArq.println("#   ESSA TELA POSSUI TR�S BOT�ES: DELETAR(APAGA O LIVRO SELECIONADO NA TABELA   #");
			gravarArq.println("#  DO BANCO DE DADOS); ATUALIZAR(ATUALIZA O LIVRO SELECIONADO NA TABELA DO      #");
			gravarArq.println("#  BANCO DE DADOS); E GERAR ARQUIVO(GERA UM ARQUIVO TEXTO, SALVO NA PR�PRIA     #");
			gravarArq.println("#  PASTA DO PROGRAMA, COM TODAS AS INFORMA��ES DE TODOS OS LIVROS CADASTRADOS   #");
			gravarArq.println("#  NO BANCO DE DADOS.                                                           #");
			gravarArq.println("#                                                                               #");
			gravarArq.println("#   - LISTAR FORNECEDOR                                                         #");
			gravarArq.println("#   LISTA TODOS OS FORNECEDORES CADASTRADOS NO BANCO DE DADOS, ORDENADOS PELO   #");
			gravarArq.println("#  C�DIGO DE IDENTIFICA��O. AS COLUNAS DA TABELA S�O: ID; NOME FORNECEDOR;      #");
			gravarArq.println("#  TELEFONE; E-MAIL.                                                            #");
			gravarArq.println("#   ESSA TELA POSSUI TR�S BOT�ES: DELETAR(APAGA O FORNECEDOR SELECIONADO NA     #");
			gravarArq.println("#  TABELA DO BANCO DE DADOS); ATUALIZAR(ATUALIZA O FORNECEDOR SELECIONADO NA    #");
			gravarArq.println("#  TABELA DO BANCO DE DADOS COM AS NOVAS INFORMA��ES PASSADAS); E GERAR ARQUIVO #");
			gravarArq.println("#  (GERA UM ARQUIVO TEXTO, SALVO NA PR�PRIA PASTA DO PROGRAMA, COM TODAS AS     #");
			gravarArq.println("#  INFORMA��ES DE TODOS OS FORNECEDORES CADASTRADOS NO BANCO DE DADOS.          #");
			gravarArq.println("#                                                                               #");
			gravarArq.println("#   - LISTAR CLIENTES                                                           #");
			gravarArq.println("#   LISTA TODOS OS CLIENTES CADASTRADOS NO BANCO DE DADOS, ORDENADOS PELO       #");
			gravarArq.println("#  C�DIGO DE IDENTIFICA��O. AS COLUNAS DA TABELA S�O: ID; NOME; CPF; CELULAR.   #");
			gravarArq.println("#   ESSA TELA POSSUI DOIS BOT�ES: DELETAR(APAGA O CLIENTE SELECIONADO NA TABELA #");
			gravarArq.println("#  DO BANCO DE DADOS) E GERAR ARQUIVO(GERA UM ARQUIVO TEXTO, SALVO NA PR�PRIA   #");
			gravarArq.println("#  PASTA DO PROGRAMA, COM TODAS AS INFORMA��ES DE TODOS OS CLIENTES CADASTRADOS #");
			gravarArq.println("#  NO BANCO DE DADOS. #");
			
			gravarArq.close();
			return true;
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO:"+e.getMessage());
			return false;
		}
	}
}
