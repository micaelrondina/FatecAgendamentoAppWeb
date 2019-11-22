package br.com.agendamentoMedico.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.agendamentoMedico.model.Medico;

public class Medicos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EntityManager em;
	
	public Medicos (EntityManager em) {
		this.em = em;
	}
	
	public Medico medicoCrm (Long crm) {
		return em.find(Medico.class, crm);
	}
	
	public List<Medico> todos() {
		TypedQuery<Medico> query = em.createQuery("from Medico m order by m.crm", Medico.class);
		return query.getResultList();
	}
	
	public void adicionar (Medico medico) {
		this.em.persist(medico);
	}
	
	public void guardar (Medico medico) {
		this.em.merge(medico);
	}
	
	public void remover (Medico medico) {
		this.em.remove(medico);
	}
}
