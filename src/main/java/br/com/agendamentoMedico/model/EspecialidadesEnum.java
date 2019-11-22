package br.com.agendamentoMedico.model;

public enum EspecialidadesEnum {
	
	GERAL("Clínico Geral"), CGERAL("Cirurgião Geral");
	
	private String descricao;
	
	EspecialidadesEnum(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
