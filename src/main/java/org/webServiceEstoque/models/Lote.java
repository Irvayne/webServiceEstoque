package org.webServiceEstoque.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.google.gson.annotations.Expose;

@Entity
public class Lote {
	@Id
	@GenericGenerator(name = "id", strategy = "uuid2")
	@Expose
	private String id;

	@Expose
	private double precoDeCompra;
	@Expose
	private int quantidade;
	@Expose
	private int quantidadeVendidaMovimentada;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Expose
	private Date data;
	
	@ManyToOne
	@JoinColumn(name="produto_id")
	@Expose
	private Produto produto;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Expose
	private Date momentoDaUltimaAtualizacao;
	
	
	
	public int getQuantidadeVendidaMovimentada() {
		return quantidadeVendidaMovimentada;
	}
	public void setQuantidadeVendidaMovimentada(int quantidadeVendidaMovimentada) {
		this.quantidadeVendidaMovimentada = quantidadeVendidaMovimentada;
	}
	public Date getMomentoDaUltimaAtualizacao() {
		return momentoDaUltimaAtualizacao;
	}
	public void setMomentoDaUltimaAtualizacao(Date momentoDaUltimaAtualizacao) {
		this.momentoDaUltimaAtualizacao = momentoDaUltimaAtualizacao;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getPrecoDeCompra() {
		return precoDeCompra;
	}
	public void setPrecoDeCompra(double precoDeCompra) {
		this.precoDeCompra = precoDeCompra;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public int getQuantidadeVendida() {
		return quantidadeVendidaMovimentada;
	}
	public void setQuantidadeVendida(int quantidadeVendida) {
		this.quantidadeVendidaMovimentada = quantidadeVendida;
	}
	
	
	
	

	
	
	

}
