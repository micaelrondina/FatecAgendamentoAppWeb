package br.com.agendamentoMedico.model;

public enum SexoEnum {
	
	MASCULINO("Masculino"), FEMININO("Feminino"), ND("Não declarado");
	
	private String descricao;
	
	SexoEnum(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
