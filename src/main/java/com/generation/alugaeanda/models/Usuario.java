package com.generation.alugaeanda.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo nome é obrigatorio")
	@Size(min = 2, max = 100, message = "O atributo nome deve ter no minino 2 e no maximo 100 caracteres")
	private String nome;
	
	@NotBlank(message = "O atributo email é obrigatorio")
	@Email(message = "O atributo email deve ser um endereço de email valido")
	@Size(max = 100, message = "O atributo email deve ter no maximo 100 caracteres")
	private String email;
	
	@NotBlank(message = "O atributo senha é obrigatorio")
	@Size(min = 8, max = 255, message = "O atributo senha deve ter no minino 8 e no maximo 255 caracteres")
	private String senha;
	
	@NotBlank(message = "O atributo foto é obrigatorio")
	@Size(max = 255, message = "O atributo foto deve ter no maximo 100 caracteres")
	private String foto;
	
	@NotNull(message = "O atributo locador é obrigatorio")
	private boolean locador;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Aluguel> aluguel;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Carro> carro;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public boolean isLocador() {
		return locador;
	}

	public void setLocador(boolean locador) {
		this.locador = locador;
	}

	public List<Aluguel> getAluguel() {
		return aluguel;
	}

	public void setAluguel(List<Aluguel> aluguel) {
		this.aluguel = aluguel;
	}

	public List<Carro> getCarro() {
		return carro;
	}

	public void setCarro(List<Carro> carro) {
		this.carro = carro;
	}

	
}
