package br.com.agendamentoMedico.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "medico")
public class Medico implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long crm;
	private String nome;
	private EstadoEnum crmEstado;
	private EspecialidadesEnum especialidade;
	private SexoEnum sexo;
	private Date dataNascimento;
	
	@Id
	public Long getCrm() {
		return crm;
	}
	
	public void setCrm(Long crm) {
		this.crm = crm;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "crm_estado", nullable = false)
	public EstadoEnum getCrmEstado() {
		return crmEstado;
	}

	public void setCrmEstado(EstadoEnum crmEstado) {
		this.crmEstado = crmEstado;
	}

	@Column(nullable = false)
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "especialidade", nullable = false)
	public EspecialidadesEnum getEspecialidade() {
		return especialidade;
	}
	
	public void setEspecialidade(EspecialidadesEnum especialidade) {
		this.especialidade = especialidade;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "sexo", nullable = false)
	public SexoEnum getSexo() {
		return sexo;
	}
	
	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento", nullable = false)
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((crm == null) ? 0 : crm.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medico other = (Medico) obj;
		if (crm == null) {
			if (other.crm != null)
				return false;
		} else if (!crm.equals(other.crm))
			return false;
		return true;
	}

}
