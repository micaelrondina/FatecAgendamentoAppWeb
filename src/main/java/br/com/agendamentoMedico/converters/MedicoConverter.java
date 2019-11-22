package br.com.agendamentoMedico.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.agendamentoMedico.model.Medico;
import br.com.agendamentoMedico.repository.Medicos;
import br.com.agendamentoMedico.util.DataSource;

@FacesConverter(forClass = Medico.class)
public class MedicoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Medico retorno = null;
		EntityManager em = DataSource.getEntityManager();
		try {
			if (arg2 != null && !"".equals(arg2)) {
				retorno = new Medicos(em).medicoCrm(new Long(arg2));
			}
			return retorno;
		} finally {
			em.close();
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 != null) {
			Medico medico = ((Medico) arg2);
			return medico.getCrm() == null ? null : medico.getCrm().toString();
		}
		return null;
	}
	
}
