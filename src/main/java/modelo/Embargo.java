package modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import enumeraciones.TipoEmbargo;

public class Embargo {

	private String idEmbargo;
	private String idAutoridad;
	private String numProceso;
	private LocalDate fechaOficio;
	private TipoEmbargo tipoEmbargo;
	private String numCuentaAgrario;
	private String ciudadCuentaAgrario;
	private String departamentoCuentaAgrario;
	private Boolean embargoProcesado;
    private ArrayList<Demandado> demandados;
    
    
	public Embargo(String idEmbargo, String idAutoridad, String numProceso, LocalDate fechaOficio,
			TipoEmbargo tipoEmbargo, String numCuentaAgrario, String ciudadCuentaAgrario,
			String departamentoCuentaAgrario, ArrayList<Demandado> demandados) {
		super();
		this.idEmbargo = idEmbargo;
		this.idAutoridad = idAutoridad;
		this.numProceso = numProceso;
		this.fechaOficio = fechaOficio;
		this.tipoEmbargo = tipoEmbargo;
		this.numCuentaAgrario = numCuentaAgrario;
		this.ciudadCuentaAgrario = ciudadCuentaAgrario;
		this.departamentoCuentaAgrario = departamentoCuentaAgrario;
		this.embargoProcesado = false;
		this.demandados = demandados;
	}
	
	public String getIdEmbargo() {
		return idEmbargo;
	}
	public void setIdEmbargo(String idEmbargo) {
		this.idEmbargo = idEmbargo;
	}
	public String getIdAutoridad() {
		return idAutoridad;
	}
	public void setIdAutoridad(String idAutoridad) {
		this.idAutoridad = idAutoridad;
	}
	public String getNumProceso() {
		return numProceso;
	}
	public void setNumProceso(String numProceso) {
		this.numProceso = numProceso;
	}
	public LocalDate getFechaOficio() {
		return fechaOficio;
	}
	public void setFechaOficio(LocalDate fechaOficio) {
		this.fechaOficio = fechaOficio;
	}
	public TipoEmbargo getTipoEmbargo() {
		return tipoEmbargo;
	}
	public void setTipoEmbargo(TipoEmbargo tipoEmbargo) {
		this.tipoEmbargo = tipoEmbargo;
	}
	public String getNumCuentaAgrario() {
		return numCuentaAgrario;
	}
	public void setNumCuentaAgrario(String numCuentaAgrario) {
		this.numCuentaAgrario = numCuentaAgrario;
	}
	public String getCiudadCuentaAgrario() {
		return ciudadCuentaAgrario;
	}
	public void setCiudadCuentaAgrario(String ciudadCuentaAgrario) {
		this.ciudadCuentaAgrario = ciudadCuentaAgrario;
	}
	public String getDepartamentoCuentaAgrario() {
		return departamentoCuentaAgrario;
	}
	public void setDepartamentoCuentaAgrario(String departamentoCuentaAgrario) {
		this.departamentoCuentaAgrario = departamentoCuentaAgrario;
	}
	public Boolean getEmbargoProcesado() {
		return embargoProcesado;
	}
	public void setEmbargoProcesado(Boolean embargoProcesado) {
		this.embargoProcesado = embargoProcesado;
	}
	public ArrayList<Demandado> getDemandados() {
		return demandados;
	}
	public void setDemandados(ArrayList<Demandado> demandados) {
		this.demandados = demandados;
	}

	@Override
	public String toString() {
		return "Embargo [idEmbargo=" + idEmbargo + ", numProceso=" + numProceso + ", tipoEmbargo=" + tipoEmbargo
				+ ", numCuentaAgrario=" + numCuentaAgrario + ", ciudadCuentaAgrario=" + ciudadCuentaAgrario
				+ ", departamentoCuentaAgrario=" + departamentoCuentaAgrario + ", embargoProcesado=" + embargoProcesado
				+ "]";
	}


}
