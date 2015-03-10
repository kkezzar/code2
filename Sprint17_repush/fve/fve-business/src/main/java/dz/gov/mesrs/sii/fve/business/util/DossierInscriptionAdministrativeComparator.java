/**
 * [dz.gov.mesrs.sii.fve.business.util.DossierInscriptionAdministrativeComparator.java] 
 * @author BELDI Jamel on : 24 juin 2014  17:19:59
 */
package dz.gov.mesrs.sii.fve.business.util;

import java.io.Serializable;
import java.util.Comparator;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;

/**
 * @author BELDI Jamel  on : 24 juin 2014  17:19:59
 * @param <D>
 */
public class DossierInscriptionAdministrativeComparator  implements Comparator<DossierInscriptionAdministrativeDto>, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public int compare(DossierInscriptionAdministrativeDto ligne1, DossierInscriptionAdministrativeDto ligne2) {
        int indice = 0;
        if (ligne1.getIndividuNomArabe() != null && ligne2.getIndividuNomArabe() != null) {
            indice = ligne1.getIndividuNomArabe().compareTo(ligne2.getIndividuNomArabe());
        }
        if (indice != 0) {
            return indice;
        }
       
        return indice;
    }
}
