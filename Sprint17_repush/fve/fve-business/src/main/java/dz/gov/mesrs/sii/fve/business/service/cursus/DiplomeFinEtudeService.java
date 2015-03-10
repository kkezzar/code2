package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DiplomeFinEtudeDto;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */

public interface DiplomeFinEtudeService {

	public DiplomeFinEtudeDto insertOrUpdate(
			DiplomeFinEtudeDto diplomeFinEtudeDto);

	public void remove(DiplomeFinEtudeDto diplomeFinEtudeDto);

	public DiplomeFinEtudeDto findById(int id);

	public List<DiplomeFinEtudeDto> findAll();

	public List<DiplomeFinEtudeDto> findBySession(int idSession);

	public DiplomeFinEtudeDto findUniqueByBilanSession(long idBilanSession);

	public List<DiplomeFinEtudeDto> findByQuery(String query);

	public List<DiplomeFinEtudeDto> findAdvanced(DiplomeFinEtudeDto searchDto);

	public List<DiplomeFinEtudeDto> findAdvancedDiplomeNonSigneMinist(
			DiplomeFinEtudeDto searchDto);

	public DiplomeFinEtudeDto findByIdDossier(int idDossier);

	void delete(DiplomeFinEtudeDto diplomeFinEtudeDto);

	public DiplomeFinEtudeDto findByIdInscriptionAdministrative(
			int idInscriptionAdministrative);

	public List<DiplomeFinEtudeDto> findDiplometoValidate(String codeEtab);

	public DiplomeFinEtudeDto findByIdInscriptionAdministrativeAndPeriode(
			int idInscriptionAdministrative, int idPeriode);

	public String generateIdentify(String prefix, int orderLength);

	public List<DiplomeFinEtudeDto> findByOofAndCycle(Integer oofId,
			Integer cycleId);

	public List<DiplomeFinEtudeDto> findValidateDiplome(Integer oofId);

}