package fr.eseo.servicesweb;

import javax.jws.WebService;

@WebService(name = "SEIGestionHotelMethodesBis", targetNamespace = "http://servicesweb.eseo.fr/")
public interface SEIGestionHotelMethodesBis {

	Chambre[] trouverChambre(Chambre chambre, int prixMin_param, int prixMax_param, String dateDeb_param,
			String dateFin_param);

	int reserverChambre(Reservation reservation);

	boolean payerChambre(int codeReservation);

	/**
	 * Méthode pour annuler une réservation 
	 * @param codeReservation : id de la réservation
	 * @return
	 */
	boolean annulerChambre(int codeReservation);

	int connexion(String login, String mdp);

}