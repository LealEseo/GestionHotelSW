
package fr.eseo.servicesweb.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.3.0
 * Wed Mar 13 08:20:44 CET 2019
 * Generated source version: 3.3.0
 */

@XmlRootElement(name = "trouverChambreResponse", namespace = "http://servicesweb.eseo.fr/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trouverChambreResponse", namespace = "http://servicesweb.eseo.fr/")

public class TrouverChambreResponse {

    @XmlElement(name = "return")
    private fr.eseo.servicesweb.Chambre[] _return;

    public fr.eseo.servicesweb.Chambre[] getReturn() {
        return this._return;
    }

    public void setReturn(fr.eseo.servicesweb.Chambre[] new_return)  {
        this._return = new_return;
    }

}

