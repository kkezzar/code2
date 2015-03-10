package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DemandePreinscriptionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.SearchPreinscriptionDto;

public interface PreinscriptionService {

    DemandePreinscriptionDto save(DemandePreinscriptionDto dto);

    List<DemandePreinscriptionDto> search(SearchPreinscriptionDto searchDto);

    DemandePreinscriptionDto find(Long demandeId);

    void genereDossierEtudiant(DemandePreinscriptionDto demandePreinscriptionDto);

    void delete(DemandePreinscriptionDto dto);

}
