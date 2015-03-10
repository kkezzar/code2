package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DiplomeFinEtudeEdition;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */
 

public interface DiplomeFinEtudeEditionDao {


	public  void persist(DiplomeFinEtudeEdition transientInstance);

	public  void remove(DiplomeFinEtudeEdition persistentInstance);

	public  DiplomeFinEtudeEdition merge(DiplomeFinEtudeEdition detachedInstance);

	public  DiplomeFinEtudeEdition findById(int id);
		
	public  List<DiplomeFinEtudeEdition> findAll();
		
	public  List<DiplomeFinEtudeEdition> findAdvanced(DiplomeFinEtudeEdition searchBo);
		
	public  DiplomeFinEtudeEdition findByIdInscriptionAdministrative(int idInscriptionAdministrative);
	
	public List<DiplomeFinEtudeEdition>  findToValidate(String codeEtab);
	
	public  DiplomeFinEtudeEdition findByIdInscriptionAdministrativeAndPeriode(int idInscriptionAdministrative, int idPeriode);

	public List<DiplomeFinEtudeEdition> findEditionByIdDiplome(int idDiplome);
	

		
	

	
}