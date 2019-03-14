
--Trouver une chambre à partir d'une chambre demandée par le client

sql_query = "SELECT DISTINCT c.* FROM chambre c LEFT JOIN reservation r ON c.idChambre=r.idChambre WHERE c.typeChambre LIKE '%"+typeChambre_param+"%'";
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


-- Réserver une chambre (Validé)

"INSERT INTO Reservation (
				idChambre, 
				idClient, 
				dateDeb, 
				dateFin, 
				nbPlaces, 
				paiementEffectue) 
		VALUES ("
				+idChambre_param+","
				+idClient_param+",'"
				+dateDeb_param+"','"
				+dateFin_param+"',"
				+nbPlaces_param+",
				'0');"


-- Payer une chambre (Validé)

"UPDATE Reservation SET paiementEffectue = '1' WHERE idReservation = "+codeReservation+";"


-- Annuler une réservation (Validé)

'DELETE FROM Reservation WHERE idReservation = '+codeReservation+';'

