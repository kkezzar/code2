package dz.gov.mesrs.sii.commons.data.dao.fve.bac;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.bac.CapaciteEtab;


/**
 * @author Mounir.MESSAOUDI on : 14 ao�t 2014 12:13:25
 */
public interface CapaciteEtabDao {

	public  void persist(CapaciteEtab transientInstance);

	public  void remove(CapaciteEtab persistentInstance);

	public  CapaciteEtab merge(CapaciteEtab detachedInstance);

	public  CapaciteEtab findById(int id);
		
	public  List<CapaciteEtab> findAll();
	
	/**
	 * Renvoi la liste des capcistes par annee academique
	 * @author Mounir.MESSAOUDI on : 2 sept. 2014 09:24:24 
	 * @param idAnneeAcademique
	 * @return
	 */
	public List<CapaciteEtab> findByIdAnneeAcademique(int idAnneeAcademique);
	
	/**
	 * Recherche avancee
	 * 
	 * @author Mounir.MESSAOUDI on : 2 sept. 2014 09:24:24 
	 * @param searchBo
	 * @return
	 */
	public List<CapaciteEtab> findAdvanced(CapaciteEtab searchBo);

}