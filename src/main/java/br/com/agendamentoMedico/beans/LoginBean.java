package br.com.agendamentoMedico.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.agendamentoMedico.model.FuncaoEnum;
import br.com.agendamentoMedico.model.Paciente;
import br.com.agendamentoMedico.repository.Pacientes;
import br.com.agendamentoMedico.util.DataSource;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Paciente paciente = new Paciente();
	private Paciente pacienteLogado;
	private List<Paciente> lista_pacientes;

	EntityManager em = DataSource.getEntityManager();
	private Pacientes pacientes = new Pacientes(em);

	public void prepararCadastro() {
		if (this.paciente == null) {
			this.paciente = new Paciente();
		}
	}

	public String login() throws IOException {
		
			lista_pacientes = pacientes.loginUsuario(paciente.getMatricula(), 
					DigestUtils.md5Hex(paciente.getSenha()), paciente.getFuncao());
			
			if (lista_pacientes.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Usuário, tipo de usuário ou senha incorretos!", "Erro de login!"));
				return null;

			} else {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
						.getSession(false);
				if (session != null) {
					session.setAttribute("paciente", lista_pacientes);
				}
			}

		return "/ConsultaConsultas?faces-redirect=true";
	}

	public String logout() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.invalidate();
		return "/Home?faces-redirect=true";
	}
	
	public Paciente getPacienteLogado() {
		if(pacienteLogado == null) {
			pacienteLogado = new Paciente();
		}
		return pacienteLogado;
	}

	public boolean isAdministrador() {
		if (this.paciente.getFuncao().equals("ADMINISTRADOR")) {
			return true;
		} else {
			return false;
		}
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<Paciente> getLista_pacientes() {
		return lista_pacientes;
	}

	public void setLista_pacientes(List<Paciente> lista_pacientes) {
		this.lista_pacientes = lista_pacientes;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public Pacientes getPacientes() {
		return pacientes;
	}

	public void setPacientes(Pacientes pacientes) {
		this.pacientes = pacientes;
	}
	
	public FuncaoEnum[] getFuncaoEnum() {
		return FuncaoEnum.values();
	}
}
