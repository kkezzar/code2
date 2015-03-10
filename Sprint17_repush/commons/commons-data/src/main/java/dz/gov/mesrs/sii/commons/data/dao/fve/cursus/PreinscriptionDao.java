package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DemandePreinscription;

public interface PreinscriptionDao {

    List<DemandePreinscription> findByExample(DemandePreinscription demandePreinscription);

    DemandePreinscription save(DemandePreinscription demandePreinscription);

    DemandePreinscription finById(Long demandeId);
    
    void saveDemandeur(DemandePreinscription demandePreinscription);

    void saveTitreAcces(DemandePreinscription demandePreinscription);

    void remove(DemandePreinscription map);


}
