package com.marcelo.api.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.marcelo.api.enums.PerfilEnum;

@Entity
@Table (name = "funcionario")
public class Funcionario implements Serializable{
	
	private static final long serialVersionUID = 2006013237888207883L;
	
	private Long id;
	private String nome;
	private String email;
	private String senha;
	private String cpf;
	private BigDecimal ValorHora;
	private Float qtdHorasTrabalhoDia;
	private Float qtdHorasAlmoco;
	private PerfilEnum perfil;// tem que ter o mesmo nome da classe que ele é feito
	private LocalDateTime dataCriacao;
	private LocalDateTime dataAtualizacao;
	private Empresa empresa;//
	private List<Lancamento> lancamentos; //>>TEM QUE CRIAR A CLASSE DESSA MERDA AQUI<<<
	
	public Funcionario(){
	}
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//como na classe Empresa
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	@Column (name = "nome", nullable = false)
	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}


	@Column (name = "email", nullable = false)
	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}


	@Column (name = "senha", nullable = false)
	public String getSenha() {
		return senha;
	}



	public void setSenha(String senha) {
		this.senha = senha;
	}


	@Column (name = "cpf", nullable = false)
	public String getCpf() {
		return cpf;
	}



	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	@Column (name = "valor_hora", nullable = true)
	public BigDecimal getValorHora() {
		return ValorHora;
	}



	public void setValorHora(BigDecimal valorHora) {
		ValorHora = valorHora;
	}


	@Column (name="qtd_hora_trabalho_dia", nullable = true)
	public Float getQtdHorasTrabalhoDia() {
		return qtdHorasTrabalhoDia;
	}



	public void setQtdHorasTrabalhoDia(Float qtdHorasTrabalhoDia) {
		this.qtdHorasTrabalhoDia = qtdHorasTrabalhoDia;
	}


	@Column (name="qtd_horas_almoco", nullable = true)
	public Float getQtdHorasAlmoco() {
		return qtdHorasAlmoco;
	}



	public void setQtdHorasAlmoco(Float qtdHorasAlmoco) {
		this.qtdHorasAlmoco = qtdHorasAlmoco;
	}

	@Enumerated(EnumType.STRING)//grava o valor em texto ao invez de número (ordinal)
	@Column (name="perfil", nullable = false)
	public PerfilEnum getPerfil() {
		return perfil;
	}



	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}


	@Column (name="data_criacao", nullable=false)
	public LocalDateTime getdataCriacao() {
		return dataCriacao;
	}



	public void setdataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}


	@Column (name="data_atualizacao", nullable = false)
	public LocalDateTime getdataAtualizacao() {
		return dataAtualizacao;
	}



	public void setdataAtualizacao(LocalDateTime dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	/**
	 * temos muitos funcionários em uma unica empresa
	 * o FetchType agora muda para EAGER pq muito provavelmente, quando tivermos
	 * o nome do funcionário também iremos querer o nome da empresa junto
	 */
	@ManyToOne(fetch = FetchType.EAGER)//vários funcionários possuem uma unica empresa
	//@Column (name="empresa", nullable = false)
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)//um funcionário possui vários lançamentos
	public List<Lancamento> getLancamentos(){
		return lancamentos;
	}
	
	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	@PreUpdate
    public void preUpdate() {
        LocalDateTime dataAtualizacao = LocalDateTime.now();
    }
	
	@PrePersist
	public void prePersist() {
		LocalDateTime atual = LocalDateTime.now();
		dataCriacao = atual;
		dataAtualizacao = atual;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", cpf=" + cpf
				+ ", ValorHora=" + ValorHora + ", qtdHorasTrabalhoDia=" + qtdHorasTrabalhoDia + ", qtdHorasAlmoco="
				+ qtdHorasAlmoco + ", perfil=" + perfil + ", dataCriacao=" + dataCriacao + ", dataAtualizacao="
				+ dataAtualizacao + ", empresa=" + empresa + "]";
	}
	
}
