package dz.gov.mesrs.sii.fve.business.service.concours;

import dz.gov.mesrs.sii.fve.business.model.dto.concours.ConcoursDto;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.DossierCandidatDto;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.EtablissementAdmissionDto;

public interface AdmissionService {

    /**
     * Compte le nombre d'admis a un etablissement pour un concours
     * @param dto , {@link EtablissementAdmissionDto} a un {@link ConcoursDto}
     */
    void countNombreAdmis(EtablissementAdmissionDto dto);
    
    /**
     * Enregistre l'admission d'un candidat d'un concours pour un etablissement
     * @param dto , le dossier du candidat
     * @throws NombreAdmisAtteintException , si le nombre d'admis est atteint
     */
    void admettreCandidat(DossierCandidatDto dto) throws NombreAdmisAtteintException;
    
    
    
}
