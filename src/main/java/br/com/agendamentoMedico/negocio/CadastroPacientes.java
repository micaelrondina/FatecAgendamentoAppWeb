package br.com.agendamentoMedico.negocio;

import java.io.Serializable;
import java.util.Date;

import br.com.agendamentoMedico.exception.NegocioException;
import br.com.agendamentoMedico.model.Paciente;
import br.com.agendamentoMedico.repository.Pacientes;

public class CadastroPacientes implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pacientes pacientes;

	public CadastroPacientes(Pacientes pacientes) {
		this.pacientes = pacientes;
	}

	/**
	 * Não permite data de nascimento futura à data atual, não deixa paciente cadastrar com matrícula existente.
	 * cadastrar com a mesma matricula.
	 * 
	 * @param paciente
	 * @throws NegocioException
	 */
	public void salvar(Paciente paciente) throws NegocioException {
		Paciente pacienteExistente = pacientes.pacienteId(paciente.getMatricula());
		if(pacienteExistente != null && !pacienteExistente.equals(paciente.getMatricula())) {
			throw new NegocioException("Paciente já cadastrado.");
		}
		if (paciente.getDataNascimento() != null && paciente.getDataNascimento().after(new Date())) {
			throw new NegocioException("Data de nascimento não pode ser futura à data atual.");
		}
		this.pacientes.guardar(paciente);
	}

	public void excluir(Paciente paciente) throws NegocioException {
		paciente = this.pacientes.pacienteId(paciente.getMatricula());
		this.pacientes.remover(paciente);
	}
}
