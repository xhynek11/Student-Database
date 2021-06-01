package projekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Connect {
	private Connection conn; 
	//list studentù sloužící pro ukládání studentù naètených z SQL databáze
	ArrayList<Student> arrStudenti=new ArrayList<Student>();
	//pøipojení k SQL databázi
	public boolean connect(String username, String password) { 
       conn= null; 
       try {
              conn = DriverManager.getConnection("jdbc:sqlite:myDB.db",username,password);                       
       } 
      catch (SQLException e) { 
            System.out.println(e.getMessage());
	    return false;
      }
      return true;
	}
	public void disconnect() { 
		if (conn != null) {
		try {conn.close();} 
	       catch (SQLException ex) { System.out.println(ex.getMessage()); }
		}
	}
	//vytvoøení tabulky v SQL databázi
	public boolean createTable()
	{
	     if (conn==null)
	           return false;
	    String sql = "CREATE TABLE IF NOT EXISTS studenti (" + "id integer PRIMARY KEY," + "jmeno varchar(50) NOT NULL,"+ "prijmeni varchar(50) NOT NULL," + "datum DATE NOT NULL," + " obor varchar(50) NOT NULL" + ");";
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
	//Vložení záznamu do SQL databáze pomocí preparedStatementù
	public boolean insertRecord(int id,String jmeno, String prijmeni, String datum,String obor ) {
        String sql = "INSERT INTO studenti (id,jmeno,prijmeni,datum,obor) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql); 
            pstmt.setInt(1, id);
            pstmt.setString(2, jmeno);
            pstmt.setString(3, prijmeni);
            pstmt.setString(4, datum);
            pstmt.setString(5, obor);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
	//naètení všech studentù z SQL databáze do listu arrStudenti
	public void selectAll(){
		int ID;
		String jmeno,prijmeni,datum,obor;
        String sql = "SELECT id, jmeno, prijmeni, datum, obor FROM studenti";
        try {
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql); 
             while (rs.next()) {
            	 	ID=rs.getInt("id");
            	 	jmeno=rs.getString("jmeno");
            	 	prijmeni=rs.getString("prijmeni");
            	 	datum=rs.getString("datum");
            	 	obor=rs.getString("obor");
            	 	
            	 	String Datum[]=datum.split("[-]+");
            		String DAtumFromSql=Datum[0]+"."+Datum[1]+"."+Datum[2];
           
                	switch(obor) {
                	case "Tech":
                		arrStudenti.add(new StudentTech(ID,jmeno,prijmeni,DAtumFromSql));
                		break;
                	case "Kom":
                		arrStudenti.add(new StudentKom(ID,jmeno,prijmeni,DAtumFromSql));
                		break;
                	case "Hum":
                		arrStudenti.add(new StudentHum(ID,jmeno,prijmeni,DAtumFromSql));
                		break;
                	}
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	//vypsání všech studentù z SQL databáze, ve finální podobì projektu se nevyužívá
	/*public void nactiVse(){
        String sql = "SELECT id, jmeno, prijmeni, datum, obor FROM studenti";
        try {
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql); 
             while (rs.next()) {
                	System.out.println(rs.getInt("id") +  "\t" +  
                			rs.getString("jmeno") + "\t" + 
                			rs.getString("prijmeni") + "\t" + 
                			rs.getString("datum") + "\t" + 
                			rs.getString("obor"));
                	
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}*/
	//vymaže studenta z SQL databáze podle ID, využívá preparedStatementy
	public void delete(int id) {
        String sql = "DELETE FROM studenti WHERE id = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	//naète studenta podle jmena a pøijmení a uloží do listu arrStudenti, také využívá preparedStatementy
	public void nactiStudentaPodleJmePri(String Jmeno,String Prijmeni){
		int ID;
		String jmeno,prijmeni,datum,obor;
        String sql = "SELECT id, jmeno, prijmeni, datum, obor FROM studenti where jmeno = ? AND prijmeni = ?";
        try {
             PreparedStatement pstmt  = conn.prepareStatement(sql);
             pstmt.setString(1, Jmeno);
             pstmt.setString(2, Prijmeni);
             ResultSet rs    = pstmt.executeQuery(); 
             while (rs.next()) {
            	 	ID=rs.getInt("id");
            	 	jmeno=rs.getString("jmeno");
            	 	prijmeni=rs.getString("prijmeni");
            	 	datum=rs.getString("datum");
            	 	obor=rs.getString("obor");
            	 	System.out.println(jmeno);
            	 	
            	 	String Datum[]=datum.split("[-]+");
            		String DAtumFromSql=Datum[0]+"."+Datum[1]+"."+Datum[2];
           
                	switch(obor) {
                	case "Tech":
                		arrStudenti.add(new StudentTech(ID,jmeno,prijmeni,DAtumFromSql));
                		break;
                	case "Kom":
                		arrStudenti.add(new StudentKom(ID,jmeno,prijmeni,DAtumFromSql));
                		break;
                	case "Hum":
                		arrStudenti.add(new StudentHum(ID,jmeno,prijmeni,DAtumFromSql));
                		break;
                	}
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}

}
