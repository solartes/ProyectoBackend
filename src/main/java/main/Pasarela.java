package main;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import enumeraciones.*;
import enumeraciones.EstadoCuenta;
import modelo.Cuenta;

public class Pasarela {
	ArrayList<TipoCuenta> tiposCuenta;
	ArrayList<SubtipoCuenta> subtiposCuenta;
	ArrayList<TipoEmbargo> tiposEmbargo;
	
	
	
	public Pasarela() {
		super();
		tiposCuenta=new ArrayList<>();
		subtiposCuenta=new ArrayList<>();
		tiposEmbargo=new ArrayList<>();
		tiposCuenta.add(TipoCuenta.AHORROS);
		tiposCuenta.add(TipoCuenta.CORRIENTE);
		tiposCuenta.add(TipoCuenta.CDT);
		tiposCuenta.add(TipoCuenta.CDAT);
		tiposCuenta.add(TipoCuenta.ELECTRONICOS);
		subtiposCuenta.add(SubtipoCuenta.PENSION);
		subtiposCuenta.add(SubtipoCuenta.NOMINA);
		subtiposCuenta.add(SubtipoCuenta.BASICA);
		subtiposCuenta.add(SubtipoCuenta.PUBLICO);
		tiposEmbargo.add(TipoEmbargo.FAMILIAR);
		tiposEmbargo.add(TipoEmbargo.JUDICIAL);
		tiposEmbargo.add(TipoEmbargo.COACTIVO);
		tiposEmbargo.add(TipoEmbargo.COOPERATIVA);
	}



	public ArrayList<Cuenta> consulta(String idPersona, String idEmbargo){
		int randomNum = ThreadLocalRandom.current().nextInt(0, 5 + 1);		
		ArrayList<Cuenta> cuentas=new ArrayList<>();
		for (int i = 0; i < randomNum; i++) {
		    long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
		    long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
		    long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
		    LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
			BigDecimal min=new BigDecimal(0);
			BigDecimal max=new BigDecimal(70000000);
		    BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
		    randomBigDecimal=randomBigDecimal.setScale(0,BigDecimal.ROUND_HALF_UP);		    
			cuentas.add(new Cuenta(Integer.toString(ThreadLocalRandom.current().nextInt(0,99999999)),idPersona,idEmbargo,tiposCuenta.get(ThreadLocalRandom.current().nextInt(0, tiposCuenta.size())), subtiposCuenta.get(ThreadLocalRandom.current().nextInt(0, subtiposCuenta.size())),randomDate,randomBigDecimal,EstadoCuenta.ACTIVA));
		}
		return cuentas;		
	}
}
