package fr.eseo.servicesweb.test;

import javax.jws.WebService;

import fr.eseo.servicesweb.Chambre;

@WebService(name = "SEITrouverChambreSW", targetNamespace = "http://test.servicesweb.eseo.fr/")
public interface SEITrouverChambreSW {

	Chambre[] trouverChambre(Chambre chambre, int prixMin_param, int prixMax_param, String dateDeb_param,
			String dateFin_param);

}