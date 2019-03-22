package util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import modelo.*;

public class Functions {

	public static void embargarCuentasAhorroJudicialLimite(ArrayList<Persona> personas, ArrayList<Cuenta> cuentas,
			BigDecimal limite) {
		for (Persona persona : personas) {
			BigDecimal montoPorEmbargar;
			if (persona.getMontoAEmbargar().compareTo(persona.getMontoEmbargado()) == 1) {
				if (persona.getMontoPorEmbargar().compareTo(new BigDecimal(0)) > 0) {
					montoPorEmbargar = persona.getMontoPorEmbargar();
				} else {
					montoPorEmbargar = persona.getMontoAEmbargar();
				}
				BigDecimal saldoExcedente = cuentas.stream()
						.filter(c -> c.getIdPersona().equalsIgnoreCase(persona.getIdPersona()))
						.map(Cuenta::getSaldoCuentaFecha).reduce(BigDecimal::add).orElse(null);
				if (saldoExcedente != null) {
					if (saldoExcedente.compareTo(limite) > 0) {
						BigDecimal montoSinBenificio = saldoExcedente.subtract(limite);
						BigDecimal montoAEmbargar;
						if (montoPorEmbargar.compareTo(montoSinBenificio) > 0) {
							montoAEmbargar = montoSinBenificio;
						} else {
							montoAEmbargar = montoPorEmbargar;
						}
						for (int i = 0; i < cuentas.size() && montoAEmbargar.compareTo(new BigDecimal(0)) > 0; i++) {
							Cuenta cuenta = cuentas.get(i);
							if (cuenta.getIdPersona().equals(persona.getIdPersona())) {
								if (montoAEmbargar.compareTo(cuenta.getSaldoCuentaFecha()) > 0) {
									cuenta.setMontoEmbargado(cuenta.getSaldoCuentaFecha());
									cuenta.getReglas().add("Carta circular 64 de 2018 por la SuperFinanciera");
									persona.setMontoEmbargado(
											cuenta.getSaldoCuentaFecha().add(persona.getMontoEmbargado()));
									montoAEmbargar = montoAEmbargar.subtract(cuenta.getSaldoCuentaFecha());
									cuenta.setSaldoCuentaFecha(
											cuenta.getSaldoCuentaFecha().subtract(cuenta.getMontoEmbargado()));
								} else {
									cuenta.setMontoEmbargado(montoAEmbargar);
									cuenta.getReglas().add("Carta circular 64 de 2018 por la SuperFinanciera");
									persona.setMontoEmbargado(montoAEmbargar.add(persona.getMontoEmbargado()));
									cuenta.setSaldoCuentaFecha(
											cuenta.getSaldoCuentaFecha().subtract(cuenta.getMontoEmbargado()));
									montoAEmbargar = montoAEmbargar.subtract(montoAEmbargar);
								}
							}
						}
					} else {
						cuentas.stream().filter(c -> c.getIdPersona().equalsIgnoreCase(persona.getIdPersona()))
								.forEach(c -> c.getReglas().add("Carta circular 64 de 2018 por la SuperFinanciera"));
					}
					persona.setMontoPorEmbargar(persona.getMontoAEmbargar().subtract(persona.getMontoEmbargado()));
				}
			}
		}
	}

	public static void embargarCuentasAhorroCoactivoLimite(ArrayList<Persona> personas, ArrayList<Cuenta> cuentas,
			BigDecimal limite) {
		for (Persona persona : personas) {
			BigDecimal montoAEmbargar;
			if (persona.getMontoAEmbargar().compareTo(persona.getMontoEmbargado()) == 1) {
				if (persona.getMontoPorEmbargar().compareTo(new BigDecimal(0)) > 0) {
					montoAEmbargar = persona.getMontoPorEmbargar();
				} else {
					montoAEmbargar = persona.getMontoAEmbargar();
				}
				Comparator<Cuenta> comparator = Comparator.comparing(Cuenta::getFechaCreacion);
				Cuenta cuentaProtegida = cuentas.stream()
						.filter(c -> c.getIdPersona().equalsIgnoreCase(persona.getIdPersona())).min(comparator)
						.orElse(null);
				for (int i = 0; i < cuentas.size() && montoAEmbargar.compareTo(new BigDecimal(0)) > 0; i++) {
					Cuenta cuenta = cuentas.get(i);
					if (cuenta.getIdPersona().equals(persona.getIdPersona())) {
						cuenta.getReglas().add("Carta circular 65 de 2018 por la SuperFinanciera");
						if (cuenta.equals(cuentaProtegida)) {
							if (cuenta.getSaldoCuentaFecha().compareTo(limite) > 0) {
								BigDecimal montoDisponible = cuenta.getSaldoCuentaFecha().subtract(limite);
								if (montoAEmbargar.compareTo(montoDisponible) > 0) {
									cuenta.setMontoEmbargado(montoDisponible);
									persona.setMontoEmbargado(montoDisponible.add(persona.getMontoEmbargado()));
									montoAEmbargar = montoAEmbargar.subtract(montoDisponible);
									cuenta.setSaldoCuentaFecha(
											cuenta.getSaldoCuentaFecha().subtract(cuenta.getMontoEmbargado()));
								} else {
									cuenta.setMontoEmbargado(montoAEmbargar);
									persona.setMontoEmbargado(montoAEmbargar.add(persona.getMontoEmbargado()));
									montoAEmbargar = montoAEmbargar.subtract(montoAEmbargar);
									cuenta.setSaldoCuentaFecha(
											cuenta.getSaldoCuentaFecha().subtract(cuenta.getMontoEmbargado()));
								}
							}
						} else {
							if (montoAEmbargar.compareTo(cuenta.getSaldoCuentaFecha()) > 0) {
								cuenta.setMontoEmbargado(cuenta.getSaldoCuentaFecha());
								persona.setMontoEmbargado(
										cuenta.getSaldoCuentaFecha().add(persona.getMontoEmbargado()));
								montoAEmbargar = montoAEmbargar.subtract(cuenta.getSaldoCuentaFecha());
								cuenta.setSaldoCuentaFecha(
										cuenta.getSaldoCuentaFecha().subtract(cuenta.getMontoEmbargado()));
							} else {
								cuenta.setMontoEmbargado(montoAEmbargar);
								persona.setMontoEmbargado(montoAEmbargar.add(persona.getMontoEmbargado()));
								montoAEmbargar = montoAEmbargar.subtract(montoAEmbargar);
								cuenta.setSaldoCuentaFecha(
										cuenta.getSaldoCuentaFecha().subtract(cuenta.getMontoEmbargado()));
							}
						}
					}
				}
				persona.setMontoPorEmbargar(persona.getMontoAEmbargar().subtract(persona.getMontoEmbargado()));
			}
		}
	}

	public static void embargarCuentasLimite(ArrayList<Persona> personas, ArrayList<Cuenta> cuentas,
			ArrayList<String> reglas, BigDecimal limite) {
		for (Persona persona : personas) {
			if (persona.getMontoAEmbargar().compareTo(persona.getMontoEmbargado()) == 1) {
				BigDecimal montoAEmbargar;
				if (persona.getMontoPorEmbargar().compareTo(new BigDecimal(0)) > 0) {
					montoAEmbargar = persona.getMontoPorEmbargar();
				} else {
					montoAEmbargar = persona.getMontoAEmbargar();
				}
				for (int i = 0; i < cuentas.size() && montoAEmbargar.compareTo(new BigDecimal(0)) > 0; i++) {
					Cuenta cuenta = cuentas.get(i);
					if (cuenta.getIdPersona().equals(persona.getIdPersona())) {
						if (cuenta.getSaldoCuentaFecha().compareTo(limite) > 0) {
							BigDecimal monto = cuenta.getSaldoCuentaFecha().subtract(limite);
							if (montoAEmbargar.compareTo(monto) > 0) {
								cuenta.setMontoEmbargado(monto);
								persona.setMontoEmbargado(monto.add(persona.getMontoEmbargado()));
								for (String regla : reglas) {
									cuenta.getReglas().add(regla);
								}
								montoAEmbargar = montoAEmbargar.subtract(monto);
								cuenta.setSaldoCuentaFecha(
										cuenta.getSaldoCuentaFecha().subtract(cuenta.getMontoEmbargado()));
							} else {
								cuenta.setMontoEmbargado(montoAEmbargar);
								persona.setMontoEmbargado(montoAEmbargar.add(persona.getMontoEmbargado()));
								for (String regla : reglas) {
									cuenta.getReglas().add(regla);
								}
								cuenta.setSaldoCuentaFecha(
										cuenta.getSaldoCuentaFecha().subtract(cuenta.getMontoEmbargado()));
								montoAEmbargar = montoAEmbargar.subtract(montoAEmbargar);
							}
						} else {
							for (String regla : reglas) {
								cuenta.getReglas().add(regla);
							}
						}
					}
				}
				persona.setMontoPorEmbargar(persona.getMontoAEmbargar().subtract(persona.getMontoEmbargado()));
			}
		}
	}

	public static void agregarLeyes(ArrayList<Cuenta> cuentas) {
    	for(Cuenta o:cuentas){
    		o.getReglas().add("Artículo 25 ley 1751 de 2015 ");
	    	o.getReglas().add("Decreto 780 de 2016 ARTÍCULO 2.6.4.1.4 ");
	    	o.getReglas().add("Numeral 1 Artículo 594 código general del proceso Ley 1564 de 2012 ");
	    	o.getReglas().add("Numeral 4 Artículo 594 código general del proceso Ley 1564 de 2012 ");
	    	o.getReglas().add("Numeral 5 Artículo 594 código general del proceso Ley 1564 de 2012 ");
	    	o.getReglas().add("Artículo 91 de la Ley 715 de 2001");
	    	o.getReglas().add("Artículo 8 Decreto 050 de 2003");
    	}  
	}

}
