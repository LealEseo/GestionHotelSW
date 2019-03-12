import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eseo.servicesweb.Reservation;

public class ReserverChambreTest {

	public static void main(String[] args) {
		Reservation reservation = new Reservation(0,1,1,1,Date.valueOf("2019-03-22"),Date.valueOf("2019-03-23"),false);
		int codeReservation = 0;
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gestionhotel?useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","");
			System.out.println("Connection OK");
			//Récupération des informations relatives à la réservation 
			int idReservation= reservation.getIdReservation();
			int idChambre_param = reservation.getIdChambre();
			int idClient_param = reservation.getIdClient();
			int nbPlaces_param = reservation.getNbPlaces();
			Date dateDeb_param = reservation.getDateDeb();
			Date dateFin_param = reservation.getDateFin();
			
			Statement stmt = conn.createStatement();
			System.out.println("Statement OK");
			String insert = "INSERT INTO Reservation (idChambre,idClient,dateDeb,dateFin,nbPlaces,paiementEffectue)"
					+ "		VALUES ("+idChambre_param+","+idClient_param+",'"+dateDeb_param+"','"+dateFin_param+"',"+nbPlaces_param+",0)";
			System.out.println("insert : "+insert);
			Boolean executebool = stmt.execute(insert);
			//System.out.println(String.valueOf(result.next()));
			if(!executebool) {
				System.out.print("Exécution réussie");
				String verif = "SELECT idReservation FROM Reservation "
						+ "			WHERE idChambre="+idChambre_param
						+"			AND dateDeb='"+dateDeb_param
						+"'			AND dateFin='"+dateFin_param
						+"';";
				System.out.println("verif : "+verif);
				ResultSet result = stmt.executeQuery(verif);
				result.next();
				codeReservation = result.getInt("idReservation");
			}else {
				System.out.print("Problème d'exécution");
			}
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("codeReservation : "+codeReservation);
	}
}
