package br.com.agendamentoMedico.model;

public enum ConsultaEnum {
	
	CONSULTA("Consulta"), GUIA("Emissão de Guia"), CONSULTA_GUIA("Consulta e emissão de guia");
	
	private String descricao;
	
	ConsultaEnum(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
