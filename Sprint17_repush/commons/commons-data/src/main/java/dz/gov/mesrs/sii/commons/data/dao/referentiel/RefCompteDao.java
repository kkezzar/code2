package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefCompte;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface RefCompteDao {

	public  void persist(RefCompte transientInstance);

	public  void remove(RefCompte persistentInstance);

	public  RefCompte merge(RefCompte detachedInstance);

	public  RefCompte findById(int id);
	
	public  List<RefCompte> findByQuery(String query) ;
	
	public  List<RefCompte> findAll();

	public List<RefCompte> findGeneric(String value);
	
    public List<RefCompte> findGeneric(Integer etabId, String value);
	
	public List<RefCompte> findAdvanced(RefCompte refCompte);
	
	public  RefCompte findByUser(Integer individuId);
	
	public  RefCompte findByLogin(String nomUtilisateur);
	
	public Integer  generateUsername(String prefix);
	
	public List<RefIndividu> findByEtablissement(String nom, String prenom, Integer idEtab);
}