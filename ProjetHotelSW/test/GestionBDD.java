

import java.sql.*;

public class GestionBDD {

	public static void main(String[] args) {
		
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gestionhotel?useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","");
			
			Statement stmt = conn.createStatement();
			String insert = "INSERT INTO `chambre` (`idChambre`, `typeChambre`, `nbPlaceLit`, `prixJournalier`) VALUES (6, 'chambre simple', '1', '25')";
			boolean executebool = stmt.execute(insert);
			
			if(!executebool) {
			System.out.print("Exécution réussie");
			}else {
				System.out.print("Problème d'exécution");
			}
			String query = "SELECT * FROM Chambre";
			ResultSet result = stmt.executeQuery(query);
			
			while(result.next()) { //Tant qu'il y a des lignes dispos 
				System.out.println("*****************");
				System.out.println(result.getInt("idChambre"));
				System.out.println(result.getString(2));
				System.out.println(result.getString("nbPlaceLit"));
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
