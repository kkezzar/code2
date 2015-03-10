package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefTypePieceConstitutive;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */
 

public interface RefTypePieceConstitutiveDao {

	public  void persist(RefTypePieceConstitutive transientInstance);

	public  void remove(RefTypePieceConstitutive persistentInstance);

	public  RefTypePieceConstitutive merge(RefTypePieceConstitutive detachedInstance);

	public  RefTypePieceConstitutive findById(int id);
	
	public  List<RefTypePieceConstitutive> findByQuery(String query) ;
	
	public  List<RefTypePieceConstitutive> findAll();
	
	public  List<RefTypePieceConstitutive> findByIdTypeDossierDate(int idTypeDossier, Date annee);
	
	public  List<RefTypePieceConstitutive> findByIdTypeDossier(int idTypeDossier, boolean aFournir);
	
	public  List<RefTypePieceConstitutive> findByIdTypeDossier(int idTypeDossier);
	
	public  List<RefTypePieceConstitutive> findByCodeTypeDossierDate(String codeTypeDossier, Date annee);
	
	public  List<RefTypePieceConstitutive> findByTypeDossierDate(String typeDossier, Date annee);
	
	public int findLastRang(int idTypeDossier, boolean aFournir);

}