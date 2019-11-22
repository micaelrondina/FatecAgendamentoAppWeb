package br.com.agendamentoMedico.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



/**
 * Cria uma instância EntityManager compartilhada na aplicação
 * @author FatecTaquaritinga
 *
 */
public class DataSource {
	
	private static EntityManagerFactory factory;
	
	static { factory = Persistence.createEntityManagerFactory("AgendamentoPU");}
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
	/**
	 * Teste de conexão com o banco
	 */
	
	/*
	public static void main(String[] args) {
		try {
			Persistence.createEntityManagerFactory("AgendamentoPU");
			System.out.println("Conectado com sucesso!");
		}catch (HibernateException e){
			System.out.println("Falha na conexão: " + e.getMessage());
		}
	}
	*/
}
