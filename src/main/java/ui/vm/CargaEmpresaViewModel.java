package ui.vm;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import model.Empresa;
import model.repositories.Repositorios;

@Observable
public class CargaEmpresaViewModel {

	Empresa empresa = new Empresa();
	private boolean habilitaCarga = true;

	public void nuevaEmpresa() {		
		this.setEmpresa(new Empresa());
		this.setHabilitaCarga(true);
	}

	public void cargarEmpresa() {		
		if (Repositorios.empresas.existeEmpresa(empresa))
			throw new UserException("La empresa ingresada ya existe.");
		if(empresa.getSymbol() == null || empresa.getNombre() == null){
			throw new UserException("Complete los datos de la empresa.");
		}
		Repositorios.empresas.agregarEmpresa(empresa);
		this.setHabilitaCarga(false);
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public boolean isHabilitaCarga() {
		return habilitaCarga;
	}

	public void setHabilitaCarga(boolean habilitaCarga) {
		this.habilitaCarga = habilitaCarga;
		ObservableUtils.firePropertyChanged(this, "habilitaNueva");
	}

	public boolean isHabilitaNueva() {
		return !habilitaCarga;
	}

}