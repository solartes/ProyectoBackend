package modelo;

import java.math.BigDecimal;
import java.util.ArrayList;

import enumeraciones.TipoIdentificacion;
import util.Exclude;

public class Demandado extends Persona {

	private BigDecimal montoAEmbargar;
	private BigDecimal montoEmbargado;
	private ArrayList<Intento> intentos;
	@Exclude
	private ArrayList<Cuenta> cuentas;
	
	public Demandado(String identificacion, String nombres, String apellidos,
			TipoIdentificacion tipoIdentificacion, BigDecimal montoAEmbargar,ArrayList<Cuenta> cuentas) {
		super(identificacion,nombres, apellidos, tipoIdentificacion);
		// TODO Auto-generated constructor stub
		this.montoAEmbargar = montoAEmbargar;
		this.montoEmbargado = new BigDecimal(0);
		this.cuentas= cuentas;
	}

	public Demandado(String identificacion, String nombres, String apellidos,
			TipoIdentificacion tipoIdentificacion, BigDecimal montoAEmbargar) {
		super(identificacion,nombres, apellidos, tipoIdentificacion);
		// TODO Auto-generated constructor stub
		this.montoAEmbargar = montoAEmbargar;
		this.montoEmbargado = new BigDecimal(0);
		this.cuentas= new ArrayList<Cuenta>();
	}
	
	

	public ArrayList<Intento> getIntentos() {
		return intentos;
	}

	public void setIntentos(ArrayList<Intento> intentos) {
		this.intentos = intentos;
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

	public ArrayList<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(ArrayList<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	
	
	

}
