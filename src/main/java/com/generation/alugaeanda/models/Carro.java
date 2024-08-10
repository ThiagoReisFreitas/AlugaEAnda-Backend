package com.generation.alugaeanda.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_carros")
public class Carro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo marca é obrigatorio")
	@Size(max = 100, message = "O atributo marca deve conter no minimo 1 e no maximo 100 caracteres")
	private String marca;
	
	@NotBlank(message = "O atributo modelo é obrigatorio")
	@Size(max = 100, message = "O atributo modelo deve conter no minimo 1 e no maximo 100 caracteres")
	private String modelo;
	
	@NotNull(message = "O atributo ano é obrigatorio")
	@Size(max = 10, message = "O atributo ano deve conter no maximo 10 caracteres")
	private String ano;
	
	@NotBlank(message = "O atributo ano é obrigatorio")
	@Size(max = 255, message = "O atributo ano deve conter no maximo 255 caracteres")
	private String foto;
	
	@NotBlank(message = "O atributo placa é uobrigatorio")
	@Size(max = 100, message = "O atributo placa deve conter no minimo 1 e no maximo 100 caracteres")
	private String placa;
	
	@NotNull(message = "O atributo preço é obrigatorio")
	private double preco;
	
	@NotNull(message = "O atributo disponibilidade é obrigatorio")
	private boolean alugado;
	
	//@ManyToOne(fetch = FetchType.LAZY)
	//@JsonIgnoreProperties("carro")
	//private Usuario usuario;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "carro", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("carro")
	private Aluguel aluguel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public boolean isAlugado() {
		return alugado;
	}

	public void setAlugado(boolean alugado) {
		this.alugado = alugado;
	}

	//public Usuario getUsuario() {
	//	return usuario;
	//}

	//public void setUsuario(Usuario usuario) {
	//	this.usuario = usuario;
	//}

	public Aluguel getAluguel() {
		return aluguel;
	}

	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}
	
	
}
