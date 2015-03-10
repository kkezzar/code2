package dz.gov.mesrs.sii.commons.data.dao.grh;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DiplomeFinEtudeEdition;
import dz.gov.mesrs.sii.commons.data.model.grh.Filiere;



/**
 * 
 * @author BELBRIK Oualid
 * @version V1.0
 * @date 17-10-2014
 * @description Interface  DAO Filiere
 * 
 */
public interface FiliereDao extends CommonDao<Filiere, Integer> {

	/**
	 * for specific method
	 */
	public List<Filiere> findFiliereByIdRubrique(int idRubrique);
	
	public  void persist(Filiere transientInstance);

	public  void remove(Filiere persistentInstance);

	public  Filiere merge(Filiere detachedInstance);

	public  Filiere findById(int id);
		
	public  List<Filiere> findAll();
}