package modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import enumeraciones.TipoEmbargo;

public class Embargo {

	private String idEmbargo;
	private String idAutoridad;
	private String numProceso;
	private String numOficio;
	private LocalDate fechaOficio;
	private TipoEmbargo tipoEmbargo;
	private BigDecimal montoAEmbargar;
	private String numCuentaAgrario;
	private String ciudadCuentaAgrario;
	private String departamentoCuentaAgrario;
	private ArrayList<Demandante> demandantes;
	private ArrayList<Demandado> demandados;

	public Embargo(String idEmbargo, String idAutoridad, String numProceso, String numOficio, LocalDate fechaOficio,
			TipoEmbargo tipoEmbargo, BigDecimal montoAEmbargar, String numCuentaAgrario, String ciudadCuentaAgrario,
			String departamentoCuentaAgrario, ArrayList<Demandante> demandantes, ArrayList<Demandado> demandados) {
		super();
		this.idEmbargo = idEmbargo;
		this.idAutoridad = idAutoridad;
		this.numProceso = numProceso;
		this.numOficio = numOficio;
		this.fechaOficio = fechaOficio;
		this.tipoEmbargo = tipoEmbargo;
		this.montoAEmbargar = montoAEmbargar;
		this.numCuentaAgrario = numCuentaAgrario;
		this.ciudadCuentaAgrario = ciudadCuentaAgrario;
		this.departamentoCuentaAgrario = departamentoCuentaAgrario;
		this.demandantes = demandantes;
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

	public String getNumOficio() {
		return numOficio;
	}

	public void setNumOficio(String numOficio) {
		this.numOficio = numOficio;
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

	public void setTipoEmbargo(TipoEmbargo tipo) {
		this.tipoEmbargo = tipo;
	}

	public BigDecimal getMontoAEmbargar() {
		return montoAEmbargar;
	}

	public void setMontoAEmbargar(BigDecimal montoAEmbargar) {
		this.montoAEmbargar = montoAEmbargar;
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

	public ArrayList<Demandante> getDemandantes() {
		return demandantes;
	}

	public void setDemandantes(ArrayList<Demandante> demandantes) {
		this.demandantes = demandantes;
	}

	public ArrayList<Demandado> getDemandados() {
		return demandados;
	}

	public void setDemandados(ArrayList<Demandado> demandados) {
		this.demandados = demandados;
	}

}
