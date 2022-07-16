package com.marcelo.api.entidades;//entidades que irão conversar com o bd
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

/**
 * 
 * @author marcelo
 * AS ANOTAÇÕES SÃO MUITO IMPORTANTES. SÃO ELAS QUE VAI FAZER COM QUE A CLASSE SEJA REFERÊNCIA AO BANCO DE DADOS
 * TODAS ELAS DEVEM COMEÇAR COM LETRA MAIÚSCULA E DEVEM SER IMPORTADAS LÁ EM CIMA
 */
@Entity
@Table(name = "empresa")

public class Empresa implements Serializable{/**
	 * cria um id único para a serealização a classe para
	 * armazenamento em disco temporariamente ou fazer
	 * transporte pela rede.
	 * 
	 * o hash é criado por causa da classe, basta colocarmos
	 * ela do jeito que ta acima que vai aparecer a opção pra gerar
	 * o id
	 */
	
	private static final long serialVersionUID = 1009205827202343756L;
	
	@Id //chave primária do banco de dados 
	@GeneratedValue(strategy=GenerationType.AUTO)//como a primary-key trabalha (o .AUTO é para ele se auto incrementar)
	private Long id;
	
	@Column(name = "razao_social", nullable = false)
	private String razaoSocial;
	
	@Column(name = "cnpj", nullable = false)//idem o comentário acima
	private String cnpj;
	
	@Column(name = "data_criacao", nullable = false)
	private LocalDateTime dataCriacao;
	
	@Column(name = "data_atualizacao", nullable = false)
	private LocalDateTime dataAtualizacao;
	
	
	private List<Funcionario> funcionarios;
	
	public Empresa() {//construtor padrão
		
	}
	/**
	 * na declaração variaveis, precisamos adicionar as anotações
	 * para fazermos o mapeamento para o banco de dados
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*o campo razão social é uma coluna (o name é opcional)
	 * o comando nullable=false faz com que o campo seja obrigatório no banco de dados
	 */
	
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public LocalDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	
	
	/*@Column (name="funcionario", nullable=false)
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}*/

	
	/**
	 ** Uma empresa possui 0 um N funcionários, sendo assim, usamos 
	 ** função um para muitos.
	 ** Precisamos tbm do mappedBy da empresa para pegar o atributo
	 ** da classe Funcionario.
	 ** O fetch é como ele obtem esses funcionários. Pode ser feito 
	 ** de dois tipos: EAGER e LAZY.
	 ** Quando obtemos uma empresa, nos temos que falar se precisamos do funcionário ou não,
	 ** a opção LAZY não queremos a lista de funcionários. No banco, ele faz apenas uma query.
	 ** Com o EAGER, ele vai obter todos os funcionários daquela empresa.
	 ** O Cascade vai acontecer com os funcionários se mudarmos algum elemento na empresa. Se deletarmos 
	 ** uma empresa, todos os funcionários dessa emrpesa são removidos tbm.
	 **/
	
	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Funcionario> getFuncionarios(){
		return funcionarios;
	}
	
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	@PreUpdate//Fazendo uma atualização
	public void preUpdate() {
		LocalDateTime dataAtualizacao = LocalDateTime.now();
	}
	
	@PrePersist//quando estamos inserindo um elemento pela primeira vez
	public void prePersist() {
		LocalDateTime atual = LocalDateTime.now();//cria uma nova data
		dataCriacao = atual;//a data de criação é atualizada
		dataAtualizacao=atual;//a de atualização tbm
	}

	@Override
	public String toString() {
		return "Empresa [id=" + id + ", razaoSocial=" + razaoSocial + ", cnpj=" + cnpj + ", dataCriacao=" + dataCriacao
				+ ", dataAtualizacao=" + dataAtualizacao + "]";
	}
	
	
	
}