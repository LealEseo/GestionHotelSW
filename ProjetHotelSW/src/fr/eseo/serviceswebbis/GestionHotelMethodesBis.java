package fr.eseo.serviceswebbis;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.jws.WebService;


@WebService(targetNamespace = "http://serviceswebbis.eseo.fr/", endpointInterface = "fr.eseo.serviceswebbis.SEIGestionHotelMethodeBis", portName = "GestionHotelMethodesBisPort", serviceName = "GestionHotelMethodesBisService")
public class GestionHotelMethodesBis implements SEIGestionHotelMethodeBis  {

	public Chambre[] trouverChambre(Chambre chambre, int prixMin_param, int prixMax_param, String dateDeb_param,String dateFin_param) {
		Chambre[] chambre_array = new Chambre[100];
		int i=0;
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gestionhotel?useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","");
			Statement stmt = conn.createStatement();
			
			//Tous les attributs de la chambre en paramètre
			int nbPlaceLit_param = chambre.getNbPlaceLit();
			String typeChambre_param = chambre.getTypeChambre();
			
			String sql_query = "SELECT DISTINCT c.* FROM chambre c LEFT JOIN reservation r ON c.idChambre=r.idChambre WHERE c.typeChambre LIKE '%"+typeChambre_param+"%'";
			if(nbPlaceLit_param != 0){
				sql_query+= " and c.nbPlaceLit = "+ String.valueOf(nbPlaceLit_param);
			}
			if(prixMin_param != 0){
				sql_query+= " and c.prixJournalier >= "+ String.valueOf(prixMin_param);
			}
			if(prixMax_param != 0){
				sql_query+= " and c.prixJournalier <= "+ String.valueOf(prixMax_param);
			}
			sql_query+= " and ((r.dateDeb IS NULL AND r.dateFin IS NULL) OR (r.dateDeb > '"+dateFin_param+"' OR r.dateFin < '"+dateDeb_param+"'));";
			ResultSet result = stmt.executeQuery(sql_query);
			System.out.println("SQL QUERY :"+ sql_query);
			while(result.next()) { //Tant qu'il y a des lignes dispos 
				System.out.println("*****************");
				System.out.println(result.getInt("idChambre"));
				System.out.println(result.getInt("idChambre"));
				System.out.println(result.getString("typeChambre"));
				System.out.println(result.getFloat("prixJournalier"));
				System.out.println("*****************");
				
				int idChambre = result.getInt("idChambre");
				int nbPlaceLit = result.getInt("nbPlaceLit");
				String typeChambre = result.getString("typeChambre");
				float prixJournalier = result.getFloat("prixJournalier");
				chambre = new Chambre(idChambre, nbPlaceLit, typeChambre, prixJournalier);
				System.out.println("chambre :"+ chambre.getIdChambre() + ", "+ chambre.getTypeChambre()+"," + chambre.getNbPlaceLit());
				chambre_array[i]=chambre;
				System.out.println(chambre_array[i].toString());
				i++;
			}
			result.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chambre_array;
	}
			
	public int reserverChambre(Reservation reservation) {
		int codeReservation = 0;
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gestionhotel?useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","");
			//System.out.println("Connection OK");
			//Récupération des informations relatives à la réservation 
			int idReservation= reservation.getIdReservation();
			int idChambre_param = reservation.getIdChambre();
			int idClient_param = reservation.getIdClient();
			int nbPlaces_param = reservation.getNbPlaces();
			Date dateDeb_param = reservation.getDateDeb();
			Date dateFin_param = reservation.getDateFin();
			
			Statement stmt = conn.createStatement();
			//System.out.println("Statement OK");
			String insert = "INSERT INTO Reservation (idChambre,idClient,dateDeb,dateFin,nbPlaces,paiementEffectue)"
					+ "		VALUES ("+idChambre_param+","+idClient_param+",'"+dateDeb_param+"','"+dateFin_param+"',"+nbPlaces_param+",0)";
			//System.out.println("insert : "+insert);
			Boolean executebool = stmt.execute(insert);
			//System.out.println(String.valueOf(result.next()));
			if(!executebool) {
				//System.out.print("Exécution réussie");
				String verif = "SELECT idReservation FROM Reservation "
						+ "			WHERE idChambre="+idChambre_param
						+"			AND dateDeb='"+dateDeb_param
						+"'			AND dateFin='"+dateFin_param
						+"';";
				//System.out.println("verif : "+verif);
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
		return codeReservation;
	}
	
	
	public boolean payerChambre(int codeReservation) {
		boolean executebool = false;
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gestionhotel?useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","");
			
			Statement stmt = conn.createStatement();
			String insert = "UPDATE Reservation SET paiementEffectue = '1' WHERE idReservation = "+codeReservation;
			executebool = stmt.execute(insert);
			
			if(!executebool) {
			System.out.print("Exécution réussie");
			}else {
				System.out.print("Problème d'exécution");
			}
		
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return executebool;
	}
	
	/**
	 * Méthode pour annuler une réservation 
	 * @param codeReservation : id de la réservation
	 * @return
	 */
	public boolean annulerChambre(int codeReservation) {
		boolean executebool= false;
		try {
			//Connexion à la BDD
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gestionhotel?useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","");
			
			//Création de la requête 
			Statement stmt = conn.createStatement();
			
			//Suppression de la réservation choisie dans la BDD
			String insert = "DELETE FROM Reservation WHERE idReservation = "+codeReservation;
			executebool = stmt.execute(insert);
			
			//Vérifier que la requête s'est bien effectuée
			if(!executebool) {
			System.out.print("Exécution réussie");
			}else {
				System.out.print("Problème d'exécution");
			}
		
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//Si c'est faux, l'exécution est réussie donc on renvoie vrai à l'aplication web
		return !executebool; 
	}
	
	public int connexion(String login, String mdp) {
		int idClient = 0;
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/gestionhotel?useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","");
			//System.out.println(conn.toString());
			//Création de la requête 
			Statement stmt = conn.createStatement();
			//System.out.println(stmt.toString());
			//Suppression de la réservation choisie dans la BDD
			String insert = "SELECT idClient FROM Client WHERE numTelephone='"+login+"' AND password='"+mdp+"';";
			//System.out.println("insert : "+insert);
			ResultSet result = stmt.executeQuery(insert);
			if(result.next() != false) {
				//System.out.println("resultat du getInt IdClient : "+result.getInt("idClient"));
				idClient = result.getInt("idClient");
//			} else {
//				System.out.println("resultat vide");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idClient;
	}
}

