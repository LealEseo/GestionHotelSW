import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PayerChambreTest {

	public static void main(String[] args) {
		boolean executebool = false;
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/GestionHotel?user=root&password=");
			
			Statement stmt = conn.createStatement();
			String insert = "UPDATE Reservation SET paiementEffectue = '1' WHERE idReservation = "+1;
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
		System.out.println("execute bool: " +!executebool);

	}

}