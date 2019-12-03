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
			gravarArq.println("# 					AJUDA 					#");
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
			gravarArq.println("# PERFEIÇÃO. #");
			gravarArq.println("#                                                                               #");
			gravarArq.println("#  1.2- CADASTRAR FORNECEDOR                                                    #");
			gravarArq.println("#  ESSE TÓPICO REALIZA A FUNÇÃO DE CADASTRAR UM FORNECEDOR NO BANCO DE DADOS. É #");
			gravarArq.println("# SÓ PREENCHER TODOS OS CAMPOS CORRETAMENTE E CLICAR EM CONCLUIR QUE O TODOS OS #");
			gravarArq.println("# DADOS DO FORNECEDOR SERÃO SALVOS. #");
			gravarArq.println("#                                                                               #");
			gravarArq.println("#  2-  #");
			gravarArq.println("# ESSE PROGRAMA TEM A FUNÇÃO DE DISPONIBILIZAR ACESSO AO BANCO DE DADOS E SUAS  #");
			gravarArq.println("# ESSE PROGRAMA TEM A FUNÇÃO DE DISPONIBILIZAR ACESSO AO BANCO DE DADOS E SUAS  #");
			gravarArq.println("# ESSE PROGRAMA TEM A FUNÇÃO DE DISPONIBILIZAR ACESSO AO BANCO DE DADOS E SUAS  #");
			gravarArq.println("# ESSE PROGRAMA TEM A FUNÇÃO DE DISPONIBILIZAR ACESSO AO BANCO DE DADOS E SUAS  #");
			gravarArq.println("# ESSE PROGRAMA TEM A FUNÇÃO DE DISPONIBILIZAR ACESSO AO BANCO DE DADOS E SUAS  #");
			gravarArq.println("# ESSE PROGRAMA TEM A FUNÇÃO DE DISPONIBILIZAR ACESSO AO BANCO DE DADOS E SUAS  #");
			gravarArq.println("# ESSE PROGRAMA TEM A FUNÇÃO DE DISPONIBILIZAR ACESSO AO BANCO DE DADOS E SUAS  #");
			gravarArq.println("# ESSE PROGRAMA TEM A FUNÇÃO DE DISPONIBILIZAR ACESSO AO BANCO DE DADOS E SUAS  #");
			gravarArq.println("# ESSE PROGRAMA TEM A FUNÇÃO DE DISPONIBILIZAR ACESSO AO BANCO DE DADOS E SUAS  #");
			gravarArq.println("# ESSE PROGRAMA TEM A FUNÇÃO DE DISPONIBILIZAR ACESSO AO BANCO DE DADOS E SUAS  #");
			gravarArq.println("# ESSE PROGRAMA TEM A FUNÇÃO DE DISPONIBILIZAR ACESSO AO BANCO DE DADOS E SUAS  #");
			gravarArq.println("# ESSE PROGRAMA TEM A FUNÇÃO DE DISPONIBILIZAR ACESSO AO BANCO DE DADOS E SUAS  #");
			
			gravarArq.close();
			return true;
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO:"+e.getMessage());
			return false;
		}
	}
}
