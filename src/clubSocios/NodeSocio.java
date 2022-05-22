package clubSocios;

public class NodeSocio {
	private Socio content;
	private NodeSocio next;
	
	// METODO CONSTRUCTOR
	public NodeSocio (Socio content, NodeSocio next) {
		this.content = content;
		this.next = next;
	}
	
	public Socio getContent() {
		return content;
	}
	
	// METODOS GET Y SET
	public void setContent(Socio content) {
		this.content = content;
	}
	
	public NodeSocio getNext() {
		return next;
	}
	
	public void setNext(NodeSocio next) {
		this.next = next;
	}
	
	@Override
	public String toString() {
		return "Content = " + content + "\n";
	}

}
