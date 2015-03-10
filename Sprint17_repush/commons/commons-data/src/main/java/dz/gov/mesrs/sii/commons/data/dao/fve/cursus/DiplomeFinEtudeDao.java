package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DiplomeFinEtude;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */

public interface DiplomeFinEtudeDao {

	public void persist(DiplomeFinEtude transientInstance);

	public void remove(DiplomeFinEtude persistentInstance);

	public DiplomeFinEtude merge(DiplomeFinEtude detachedInstance);

	public DiplomeFinEtude findById(int id);

	public List<DiplomeFinEtude> findAll();

	public List<DiplomeFinEtude> findAdvanced(DiplomeFinEtude searchBo);

	public DiplomeFinEtude findByIdInscriptionAdministrative(
			int idInscriptionAdministrative);

	public List<DiplomeFinEtude> findToValidate(String codeEtab);

	public DiplomeFinEtude findByIdInscriptionAdministrativeAndPeriode(
			int idInscriptionAdministrative, int idPeriode);

	public List<DiplomeFinEtude> findAdvancedDiplomeNonSigneMinist(
			DiplomeFinEtude searchBo);

	public DiplomeFinEtude findUniqueByBilanSession(long idBilanSession);
	
	public Integer findLastOrder(String prefix, int orderLength);
	
	public List<DiplomeFinEtude> findByOofAndCycle(Integer  oofId, Integer cycleId);
	
	public List<DiplomeFinEtude> findValidateDiplome(Integer  oofId);


}
