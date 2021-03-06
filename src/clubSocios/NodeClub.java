package clubSocios;

public class NodeClub {
	private Club content;
	private NodeClub next;


	// METODO CONSTRUCTOR
	public NodeClub(Club content, NodeClub next) {
		this.content = content;
		this.setNext(next);
	}

	// METODOS GET Y SET
	public Club getContent() {
		return content;
	}
	
	public void setContent(Club content) {
		this.content = content;
	}
	
	public NodeClub getNext() {
		return next;
	}
	
	public void setNext(NodeClub next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return content.getNombre();
	}
	
	public int compareTo(NodeClub n) {
		if (this.content.getNombre().compareTo(n.content.getNombre()) >= 1) {
			return 1;
		} else if (this.content.getNombre().compareTo(n.content.getNombre()) <= 1) {
			return -1;
		} else {
			return 0;
		}		
	}

}
