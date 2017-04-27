package cr.ac.ucenfotec.sistemajudicial.classes;

public class Persona {
	protected int ID;
	protected String cedula;
	protected String nombre;
	protected String apellidos;
	protected String telefono;
	//Constructores
	public Persona(int iD, String cedula, String nombre, String apellidos, String telefono) {
		super();
		ID = iD;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
	}
	//Getters y Setters
	public int getID() {
		return ID;
	}
	public String getCedula() {
		return cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	//toString
	@Override
	public String toString() {
		return "=======================================================\n"
				+ "Cedula:\t\t\t" + cedula + "\nNombre:\t\t\t" + nombre + "\nApellidos:\t\t\t" + apellidos
				+ "\nTelefono:\t\t\t" + telefono
				+ "\n=======================================================";
	}
	
	
	
	
}
