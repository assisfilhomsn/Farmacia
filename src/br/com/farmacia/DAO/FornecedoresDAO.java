package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.factory.conexaoFactory;

public class FornecedoresDAO {

	public void salvar(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder(); // permite concatenar sem precisar dos sinais de "+" e concatena com
													// append
		sql.append("insert into fornecedores"); // bota o SQL nas linhas Append
		sql.append("(descricao)");
		sql.append("values(?)"); // A quantidade de "?" varia com a quantidade de Campos do SQL

		Connection conexao = conexaoFactory.conectar(); // Instancia a Classe de Conex�o com o Banco de Dados

		PreparedStatement comando = conexao.prepareStatement(sql.toString()); // Permitir utilizar os comando SQL na
																				// Interface Java
		comando.setString(1, f.getDescricao()); // acessa o campo 1 do Apeend
		comando.executeUpdate(); // Executa o SQL e Grava no Banco de Dados
	}

	public void excluir(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("Delete from fornecedores where codigo = ?");

		Connection conexao = conexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo()); // acessa o campo 1 do SQL no Append
		comando.executeUpdate();
	}

	public void Editar(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("update fornecedores ");
		sql.append("set descricao = ? ");
		sql.append("where codigo = ? ");

		Connection conexao = conexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao()); // acessa o campo 1 do SQL no Append
		comando.setLong(2, f.getCodigo()); // acessa o campo 1 do SQL no Append
		comando.executeUpdate();

	}
	
	public Fornecedores BuscaPorCodigo(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("Select codigo, descricao ");
		sql.append("from fornecedores ");
		sql.append("where codigo = ? ");

		Connection conexao = conexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo()); // acessa o campo 1 do SQL no Append

		ResultSet resultado = comando.executeQuery(); // Executa a Query e guarda o resultado do SQL na variavel "resultado" 
		Fornecedores retorno = null; // Criou uma variavel do tipo Fornecedores
		
		if (resultado.next()) {
			retorno = new Fornecedores(); // Estancia a variavel acima criada
			retorno.setCodigo(resultado.getLong("codigo")); // Se ele encontrar algo, passa a variavel de retorno e guarda a informa��o que foi pego dela.
			retorno.setDescricao(resultado.getString("descricao"));
		}
		
		return retorno; // Exige retorno por que a Classe n�o � do tipo VOID. Exige um retorno 
	}
	
	
	public ArrayList<Fornecedores> BuscaPorDescricao(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("Select codigo, descricao ");
		sql.append("from fornecedores ");
		sql.append("where descricao like ? ");
		sql.append("order by descricao ASC ");

		Connection conexao = conexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, "%" + f.getDescricao() + "%" ); // Tratando o parametro usando as defini��es de like (%) do SQL ANSI
		ResultSet resultado = comando.executeQuery(); // Executa a Query e guarda o resultado do SQL na variavel "resultado" 
		
		ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();
		while (resultado.next()) {
			   Fornecedores item = new Fornecedores();
			   item.setCodigo(resultado.getLong("codigo"));
			   item.setDescricao(resultado.getString("descricao"));
			   
			   lista.add(item);
		   }
		   
		return lista; // Retorno do Tipo ArrayList de Fornecedores. (Aqui guarda todas as informa��es) 
	}
	
	
	
	public ArrayList<Fornecedores> listar() throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("Select codigo, descricao ");
		sql.append("from fornecedores ");
		sql.append("order by 2 asc ");

		Connection conexao = conexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		//comando.setLong(1, f.getCodigo()); // acessa o campo 1 do SQL no Append -> Esta linha quando passa algum parametro no SQL (?)
		
		ResultSet resultado = comando.executeQuery(); // Executa a Query e guarda o resultado do SQL na variavel "resultado" 
		
		//Fornecedores retorno = null; // Criou uma variavel do tipo Fornecedores
		ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();
		
	    while (resultado.next()) {
		   Fornecedores f = new Fornecedores();
		   f.setCodigo(resultado.getLong("codigo"));
		   f.setDescricao(resultado.getString("descricao"));
		   
		   lista.add(f);
	   }
	   
	   return lista; // Retorno do Tipo ArrayList de Fornecedores. (Aqui guarda todas as informa��es)  
	}

	// Main apenas para teste de conex�o e grava��o no Banco de Dados. (Inicio)
	public static void main(String[] args) {
		
		
		//  ============================================================
		//Realizando uma inclus�o (Inicio) 
		Fornecedores f1 = new Fornecedores(); // Estancia a Classe Fornecedores 
		Fornecedores f2 = new Fornecedores();
		Fornecedores f3 = new Fornecedores();
		Fornecedores f4 = new Fornecedores();
		Fornecedores f5 = new Fornecedores();
		Fornecedores f6 = new Fornecedores();
		Fornecedores f7 = new Fornecedores();
		Fornecedores f8 = new Fornecedores();
		Fornecedores f9 = new Fornecedores();
		
		f1.setDescricao("Descricao 3"); // Popula os  atributos usando os m�todos Set
		f2.setDescricao("Descricao 5");
		f3.setDescricao("Descricao 6");
		f4.setDescricao("Descricao 7");
		f5.setDescricao("Descricao 8");
		f6.setDescricao("Descricao 9");
		f7.setDescricao("Descricao 10");
		f8.setDescricao("Descricao 11");
		f9.setDescricao("Descricao 12");
		
		FornecedoresDAO fdao = new FornecedoresDAO(); // Estancia a Classe de Fornecedores onde tem as regras do negocio (SQL) 
		try { fdao.salvar(f1); // Usa o M�todo SALVAR da Regra de negocio das Classes DAO (neste caso o FoenecedorDAO.Salvar() ) e grava na Tabea 
		      fdao.salvar(f2);
		      fdao.salvar(f3);
		      fdao.salvar(f4);
		      fdao.salvar(f5);
		      fdao.salvar(f6);
		      fdao.salvar(f7);
		      fdao.salvar(f8);
		      fdao.salvar(f9);
		      System.out.println("Dados gravados com sucesso"); 
		    } catch (SQLException ex) {
		      ex.printStackTrace(); 
		      System.out.println("Falha ao gravar os Dados");
		 } 
		// Realizando uma inclus�o (Fim)
		//============================================================ 
		

		/* 
		 // ============================================================
		 // Realizando uma exclus�o (inicio) 
		 Fornecedores f1 = new Fornecedores(); //  Fornecedores 
		 f1.setCodigo(12); // Popula os atributos  usando os m�todos Set
		  
		 FornecedoresDAO fdao = new FornecedoresDAO(); // Estancia a Classe de // (SQL) 
		 try { fdao.excluir(f1); // Usa o M�todo SALVAR da Regra de negocio das Classes DAO (neste caso o FoenecedorDAO.Salvar() ) e grava na Tabea
		       System.out.println("Dados Excluido com sucesso"); } 
		 catch (SQLException ex) {
		    ex.printStackTrace(); 
		    System.out.println("Falha ao excluir os Dados"); } //Realizando uma exclus�o (FIm)
		// Realizando uma exclus�o (Fim)
		// ============================================================ 
		*/
		
		
		/* ============================================================
		// Realizando uma edi��o (inicio)
		Fornecedores f1 = new Fornecedores(); // Estancia a Classe Fornecedores
		f1.setCodigo(7); // Popula os atributos usando os m�todos Set
		f1.setDescricao("Let�cia Lima");

		FornecedoresDAO fdao = new FornecedoresDAO(); // Estancia a Classe de Fornecedores onde tem as regras do negocio
														// (SQL)
		try {
			fdao.Editar(f1); // Usa o M�todo SALVAR da Regra de negocio das Classes DAO (neste caso o
								// FoenecedorDAO.Salvar() ) e grava na Tabea
			System.out.println("Dados alterado com sucesso");
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Falha ao alterar os Dados");
		}
		// Realizando uma edi��o (FIm)
		============================================================*/
		
		/* ============================================================
		// Resultado de Busca do SQL (incio)
		Fornecedores f1 = new Fornecedores(); 
		f1.setCodigo(7);
		
		Fornecedores f2 = new Fornecedores(); 
		f2.setCodigo(5);

		FornecedoresDAO fdao = new FornecedoresDAO(); // Estancia a Classe de Fornecedores onde tem as regras do negocio (SQL) 
		try { 
			  Fornecedores f3 = fdao.BuscaPorCodigo(f1); // Guarda em f3 o valor do campo codigo encontrado na Tabea Fornecedores 
			  Fornecedores f4 = fdao.BuscaPorCodigo(f2); // Guarda em f4 o valor do campo descricao encontrado na Tabea Fornecedores
			  
		      System.out.println("Resultado 1 => "+ f3);  
		      System.out.println("Resultado 2 => "+ f4);
		      
		    } catch (SQLException ex) {
		      ex.printStackTrace(); 
		      System.out.println("Falha na busca dos Dados");
		 } 
		// Resultado de Busca do SQL (incio)
		============================================================ */
		
		/*
		//============================================================
		// Listando a busca (Inicio)
		FornecedoresDAO fdao = new FornecedoresDAO(); // Estancia a Classe de Fornecedores onde tem as regras do negocio (SQL) 
		try { 
			  ArrayList<Fornecedores> lista = fdao.listar();
			  for (Fornecedores f: lista) {
				  System.out.println("Resultado: " + f);
			  }
		      
		    } catch (SQLException ex) {
		      ex.printStackTrace(); 
		      System.out.println("Falha na busca dos Dados");
		 }
		// Listando a busca (Fim)
		//============================================================ 
		*/
		
		/*
		// Listgando por Descri��o (incio)
		Fornecedores f1 = new Fornecedores(); 
		f1.setDescricao("lima");
		
		FornecedoresDAO fdao = new FornecedoresDAO();
		try { 
			  ArrayList<Fornecedores> lista = fdao.BuscaPorDescricao(f1);
			  for (Fornecedores f: lista) {
				  System.out.println("Resultado: " + f);
			  }
		      
		    } catch (SQLException ex) {
		      ex.printStackTrace(); 
		      System.out.println("Falha na busca dos Dados");
		 }
		*/	
		
		// Listgando por Descri��o (Fim)

	}
	// Main apenas para teste de conex�o e grava��o no Banco de Dados (Fim)

}

