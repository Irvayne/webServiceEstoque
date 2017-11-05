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
import org.webServiceEstoque.models.Lote;
import org.webServiceEstoque.models.Produto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



@Repository
@Transactional
public class LoteDAO {
	
	@PersistenceContext
    private EntityManager manager;

    public void gravar(Lote lote){
    	 manager.persist(lote);
    	 

    }
    
    @SuppressWarnings("unchecked")
	public String listarTodos() {
		Query query = manager.createQuery("from " + "Lote");
		List<Lote> listaDeLote = query.getResultList();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String lotesGson = gson.toJson(listaDeLote);
		return lotesGson;
	}
    
	public String novosRegistro(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = formatter.format(date);
		String Stringquery = " from Lote where momentoDaUltimaAtualizacao > '"+s+"'"+" order by momentoDaUltimaAtualizacao desc ";
		Query query = manager.createQuery(Stringquery);
		List<Lote> listaDeLote = query.getResultList();
		
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		//GsonBuilder g = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
		//Gson gson = new Gson();
		String loteGson = gson.toJson(listaDeLote);
		return loteGson;
		
		
		
	}
	
	public String merge(List<Lote> l){
		List<Lote> lotes = new ArrayList<>();
		Date data = new Date();
		for (Lote lote : l) {
			lote.setMomentoDaUltimaAtualizacao(data);
			lotes.add(manager.merge(lote));
		}
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		//GsonBuilder g = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
		//Gson gson = new Gson();
		String lotesGson = gson.toJson(lotes);
		return lotesGson;
		
		
		
		
	}
}