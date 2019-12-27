package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.util.JSFUtil;

@ManagedBean (name = "MBFornecedores")
@ViewScoped

public class FornecedoresBean {

	private Fornecedores fornecedores;
	private ArrayList<Fornecedores> itens;
	private ArrayList<Fornecedores> itensFiltrados;
	
	//==========================================|
	public Fornecedores getFornecedores() {
		if (fornecedores == null) {
			fornecedores = new Fornecedores();
		}
		return fornecedores;
	}

	public void setFornecedores(Fornecedores fornecedores) {
		this.fornecedores = fornecedores;
	}
	//==========================================|

	//==========================================|
	public ArrayList<Fornecedores> getItens() {
		return itens;
	}
	
	public void setItens(ArrayList<Fornecedores> itens) {
		this.itens = itens;
	}
	//==========================================|
	
	//==========================================|
	public ArrayList<Fornecedores> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(ArrayList<Fornecedores> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	//==========================================|
	
	//--- Constroi o xhtml com os Dados já carregados -----------| 
	@PostConstruct
	public void prepararPesquisa() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO(); 
			itens = fdao.listar();

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void prepararNovo() {
		fornecedores = new Fornecedores();
	}
	
	// Botao de gravar no arquivo fornecedores.xhtml (inicio)
	public void novo() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.salvar(fornecedores);
			itens = fdao.listar();
			JSFUtil.adicionarMensagemSucesso("Fornecedor cadastrado com Sucesso!");
			
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());  
			e.printStackTrace();
		}
	}
	// Botao de gravar no arquivo fornecedores.xhtml (Fim)
	
	//--- Botão de Excluir Fornecedor (Incio) -----|
	/*
	public void prepararExcluir() {
		fornecedores = itens.getRowData(); 
	}
	*/
	
	
	/* 
	 * Captura a linha que foi clicado no <p:dataTable> usando o ArrayList
	 * O ListaDataModel a partir da versão 4 do Primefaces dá problemas com Dados em paginação.
	 * Nesse caso para resolver o problema, usa o ArrayList no lugar do ListaDataModel. 
	 * A função prepararExcluir() logo acima fica em desuso.
	 * 
	 * No arquivo xhtml fica assim para uso com ArreyList: 
	 * 
	 * <p:commandButton icon="ui-icon-trash"
		                oncomplete="PF('dlgForExcluir').show()" 
		                update=":frmForExcluir:pnForExcluir">
			<f:setPropertyActionListener value="#{item}" target="#{MBFornecedores.fornecedores}" />
		</p:commandButton>
	 *	
	 * A tag <f:setPropertyActionListener> 
	 *			  
	 * OBS.: Não esquecer que de colocar entre colchetes #{}
	 */
	public void excluir() {
		try {
			//--- Executa a Exclusão (Inicio) ----|
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.excluir(fornecedores);
			//--- Executa a Exclusão (Fim) ----|
			
			//--- Atualiza a Lista (Inicio) ---|
			itens = fdao.listar();
			//--- Atualiza a Lista (Fim) ---|
			
			//--- Envia uma mensagem ao usuario (Inicio) ----|
			JSFUtil.adicionarMensagemSucesso("Fornecedor Excluido com Sucesso!");
			//--- Envia uma mensagem ao usuario (Fim) ----|
			
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Não é possível excluir fornecedor que tenha um produto associado!");  
			e.printStackTrace();
		}
	}
	//--- Botão de Excluir Fornecedor (Fim) -----|
	
	//--- Botão de Editar/Alterar Fornecedor (Inicio) -----|
	/*
	public void prepararEditar() {
		fornecedores = itens.getRowData(); // Captura a linha que foi clicado
	}
	*/
	
	public void editar() {
		try {
			//--- Executa a Exclusão (Inicio) ----|
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.Editar(fornecedores);
			//--- Executa a Exclusão (Fim) ----|
			
			//--- Atualiza a Lista (Inicio) ---|
			itens = fdao.listar();
			//--- Atualiza a Lista (Fim) ---|
			
			//--- Envia uma mensagem ao usuario (Inicio) ----|
			JSFUtil.adicionarMensagemSucesso("Fornecedor alterado com sucesso!");
			//--- Envia uma mensagem ao usuario (Fim) ----|
			
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());  
			e.printStackTrace();
		}
	}
	//--- Botão de Editar/Alterar Fornecedor (Fim) -----|
}
