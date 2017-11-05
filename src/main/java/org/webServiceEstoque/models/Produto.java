package org.webServiceEstoque.models;



import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.google.gson.annotations.Expose;

@Entity
public class Produto {
	@Id
	@GenericGenerator(name = "id", strategy = "uuid2")
	@Expose
	private String id;

	@Expose
	private String nome;
	@Expose
	private double preco;
	@Expose
	private int quantidade;
	
	@OneToMany(mappedBy = "produto", targetEntity = Lote.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Lote> lotes;
	 
	@ManyToOne
	@JoinColumn(name="localizacao_id")
	@Expose
	private Localizacao localizacao;
	@Expose
	@Temporal(TemporalType.TIMESTAMP)
	private Date momentoDaUltimaAtualizacao;

	public Produto() { momentoDaUltimaAtualizacao = new Date();

	}

	public Date getMomentoDaUltimaAtualizacao() {
		return momentoDaUltimaAtualizacao;
	}

	public void setMomentoDaUltimaAtualizacao(Date momentoDaUltimaAtualizacao) {
		this.momentoDaUltimaAtualizacao = momentoDaUltimaAtualizacao;
	}

	public Produto(String id, String nome, int quantidade) {

		this.id = id;
		this.nome = nome;
		this.quantidade = quantidade;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public String getNome() {
		return nome;
	}

	
	public List<Lote> getLotes() {
		return lotes;
	}

	public void setLotes(List<Lote> lotes) {
		this.lotes = lotes;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}



	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "Produto [nome=" + nome + ", preco=" + preco + ", Quantidade=" + quantidade + "]";
	}
}
