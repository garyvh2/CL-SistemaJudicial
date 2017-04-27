package cr.ac.ucenfotec.sistemajudicial.classes;

public class Juez extends Persona {
	private String sala;
	private String usuario;
	private String clave;
	//Constructor
	public Juez(int iD, String cedula, String nombre, String apellidos, String telefono, String sala, String usuario, String clave) {
		super(iD, cedula, nombre, apellidos, telefono);
		// TODO Auto-generated constructor stub
		this.sala = sala;
		this.usuario = usuario;
		this.clave = clave;
	}
	//Getters y Setters
	public String getSala() {
		return sala;
	}
	public String getUsuario() {
		return usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setSala(String sala) {
		this.sala = sala;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	//toString
	@Override
	public String toString() {
		return "=======================================================\n"
				+ "Cedula:\t\t\t" + cedula + "\nNombre:\t\t\t" + nombre + "\nApellidos:\t\t\t" + apellidos
				+ "\nTelefono:\t\t\t" + telefono + "\nSala:\t\t\t" + sala + "\nUsuario:\t\t\t" + usuario 
				+ "\n=======================================================";
	}
	
	
	
}
