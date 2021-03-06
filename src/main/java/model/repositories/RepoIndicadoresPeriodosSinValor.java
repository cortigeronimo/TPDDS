package model.repositories;

import java.util.Arrays;
import java.util.List;

import model.precalculo.IndicadorPeriodoSinValor;
import model.Periodo;
import model.Usuario;

public class RepoIndicadoresPeriodosSinValor extends RepoBD<IndicadorPeriodoSinValor>{
	
private static final RepoIndicadoresPeriodosSinValor instance = new RepoIndicadoresPeriodosSinValor();
	
	private RepoIndicadoresPeriodosSinValor() {
		this.entidad = IndicadorPeriodoSinValor.class;
	}
	
	public static RepoIndicadoresPeriodosSinValor getInstance() {
		return instance;
	}
	
	@Override
	protected List<String> camposDeBusqueda() {
		return Arrays.asList("indicador","periodo");
	}
	
	@Override
	protected List<Object> valoresDeBusqueda(IndicadorPeriodoSinValor indicador) {
		return Arrays.asList(indicador.getIndicador(),indicador.getPeriodo());			
	}

	@SuppressWarnings("unchecked")
	public List<IndicadorPeriodoSinValor> findAllByUserYPeriodo(Periodo periodo, Usuario user){		
		
		return this.entityManager().createQuery
				("from " + getEntidad().getSimpleName() + " e "
						 + "where e.periodo = " + periodo.getId()
						 + " and e.indicador.user = " + user.getId()
						 + " order by e.id asc").getResultList();
	}
	
}
