package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * 
 * Global class where general methods are used
 * @author Aitor Angulo
 *
 */
public class UtilsMethods {
	
	/**
	 * Sets config credentials
	 * @param path
	 * @return
	 */
	public static Document getConfigData(String path)
	{
		File inputFile;
        DocumentBuilderFactory dbFactory;
        DocumentBuilder dBuilder;
        Document doc = null;
       
		try {
			
			inputFile = new File(path);
			if(inputFile != null && inputFile.exists())
			{
				dbFactory = DocumentBuilderFactory.newInstance();
				dBuilder = dbFactory.newDocumentBuilder();
				doc = dBuilder.parse(inputFile);
				doc.getDocumentElement().normalize();
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {

		}
             
		return doc;
	}
	
	/**
	 * Gets how many rows are obtained in a query
	 * @param rs
	 * @return
	 */
	public static int getQueryRows(ResultSet rs) {
		if(rs == null) return 0;
		try {
			rs.last();
			return rs.getRow();
		} catch (SQLException e) {
			return 0;
		}		
		
	}
	
	/**
	 * Gets a list of rows contents which fields are separated by commas
	 * @param rs
	 * @return
	 */
	public static List<String> getRowsContent(ResultSet rs)  {
		
		List<String> rows = new ArrayList<String>();
		
		if(getQueryRows(rs) > 0)
		{
			ResultSetMetaData rsmd;
			try {
				rs.beforeFirst();
				while(rs.next())
				{
					String row = "";
					rsmd = rs.getMetaData();
					int columnsNumber = rsmd.getColumnCount();
					for (int i = 1; i <= columnsNumber; i++) {	
						//rsmd.getColumnName(i);
				        String columnValue = rs.getString(i);
				        row += columnValue+",";
				    }
					row.replace(",$", "");
					rows.add(row);
				}
				
			} catch (SQLException e) {
				
			}
			
		}
		
		return rows;
	}
	
	/**
	 * Method made to create query txt file
	 * @param text
	 * @param path
	 */
	public static void queryfileCreator(String text, String path) {
		
		try{
			
            File file = new File(path);
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(text);
            bw.flush();
            bw.close();

        }catch(IOException e){
        	System.out.println("No se ha podido generar el archivo "+path);
        }
		
	}

}
