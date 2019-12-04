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
			gravarArq.println("#  ESSE PROGRAMA TEM A FUNÇÃO DE DISPONIBILIZAR ACESSO AO BANCO DE DADOS E SUAS #");
			gravarArq.println("# POSSÍVEIS ALTERAÇÕES. BASEADO NA LINGUAGEM DE PROGRAMAÇÃO JAVA, O SISTEMA TEM #");
			gravarArq.println("# FUNCIONALIDADES ACESSÍVEIS POR MEIO DA BARRA DE TAREFAS LOCALIZADO NA REGIÃO  #");
			gravarArq.println("# SUPERIOR DA TELA.                                                             #");
			gravarArq.println("#                                                                               #");
			gravarArq.println("#  O MENU:                                                                      #");
			gravarArq.println("#  NO MENU PODEMOS LOCALIZAR QUATRO TÓPICOS DE TRABALHOS, NA QUAL CADA UM DELES #");
			gravarArq.println("# POSSUI SUA DETERMINADA FUNÇÃO. A SEGUIR EXPLICAREMOS A FUNÇÃO DOS TÓPICOS, NA #");
			gravarArq.println("# QUAL ESTÃO DIVIDOS EM:                                                        #");
			gravarArq.println("#                                                                               #");
			gravarArq.println("#  1- CADASTRO                                                                  #");
			gravarArq.println("#  O CADASTRO É UM TÓPICO ESPECÍFICO PARA REALIZAR QUALQUER TIPO DE CADASTRO NO #");
			gravarArq.println("# BANCO DE DADOS. ESTE TÓPICO POSSUI SUBTÓPICOS, SENDO ESTES:                   #");
			gravarArq.println("#                                                                               #");
			gravarArq.println("#  1.1- CADASTRAR LIVRO                                                         #");
			gravarArq.println("#  ESSE TÓPICO REALIZA A FUNÇÃO DE CADASTRAR UM LIVRO NO BANCO DE DADOS. BASTA  #");
			gravarArq.println("# PREENCHER TODOS OS CAMPOS CORRETAMENTES E CLICAR EM CONCLUIR QUE O LIVRO SERÁ #");
			gravarArq.println("# INCLUSO NO BANCO DE DADOS. VALE RESSALTAR QUE PARA O CADASTRO DO LIVRO É      #");
			gravarArq.println("# OBRIGATÓRIO A SELEÇÃO DE UM FORNECEDOR PARA QUE O CADASTRO SEJA CONCLUIDO COM #");
			gravarArq.println("# PERFEIÇÃO.                                                                    #");
			gravarArq.println("#                                                                               #");
			gravarArq.println("#  1.2- CADASTRAR FORNECEDOR                                                    #");
			gravarArq.println("#  ESSE TÓPICO REALIZA A FUNÇÃO DE CADASTRAR UM FORNECEDOR NO BANCO DE DADOS. É #");
			gravarArq.println("# SÓ PREENCHER TODOS OS CAMPOS CORRETAMENTE E CLICAR EM CONCLUIR QUE O TODOS OS #");
			gravarArq.println("# DADOS DO FORNECEDOR SERÃO SALVOS.                                             #");
			gravarArq.println("#                                                                               #");
			gravarArq.println("#  2- COMPRAS                                                                   #");
			gravarArq.println("#  NESSE TÓPICO, POSSUI APENAS UM SUBTÓPICO, SENDO ELE O GERAR COMPRA, E ESTE,  #");
			gravarArq.println("# TEM A FUNÇÃO DE INSERIR NO BANCO DE DADOS MA COMPRA REALIZADA. PARA TAL AÇÃO  #");
			gravarArq.println("# BASTA QUE SEJA SELECIONADO UM FORNECEDOR EXISTENTE E UM LIVRO EXISTENTE. CASO #");
			gravarArq.println("# NÃO EXISTA NENHUM LIVRO OU FORNECEDOR CADASTRADO NO BANCO, OBRIGATORIAMENTE   #");
			gravarArq.println("# EXIGE-SE QUE SEJA CADASTRADO UM FORNECEDOR E UM LIVRO RESPECTIVAMENTE, E POR  #");
			gravarArq.println("# FIM, SEJA GERADO UMA COMPRA.                                                  #");
			gravarArq.println("#  NO FINAL DE UMA COMPRA, É EMITIDO UMA NOTA FISCAL, E ESTE ARQUIVO É SALVO NA #");
			gravarArq.println("# PRÓPRIA PASTA DO PROGRAMA.                                                    #");
			gravarArq.println("#                                                                               #");
			gravarArq.println("#  3-LISTAR                                                                     #");
			gravarArq.println("#  ESSE TÓPICO TEM A FUNÇÃO DE APRESENTAR EM UMA TABELA ALGUMAS INFORMAÇÕES QUE #");
			gravarArq.println("# ESÃO SALVAS NO BANCO DE DADOS REFERENTES A CADA SUBTOPICO, SENDO ELES:        #");
			gravarArq.println("#   - LISTAR LIVROS                                                             #");
			gravarArq.println("#   LISTA TODOS OS LIVROS CADASTRADOS NO BANCO DE DADOS, ORDENADOS PELO CÓDIGO  #");
			gravarArq.println("#  DE IDENTIFICAÇÃO. AS COLUNAS DA TABELA SÃO: CÓDIGO; TÍTULO; QUANTIDADE.      #");
			gravarArq.println("#   ESSA TELA POSSUI TRÊS BOTÕES: DELETAR(APAGA O LIVRO SELECIONADO NA TABELA   #");
			gravarArq.println("#  DO BANCO DE DADOS); ATUALIZAR(ATUALIZA O LIVRO SELECIONADO NA TABELA DO      #");
			gravarArq.println("#  BANCO DE DADOS); E GERAR ARQUIVO(GERA UM ARQUIVO TEXTO, SALVO NA PRÓPRIA     #");
			gravarArq.println("#  PASTA DO PROGRAMA, COM TODAS AS INFORMAÇÕES DE TODOS OS LIVROS CADASTRADOS   #");
			gravarArq.println("#  NO BANCO DE DADOS.                                                           #");
			gravarArq.println("#                                                                               #");
			gravarArq.println("#   - LISTAR FORNECEDOR                                                         #");
			gravarArq.println("#   LISTA TODOS OS FORNECEDORES CADASTRADOS NO BANCO DE DADOS, ORDENADOS PELO   #");
			gravarArq.println("#  CÓDIGO DE IDENTIFICAÇÃO. AS COLUNAS DA TABELA SÃO: ID; NOME FORNECEDOR;      #");
			gravarArq.println("#  TELEFONE; E-MAIL.                                                            #");
			gravarArq.println("#   ESSA TELA POSSUI TRÊS BOTÕES: DELETAR(APAGA O FORNECEDOR SELECIONADO NA     #");
			gravarArq.println("#  TABELA DO BANCO DE DADOS); ATUALIZAR(ATUALIZA O FORNECEDOR SELECIONADO NA    #");
			gravarArq.println("#  TABELA DO BANCO DE DADOS COM AS NOVAS INFORMAÇÕES PASSADAS); E GERAR ARQUIVO #");
			gravarArq.println("#  (GERA UM ARQUIVO TEXTO, SALVO NA PRÓPRIA PASTA DO PROGRAMA, COM TODAS AS     #");
			gravarArq.println("#  INFORMAÇÕES DE TODOS OS FORNECEDORES CADASTRADOS NO BANCO DE DADOS.          #");
			gravarArq.println("#                                                                               #");
			gravarArq.println("#   - LISTAR CLIENTES                                                           #");
			gravarArq.println("#   LISTA TODOS OS CLIENTES CADASTRADOS NO BANCO DE DADOS, ORDENADOS PELO       #");
			gravarArq.println("#  CÓDIGO DE IDENTIFICAÇÃO. AS COLUNAS DA TABELA SÃO: ID; NOME; CPF; CELULAR.   #");
			gravarArq.println("#   ESSA TELA POSSUI DOIS BOTÔES: DELETAR(APAGA O CLIENTE SELECIONADO NA TABELA #");
			gravarArq.println("#  DO BANCO DE DADOS) E GERAR ARQUIVO(GERA UM ARQUIVO TEXTO, SALVO NA PRÓPRIA   #");
			gravarArq.println("#  PASTA DO PROGRAMA, COM TODAS AS INFORMAÇÕES DE TODOS OS CLIENTES CADASTRADOS #");
			gravarArq.println("#  NO BANCO DE DADOS. #");
			
			gravarArq.close();
			return true;
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO:"+e.getMessage());
			return false;
		}
	}
}
