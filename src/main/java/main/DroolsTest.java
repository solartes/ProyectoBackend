package main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.drools.compiler.compiler.DroolsParserException;
import org.drools.core.WorkingMemory;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import datos.Data;
import enumeraciones.EstadoCuenta;
import enumeraciones.SubtipoCuenta;
import enumeraciones.TipoCuenta;
import enumeraciones.TipoEmbargo;
import enumeraciones.TipoIdentificacion;
import modelo.Cuenta;
import modelo.Embargo;
import modelo.Persona;

public class DroolsTest {

	public static void main(String[] args) throws DroolsParserException, IOException {
		DroolsTest droolsTest = new DroolsTest();
		droolsTest.executeDrools();
	}

	public void executeDrools() throws DroolsParserException, IOException {

		KieServices kServices = KieServices.Factory.get();

		KieFileSystem kfs = kServices.newKieFileSystem();
		KieRepository kr = kServices.getRepository();
		File file;
		try {
			file = new File(getClass().getResource("/com/rule/Rules.drl").toURI());
			Resource resource = kServices.getResources().newFileSystemResource(file).setResourceType(ResourceType.DRL);
			kfs.write(resource);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		KieBuilder kb = kServices.newKieBuilder(kfs);
		kb.buildAll();
		KieContainer kContainer = kServices.newKieContainer(kr.getDefaultReleaseId());
		KieSession sessionStatefull = kContainer.newKieSession();

		Data datos = new Data();
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
			BigDecimal montoPorEmbargar=persona.getMontoAEmbargar().subtract(persona.getMontoEmbargado());
			if(montoPorEmbargar.compareTo(new BigDecimal(0))==1) {
				System.out.println("La(s) cuenta(s) de la Persona con identificacion: "+persona.getIdPersona() 
									+" fueron bloqueada(s)");
				System.out.println("Por un faltante por embargar de: "+montoPorEmbargar);	
			}
			System.out.println("=============================================");
		}
	}
}
