package fr.eseo.serviceswebbis;

import javax.jws.WebService;

@WebService(name = "SEIGestionHotelMethodeBis", targetNamespace = "http://serviceswebbis.eseo.fr/")
public interface SEIGestionHotelMethodeBis {

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