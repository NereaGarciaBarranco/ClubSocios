package clubSocios;

public class Club {
	private String nombre;
	private SimpleLinkedListSocio socios = new SimpleLinkedListSocio();

	// METODOS CONSTRUCTORES
	public Club(String nombre, SimpleLinkedListSocio socios) {
		super();
		this.nombre = nombre;
		this.socios = socios; 
	}
	
	// Constructor solo con nombre, para introducir despues los socios
	public Club (String nombre) {
		this.nombre = nombre;
	}
	
	// METODOS GET Y SET
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public SimpleLinkedListSocio getSocios() {
		return socios;
	}

	public void setSocios(SimpleLinkedListSocio socios) {
		this.socios = socios;
	}

	@Override
	public String toString() {
		return  nombre;
	}
}
