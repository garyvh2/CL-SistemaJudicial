package cr.ac.ucenfotec.sistemajudicial.classes;

import java.sql.SQLException;
import java.time.*;

import cr.ac.ucenfotec.sistemajudicial.datamanagment.*;


public class Caso {
	private int 		ID;
	private String 		descripcion;
	private int			idQuerellante;
	private Querellante querellante;
	private int 		idJuez;
	private Juez 		juez;
	private Estado 		estado;
	private LocalDate 	fecha;
	private String		historial;
	

	
	//Constructor
	public Caso(int iD, String descripcion, int idQuerellante, int idJuez, Estado estado, LocalDate fecha,
			String historial) {
		super();
		ID = iD;
		this.descripcion = descripcion;
		this.idQuerellante = idQuerellante;
		this.querellante = null;
		this.idJuez = idJuez;
		this.juez = null;
		this.estado = estado;
		this.fecha = fecha;
		this.historial = historial;
	}
	//Getters y Setters
	public int getID() {
		return ID;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public int getidQuellerarte() {
		return idQuerellante;
	}
	public Querellante getQuellerarte() throws SQLException, Exception {
		if (querellante == null) {
			querellante = (new MultiQuerellante()).buscarPorID(idQuerellante);			
		}
		return querellante;
	}
	public int getidJuez() {
		return idJuez;
	}
	public Juez getJuez() throws SQLException, Exception {
		if (juez == null) {
			juez = (new MultiJuez()).buscarPorID(idJuez);
		}
		return juez;
	}
	public Estado getEstado() {
		return estado;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public String getHistorial() {
		return historial;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void setQuellerarte(int idQuellerarte) {
		this.idQuerellante = idQuellerarte;
	}
	public void setQuellerarte(Querellante quellerarte) {
		this.querellante = quellerarte;
	}
	public void setJuez(int idJuez) {
		this.idJuez = idJuez;
	}
	public void setJuez(Juez juez) {
		this.juez = juez;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public void setHistorial(String historial) {
		this.historial = historial;
	}
	//Caso
	@Override
	public String toString() {
		return "=======================================================\nID:\t\t\t" + ID + "\ndescripcion:\t\t\t"
				+ descripcion + "\nquellerarte:\n\t\t\t" + idQuerellante + "\njuez:\n\t\t\t" + idJuez + "\nestado:\t\t\t"
				+ estado.val() + "\nfecha:\t\t\t" + fecha + "\nhistorial:\t\t\t" + historial
				+ "\n=======================================================";
	}
	
	
	
	
	
	
	
	
	
}
