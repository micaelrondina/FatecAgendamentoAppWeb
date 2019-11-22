package br.com.agendamentoMedico.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.agendamentoMedico.model.Medico;
import br.com.agendamentoMedico.negocio.CadastroMedicos;
import br.com.agendamentoMedico.repository.Medicos;
import br.com.agendamentoMedico.util.DataSource;

@ManagedBean
@ViewScoped
public class MedicosBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<Medico> medicos;
	
	private Medico medicoSelecionado;
	
	public void consultar() {
		EntityManager em = DataSource.getEntityManager();
		Medicos medicos = new Medicos(em);
		this.medicos = medicos.todos();
		em.close();
	}
	
	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		EntityManager em = DataSource.getEntityManager();
		EntityTransaction et = em.getTransaction();
		
		CadastroMedicos cadastro = new CadastroMedicos(new Medicos(em));
		
		try {
			et.begin();
			cadastro.excluir(this.medicoSelecionado);
			context.addMessage(null, new FacesMessage("Médico excluído com sucesso!"));
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
	
	public List<Medico> getMedicos(){
		return medicos;
	}
	
	public Medico getMedicoSelecionado() {
		return medicoSelecionado;
	}
	
	public void setMedicoSelecionado(Medico medicoSelecionado) {
		this.medicoSelecionado = medicoSelecionado;
	}
}
