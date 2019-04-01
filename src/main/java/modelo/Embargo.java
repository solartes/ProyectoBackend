package modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import enumeraciones.TipoEmbargo;

public class Embargo {
	
	private String idEmbargo;
	private LocalDate fechaOficio;
	private TipoEmbargo tipo;
	private BigDecimal montoAEmbargar;
	
	public Embargo(String idEmbargo, LocalDate fechaOficio,TipoEmbargo tipo, BigDecimal montoAEmbargar) {
		super();
		this.idEmbargo = idEmbargo;
		this.fechaOficio = fechaOficio;
		this.tipo = tipo;
		this.montoAEmbargar = montoAEmbargar;
	}
	
	public String getIdEmbargo() {
		return idEmbargo;
	}
	public void setIdEmbargo(String idEmbargo) {
		this.idEmbargo = idEmbargo;
	}
	
	public LocalDate getFechaOficio() {
		return fechaOficio;
	}
	public void setFechaOficio(LocalDate fechaOficio) {
		this.fechaOficio = fechaOficio;
	}

	public TipoEmbargo getTipo() {
		return tipo;
	}
	public void setTipo(TipoEmbargo tipo) {
		this.tipo = tipo;
	}
	
	public BigDecimal getMontoAEmbargar() {
		return montoAEmbargar;
	}

	public void setMontoAEmbargar(BigDecimal montoAEmbargar) {
		this.montoAEmbargar = montoAEmbargar;
	}

}
