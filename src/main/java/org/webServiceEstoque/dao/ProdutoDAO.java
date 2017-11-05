package org.webServiceEstoque.dao;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.webServiceEstoque.models.Localizacao;
import org.webServiceEstoque.models.Produto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



@Repository
@Transactional
public class ProdutoDAO {
	
	@PersistenceContext
    private EntityManager manager;

    public void gravar(Produto produto){
    	 manager.persist(produto);
    	 
    }
    
    @SuppressWarnings("unchecked")
	public String listarTodos() {
		Query query = manager.createQuery("from " + "Produto");
		List<Produto> listaDeProdutos = query.getResultList();
		//Gson gson = new Gson();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String produtosGson = gson.toJson(listaDeProdutos);
		return produtosGson;
	}

	public String novosRegistro(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = formatter.format(date);
		String Stringquery = " from Produto where momentoDaUltimaAtualizacao > '"+s+"'"+" order by momentoDaUltimaAtualizacao desc ";
		Query query = manager.createQuery(Stringquery);
		List<Produto> listaDeProduto = query.getResultList();
		
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		//GsonBuilder g = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
		//Gson gson = new Gson();
		String produtosGson = gson.toJson(listaDeProduto);
		return produtosGson;
		
		
		
	}
	public String merge(List<Produto> p){
		List<Produto> produtos = new ArrayList<>();
		Date data = new Date();
		for (Produto produto : p) {
			produto.setMomentoDaUltimaAtualizacao(data);
			produtos.add(manager.merge(produto));
		}
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		//GsonBuilder g = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
		//Gson gson = new Gson();
		String produtosGson = gson.toJson(produtos);
		return produtosGson;
		
		
		
		
	}
}