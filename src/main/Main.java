/**
 * 
 */
package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.w3c.dom.Document;


import connection.MySqlAccess;
import utils.UtilsMethods;

/**
 * @author Aitor Angulo Martínez
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws IOException {
		String query = "SELECT id_proveedor, nombre, DATE_FORMAT(fecha_alta,'%d/%m/%Y') as fecha_alta, id_cliente "+
				"FROM Proveedores WHERE id_cliente = "+args[0];
		String textContent = "";
				
		Document doc 		= UtilsMethods.getConfigData("src/config/config.xml");
		
		String queryPath 		= doc.getElementsByTagName("queryPath") != null ? doc.getElementsByTagName("queryPath").item(0).getTextContent() : "";
		
		Connection conn 	= MySqlAccess.getConnection(doc);
		
		ResultSet rs 		= MySqlAccess.proccessQuery(conn,query);
		
		if(UtilsMethods.getQueryRows(rs) > 0)
		{
			List<String> rows = UtilsMethods.getRowsContent(rs);
			for(String line : rows)
			{
				textContent += line + "\n";
			}
			UtilsMethods.queryfileCreator(textContent, (queryPath.length() > 0 ? queryPath : "src/bin/text.txt"));
			
		}
		else
		{
			System.out.println("No se han encontrado proveedores asociados al cliente ");
		}
		
		MySqlAccess.closeResultSet(rs);
		
		MySqlAccess.closeConnection(conn);
	
		
	}
	
	

	

}
