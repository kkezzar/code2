package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefFonction;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefModule;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface RefFonctionDao {

	public  void persist(RefFonction transientInstance);

	public  void remove(RefFonction persistentInstance);

	public  RefFonction merge(RefFonction detachedInstance);

	public  RefFonction findById(int id);
	
	public  List<RefFonction> findByQuery(String query) ;
	
	public  List<RefFonction> findAll();
	
	public List<RefFonction> findGeneric(String value);
	
	public List<RefFonction> findActionGeneric(String value);
	
	public  RefFonction findByIdentifiant(String identifiant);
	
	public  List<RefModule> findModules(int id); 
	
	public  List<RefFonction> findFonctions(int id);// module id
	
	public  List<RefFonction> findFonctionsOfDomaine(int idDomaine);
	
	public int findFonctionLastRang(int id);
	
	public int findActionLastRang(int id);
	
	public List<RefFonction> findActions(int id);
	
	public  RefFonction findByName(Integer id, String name);
	
	public  RefFonction findByNameArabe(Integer id, String name);
	
	public  List<RefFonction> findAllFonctions(String domaine);
	
	public  List<RefFonction> findAllActions();
	
	public  RefFonction findPeriode(int idPeriode);
	
	public Integer findLastOrder(String prefix, int orderLength);

}