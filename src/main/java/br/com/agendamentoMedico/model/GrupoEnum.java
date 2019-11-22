package br.com.agendamentoMedico.model;

public enum GrupoEnum {
	
	FUSEX("FUSEx"), PASS("PASS"), EXCMB("Ex-Combatente");
	
	private String descricao;
	
	GrupoEnum(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
