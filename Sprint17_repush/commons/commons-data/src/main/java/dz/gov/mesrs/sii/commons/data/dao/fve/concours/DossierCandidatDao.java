package dz.gov.mesrs.sii.commons.data.dao.fve.concours;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.concours.DossierCandidat;

public interface DossierCandidatDao {

    DossierCandidat findUniqueOrNoneByExample(DossierCandidat dossierCandidat);

    List<DossierCandidat> findAllByExample(DossierCandidat dossierCandidat);

    List<DossierCandidat> findAllByExample(DossierCandidat dossierCandidat, String sortField, Integer pageSize,
	    Integer first, Boolean descending);

    void persist(DossierCandidat dossierCandidat);

    DossierCandidat merge(DossierCandidat dossierCandidat);

    void delete(DossierCandidat dossierCandidat);

    /**
     * @author Mounir.MESSAOUDI on : 2 nov. 2014 09:35:39
     * @param idConcours
     * @param idEtabAdmission
     * @return
     */
    public List<DossierCandidat> findAllAdmisByIdConcoursIdEtabAdmission(Long idConcours, Integer idEtabAdmission);

    int countByExample(DossierCandidat dossierCandidat);

}
