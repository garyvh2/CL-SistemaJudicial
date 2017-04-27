package cr.ac.ucenfotec.sistemajudicial.classes;

public class Querellante extends Persona {
	private String direccion;
	//Constructor
	public Querellante(int iD, String cedula, String nombre, String apellidos, String telefono, String direccion) {
		super(iD, cedula, nombre, apellidos, telefono);
		// TODO Auto-generated constructor stub
		this.direccion = direccion;
	}
	//Getters y Setters
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	//toString
	@Override
	public String toString() {
		return "======================================================="
				+ "\nCedula:\t\t\t" + cedula + "\nNombre:\t\t\t" + nombre + "\nApellidos:\t\t\t" + apellidos
				+ "\nTelefono:\t\t\t" + telefono + "\nDireccion:\t\t\t" + direccion +"\n=======================================================";
	}
	

	
}
