package projekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectZnamky {
	private Connection conn; 
	//slou�� k ulo�en� zn�mek v�ech student� z SQL datab�ze
	ArrayList<Integer> arrZnamky=new ArrayList<Integer>();
	//p�ipojen� k SQL datab�zi
	public boolean connect(String username, String password) { 
       conn= null; 
       try {
              conn = DriverManager.getConnection("jdbc:sqlite:myDBZ.db",username,password);                       
       } 
      catch (SQLException e) { 
            System.out.println(e.getMessage());
	    return false;
      }
      return true;
	}
	//odpojen� od SQL datab�za
	public void disconnect() { 
		if (conn != null) {
		try {conn.close();} 
	       catch (SQLException ex) { System.out.println(ex.getMessage()); }
		}
	}
	//vytvo�en� SQL datab�ze zn�mky, kter� obsahuje sloupec id, idstudenta , znamka
	public boolean createTable()
	{
	     if (conn==null)
	           return false;
	    String sql = "CREATE TABLE IF NOT EXISTS znamky (" + "id integer PRIMARY KEY," + "idstudenta integer NOT NULL," + "znamka integer NOT NULL"+ ");";
	    try{
	            Statement stmt = conn.createStatement(); 
	            stmt.execute(sql);
	            return true;
	    } 
	    catch (SQLException e) {
	    System.out.println(e.getMessage());
	    }
	    return false;
	}
	//slou�� k vlo�en� z�znamu do tabulky, vkl�d� se jen idstudenta a p��slu�n� zn�mka , id se generuje samo
	public boolean insertRecord(int idstudenta, int znamka) {
        String sql = "INSERT INTO znamky (idstudenta,znamka) VALUES(?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql); 
            //pstmt.setInt(1, id);
            pstmt.setInt(1, idstudenta);
            pstmt.setInt(2, znamka);
       
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
	//vyp�e v�echny zn�mky z datab�ze, ve fin�ln� verzy projektu nen� tato metoda vyu��van�
	/*public void nactiVse(){
        String sql = "SELECT id, idstudenta, znamka FROM znamky";
        try {
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql); 
             while (rs.next()) {
                	System.out.println(rs.getInt("id") +  "\t" +  
                			rs.getInt("idstudenta") + "\t" + 
                			rs.getInt("znamka"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}*/
	//vyma�e z�znam podle id z datab�ze
	public void delete(int id) {
        String sql = "DELETE FROM znamky WHERE idstudenta = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	//nahraje v�echny zn�mky z SQL databaze do arrZnamky
	public void selectAll(int id){
		int znamka;
		String jmeno,prijmeni,datum,obor;
        String sql = "SELECT znamka FROM znamky WHERE idstudenta = ?";
        try {
             PreparedStatement pstmt  = conn.prepareStatement(sql);
             pstmt.setInt(1, id);
             ResultSet rs    = pstmt.executeQuery();
             while (rs.next()) {
            	 	znamka=rs.getInt("znamka");
            	 	arrZnamky.add(znamka);	 	
            }
            }
         catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}

}
