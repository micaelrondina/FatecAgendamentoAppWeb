package br.com.agendamentoMedico.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.agendamentoMedico.exception.NegocioException;
import br.com.agendamentoMedico.model.GrupoEnum;
import br.com.agendamentoMedico.model.Paciente;
import br.com.agendamentoMedico.model.SexoEnum;
import br.com.agendamentoMedico.negocio.CadastroPacientes;
import br.com.agendamentoMedico.repository.Pacientes;
import br.com.agendamentoMedico.util.DataSource;

@ManagedBean
@ViewScoped
public class CadastroPacienteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Paciente paciente;
	
	public void prepararCadastro() {
		if (this.paciente == null) {
			this.paciente = new Paciente();
		}
	}
	
	public void salvar() {
		EntityManager em = DataSource.getEntityManager();
		EntityTransaction et = em.getTransaction();
		FacesContext faces = FacesContext.getCurrentInstance();
		
		try {
			et.begin();
			CadastroPacientes cadastro = new CadastroPacientes(new Pacientes(em));
			paciente.setSenha(DigestUtils.md5Hex(paciente.getSenha()));
			paciente.setFuncao("Administrador");
			cadastro.salvar(paciente);
			this.paciente = new Paciente();
			faces.addMessage(null, new FacesMessage("Salvo com sucesso"));
			et.commit();
		} catch (NegocioException e) {
			et.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			faces.addMessage(null, mensagem);
		} finally {
			em.close();
		}
		
	}
	
	public Paciente getPaciente() {
		return paciente;
	}
	
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	public SexoEnum[] getSexoPaciente() {
		return SexoEnum.values();
	}
	
	public GrupoEnum[] getGrupoPaciente() {
		return GrupoEnum.values();
	}
}
