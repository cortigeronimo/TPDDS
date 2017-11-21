package model.precalculo;

import java.math.BigDecimal;

import java.util.LinkedList;
import java.util.List;

import model.Empresa;
import model.Indicador;
import model.Periodo;
import model.Usuario;
import model.repositories.RepoEmpresas;
import model.IndicadorPeriodo;
import model.repositories.RepoIndicadores;
import model.repositories.RepoIndicadoresPeriodos;
import model.repositories.RepoPeriodos;
import model.repositories.RepoUsuarios;


public class PrecalculoIndicadores {

	
	public void precalcularIndicadores(){
		BigDecimal valor;
		List<IndicadorPeriodo> indicadoresConValor = new LinkedList<>();
		
		List<Usuario> usuarios = RepoUsuarios.getInstance().findAll();
		for (Usuario usuario : usuarios) {
			
			List<Indicador> indicadores = RepoIndicadores.getInstance().findAllBy("user",usuario.getId());
			List<Empresa> empresas = RepoEmpresas.getInstance().findAll();
			
			for (Empresa empresa : empresas) {
				
				List<Periodo> periodos = RepoPeriodos.getInstance().findAllBy("empresa",empresa.getId());
				
				for (Periodo periodo : periodos) {	
				
					for (Indicador indicador : indicadores) {
						
						IndicadorPeriodo indicadorConValor = new IndicadorPeriodo();
						valor = indicador.evaluar(periodo);
						indicadorConValor.setIndicador(indicador);
						indicadorConValor.setPeriodo(periodo);
						indicadorConValor.setValor(valor);
						indicadoresConValor.add(indicadorConValor);
					}					
				}
			}		
		}
		
		RepoIndicadoresPeriodos.getInstance().insertarVarios(indicadoresConValor);
	}
	
}
