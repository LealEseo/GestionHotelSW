
package fr.eseo.serviceswebbis.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.3.0
 * Thu Mar 14 15:22:40 CET 2019
 * Generated source version: 3.3.0
 */

@XmlRootElement(name = "payerChambreResponse", namespace = "http://serviceswebbis.eseo.fr/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "payerChambreResponse", namespace = "http://serviceswebbis.eseo.fr/")

public class PayerChambreResponse {

    @XmlElement(name = "return")
    private boolean _return;

    public boolean getReturn() {
        return this._return;
    }

    public void setReturn(boolean new_return)  {
        this._return = new_return;
    }

}
