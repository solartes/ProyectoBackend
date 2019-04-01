package modelo;

import java.math.BigDecimal;

import enumeraciones.TipoIdentificacion;

public class Persona {
	
	private String idPersona;
	private String idEmbargo;
	private TipoIdentificacion tipoId;
	private BigDecimal montoAEmbargar;
	private BigDecimal montoEmbargado;
	
	
	
	public Persona(String idPersona, String idEmbargo, TipoIdentificacion tipoId, BigDecimal montoAEmbargar) {
		super();
		this.idPersona = idPersona;
		this.idEmbargo = idEmbargo;
		this.tipoId = tipoId;
		this.montoAEmbargar = montoAEmbargar;
		this.montoEmbargado= new BigDecimal(0);
		
		
	}
	public String getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}
	public String getIdEmbargo() {
		return idEmbargo;
	}
	public void setIdEmbargo(String idEmbargo) {
		this.idEmbargo = idEmbargo;
	}
	public TipoIdentificacion getTipoId() {
		return tipoId;
	}
	public void setTipoId(TipoIdentificacion tipoId) {
		this.tipoId = tipoId;
	}
	public BigDecimal getMontoAEmbargar() {
		return montoAEmbargar;
	}
	public void setMontoAEmbargar(BigDecimal montoAEmbargar) {
		this.montoAEmbargar = montoAEmbargar;
	}
	public BigDecimal getMontoEmbargado() {
		return montoEmbargado;
	}
	public void setMontoEmbargado(BigDecimal montoEmbargado) {
		this.montoEmbargado = montoEmbargado;
	}
	

	
	
	
	
	
	
	
	
}
