package cr.ac.ucenfotec.sistemajudicial.classes;

public enum Estado {
	RECIBIDO 	("Recibido"), 
	ACEPTADO	("Aceptado"),
	CONSULTA 	("Consulta"),
	RECHAZADO	("Rechazado"),
	REDACTADO	("Redactado"),
	RESUELTO	("Resuelto"),
	REVISION	("Revision");
	
	private	String estado;	
	
	Estado (String estado) {
		this.estado = estado;
	}
	public String val () {
		return estado;
	}
	
}
