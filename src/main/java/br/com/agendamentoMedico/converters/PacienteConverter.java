package br.com.agendamentoMedico.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.agendamentoMedico.model.Paciente;
import br.com.agendamentoMedico.repository.Pacientes;
import br.com.agendamentoMedico.util.DataSource;

@FacesConverter(forClass = Paciente.class)
public class PacienteConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Paciente retorno = null;
		EntityManager em = DataSource.getEntityManager();
		try {
			if (arg2 != null && !"".equals(arg2)) {
				retorno = new Pacientes(em).pacienteId(new Long(arg2));
			}
			return retorno;
		} finally {
			em.close();
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 != null) {
			Paciente paciente = ((Paciente) arg2);
			return paciente.getMatricula() == null ? null : paciente.getMatricula().toString();
		}
		return null;
	}
	
}
