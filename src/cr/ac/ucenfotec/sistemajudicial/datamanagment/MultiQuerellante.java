package cr.ac.ucenfotec.sistemajudicial.datamanagment;

import cr.ac.ucenfotec.sistemajudicial.accesodb.*;
import cr.ac.ucenfotec.sistemajudicial.classes.*;

import java.sql.*;
import java.util.*;

public class MultiQuerellante {
	
	//F1 - Crear
	public Querellante crear				(String pCedula, String pNombre, String pApellidos, String pTelefono, String pDireccion) throws java.sql.SQLException,Exception {
		//New Object Querellante
		Querellante Querellante = null;
		//New result set
		ResultSet rs;
		//SQL query
		String sql;
		sql 	= 	"INSERT INTO querellante " 
					+	"(cedula, nombre, apellidos,telefono,direccion) "
					+	"VALUES ('"+pCedula+"','"+pNombre+"','"+pApellidos+"','"+pTelefono+"','"+pDireccion+"');";
		if (alreadyExist(pCedula) == false) {
			try {
				//The SQL query is executed
				Conector.getConector().ejecutarSQL(sql);
				//The id is got from the DataBase
				sql = "SELECT MAX(ID) AS 'ID' FROM querellante";
				rs = Conector.getConector().ejecutarSQL(sql, true);
				if (rs.next()) {
					//The ID is obtained
					int id = rs.getInt ("ID");
					//Querellante object is created
					Querellante = new Querellante(id, pCedula, pNombre, pApellidos, pTelefono, pDireccion);
				}
				 	
			}
			catch(Exception e) {
				throw new Exception ("Error. Este Querellante ya se encuentra en el sistema");
			}
		} else {
			throw new Exception ("Error. Ya existe un usuario con este numero de cedula");
		}
		
		rs.close();
		return Querellante;		
	}
	//F2 - Buscar
	public Querellante buscarPorID 		(int id) throws java.sql.SQLException, Exception {
		//New Object Querellante
		Querellante Querellante = null;
		//New result set
		ResultSet rs;
		//SQL query
		String sql;
		sql 	= 	"SELECT * FROM querellante " 
				+	"WHERE ID = " + id;
		//The SQL query is executed	
		rs = Conector.getConector().ejecutarSQL(sql, true);
		if(rs.next()){
			//El Querellante se almacena en un objeto y se retorna
			Querellante = new Querellante	(id,
					rs.getString("cedula"), 
					rs.getString("nombre"), 
					rs.getString("apellidos"),
					rs.getString("telefono"),
					rs.getString("direccion"));
		} else {
			throw new Exception ("Error. El Querellante no esta registrado");
		}
		rs.close();
		return Querellante;
	}
	public Querellante buscarPorCedula 	(String cedula) throws java.sql.SQLException, Exception {
		//New Object Querellante
		Querellante Querellante = null;
		//New result set
		ResultSet rs;
		//SQL query
		String sql;
		sql 	= 	"SELECT * FROM querellante " 
				+	"WHERE cedula = '" + cedula + "';";
		//The SQL query is executed	
		rs = Conector.getConector().ejecutarSQL(sql, true);
		if(rs.next()){
			//El Querellante se almacena en un objeto y se retorna
			Querellante = new Querellante	(rs.getInt("id"),
					cedula, 
					rs.getString("nombre"), 
					rs.getString("apellidos"),
					rs.getString("telefono"),
					rs.getString("direccion"));
		} else {
			throw new Exception ("Error. El Querellante no esta registrado");
		}
		rs.close();
		return Querellante;
	}
	//F3 - Actualizar	
	public void actualizar			(Querellante pQuerellante) throws java.sql.SQLException, Exception {
		String sql;
		sql = 	"UPDATE querellante " 
				+	"SET cedula='"	+	pQuerellante.getCedula()		+ "', "
				+	"nombre='"		+	pQuerellante.getNombre()		+ "', "
				+	"apellidos='"	+	pQuerellante.getApellidos()		+ "', "
				+	"telefono='"	+	pQuerellante.getTelefono()		+ "', "
				+	"direccion='"	+	pQuerellante.getDireccion()		+ "' "
				+	"WHERE id="		+	pQuerellante.getID();
		
		try {
			Conector.getConector().ejecutarSQL(sql);
		} 
		catch (Exception e) {
			throw new Exception ("Error. El Querellante no esta registrado");
		}
	}
	//F4 - Borrar
	public void borrar				(Querellante pQuerellante) throws java.sql.SQLException, Exception {
		String sql;
		sql = 	"DELETE FROM querellante "
				+	"WHERE id="		+	pQuerellante.getID();
		try {
			Conector.getConector().ejecutarSQL(sql);
		}
		catch (Exception e){
			throw new Exception("Error. El Querellante tiene casos");
		}
	}
	//F5 - Listar
	public  Vector<Querellante> listarQuerellantes () throws SQLException,Exception{
		//New object Caso
		Querellante Querellante = null;
		//New vector
		Vector<Querellante> vector = new Vector<Querellante>();		
		//New result set
		ResultSet rs;
		//SQL query
		String sql;
		sql 	= 	"SELECT * FROM querellante";
		//The SQL query is executed
		try {
			rs = Conector.getConector().ejecutarSQL(sql, true);
			while (rs.next()) {
				Querellante = new Querellante	(rs.getInt("id"),
						rs.getString("cedula"), 
						rs.getString("nombre"), 
						rs.getString("apellidos"),
						rs.getString("telefono"),
						rs.getString("direccion"));
				vector.add(Querellante);
			}
		}
		catch (Exception e) {
			throw new Exception ("Error. Error de comunicacion.");
		}
		if (vector.size() == 0) 
			throw new Exception ("Error. No hay querellantes registrados.");	
		
		
		rs.close();		
		return vector;
	}
	//F6 - login
	public Querellante login 	(String cedula) throws java.sql.SQLException, Exception {
		//New Object Querellante		
		Querellante querellante = null;
		//Vector for all the Querellantes
		Vector<Querellante> querellantes = 	listarQuerellantes();
		//New result set
		for (Querellante cadaQuerellante : querellantes) {
			if (cadaQuerellante.getCedula().equals(cedula)) {
				querellante = cadaQuerellante;
			}
		}
		return querellante;
	}
	//F7 - Already Exists
	public Boolean alreadyExist 	(String cedula) throws java.sql.SQLException, Exception {
		//New Object Querellante		
		Boolean exists = false;
		//Vector for all the Querellantes
		Vector<Querellante> querellantes = 	listarQuerellantes();
		//New result set
		for (Querellante cadaQuerellante : querellantes) {
			if (cadaQuerellante.getCedula().equals(cedula)) {
				exists = true;
			}
		}
		return exists;
	}
	
	
}	