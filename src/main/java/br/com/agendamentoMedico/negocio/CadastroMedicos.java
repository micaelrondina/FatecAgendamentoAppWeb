package br.com.agendamentoMedico.negocio;

import java.io.Serializable;
import java.util.Date;

import br.com.agendamentoMedico.exception.NegocioException;
import br.com.agendamentoMedico.model.Medico;
import br.com.agendamentoMedico.repository.Medicos;

public class CadastroMedicos implements Serializable {

	private static final long serialVersionUID = 1L;

	private Medicos medicos;

	public CadastroMedicos(Medicos medicos) {
		this.medicos = medicos;
	}
	
	/**
	 * Não permite data de nascimento futura à data atual, não deixa cadastrar médico com o mesmo CRM.
	 * @param medico
	 * @throws NegocioException
	 */
	public void salvar(Medico medico) throws NegocioException {
		Medico medicoCadastrado = medicos.medicoCrm(medico.getCrm());
		if(medicoCadastrado != null && !medicoCadastrado.equals(medico.getCrm())) {
			throw new NegocioException("Médico já cadastrado.");
		}
		if (medico.getDataNascimento() != null && medico.getDataNascimento().after(new Date())) {
			throw new NegocioException("Data de nascimento não pode ser futura à data atual.");
		}
		this.medicos.guardar(medico);
	}

	public void excluir(Medico medico) throws NegocioException {
		medico = this.medicos.medicoCrm(medico.getCrm());
		this.medicos.remover(medico);
	}
}
