

import java.sql.*;

public class GestionBDD {

	public static void main(String[] args) {
		
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionhotel?useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","");
			
			Statement stmt = conn.createStatement();
			String insertChambre = "INSERT INTO `chambre` (`idChambre`, `typeChambre`, `nbPlaceLit`, `prixJournalier`) VALUES (588, 'chambre simple', '1', '25')";
			String insertClient = "INSERT INTO `client` (`idClient`, `nom`, `prenom`, `addresse`, `email`, `numTelephone`) VALUES ('3086', 'Client1', 'client1prenom', 'angers', 'client@mail.com', '0785560021')";
			String insertReservation ="INSERT INTO `reservation` (`idReservation`, `idChambre`, `idClient`, `dateDeb`, `dateFin`, `nbPlaces`, `paiementEffectue`) VALUES ('981', '01', '01', '2019-03-11', '2019-03-22', '2', '1')";
			boolean executebool = stmt.execute(insertChambre);
			boolean executebool1 = stmt.execute(insertClient);
			boolean executebool2 = stmt.execute(insertReservation);
			
			if(!executebool && executebool1 && executebool2) {
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
			String query1 = "SELECT * FROM Client";
			ResultSet result1 = stmt.executeQuery(query1);
			while(result1.next()) { //Tant qu'il y a des lignes dispos 
				System.out.println("*****************");
				System.out.println(result1.getInt("idClient"));
				System.out.println(result1.getString("nom"));
				System.out.println(result1.getString("prenom"));
				System.out.println(result1.getString("addresse"));
				System.out.println(result1.getString("email"));
				System.out.println(result1.getInt("numTelephone"));
				System.out.println("*****************");
			}
			String query2 = "SELECT * FROM Reservation";
			ResultSet result2 = stmt.executeQuery(query2);
			while(result2.next()) { //Tant qu'il y a des lignes dispos 
				System.out.println("*****************");
				System.out.println(result2.getDate("dateDeb"));
				System.out.println(result2.getDate("dateFin"));
				System.out.println(result2.getInt("idReservation"));
				System.out.println(result2.getInt("idChambre"));
				System.out.println(result2.getInt("idClient"));
				System.out.println(result2.getInt("nbPlaces"));
				System.out.println(result2.getInt("paiementEffectue"));
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
