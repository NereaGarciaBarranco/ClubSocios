package clubSocios;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	
	static String ruta = "C:\\Users\\Nerea\\Desktop\\Clubes";
	static Scanner entrada = new Scanner(System.in);
	static SimpleLinkedListClub clubes = new SimpleLinkedListClub();
	// Hay que hacer un objeto de tipo file y hay que almacenarlo todo en las listas enlazadas
	
	public static void main(String[] args) {
		// Algunas pruebitas
		System.out.println("Fundacion de un club sin socios:");
		String nombreClub = "Club 1";
		Club nuevoClub = new Club(nombreClub);
		
		// Incorporacion de un socio en un club
		Socio nuevoSocio1 = new Socio("Pepe", "Garcia", "Garcia", "10-05-2022");
		NodeSocio n1 = new NodeSocio (nuevoSocio1, null);
		Socio nuevoSocio2 = new Socio("Alejandro", "Garcia", "Garcia", "10-05-2022");
		NodeSocio n2 = new NodeSocio (nuevoSocio2, null);
		nuevoClub.getSocios().add(n1);
		
		nuevoClub.getSocios().show();
		
		// Borrado de un socio en un club
		nuevoClub.getSocios().delete(0);
		
		nuevoClub.getSocios().show();
		
		/*
		 * Creamos un objeto de tipo File con la ruta de la carpeta donde
		 * se almacenan todos los clubes.
		 */
		File folder = new File(ruta);
		findAllFilesInFolder(folder);
		
		System.out.println("¡Bienvenidos al gestor de Clubes de Socios!");
		// Con este bucle permitimos al usuario elegir lo que quiere hacer
		while (true) {
			mostrarMenu();
			// Comprobamos la opcion que ha elegido el usuario
			try {
				int respuesta = entrada.nextInt();
				if (respuesta == 1) {
					mostrarClubs();
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
	
	
	private static void unirDosClubes() {
		// TODO Auto-generated method stub
		
	}


	/**
	 * Pre: ---
	 * Post: Este metodo recorre todos los archivos de una carpeta
	 * de la que se conoce su ruta.
	 * @param folder
	 */
	public static void findAllFilesInFolder(File folder) {
		int posicionClub = 0;
		for (File file : folder.listFiles()) {
			if (!file.isDirectory()) {
				System.out.println(file.getName());
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
				// Si estamos en la cabecera del .csv cogemos el nombre del club
				if (contador == 0) {
					String nombreClub = f.nextLine();
					// Añadimos la lista de socios vacia 
					SimpleLinkedListSocio socios = new SimpleLinkedListSocio();
					Club club = new Club (nombreClub, socios);
					NodeClub node = new NodeClub(club, null);
					clubes.add(node);
				}
				/*
				 * En el resto de lineas tenemos los socios asi que vamos cogiendo
				 * sus datos, convirtiendolos en objetos de tipo Socio y añadiendolos
				 * a la lista simple enlazada de su club correspondiente.
				 */
				else {
					String linea = f.nextLine();
					// Separamos la linea por el separador del csv
					String[] datosSocio = linea.split(";");
					String nombre = datosSocio[0];
					String apellido1 = datosSocio[1];
					String apellido2 = datosSocio[2];
					String fechaIncorporacion = datosSocio[3];
					Socio socio = new Socio (nombre, apellido1, apellido2, fechaIncorporacion);
					NodeSocio nodeSocio = new NodeSocio(socio, null);
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
	
	public static void mostrarMenu() {
		System.out.println("¿Que deseas hacer?");
		System.out.println("Elegir un club existente [1]");
		System.out.println("Añadir un nuevo club (sin socios) [2]");
		System.out.println("Unir dos clubes [3]");
		System.out.println("Apagar [3]");
	}
	
	public static void mostrarSubMenu() {
		System.out.println("¿Que deseas hacer con este club?");
		System.out.println("Comprobar si tiene socios [1]");
		System.out.println("Ver numero de socios del club [2]");
		System.out.println("Dar de alta un socio [3]");
		System.out.println("Dar de baja un socio [4]");
		System.out.println("Ver socios de este club [5]");
		System.out.println("Comprobar pertenencia de un socio [6]");
		System.out.println("Guardar en un fichero este club [7]");
		while (true) {
			int opcionEscogida = entrada.nextInt();
			if (opcionEscogida == 1) {
				clubIsEmpty();
			} else if (opcionEscogida == 2) {
				verNumeroSocios();
			} else if (opcionEscogida == 3) {
				altaSocio();
			} else if (opcionEscogida == 4) {
				bajaSocio();
			} else if (opcionEscogida == 5) {
				verSocios();				
			} else if (opcionEscogida == 6) {
				comprobarPertenenciaSocio();
			} else if (opcionEscogida == 7) {				
				guardarFicheroClub();
			}
			else {
				System.out.println("Introduce una opcion correcta.");
			}
		}
	}
	
	private static void comprobarPertenenciaSocio() {
		// TODO Auto-generated method stub
		
	}


	private static void verNumeroSocios() {
		// TODO Auto-generated method stub
		
	}


	private static void clubIsEmpty() {
		// TODO Auto-generated method stub
		
	}


	private static void guardarFicheroClub() {
		// TODO Auto-generated method stub
		
	}

	private static void verSocios() {
		// TODO Auto-generated method stub
		
	}

	private static void bajaSocio() {
		// TODO Auto-generated method stub
		
	}

	private static void altaSocio() {
		// TODO Auto-generated method stub
		
	}

	public static void mostrarClubs() {
		System.out.println("Elige un club:");
		for (int i = 0; i < clubes.getSize(); i++) {
			System.out.println("[" + i + "] " + clubes.get(i).toString());
		}
		while (true) {
			int clubEscogido = entrada.nextInt();
			if (clubEscogido <= clubes.getSize()) {
				break;
			} else {
				System.out.println("Introduce un numero de club existente");
			}
		}
		mostrarSubMenu();
		
	}
	
	public static void agregarClub() {
		File f;
		System.out.println("Introduce el nombre del nuevo club:");
		String nombreClub = entrada.nextLine();
		nombreClub = nombreClub + ".csv";
		Club nuevoClub = new Club(nombreClub);
		f = new File(ruta, nombreClub);
		// Falta a�adir el club
		System.out.println("El club ha sido creado correctamente.");
		System.out.println("Si desea agregarle socios seleccionelo desde el menu.");
		
	}

}
