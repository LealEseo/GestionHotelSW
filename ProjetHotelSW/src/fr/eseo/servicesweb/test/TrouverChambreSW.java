package fr.eseo.servicesweb.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.jws.WebService;

import fr.eseo.servicesweb.Chambre;

@WebService(targetNamespace = "http://test.servicesweb.eseo.fr/", endpointInterface = "fr.eseo.servicesweb.test.SEITrouverChambreSW", portName = "TrouverChambreSWPort", serviceName = "TrouverChambreSWService")
public class TrouverChambreSW implements SEITrouverChambreSW {

	public Chambre[] trouverChambre(Chambre chambre, int prixMin_param, int prixMax_param, String dateDeb_param,String dateFin_param) {
		Chambre[] chambre_array = new Chambre[100];
		int i=0;
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gestionhotel?user=root&password=");
			
			Statement stmt = conn.createStatement();
			
			//Tous les attributs de la chambre en paramètre
			int nbPlaceLit_param = chambre.getNbPlaceLit();
			String typeChambre_param = chambre.getTypeChambre();
			
			String sql_query ="SELECT c.* FROM Chambre c, Reservation r WHERE c.typeChambre LIKE '%"+typeChambre_param+"%'";
			if(nbPlaceLit_param != 0){
				sql_query+= "and c.nbPlaceLit = "+ String.valueOf(nbPlaceLit_param);
			}
			if(prixMin_param != 0){
				sql_query+= "and c.prixJournalier >= "+ String.valueOf(prixMin_param);
			}
			if(prixMax_param != 0){
				sql_query+= "and c.prixJournalier <= "+ String.valueOf(prixMax_param);
			}
			//sql_query+= "and c.idChambre = r.idChambre and (r.dateDeb > '"+dateFin_param+"' or r.dateFin < '"+dateDeb_param+"');";
			ResultSet result = stmt.executeQuery(sql_query);
			
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
}
