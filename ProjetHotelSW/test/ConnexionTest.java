import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnexionTest {
	public static void main(String[] args) {
		int idClient = 0;
		String login = "0616645151";
		String mdp = "test";
		try {
			//INSERT INTO `client`(`idClient`, `nom`, `prenom`, `addresse`, `email`, `numTelephone`, `password`) VALUES (1,'nomTest', 'prenomTest', 'adresseTest','emailTest@hotmail.fr','0616645151','test')
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionhotel?useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","");
			System.out.println(conn.toString());
			//Création de la requête 
			Statement stmt = conn.createStatement();
			System.out.println(stmt.toString());
			//Suppression de la réservation choisie dans la BDD
			String insert = "SELECT idClient FROM Client WHERE numTelephone='"+login+"' AND password='"+mdp+"';";
			System.out.println("insert : "+insert);
			ResultSet result = stmt.executeQuery(insert);
			if(result.next() != false) {
				System.out.println("resultat du getInt IdClient : "+result.getInt("idClient"));
				idClient = result.getInt("idClient");
			} else {
				System.out.println("resultat vide");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("idClient : "+idClient);
	}
}
