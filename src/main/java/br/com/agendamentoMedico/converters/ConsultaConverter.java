package br.com.agendamentoMedico.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.agendamentoMedico.model.Consulta;
import br.com.agendamentoMedico.repository.Consultas;
import br.com.agendamentoMedico.util.DataSource;

@FacesConverter(forClass = Consulta.class)
public class ConsultaConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Consulta retorno = null;
		EntityManager em = DataSource.getEntityManager();
		try {
			if (arg2 != null && !"".equals(arg2)) {
				retorno = new Consultas(em).porId(new Long(arg2));
			}
			return retorno;
		} finally {
			em.close();
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 != null) {
			Consulta consulta = ((Consulta) arg2);
			return consulta.getId() == null ? null : consulta.getId().toString();
		}
		return null;
	}
	
}
