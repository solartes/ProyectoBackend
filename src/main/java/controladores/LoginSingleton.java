package controladores;

public class LoginSingleton {

	private String idAutoridad;
	private static LoginSingleton myConfig = null;

	// Private constructor suppresses
	private LoginSingleton(String idAutoridad) {
		this.idAutoridad=idAutoridad;
	}

	// creador sincronizado para protegerse de posibles problemas multi-hilo
	// otra prueba para evitar instanciación múltiple
	private synchronized static void createInstance(String idAutoridad) {
		if (myConfig == null) {
			myConfig = new LoginSingleton(idAutoridad);
		}
	}

	public static LoginSingleton getInstance(String idAutoridad) {
		if (myConfig == null) {
			createInstance(idAutoridad);
		}
		return myConfig;
	}
	
	public static LoginSingleton getInstance() {		
		return myConfig;
	}
}
