package clubSocios;

public class SimpleLinkedListSocio {
	private NodeSocio first;
	private int size;
	
	// METODOS CONSTRUCTORES
	// Cuando la lista esta vacia
	public SimpleLinkedListSocio() {
		this.first = null;
		this.size = 0;
	}
	
	// Cuando introducimos el primer dato
	public SimpleLinkedListSocio(NodeSocio first) {
		this.first = first;
		this.size = 1;
	}

	// Metodos get y set
	public NodeSocio getFirst() {
		return first;
	}
	
	public void setFirst(NodeSocio first) {
		this.first = first;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	/**
	 * Pre: Este metodo recibe un objeto de tipo NodeSocio que contiene un
	 * objeto de tipo Socio y otro NodeSocio vacio que servira para enlazarlo
	 * en la Lista Simple Enlazada.
	 * Post: Inserta el [node] recibido por parametro dentro de la Lista
	 * Simple Enlazada de manera alfabetica. 
	 * @param node
	 * @return
	 */	
	public boolean add (NodeSocio node) {
		try {		
			// Asi se añade el primer nodo
			if (size == 0) {
				first = node;
			} 
			// Si solo hay un nodo
			else if (size == 1) {
				NodeSocio p = first;
				// Si node va despues que p
				if (p.getContent().getNombre().compareTo(node.getContent().getNombre()) < 0) {
					p.setNext(node);
				}
				// Si p va despues que node
				else {
					node.setNext(p);
					first = node;
				}
			}
			// En el resto de casos buscamos la posicion en la que se inserta
			else {			
				// Cogemos el primero
				NodeSocio p = first;
				// Si p va despues de node lo ponemos antes y lo convertimos en first
				if (p.getContent().getNombre().compareTo(node.getContent().getNombre()) > 0) {
					node.setNext(p);
					first = node;
					size++; 
					return true;
				}				
				NodeSocio previo = p;
				while (true) {
					// Pasamos al siguiente
					p = p.getNext();
					// Si p va despues de node
					if (p.getContent().getNombre().compareTo(node.getContent().getNombre()) > 0) {
						node.setNext(p);
						previo.setNext(node);
						size++; 
						return true;
					} else {
						previo = p;
					}
					if (p == null || p.getNext() == null) {
						break;
					}					
				}		
				previo.setNext(node);
				size++; 
				return true;
				// Sumamos 1 al tamaño porque lo hemos añadido			
			} size++; return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}
	 
	/**
	 * Pre: ---
	 * Post: Este metodo borra un NodeSocio de la posicion pasada
	 * por parametro. Contempla la posibilidad de que este al principio,
	 * en medio o al final.
	 * @param position
	 * @return
	 */
	public boolean delete (int position) {
		try {
			// Si se quiere borrar el primer nodo
			if(position == 0) {
				// Asi el primer nodo queda desapuntado y se borra
				first = first.getNext();
			} // Si se quiere borrar el ultimo nodo 
			else if (position == size) {
				NodeSocio p = first;
				for (int i = 1; i < size -1; i++) {
					p = p.getNext();
				}
				p.setNext(null);
			} // Si el nodo que se quiere eliminar esta en el medio  
			else {
				NodeSocio p = first;
				for (int i = 0; i < position - 1; i++) {
					p = p.getNext();
				}
				p.setNext(p.getNext().getNext());
			} size--; return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo sirve para coger un NodeSocio situado en una
	 * posicion concreta de la Lista Simple Enlazada, como el metodo
	 * get de la clase ArrayList.
	 * @param position
	 * @return
	 */
	public NodeSocio get (int position) {
		try {
			// Comprobamos que la posicion sea correcta
			if (position >= 0 && position < size) {		
				NodeSocio p = first;
				for (int i = 1; i <= position; i++) {
					p = p.getNext();
				} return p;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		} return null;		
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo muestra por pantalla el contenido de una lista
	 * simple enlazada.
	 */
	public void show() {
		NodeSocio p = first;
		for (int i = 0; i < size; i++) {
			System.out.println("[" + i + "] -> " + p.getContent().getNombre());
			p = p.getNext();
		}
	}
}
