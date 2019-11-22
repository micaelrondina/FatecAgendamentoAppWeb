package br.com.agendamentoMedico.model;

public enum FuncaoEnum {
	ADMINISTRADOR("Administrador"), PACIENTE("Paciente"), MEDICO("Medico");;

	private String descricao;

	FuncaoEnum(String descricao){
		this.descricao = descricao;	
	}

	public String getDescricao() {
		return descricao;
	}
}
