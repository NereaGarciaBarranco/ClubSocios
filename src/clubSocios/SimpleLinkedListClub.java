package clubSocios;

public class SimpleLinkedListClub {
	private NodeClub first;
	private int size;
	
	// METODOS CONSTRUCTORES
	// Cuando la lista esta vacia
	public SimpleLinkedListClub() {
		this.first = null;
		this.size = 0;
	}
	
	// Cuando introducimos el primer dato
	public SimpleLinkedListClub(NodeClub first) {
		this.first = first;
		this.size = 1;
	}

	// Metodos get y set
	public NodeClub getFirst() {
		return first;
	}
	
	public void setFirst(NodeClub first) {
		this.first = first;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo añade un nodo al final de la lista, como la clase
	 * ArrayList.
	 * @param node
	 * @return
	 */
	public boolean add (NodeClub node) {
		try {		
			// Asi se añade el primer nodo
			if (size == 0) {
				// First apunta al nodo que le estas dando, eso es a�adir el primer elemento
				first = node;
			} else {
				// Creamos un puntero que apunta exactamente a donde apunta first
				// Lo vamos a ir desplazando hasta que llegue al ultimo punto de la lista enlazada
				// First no lo podemos mover, por eso creamos un puntero
				NodeClub p = first;
				// Con este bucle el puntero se mueve al final y apunta al ultimo nodo
				for (int i = 1; i < size; i++) {
					p = p.getNext();
				} 
				// Cuando apunta al final se a�ade el nodo que le acabas de pasar por parametro
				p.setNext(node);
				// Sumamos 1 al tama�o porque lo hemos a�adido
				
			} size++;
			return true;
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
				NodeClub p = first;
				for (int i = 1; i < size -1; i++) {
					p = p.getNext();
				}
				p.setNext(null);
			} // Si el nodo que se quiere eliminar esta en el medio  
			else {
				NodeClub p = first;
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
	public NodeClub get (int position) {
		try {
			// Comprobamos que la posicion sea correcta
			if (position >= 0 && position < size) {		
				NodeClub p = first;
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
		NodeClub p = first;
		for (int i = 0; i < size; i++) {
			System.out.println("[" + i + "] -> " + p.getContent().getNombre());
			p = p.getNext();
		}
	}
}
