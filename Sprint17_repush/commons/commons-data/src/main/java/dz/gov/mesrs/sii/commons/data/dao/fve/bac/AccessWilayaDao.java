package dz.gov.mesrs.sii.commons.data.dao.fve.bac;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.bac.AccessWilaya;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.DroitAccessWilaya;

/**
 * @author Mounir.MESSAOUDI on : 14 ao�t 2014 12:13:15
 */
public interface AccessWilayaDao {

	public void persist(AccessWilaya transientInstance);

	public void remove(AccessWilaya persistentInstance);

	public AccessWilaya merge(AccessWilaya detachedInstance);

	public AccessWilaya findById(int id);

	public List<AccessWilaya> findAll();

	/**
	 * Renvoi la liste des access wilayas par annee academique
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 15:25:30
	 * @param idAnneeAcademique
	 * @return
	 */
	public List<AccessWilaya> findByIdAnneeAcademique(int idAnneeAcademique);

	/**
	 * Recherche avancee
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 15:25:30
	 * @param searchBo
	 * @return
	 */
	public List<AccessWilaya> findAdvanced(AccessWilaya searchBo);

	public void remove(DroitAccessWilaya persistentInstance);

}