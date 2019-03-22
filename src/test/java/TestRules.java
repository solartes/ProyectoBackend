import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;


import util.*;
import modelo.*;
import enumeraciones.*;
import datos.*;

public class TestRules {
	
	static KieContainer kieContainer;
    StatelessKieSession sessionStateless = null;
    KieSession sessionStatefull = null;

    @BeforeClass
    public static void beforeClass() {
        kieContainer = KnowledgeSessionHelper.createRuleBase();
    }
	
    /*
	@Test
	public void testCuentasJudicialLimite(){
		
		sessionStatefull= KnowledgeSessionHelper
				.getStatefulKnowledgeSession(kieContainer,"ksession-rules");
		Data datos=new Data();
        sessionStatefull.setGlobal("datos", datos);
        Embargo embargo=new Embargo("e1",LocalDate.of(2017, 11, 24),TipoEmbargo.JUDICIAL,new BigDecimal(18000000));
        sessionStatefull.insert(embargo);
        ArrayList<Persona> personas=new ArrayList<>();
        personas.add(new Persona("p1", "e1",TipoIdentificacion.NATURAL, new BigDecimal(6000000)));
        personas.add(new Persona("p2", "e1",TipoIdentificacion.NATURAL, new BigDecimal(6000000)));
        personas.add(new Persona("p3", "e1",TipoIdentificacion.JURIDICA, new BigDecimal(6000000)));
        personas.stream().forEach(x->sessionStatefull.insert(x));
        ArrayList<Cuenta> cuentas=new ArrayList<>();
        //p1
        cuentas.add(new Cuenta("c1","p1","e1",TipoCuenta.AHORROS,SubtipoCuenta.BASICA,LocalDate.of(2016, 05, 26),
        						new BigDecimal(35000000),EstadoCuenta.ACTIVA));
        cuentas.add(new Cuenta("c2","p1","e1",TipoCuenta.AHORROS,SubtipoCuenta.BASICA,LocalDate.of(2016, 05, 26),
								new BigDecimal(3000000),EstadoCuenta.ACTIVA));
        cuentas.add(new Cuenta("c3","p1","e1",TipoCuenta.CORRIENTE,SubtipoCuenta.BASICA,LocalDate.of(2016, 05, 26),
								new BigDecimal(2000000),EstadoCuenta.ACTIVA));
        //p2
        cuentas.add(new Cuenta("c4","p2","e1",TipoCuenta.AHORROS,SubtipoCuenta.BASICA,LocalDate.of(2016, 05, 26),
								new BigDecimal(3000000),EstadoCuenta.ACTIVA));
        cuentas.add(new Cuenta("c5","p2","e1",TipoCuenta.CDT,SubtipoCuenta.BASICA,LocalDate.of(2016, 05, 26),
								new BigDecimal(1000000),EstadoCuenta.ACTIVA));
        cuentas.add(new Cuenta("c6","p2","e1",TipoCuenta.CDAT,SubtipoCuenta.BASICA,LocalDate.of(2016, 05, 26),
								new BigDecimal(1000000),EstadoCuenta.ACTIVA));
        cuentas.add(new Cuenta("c7","p2","e1",TipoCuenta.ELECTRONICOS,SubtipoCuenta.PUBLICO,LocalDate.of(2016, 05, 26),
								new BigDecimal(1000000),EstadoCuenta.ACTIVA));
        cuentas.stream().forEach(x->sessionStatefull.insert(x));
        //p3
        cuentas.add(new Cuenta("c8","p3","e1",TipoCuenta.AHORROS,SubtipoCuenta.BASICA,LocalDate.of(2014, 05, 26),
        						new BigDecimal(37000000),EstadoCuenta.ACTIVA));
        cuentas.add(new Cuenta("c9","p3","e1",TipoCuenta.AHORROS,SubtipoCuenta.BASICA,LocalDate.of(2015, 05, 26),
								new BigDecimal(3000000),EstadoCuenta.ACTIVA));
        cuentas.stream().forEach(x->sessionStatefull.insert(x));
        sessionStatefull.fireAllRules();
        
        imprimir(embargo, personas, cuentas);
	}
	*/
	
    /*
	@Test
	public void testCuentasCoactivoLimite(){
		
		sessionStatefull= KnowledgeSessionHelper
				.getStatefulKnowledgeSession(kieContainer,"ksession-rules");
		Data datos=new Data();
        sessionStatefull.setGlobal("datos", datos);
        Embargo embargo=new Embargo("e1",LocalDate.of(2018, 11, 24),TipoEmbargo.COACTIVO,new BigDecimal(18000000));
        sessionStatefull.insert(embargo);
        ArrayList<Persona> personas=new ArrayList<>();
        personas.add(new Persona("p1", "e1",TipoIdentificacion.NATURAL, new BigDecimal(6000000)));
        personas.add(new Persona("p2", "e1",TipoIdentificacion.NATURAL, new BigDecimal(6000000)));
        personas.add(new Persona("p3", "e1",TipoIdentificacion.JURIDICA, new BigDecimal(6000000)));
        personas.stream().forEach(x->sessionStatefull.insert(x));
        ArrayList<Cuenta> cuentas=new ArrayList<>();
        //p1
        cuentas.add(new Cuenta("c1","p1","e1",TipoCuenta.AHORROS,SubtipoCuenta.BASICA,LocalDate.of(2014, 05, 26),
        						new BigDecimal(17000000),EstadoCuenta.ACTIVA));
        cuentas.add(new Cuenta("c2","p1","e1",TipoCuenta.AHORROS,SubtipoCuenta.BASICA,LocalDate.of(2015, 05, 26),
								new BigDecimal(3000000),EstadoCuenta.ACTIVA));
        cuentas.add(new Cuenta("c3","p1","e1",TipoCuenta.CORRIENTE,SubtipoCuenta.BASICA,LocalDate.of(2016, 05, 26),
								new BigDecimal(2000000),EstadoCuenta.ACTIVA));
        //p2
        cuentas.add(new Cuenta("c4","p2","e1",TipoCuenta.AHORROS,SubtipoCuenta.BASICA,LocalDate.of(2016, 05, 26),
								new BigDecimal(3000000),EstadoCuenta.ACTIVA));
        cuentas.add(new Cuenta("c5","p2","e1",TipoCuenta.CDT,SubtipoCuenta.BASICA,LocalDate.of(2016, 05, 26),
								new BigDecimal(1000000),EstadoCuenta.ACTIVA));
        cuentas.add(new Cuenta("c6","p2","e1",TipoCuenta.CDAT,SubtipoCuenta.BASICA,LocalDate.of(2016, 05, 26),
								new BigDecimal(1000000),EstadoCuenta.ACTIVA));
        cuentas.add(new Cuenta("c7","p2","e1",TipoCuenta.ELECTRONICOS,SubtipoCuenta.PUBLICO,LocalDate.of(2016, 05, 26),
								new BigDecimal(1000000),EstadoCuenta.ACTIVA)); 
        //p3
        cuentas.add(new Cuenta("c8","p3","e1",TipoCuenta.AHORROS,SubtipoCuenta.BASICA,LocalDate.of(2014, 05, 26),
        						new BigDecimal(37000000),EstadoCuenta.ACTIVA));
        cuentas.add(new Cuenta("c9","p3","e1",TipoCuenta.AHORROS,SubtipoCuenta.BASICA,LocalDate.of(2015, 05, 26),
								new BigDecimal(3000000),EstadoCuenta.ACTIVA));
        cuentas.stream().forEach(x->sessionStatefull.insert(x));
        sessionStatefull.fireAllRules();
        imprimir(embargo, personas, cuentas);
	}
	*/
	
	@Test
	public void testCuentasAlimentos(){
		
		sessionStatefull= KnowledgeSessionHelper
				.getStatefulKnowledgeSession(kieContainer,"ksession-rules");
		Data datos=new Data();
        sessionStatefull.setGlobal("datos", datos);
		
        Embargo embargo=new Embargo("e1",LocalDate.of(2018, 11, 24),TipoEmbargo.FAMILIAR,new BigDecimal(12000000));
        sessionStatefull.insert(embargo);
        ArrayList<Persona> personas=new ArrayList<>();
        personas.add(new Persona("p1", "e1",TipoIdentificacion.NATURAL, new BigDecimal(6000000)));
        personas.add(new Persona("p2", "e1",TipoIdentificacion.NATURAL, new BigDecimal(6000000)));
        personas.add(new Persona("p3", "e1",TipoIdentificacion.JURIDICA, new BigDecimal(6000000)));
        personas.stream().forEach(x->sessionStatefull.insert(x));
        ArrayList<Cuenta> cuentas=new ArrayList<>();
        //p1
        cuentas.add(new Cuenta("c1","p1","e1",TipoCuenta.AHORROS,SubtipoCuenta.BASICA,LocalDate.of(2016, 05, 26),
        						new BigDecimal(35000000),EstadoCuenta.ACTIVA));
        cuentas.add(new Cuenta("c2","p1","e1",TipoCuenta.AHORROS,SubtipoCuenta.BASICA,LocalDate.of(2016, 05, 26),
								new BigDecimal(3000000),EstadoCuenta.ACTIVA));
        cuentas.add(new Cuenta("c3","p1","e1",TipoCuenta.CORRIENTE,SubtipoCuenta.BASICA,LocalDate.of(2016, 05, 26),
								new BigDecimal(2000000),EstadoCuenta.ACTIVA));
        //p2
        cuentas.add(new Cuenta("c4","p2","e1",TipoCuenta.AHORROS,SubtipoCuenta.BASICA,LocalDate.of(2016, 05, 26),
								new BigDecimal(3000000),EstadoCuenta.ACTIVA));
        cuentas.add(new Cuenta("c5","p2","e1",TipoCuenta.CDT,SubtipoCuenta.BASICA,LocalDate.of(2016, 05, 26),
								new BigDecimal(1000000),EstadoCuenta.ACTIVA));
        cuentas.add(new Cuenta("c6","p2","e1",TipoCuenta.CDAT,SubtipoCuenta.BASICA,LocalDate.of(2016, 05, 26),
								new BigDecimal(1000000),EstadoCuenta.ACTIVA));
        cuentas.add(new Cuenta("c7","p2","e1",TipoCuenta.ELECTRONICOS,SubtipoCuenta.PUBLICO,LocalDate.of(2016, 05, 26),
								new BigDecimal(1000000),EstadoCuenta.ACTIVA));
        cuentas.stream().forEach(x->sessionStatefull.insert(x));
        //p3
        cuentas.add(new Cuenta("c8","p3","e1",TipoCuenta.AHORROS,SubtipoCuenta.BASICA,LocalDate.of(2014, 05, 26),
        						new BigDecimal(37000000),EstadoCuenta.ACTIVA));
        cuentas.add(new Cuenta("c9","p3","e1",TipoCuenta.AHORROS,SubtipoCuenta.BASICA,LocalDate.of(2015, 05, 26),
								new BigDecimal(3000000),EstadoCuenta.ACTIVA));
        cuentas.stream().forEach(x->sessionStatefull.insert(x));
        sessionStatefull.fireAllRules();
        
        imprimir(embargo, personas, cuentas);
        
	}
	
	
	public void imprimir(Embargo embargo,ArrayList<Persona> personas,ArrayList<Cuenta> cuentas) {
		
		
		System.out.println("Embargo de tipo: "+ embargo.getTipo()+" por un valor: "+ embargo.getMontoAEmbargar());
		System.out.println("=============================================");
		for(Persona persona:personas) {
			System.out.println("Persona: "+ persona.getTipoId()+ " con identificacion: "+persona.getIdPersona());
			System.out.println("Con un monto a Embargar de: "+persona.getMontoAEmbargar());
			System.out.println("La(s) siguiente(s) cuenta(s)  ");
			System.out.println("-------------------------------------------------");
			if(persona.getMontoAEmbargar().compareTo(persona.getMontoEmbargado())==1) {
				cuentas.stream().filter(c -> c.getIdPersona().equalsIgnoreCase(persona.getIdPersona())).
				forEach(c->c.setEstado(EstadoCuenta.BLOQUEADA));		
			}
			for(Cuenta cuenta:cuentas) {
				if(cuenta.getIdPersona().equals(persona.getIdPersona())) {
					if(persona.getMontoEmbargado().compareTo(new BigDecimal(0))>0) {					
						System.out.println("La cuenta:"+cuenta.getIdCuenta() +" de:"+ cuenta.getTipo()
											+" de "+ cuenta.getSubtipo() );
						System.out.println("Fecha de creacion: "+cuenta.getFechaCreacion());
						System.out.println("Embargada por un monto de: "+cuenta.getMontoEmbargado());
						System.out.println("Con saldo a la fecha de:"+cuenta.getSaldoCuentaFecha());
						System.out.println(" Estado de la cuenta: "+cuenta.getEstado());
						System.out.println("  en base a las siguientes leyes:");		
					}else {
						System.out.println("La cuenta:"+cuenta.getIdCuenta() +" de:"+ cuenta.getTipo()
						+" de "+ cuenta.getSubtipo() );
						System.out.println("Con saldo a la fecha de:"+cuenta.getSaldoCuentaFecha());
						System.out.println("No se puede embargar");
						System.out.println("Estado de la cuenta: "+cuenta.getEstado());
						if(cuenta.getSaldoCuentaFecha().compareTo(new BigDecimal(0))==0){System.out.println("Saldo insuficiente");}
						System.out.println("  en base a las siguientes leyes:");
					}
					for(String regla:cuenta.getReglas()) {
			    		System.out.println("\t"+regla);
			    	}
			    	System.out.println("-------------------------------------------------");
				}		
			}
			if(persona.getMontoPorEmbargar().compareTo(new BigDecimal(0))==1) {
				System.out.println("La(s) cuenta(s) de la Persona con identificacion: "+persona.getIdPersona() 
									+" fueron bloqueada(s)");
				System.out.println("Por un faltante por embargar de: "+persona.getMontoPorEmbargar());	
			}
			System.out.println("=============================================");
		}
	}
	
}
