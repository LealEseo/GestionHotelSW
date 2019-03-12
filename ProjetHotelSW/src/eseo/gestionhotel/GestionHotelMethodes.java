package fr.eseo.servicesweb;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestionHotelMethodes {

	public Chambre[] trouverChambre(Chambre chambre) {
		Chambre[] chambre_array = null;
		int i=0;
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/GestionHotel?user=root&password=");
			
			Statement stmt = conn.createStatement();
			
			//Tous les attributs de la chambre en paramètre
			int nbPlaceLit_param = chambre.getNbPlaceLit();
			String typeChambre_param = chambre.getTypeChambre();
			float prixJournalier_param = chambre.getPrixJournalier();
			
			String sql_query ='SELECT c.* FROM Chambre c, Reservation r WHERE c.typeChambre LIKE "%'+typeChambre_param+'%"';
			if(nbPlaceLit_param != 0){
				sql_query+ = ' and c.nbPlaceLit = '+ String.valueOf(nbPlace_param);
			}
			if(prixMin_param != 0){
				sql_query+= ' and c.prixJournalier >= '+ String.valueOf(prixMin_param);
			}
			if(prixMax_param != 0){
				sql_query+= ' and c.prixJournalier <= '+ String.valueOf(prixMax_param);
			}
			sql_query+= ' and c.idChambre = r.idChambre and (r.dateDeb > "'+dateFin_param+'" or r.dateFin < "'+dateDeb_param+'");';
			ResultSet result = stmt.executeQuery(query);
			
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
				chambre_array[i]=chambre;
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
		int codeReservation=0;
		boolean executebool = false;
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/GestionHotel?user=root&password=");
			
			//Récupération des informations relatives à la réservation 
			int idReservation= reservation.getIdReservation();
			int idChambre_param = reservation.getIdChambre();
			int idClient_param = reservation.getIdClient();
			int nbPlaces_param = reservation.getNbPlaces();
			Date dateDeb_param = reservation.getDateDeb();
			Date dateFin_param = reservation.getDateFin();
			
			Statement stmt = conn.createStatement();
			String insert = "INSERT INTO Reservation (idChambre, idClient, dateDeb,dateFin, nbPlaces, paiementEffectue) VALUES ("+idChambre_param+","+idClient_param+",'"+dateDeb_param+"','"+dateFin_param+"',"+nbPlaces_param+",'0');";
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
		return codeReservation;
	}
	public boolean payerChambre(int codeReservation) {
		boolean executebool = false;
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/GestionHotel?user=root&password=");
			
			Statement stmt = conn.createStatement();
			String insert = "";
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
	
	public boolean annulerChambre(int codeReservation) {
		boolean executebool= false;
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/GestionHotel?user=root&password=");
			
			Statement stmt = conn.createStatement();
			String insert = "";
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
}
