package br.com.agendamentoMedico.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.agendamentoMedico.model.Consulta;

public class Consultas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EntityManager em;
	
	public Consultas(EntityManager em) {
		this.em = em;
	}
	
	public List<Consulta> todos() {
		TypedQuery<Consulta> query = em.createQuery("from Consulta", Consulta.class);
		return query.getResultList();
	}
	
	public Consulta porId(Long id) {
		return em.find(Consulta.class, id);
	}
	
	public void adicionar(Consulta consulta) {
		em.persist(consulta);
	}
	
	public void guardar(Consulta consulta) {
		em.merge(consulta);
	}
	
	public void remover(Consulta consulta) {
		this.em.remove(consulta);
	}
}
