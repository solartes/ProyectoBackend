package modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import enumeraciones.TipoEmbargo;

public class EmbargoCoactivo extends Embargo {

	private String numOficio;

	public EmbargoCoactivo(String idEmbargo, String idAutoridad, String numProceso, LocalDate fechaOficio,
			TipoEmbargo tipoEmbargo, String numCuentaAgrario, String ciudadCuentaAgrario,
			String departamentoCuentaAgrario, ArrayList<Demandado> demandados, String numOficio) {
		super(idEmbargo, idAutoridad, numProceso, fechaOficio, tipoEmbargo, numCuentaAgrario, ciudadCuentaAgrario,
				departamentoCuentaAgrario, demandados);
		this.numOficio = numOficio;
	}
	
	

	public EmbargoCoactivo(String idEmbargo, String idAutoridad, String numProceso, LocalDate fechaOficio,
			TipoEmbargo tipoEmbargo, String numCuentaAgrario, String ciudadCuentaAgrario,
			String departamentoCuentaAgrario, ArrayList<Demandado> demandados) {
		super(idEmbargo, idAutoridad, numProceso, fechaOficio, tipoEmbargo, numCuentaAgrario, ciudadCuentaAgrario,
				departamentoCuentaAgrario, demandados);
	}

	public String getNumOficio() {
		return numOficio;
	}

	public void setNumOficio(String numOficio) {
		this.numOficio = numOficio;
	}

}
