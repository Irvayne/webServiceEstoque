package org.webServiceEstoque.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.webServiceEstoque.dao.LocalizacaoDAO;
import org.webServiceEstoque.dao.LoteDAO;
import org.webServiceEstoque.dao.ProdutoDAO;
import org.webServiceEstoque.models.Localizacao;
import org.webServiceEstoque.models.Lote;
import org.webServiceEstoque.models.Produto;

@RestController
public class WebService {

	@Autowired
	ProdutoDAO produtoDAO;
	@Autowired
	LoteDAO loteDAO;
	@Autowired
	LocalizacaoDAO localizacaoDAO;

	@RequestMapping(value = "/adicionarProduto", method = RequestMethod.POST)
	public String adicionarProduto(@RequestBody Produto p) {

		System.out.println(p.getId());
		System.out.println(p.getNome());
		System.out.println(p.getQuantidade());
		//System.out.println(p.getLocalizacao().getNome());
		Lote lote = new Lote();
		lote.setProduto(p);
		lote.setData(new Date());
		lote.setQuantidade(10);
		lote.setPrecoDeCompra(50);
		Date data = new Date();
		p.setMomentoDaUltimaAtualizacao(data);

		produtoDAO.gravar(p);
		//localizacaoDAO.gravar(p.getLocalizacao());

		return "produto adicionado com sucesso";

	}

	@RequestMapping(value = "/adicionarLocalizacao", method = RequestMethod.POST)
	public String adicionarProduto(@RequestBody Localizacao l) {

		System.out.println(l.getNome());
		

		localizacaoDAO.gravar(l);
		// loteDAO.gravar(lote);

		return "Localizacao adicionado com sucesso";

	}

	@RequestMapping(value = "/listarProdutos", method = RequestMethod.GET)
	public String listarProdutos() {
		String produtos = produtoDAO.listarTodos();
		return produtos;

	}

	@RequestMapping(value = "/listarLocalizacoes", method = RequestMethod.GET)
	public String listarLocalizacoes() {
		String localizacoes = localizacaoDAO.listarTodos();
		return localizacoes;

	}
	
//	ver o que tem de diferente no servidor em relação ao celular e servidor envia a diferenca para o celular
	@RequestMapping(value = "/diffLocalizacao", method = RequestMethod.GET)
	public String diffLocalizacao(@RequestHeader("datahora") String datahora) {
		
		String localizacoes = localizacaoDAO.novosRegistro(new Date( datahora));
		return localizacoes;

	}
	
	//atraves da variavel sinc é visto quem não está sincronizado então o celular envia para o servidor os que nao tem no servidor
	@RequestMapping(value = "/sincronizaLocalizacoes", method = RequestMethod.PUT)
	public String diffLocalizacao(@RequestBody List<Localizacao> localizacoes) {
		String localizacoesSalvas = localizacaoDAO.merge(localizacoes);
				return localizacoesSalvas;

	}
	
	
	@RequestMapping(value = "/diffProduto", method = RequestMethod.GET)
	public String diffProduto(@RequestHeader("datahora") String datahora) {
		
		String produtos = produtoDAO.novosRegistro(new Date( datahora));
		return produtos;

	}
	
	@RequestMapping(value = "/sincronizaProdutos", method = RequestMethod.PUT)
	public String sincronizaProdutos(@RequestBody List<Produto> produtos) {
		String produtosSalvos = produtoDAO.merge(produtos);
				return produtosSalvos;

	}
	
	
	
	
	@RequestMapping(value = "/listarLotes", method = RequestMethod.GET)
	public String listarLotes() {
		String lotes = loteDAO.listarTodos();
		return lotes;

	}
	
//	ver o que tem de diferente no servidor em relação ao celular e servidor envia a diferenca para o celular
	@RequestMapping(value = "/diffLotes", method = RequestMethod.GET)
	public String diffLotes(@RequestHeader("datahora") String datahora) {
		
		String lotes = loteDAO.novosRegistro(new Date( datahora));
		return lotes;

	}
	
	//atraves da variavel sinc é visto quem não está sincronizado então o celular envia para o servidor os que nao tem no servidor
	@RequestMapping(value = "/sincronizaLotes", method = RequestMethod.PUT)
	public String diffLotes(@RequestBody List<Lote> lotes) {
		String lotesSalvas = loteDAO.merge(lotes);
				return lotesSalvas;

	}
	
	
}
