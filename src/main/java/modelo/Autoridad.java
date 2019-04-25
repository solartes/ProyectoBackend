package modelo;

public class Autoridad {
	
	private String idAutoridad;
	private String tipoAutoridad;
	private String nombre;
	private String direccion;
	private String[] embargosRealizados;
	
	
	
	public Autoridad(String idAutoridad, String tipoAutoridad, String nombre, String direccion,
			String[] embargosRealizados) {
		super();
		this.idAutoridad = idAutoridad;
		this.tipoAutoridad = tipoAutoridad;
		this.nombre = nombre;
		this.direccion = direccion;
		this.embargosRealizados = embargosRealizados;
	}
	
	public Autoridad(String idAutoridad, String tipoAutoridad, String nombre, String direccion) {
		super();
		this.idAutoridad = idAutoridad;
		this.tipoAutoridad = tipoAutoridad;
		this.nombre = nombre;
		this.direccion = direccion;
	}
	
	public String getIdAutoridad() {
		return idAutoridad;
	}
	public void setIdAutoridad(String idAutoridad) {
		this.idAutoridad = idAutoridad;
	}
	public String getTipoAutoridad() {
		return tipoAutoridad;
	}
	public void setTipoAutoridad(String tipoAutoridad) {
		this.tipoAutoridad = tipoAutoridad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String[] getEmbargosRealizados() {
		return embargosRealizados;
	}
	public void setEmbargosRealizados(String[] embargosRealizados) {
		this.embargosRealizados = embargosRealizados;
	}
	
	
}
