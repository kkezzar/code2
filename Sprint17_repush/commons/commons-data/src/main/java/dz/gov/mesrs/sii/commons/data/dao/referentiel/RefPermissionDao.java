package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAffectation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefPermission;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 04-03-2014 16:44:07
 */
 

public interface RefPermissionDao {

	public  void persist(RefPermission transientInstance);

	public  void remove(RefPermission persistentInstance);

	public  RefPermission merge(RefPermission detachedInstance);

	public  RefPermission findById(int id);
	
	public  List<RefPermission> findByQuery(String query) ;
	
	public  List<RefPermission> findAll();
	
	public List<RefPermission> findByIdfIndividu(String identifiant);
	
	public List<RefPermission> findByIdfIndividu(int idAffectation, String identifiant);
	
	public  RefPermission findByIdFonction(int id);
	
	public  List<RefPermission> findByIdModule(int idRole, int id);
	
	public  List<RefPermission> findByIdDomaine(int idAffectation, int id);
	
	public  List<RefPermission> findByIdfGroupe(String idf);
	
	public  List<RefPermission> findByIdfStructure(String idf);
	
	public  List<RefPermission> findByIdRole(String domaine, int idf);
	
	public  RefPermission findByIdFonction(int idRole, int id);
	
	public  List<RefPermission> findByIdAction(int idRole, int id);
	
	public  List<RefPermission> findActions(int idRole, int id);
	
	public  RefPermission findByIdFonction(String idfIndividu, int id);

    public List<RefPermission> findGeneric(String value);
    
    public List<RefIndividu> findModele(String identifiant, int idfAffectation);
    
    public RefAffectation findAffectationModele(String identifiant, int idfAffectation);

}
