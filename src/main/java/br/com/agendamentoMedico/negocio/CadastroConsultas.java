package br.com.agendamentoMedico.negocio;

import java.io.Serializable;
import java.util.Date;

import br.com.agendamentoMedico.exception.NegocioException;
import br.com.agendamentoMedico.model.Consulta;
import br.com.agendamentoMedico.repository.Consultas;

public class CadastroConsultas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Consultas consultas;
	
	public CadastroConsultas (Consultas consultas) {
		this.consultas = consultas;
	}
	
	/**
	 * Não permite agendar consulta em dias anterioriores ao atual
	 * @param consulta
	 * @throws Exception
	 */
	public void salvar (Consulta consulta) throws NegocioException {
		if(consulta.getDataConsulta() != null && consulta.getDataConsulta().before(new Date())) {
			throw new NegocioException("Data de consulta não pode ser anterior ao dia atual.");
		}
		this.consultas.guardar(consulta);
	}
	
	/**
	 * Não permite excluir consulta já realizada
	 * @param consulta
	 * @throws Exception
	 */
	public void excluir(Consulta consulta) throws NegocioException {
		consulta = this.consultas.porId(consulta.getId());
		
		if(consulta.getDataConsulta().before(new Date())) {
			throw new NegocioException("Não é possível excluir consulta já realizada.");
		}
		this.consultas.remover(consulta);
	}
}
