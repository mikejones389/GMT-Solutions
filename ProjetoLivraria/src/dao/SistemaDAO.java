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
			gravarArq.println("# PERFEI��O. #");
			gravarArq.println("#                                                                               #");
			gravarArq.println("#  1.2- CADASTRAR FORNECEDOR                                                    #");
			gravarArq.println("#  ESSE T�PICO REALIZA A FUN��O DE CADASTRAR UM FORNECEDOR NO BANCO DE DADOS. � #");
			gravarArq.println("# S� PREENCHER TODOS OS CAMPOS CORRETAMENTE E CLICAR EM CONCLUIR QUE O TODOS OS #");
			gravarArq.println("# DADOS DO FORNECEDOR SER�O SALVOS. #");
			gravarArq.println("#                                                                               #");
			gravarArq.println("#  2-  #");
			gravarArq.println("# ESSE PROGRAMA TEM A FUN��O DE DISPONIBILIZAR ACESSO AO BANCO DE DADOS E SUAS  #");
			gravarArq.println("# ESSE PROGRAMA TEM A FUN��O DE DISPONIBILIZAR ACESSO AO BANCO DE DADOS E SUAS  #");
			gravarArq.println("# ESSE PROGRAMA TEM A FUN��O DE DISPONIBILIZAR ACESSO AO BANCO DE DADOS E SUAS  #");
			gravarArq.println("# ESSE PROGRAMA TEM A FUN��O DE DISPONIBILIZAR ACESSO AO BANCO DE DADOS E SUAS  #");
			gravarArq.println("# ESSE PROGRAMA TEM A FUN��O DE DISPONIBILIZAR ACESSO AO BANCO DE DADOS E SUAS  #");
			gravarArq.println("# ESSE PROGRAMA TEM A FUN��O DE DISPONIBILIZAR ACESSO AO BANCO DE DADOS E SUAS  #");
			gravarArq.println("# ESSE PROGRAMA TEM A FUN��O DE DISPONIBILIZAR ACESSO AO BANCO DE DADOS E SUAS  #");
			gravarArq.println("# ESSE PROGRAMA TEM A FUN��O DE DISPONIBILIZAR ACESSO AO BANCO DE DADOS E SUAS  #");
			gravarArq.println("# ESSE PROGRAMA TEM A FUN��O DE DISPONIBILIZAR ACESSO AO BANCO DE DADOS E SUAS  #");
			gravarArq.println("# ESSE PROGRAMA TEM A FUN��O DE DISPONIBILIZAR ACESSO AO BANCO DE DADOS E SUAS  #");
			gravarArq.println("# ESSE PROGRAMA TEM A FUN��O DE DISPONIBILIZAR ACESSO AO BANCO DE DADOS E SUAS  #");
			gravarArq.println("# ESSE PROGRAMA TEM A FUN��O DE DISPONIBILIZAR ACESSO AO BANCO DE DADOS E SUAS  #");
			
			gravarArq.close();
			return true;
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO:"+e.getMessage());
			return false;
		}
	}
}
