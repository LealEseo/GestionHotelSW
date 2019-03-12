import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eseo.servicesweb.Chambre;


public class TrouverChambreTest {

	public static void main(String[] args) {
		Chambre[] chambre_array = new Chambre[10];
		Chambre chambre = new Chambre();
		int i=0;
		try {
			int prixMin_param =200, prixMax_param=500;
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gestionhotel?useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","");
			Statement stmt = conn.createStatement();
			
			//Tous les attributs de la chambre en paramètre
			int nbPlaceLit_param = 4;
			String typeChambre_param = "Suite";
			
			String sql_query ="SELECT c.* FROM Chambre c, Reservation r WHERE c.typeChambre LIKE '%"+typeChambre_param+"%'";
			if(nbPlaceLit_param != 0){
				sql_query+= "and c.nbPlaceLit = "+ nbPlaceLit_param +" ";
			}
			if(prixMin_param != 0){
				sql_query+= "and c.prixJournalier >= "+ prixMin_param + " ";
				//System.out.println(sql_query);
			}
			if(prixMax_param != 0){
				sql_query+= "and c.prixJournalier <= "+ prixMax_param + " ";
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
				//System.out.println("chambre : " + chambre_array[i].getTypeChambre());
				i++;
			}
			result.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
