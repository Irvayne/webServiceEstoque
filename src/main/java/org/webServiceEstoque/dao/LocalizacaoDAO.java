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
import org.webServiceEstoque.models.Lote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Repository
@Transactional
public class LocalizacaoDAO {
	
	@PersistenceContext
    private EntityManager manager;

    public void gravar(Localizacao localizacao){
    	localizacao.setMomentoDaUltimaAtualizacao(new Date());
    	 manager.persist(localizacao);
    }
    
    @SuppressWarnings("unchecked")
	public String listarTodos() {
		Query query = manager.createQuery("from " + "Localizacao order by momentoDaUltimaAtualizacao desc");
		List<Localizacao> listaDeLocalizacao = query.getResultList();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		//GsonBuilder g = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
		//Gson gson = new Gson();
		String localizacoesGson = gson.toJson(listaDeLocalizacao);
		return localizacoesGson;
	}

	public String novosRegistro(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = formatter.format(date);
		String Stringquery = " from Localizacao where momentoDaUltimaAtualizacao > '"+s+"'"+" order by momentoDaUltimaAtualizacao desc ";
		Query query = manager.createQuery(Stringquery);
		List<Localizacao> listaDeLocalizacao = query.getResultList();
		
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		//GsonBuilder g = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
		//Gson gson = new Gson();
		String localizacoesGson = gson.toJson(listaDeLocalizacao);
		return localizacoesGson;
		
		
		
	}
	public String merge(List<Localizacao> l){
		List<Localizacao> localizacoes = new ArrayList<>();
		Date data = new Date();
		for (Localizacao localizacao : l) {
			localizacao.setMomentoDaUltimaAtualizacao(data);
			localizacoes.add(manager.merge(localizacao));
		}
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		//GsonBuilder g = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
		//Gson gson = new Gson();
		String localizacoesGson = gson.toJson(localizacoes);
		return localizacoesGson;
		
		
		
		
	}
	
	public Localizacao buscarPorId(String id) {
		return manager.find(Localizacao.class, id);
	}

}
