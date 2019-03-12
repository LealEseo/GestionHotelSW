

import java.sql.*;

public class GestionBDD {

	public static void main(String[] args) {
		
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Biblioteque?user=admin&password=admin");
			
			Statement stmt = conn.createStatement();
			String insert = "INSERT INTO `Livres`(`Code`, `Titre`, `Auteur`, `Prix`) VALUES (104, 'Monsieur Claudomir', 'Marine damingo',14)";
			boolean executebool = stmt.execute(insert);
			
			if(!executebool) {
			System.out.print("Exécution réussie");
			}else {
				System.out.print("Problème d'exécution");
			}
			String query = "SELECT * FROM Livres";
			ResultSet result = stmt.executeQuery(query);
			
			while(result.next()) { //Tant qu'il y a des lignes dispos 
				System.out.println("*****************");
				System.out.println(result.getInt("Code"));
				System.out.println(result.getString(2));
				System.out.println(result.getString("Auteur"));
				System.out.println(result.getFloat(4));
				System.out.println("*****************");
			}
			result.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
