package br.com.agendamentoMedico.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.agendamentoMedico.model.Paciente;
import br.com.agendamentoMedico.negocio.CadastroPacientes;
import br.com.agendamentoMedico.repository.Pacientes;
import br.com.agendamentoMedico.util.DataSource;

@ManagedBean
@ViewScoped
public class PacientesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Paciente> pacientes;
	
	private Paciente pacienteSelecionado;
	
	public void consultar() {
		EntityManager em = DataSource.getEntityManager();
		Pacientes pacientes = new Pacientes(em);
		this.pacientes = pacientes.todos();
		em.close();
	}
	
	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		EntityManager em = DataSource.getEntityManager();
		EntityTransaction et = em.getTransaction();
		
		CadastroPacientes cadastro = new CadastroPacientes(new Pacientes(em));
		
		try {
			et.begin();
			cadastro.excluir(this.pacienteSelecionado);
			context.addMessage(null, new FacesMessage("Paciente excluído com sucesso!"));
			et.commit();
			this.consultar();
			
		} catch (Exception e) {
			et.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		} finally {
			em.close();
		}
	}
	
	public List<Paciente> getPacientes() {
		return pacientes;
	}
	
	public Paciente getPacienteSelecionado() {
		return pacienteSelecionado;
	}
	
	public void setPacienteSelecionado(Paciente pacienteSelecionado) {
		this.pacienteSelecionado = pacienteSelecionado;
	}
}
