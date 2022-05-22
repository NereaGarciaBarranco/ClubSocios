package clubSocios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
	// Creacion de los objetos necesarios accesibles desde toda la clase
	static Scanner entrada = new Scanner(System.in);
	/*
	 * Creamos un objeto de tipo File con la ruta de la carpeta donde
	 * se almacenan todos los clubes.
	 */
	static String ruta = "C:/Users/Nerea/Desktop/Clubes";
	static File folder = new File(ruta);
	static SimpleLinkedListClub clubes = new SimpleLinkedListClub();
	static int clubEscogido = -1;
	
	/**
	 * Pre: ---
	 * Post: Este metodo principal se encarga de ejecutar el menu del gestor de
	 * los clubs de socios y de llamar a los metodos correspondientes para cada
	 * una de las funcionalidades del programa. 
	 */
	public static void main(String[] args) {
		// Cargamos todos los datos de los archivos de la carpeta
		findAllFilesInFolder(folder);
		System.out.println("¡Bienvenidos al gestor de Clubes de Socios!");
		// Con este bucle permitimos al usuario elegir lo que quiere hacer
		while (true) {
			mostrarMenu();
			// Comprobamos la opcion que ha elegido el usuario
			try {
				int respuesta = entrada.nextInt();
				if (respuesta == 1) {
					mostrarClubes();
					if (clubEscogido != -1) {
						elegirOpcionSubMenu();						
					}
				} else if (respuesta == 2) {
					agregarClub();
				} else if (respuesta == 3) {
					unirDosClubes();
				} else if (respuesta == 4) {
					break;
				} else {
					System.out.println("Introduce una opcion correcta, ¡por favor!");
				}
			} catch (InputMismatchException e) {
				System.out.println("Introduce un numero, ¡por favor!");
			}
		}
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo recorre todos los archivos de una carpeta
	 * de la que se conoce su ruta.
	 * @param folder
	 */
	public static void findAllFilesInFolder(File folder) {
		int posicionClub = 0;
		// Por cada uno de los ficheros llamamos al metodo que carga los datos
		for (File file : folder.listFiles()) {
			if (!file.isDirectory()) {
				cargarDatosFichero(file, posicionClub);
				posicionClub++;
			} else {
				findAllFilesInFolder(file);
			}
		}
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo se encarga de guardar en las listas correspondientes
	 * los clubes y los socios almacenados en los ficheros.
	 * @param file
	 */
	private static void cargarDatosFichero(File file, int posicionClub) {
		try {
			Scanner f = new Scanner(file);
			int contador = 0;
			// Mientras el fichero tenga lineas
			while (f.hasNextLine()) {
				String linea = f.nextLine();
				// Si estamos en la cabecera del .csv cogemos el nombre del club
				if (contador == 0) {
					// Añadimos la lista de socios vacia 
					SimpleLinkedListSocio socios = new SimpleLinkedListSocio();
					Club club = new Club (linea, socios);
					NodeClub node = new NodeClub(club, null);
					clubes.add(node);
				}
				/*
				 * En el resto de lineas tenemos los socios asi que vamos cogiendo
				 * sus datos, convirtiendolos en objetos de tipo Socio y añadiendolos
				 * a la lista simple enlazada de su club correspondiente.
				 */
				else {
					// Separamos la linea por el separador del .csv
					String[] datosSocio = linea.split(",");
					String nombre = datosSocio[0];
					String apellido1 = datosSocio[1];
					String apellido2 = datosSocio[2];
					String fechaIncorporacion = datosSocio[3];
					// Formamos un objeto de tipo Socio y lo convertimos en un nodo
					Socio socio = new Socio (nombre, apellido1, apellido2, fechaIncorporacion);
					NodeSocio nodeSocio = new NodeSocio(socio, null);
					// Hay que reprogramar el add para que sea alfabeticamente!! 
					clubes.get(posicionClub).getContent().getSocios().add(nodeSocio);
				}
				contador++;
			}
			f.close();
		} catch (FileNotFoundException e) {
			System.out.println("No se ha podido acceder al fichero.");
			e.printStackTrace();
		}		
	}
	
	/**
	 * Pre: ---
	 * Post Este metodo muestra por pantalla el menu del programa.
	 */
	public static void mostrarMenu() {
		System.out.println("\n¿Que deseas hacer?");
		System.out.println("Elegir un club existente [1]");
		System.out.println("Añadir un nuevo club (sin socios) [2]");
		System.out.println("Unir dos clubes [3]");
		System.out.println("Apagar [4]");
	}
	
	/**
	 * Pre: ---
	 * Post: Muestra los clubes existentes al usuario para que pueda elegir 
	 * uno de ellos mediante un numero y comprueba que ese numero exista.
	 */
	public static int mostrarClubes() {
		// Comprobamos si hay clubes
		if (clubes.getSize() > 0) {
			System.out.println("Elige un club:");
			for (int i = 0; i < clubes.getSize(); i++) {
				System.out.println("[" + i + "] " + clubes.get(i).toString());
			}
			while (true) {
				clubEscogido = entrada.nextInt();
				// Si introduce un codigo de club correcto
				if (clubEscogido <= clubes.getSize() && clubEscogido >= 0) {
					return clubEscogido;
				} else {
					System.out.println("Introduce un numero de club existente");
				}
			}			
		} 
		// Si no los hay mostramos un mensaje de advertencia y se vuelve al menu
		else {
			System.out.println("No hay ningun club registrado.");
			return -1;
		}
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo se encarga de crear un nuevo club introduciendo
	 * solo su nombre y se añade a la lista simple de clubes. Tambien se
	 * crea un archivo .csv con el mismo nombre que el club.
	 * @throws FileNotFoundException 
	 * @throws UnsupportedEncodingException 
	 */
	public static void agregarClub() {
		entrada.nextLine();
		System.out.println("Introduce el nombre del nuevo club:");
		String nombreClub = entrada.nextLine();
		// Con el nombre creamos un nuevo Club y lo añadimos a la lista simple
		SimpleLinkedListSocio socios = new SimpleLinkedListSocio();
		Club nuevoClub = new Club(nombreClub, socios);	
		NodeClub nuevoClubNode = new NodeClub (nuevoClub, null);
		clubes.add(nuevoClubNode);
		// Le añadimos la extension del archivo al nombre y lo creamos 
		nombreClub = nombreClub + ".csv";
		File f = new File(ruta, nombreClub);
		try {
			PrintWriter pw = new PrintWriter (f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// Avisamos de que se ha creado
		System.out.println("El club ha sido creado correctamente.");
		System.out.println("Si desea agregarle socios seleccionelo desde el menu.");
		actualizarFicherosClubes();
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo da a elegir al usuario dos clubes y mete los socios
	 * del segundo club escogido en el primero. Ademas, elimina el club dos
	 * en ambos casos. 
	 */
	private static void unirDosClubes() {
		entrada.nextLine();
		System.out.println("Elige el primer Club que quieres fusionar:");
		int idClubUno = mostrarClubes();
		System.out.println("Elige el segundo Club que quieres fusionar:");
		int idClubDos = mostrarClubes();
		Club clubDos = clubes.get(idClubDos).getContent();
		/*
		 * Comprobamos que el segundo club tenga socios porque sino lo unico que haremos
		 * sera eliminar el segundo club.
		 */
		if (!isClubEmpty(idClubDos, false)) {
			// Con un bucle recorremos los socios del club dos y los copiamos al uno
			for (int i = 0; i < clubDos.getSocios().getSize(); i++) {
				// Hacemos un Node con cada uno de los socios del club 2
				NodeSocio node = new NodeSocio(clubDos.getSocios().get(i).getContent(), null);
				// Lo añadimos a la lista de socios del club 1.
				clubes.get(idClubUno).getContent().getSocios().add(node);
			}
		}
		// En ambos casos se borra el club dos
		clubes.delete(idClubDos);
		System.out.println("Los socios del club 2 se han sumado al club 1 correctamente.");
		actualizarFicherosClubes();
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo muestra las opciones del submenu para que el
	 * usuario pueda escoger la opcion que necesite.
	 */
	public static void mostrarSubMenu() {
		System.out.println("\n¿Que deseas hacer con este club?");
		System.out.println("Comprobar si tiene socios [1]");
		System.out.println("Ver numero de socios del club [2]");
		System.out.println("Dar de alta un socio [3]");
		System.out.println("Dar de baja un socio [4]");
		System.out.println("Ver socios de este club [5]");
		System.out.println("Comprobar pertenencia de un socio [6]");
		System.out.println("Volver atras [7]");	
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo permite al usuario escoger la opcion del submenu
	 * que le permita hacer la accion que quiera con el club escogido. 
	 * Cada opcion llama al metodo necesario para realizar la accion.
	 */
	private static void elegirOpcionSubMenu() {
		boolean salir = false;
		while (true) {
			mostrarSubMenu();
			int opcionEscogida = entrada.nextInt();
			switch(opcionEscogida) {
				case 1:
					isClubEmpty(clubEscogido, true);
					break;
				case 2:				
					verNumeroSocios(clubEscogido);
					break;
				case 3:
					altaSocio(clubEscogido);
					break;
				case 4: 
					bajaSocio(clubEscogido);
					break;
				case 5:
					verSocios(clubEscogido);
					break;
				case 6:
					comprobarPertenenciaSocio(clubEscogido);
					break;
				case 7:
					salir = true;
					break;
				default:
					System.out.println("Introduce una opcion correcta.");	
			}
			if (salir) {
				break;
			}
		}
	}
	
	/**
	 * Pre: Recibe como parametro un entero que contiene la posicion del club
	 * escogido en la lista simple enlazada.
	 * Post: Muestra por pantalla si el club escogido tiene socios o no. Ademas,
	 * devuelve [true] o [false] para poder ser usado en condiciones.
	 * @return 
	 */
	private static boolean isClubEmpty(int clubEscogido, boolean mostrar) {
		if (clubes.get(clubEscogido).getContent().getSocios().getSize() == 0) {
			if(mostrar) {
				System.out.println("Este club de socios esta vacio.");
			}
			return true;
		} else {
			if(mostrar) {
				System.out.println("Este club tiene socios.");
			}
			return false;
		}		
	}
	
	/**
	 * Pre: Recibe como parametro un entero que contiene la posicion del club
	 * escogido en la lista simple enlazada.
	 * Post: Muestra por pantalla el numero de socios del club escogido.
	 * @param clubEscogido
	 */
	private static void verNumeroSocios(int clubEscogido) {
		int numeroSocios = clubes.get(clubEscogido).getContent().getSocios().getSize();
		System.out.println("Este club tiene un total de " + numeroSocios + " socios.");
	}
	
	/**
	 * Pre: Recibe como parametro un entero que contiene la posicion del club
	 * escogido en la lista simple enlazada.
	 * Post: Muestra por pantalla los socios que pertenecen al club escogido.
	 */
	private static void verSocios(int clubEscogido) {
		if (!isClubEmpty(clubEscogido, false)) {
			Club auxiliar = clubes.get(clubEscogido).getContent();
			int size = clubes.get(clubEscogido).getContent().getSocios().getSize();
			// Recorremos los socios del club escogido y los mostramos por pantalla
			for (int i = 0; i < size; i++) {
				System.out.println("[" + i + "] " + auxiliar.getSocios().get(i).getContent().toString()); 			
			}				
		} else {
			System.out.println("Este club no tiene socios.");
		}
	}

	/**
	 * Pre: Recibe como parametro un entero que contiene la posicion del club
	 * escogido en la lista simple enlazada.
	 * Post: Da de alta a un socio en el club escogido.
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	private static void altaSocio(int clubEscogido) {
		entrada.nextLine();
		System.out.println("Dime el nombre del nuevo socio:");
		String nombre = entrada.nextLine();
		System.out.println("Dime su primer apellido:");
		String apellido1 = entrada.nextLine();
		System.out.println("Dime su segundo apellido");
		String apellido2 = entrada.nextLine();
		System.out.println("Dame la fecha de ingreso del socio:");
		String fechaIncorporacion = entrada.nextLine();
		Socio nuevoSocio = new Socio (nombre, apellido1, apellido2, fechaIncorporacion);
		NodeSocio nuevoNode = new NodeSocio (nuevoSocio, null);
		clubes.get(clubEscogido).getContent().getSocios().add(nuevoNode);
		System.out.println("El socio " + nuevoSocio.toString() 
			+ "ha sido insertado correctamente.");	
		actualizarFicherosClubes();
	}
	
	/**
	 * Pre: Recibe como parametro un entero que contiene la posicion del club
	 * escogido en la lista simple enlazada.
	 * Post: Da de baja a un socio concreto (mediante la introduccion de un 
	 * numero que lo identifica) del club escogido.
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	private static void bajaSocio(int clubEscogido) {
		if (!isClubEmpty(clubEscogido, false)) {
			verSocios(clubEscogido);
			System.out.println("Elige el socio que quieres dar de baja: ");
			int socioEscogido = entrada.nextInt();
			clubes.get(clubEscogido).getContent().getSocios().delete(socioEscogido);
			System.out.println("Ha sido eliminado correctamente.");	
			actualizarFicherosClubes();			
		} else {
			System.out.println("Este club no tiene socios.");
		}
	}
	
	/**
	 * Pre: Recibe como parametro un entero que contiene la posicion del club
	 * escogido en la lista simple enlazada.
	 * Post: Este metodo busca si un socio pertenece a un club sabiendo
	 * su nombre y apellidos.
	 */
	private static void comprobarPertenenciaSocio(int clubEscogido) {
		// Si el club elegido tiene socios realizamos la accion
		if (!isClubEmpty(clubEscogido, false)) {
			entrada.nextLine();
			System.out.println("Dime el nombre del socio:");
			String nombre = entrada.nextLine();
			System.out.println("Dime su primer apellido:");
			String apellido1 = entrada.nextLine();
			System.out.println("Dime su segundo apellido");
			String apellido2 = entrada.nextLine();
			Club auxiliar = clubes.get(clubEscogido).getContent();
			/*
			 * Recorremos los socios del club escogido y vamos uno por uno comprobando
			 * si existe uno con el mismo nombre y apellidos.
			 */
			for (int i = 0; i < auxiliar.getSocios().getSize(); i++) {
				if (auxiliar.getSocios().get(i).getContent().getNombre().equals(nombre) &&
						auxiliar.getSocios().get(i).getContent().getApellido1().equals(apellido1) && 
						auxiliar.getSocios().get(i).getContent().getApellido2().equals(apellido2)) {
					System.out.println("Este socio pertenece al club.");
					return;
				}
			}	
			System.out.println("Este persona no pertenece a este club.");		
		} else {
			System.out.println("Este club no tiene socios.");
		}
	}

	/**
	 * Pre: ---
	 * Post: Este metodo se encarga de sobreescribir los ficheros que contienen
	 * los datos de los clubes y los socios. Se llama cada vez que se añaden o
	 * borran algunos datos para que siempre este todo actualizado con lo que 
	 * sucede en el programa.
	 */
	private static void actualizarFicherosClubes(){
		File f;
		Formatter escritura = new Formatter();
		
		// Borramos todos los archivos de la carpeta para poder sobreescribirlo
		Arrays.stream(new File("C:/Users/Nerea/Desktop/Clubes/").listFiles()).forEach(File::delete);
		
		for (int i = 0; i < clubes.getSize(); i++) {
			// Creamos el .csv con el nombre del club correspondiente
			try {
				String rutaConNombre = "C:\\Users\\Nerea\\Desktop\\Clubes\\" + clubes.get(i).getContent().getNombre() + ".csv";
				f = new File (rutaConNombre);
				escritura = new Formatter(f);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			// Escribimos la cabecera
			escritura.format(clubes.get(i).getContent().getNombre() + "\n");
			// Si ese club tiene socios
			if (clubes.get(i).getContent().getSocios().getSize() > 0) {
				// Con este bucle escribimos en cada linea un socio
				int size = clubes.get(i).getContent().getSocios().getSize();
				for (int j = 0; j < size; j++) {
					escritura.format(clubes.get(i).getContent().getSocios().get(j).getContent().getNombre() + ",");
					escritura.format(clubes.get(i).getContent().getSocios().get(j).getContent().getApellido1() + ",");
					escritura.format(clubes.get(i).getContent().getSocios().get(j).getContent().getApellido2() + ",");
					escritura.format(clubes.get(i).getContent().getSocios().get(j).getContent().getFechaIncorporacion()+ "\n");
				}	
			}
			escritura.flush();
		}
		escritura.close();
	}
	
}
