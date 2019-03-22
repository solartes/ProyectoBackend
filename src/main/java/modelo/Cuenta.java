package modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import enumeraciones.EstadoCuenta;
import enumeraciones.SubtipoCuenta;
import enumeraciones.TipoCuenta;

public class Cuenta {
	
	private String idCuenta;
	private String idPersona;
	private String idEmbargo;
	private TipoCuenta tipo;
	private SubtipoCuenta subtipo;
	private LocalDate fechaCreacion;
	private BigDecimal montoEmbargado;
	private BigDecimal saldoCuentaFecha;
	private EstadoCuenta estado;
	private ArrayList<String> reglas;
	
	
	public Cuenta(String idCuenta, String idPersona, String idEmbargo, TipoCuenta tipo, SubtipoCuenta subtipo,
			LocalDate fechaCreacion,BigDecimal saldoCuentaFecha, EstadoCuenta estado) {
		super();
		this.idCuenta = idCuenta;
		this.idPersona = idPersona;
		this.idEmbargo = idEmbargo;
		this.tipo = tipo;
		this.subtipo = subtipo;
		this.fechaCreacion = fechaCreacion;
		this.montoEmbargado = new BigDecimal(0);
		this.saldoCuentaFecha = saldoCuentaFecha;
		this.estado = estado;
		this.reglas = new ArrayList<>();
	}

	
	public Cuenta() {
	}

	public String getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
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
	public TipoCuenta getTipo() {
		return tipo;
	}
	public void setTipo(TipoCuenta tipo) {
		this.tipo = tipo;
	}
	public SubtipoCuenta getSubtipo() {
		return subtipo;
	}
	public void setSubtipo(SubtipoCuenta subtipo) {
		this.subtipo = subtipo;
	}
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public BigDecimal getMontoEmbargado() {
		return montoEmbargado;
	}
	public void setMontoEmbargado(BigDecimal montoEmbargado) {
		this.montoEmbargado = montoEmbargado;
	}
	public BigDecimal getSaldoCuentaFecha() {
		return saldoCuentaFecha;
	}
	public void setSaldoCuentaFecha(BigDecimal saldoCuentaFecha) {
		this.saldoCuentaFecha = saldoCuentaFecha;
	}
	public EstadoCuenta getEstado() {
		return estado;
	}
	public void setEstado(EstadoCuenta estado) {
		this.estado = estado;
	}
	public ArrayList<String> getReglas() {
		return reglas;
	}
	public void setReglas(ArrayList<String> reglas) {
		this.reglas = reglas;
	}
	
}
