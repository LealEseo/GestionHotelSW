
package fr.eseo.servicesweb.test.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.3.0
 * Tue Mar 12 10:08:51 CET 2019
 * Generated source version: 3.3.0
 */

@XmlRootElement(name = "trouverChambreResponse", namespace = "http://test.servicesweb.eseo.fr/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trouverChambreResponse", namespace = "http://test.servicesweb.eseo.fr/")

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
