package cr.ac.ucenfotec.sistemajudicial.datamanagment;

import cr.ac.ucenfotec.sistemajudicial.accesodb.*;
import cr.ac.ucenfotec.sistemajudicial.classes.*;

import java.sql.*;
import java.util.*;

public class MultiJuez {
	//F1 - Crear
	public Juez crear				(String pCedula, String pNombre, String pApellidos, String pTelefono, String pSala, String pUsuario, String pClave) throws java.sql.SQLException,Exception {
		//New Object Juez
		Juez juez = null;
		//New result set
		ResultSet rs;
		//SQL query
		String sql;
		sql 	= 	"INSERT INTO juez " 
					+	"(cedula, nombre, apellidos,telefono,sala,usuario,clave) "
					+	"VALUES ('"+pCedula+"','"+pNombre+"','"+pApellidos+"','"+pTelefono+"','"+pSala+"','"+pUsuario+"','"+pClave+"');";
		
		try {
			//The SQL query is executed
			Conector.getConector().ejecutarSQL(sql);
			//The id is got from the DataBase
			sql = "SELECT MAX(ID) AS 'ID' FROM juez";
			rs = Conector.getConector().ejecutarSQL(sql, true);
			if (rs.next()) {
				//The ID is obtained
				int id = rs.getInt ("ID");
				//Juez object is created
				juez = new Juez(id, pCedula, pNombre, pApellidos, pTelefono, pSala, pUsuario, pClave);
			}
			
		}
		catch(Exception e) {
			throw new Exception ("Error. Este juez ya se encuentra en el sistema");
		}
		rs.close();
		return juez;		
	}
	//F2 - Buscar
	public Juez buscarPorID 		(int id) throws java.sql.SQLException, Exception {
		//New Object Juez
		Juez juez = null;
		//New result set
		ResultSet rs;
		//SQL query
		String sql;
		sql 	= 	"SELECT * FROM juez " 
				+	"WHERE ID = " + id;
		//The SQL query is executed	
		rs = Conector.getConector().ejecutarSQL(sql, true);
		if(rs.next()){
			//El juez se almacena en un objeto y se retorna
			juez = new Juez	(id, 
							rs.getString("cedula"), 
							rs.getString("nombre"), 
							rs.getString("apellidos"), 
							rs.getString("telefono"), 
							rs.getString("sala"), 
							rs.getString("usuario"),
							rs.getString("clave"));
		} else {
			throw new Exception ("Error. El juez no esta registrado");
		}
		rs.close();
		return juez;
	}
	public Juez buscarPorCedula 	(String cedula) throws java.sql.SQLException, Exception {
		//New Object Juez
		Juez juez = null;
		//New result set
		ResultSet rs;
		//SQL query
		String sql;
		sql 	= 	"SELECT * FROM juez " 
				+	"WHERE cedula = '" + cedula + "';";
		//The SQL query is executed	
		rs = Conector.getConector().ejecutarSQL(sql, true);
		if(rs.next()){
			//El juez se almacena en un objeto y se retorna
			juez = new Juez	(rs.getInt("id"), 
							cedula, 
							rs.getString("nombre"), 
							rs.getString("apellidos"), 
							rs.getString("telefono"), 
							rs.getString("sala"), 
							rs.getString("usuario"),
							rs.getString("clave"));
		} else {
			throw new Exception ("Error. El juez no esta registrado");
		}
		rs.close();
		return juez;
	}
	//F3 - Actualizar	
	public void actualizar			(Juez pJuez) throws java.sql.SQLException, Exception {
		String sql;
		sql = 	"UPDATE juez " 
				+	"SET cedula='"	+	pJuez.getCedula()		+ "', "
				+	"nombre='"		+	pJuez.getNombre()		+ "', "
				+	"apellidos='"	+	pJuez.getApellidos()	+ "', "
				+	"telefono='"	+	pJuez.getTelefono()		+ "', "
				+	"sala='"		+	pJuez.getSala()			+ "', "
				+	"usuario='"		+	pJuez.getUsuario()		+ "', "
				+	"clave='"		+	pJuez.getClave()		+ "' "
				+	"WHERE id="		+	pJuez.getID();
		
		try {
			Conector.getConector().ejecutarSQL(sql);
		} 
		catch (Exception e) {
			throw new Exception ("Error. El juez no esta registrado");
		}
	}
	//F4 - Borrar
	public void borrar				(Juez pJuez) throws java.sql.SQLException, Exception {
		String sql;
		sql = 	"DELETE FROM juez "
				+	"WHERE id="		+	pJuez.getID();
		try {
			Conector.getConector().ejecutarSQL(sql);
		}
		catch (Exception e){
			throw new Exception("Error. El juez tiene casos");
		}
	}
	//F5 - Listar
	public  Vector<Juez> listarJueces () throws SQLException,Exception{
		//New object Caso
		Juez juez = null;
		//New vector
		Vector<Juez> vector = new Vector<Juez>();		
		//New result set
		ResultSet rs;
		//SQL query
		String sql;
		sql 	= 	"SELECT * FROM juez ";
		//The SQL query is executed
		try {
			rs = Conector.getConector().ejecutarSQL(sql, true);
			while (rs.next()) {
				//El juez se almacena en un objeto y se retorna
				juez = new Juez	(rs.getInt("id"), 
								rs.getString("cedula"), 
								rs.getString("nombre"), 
								rs.getString("apellidos"), 
								rs.getString("telefono"), 
								rs.getString("sala"), 
								rs.getString("usuario"),
								rs.getString("clave"));
				vector.add(juez);
			}
		}
		catch (Exception e) {
			throw new Exception ("Error. Error de comunicacion.");
		}
		if (vector.size() == 0) 
			throw new Exception ("Error. No hay jueces registrados.");		
		
		
		rs.close();		
		return vector;
	}
	//F6 - login
	public Juez login 	(String user, String pass) throws java.sql.SQLException, Exception {
		//New Object Juez		
		Juez juez = null;
		//Vector for all the Juezs
		Vector<Juez> jueces = 	listarJueces();
		//New result set
		for (Juez cadaJuez : jueces) {
			if (cadaJuez.getUsuario().equals(user) && cadaJuez.getClave().equals(pass)) {
				juez = cadaJuez;
			}
		}
		if (juez == null) {
			throw new Exception ("Error. El nombre de usuario o contraseña son incorrectos.");			
		}
		return juez;
	}
	//F7 - Already Exists
	/*public Boolean alreadyExist 	(String cedula) throws java.sql.SQLException, Exception {
		//New Object Juez		
		Boolean exists = false;
		//Vector for all the Juezs
		Vector<Juez> jueces = 	listarJueces();
		//New result set
		for (Juez cadaJuez : jueces) {
			if (cadaJuez.getCedula().equals(cedula)) {
				exists = true;
			}
		}
		return exists;
	}*/
	
}
