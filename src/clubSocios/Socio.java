package clubSocios;

public class Socio {
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String fechaIncorporacion;
	
	// METODO CONSTRUCTOR
	public Socio(String nombre, String apellido1, String apellido2, String fechaIncorporacion) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.fechaIncorporacion = fechaIncorporacion;
	}
	
	// METODOS GET Y SET DE LA CLASE
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getFechaIncorporacion() {
		return fechaIncorporacion;
	}

	public void setFechaIncorporacion(String fechaIncorporacion) {
		this.fechaIncorporacion = fechaIncorporacion;
	}

	@Override
	public String toString() {
		return "Nombre = " + nombre + ", Apellido 1 = " + apellido1 + ", Apellido2 = " + apellido2
				+ ", Fecha Incorporacion = " + fechaIncorporacion + " ";
	}
}
