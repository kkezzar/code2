package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.AffectationGroupePedagogique;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierInscriptionAdministrative;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */
 

public interface AffectationGroupePedagogiqueDao {

	public  void persist(AffectationGroupePedagogique transientInstance);

	public  void remove(AffectationGroupePedagogique persistentInstance);

	public  AffectationGroupePedagogique merge(AffectationGroupePedagogique detachedInstance);

	public  AffectationGroupePedagogique findById(int id);
		
	public  List<AffectationGroupePedagogique> findAll();
	
	public List<AffectationGroupePedagogique> findByGroupePedagogiqueId(int gpId);
	
	public List<AffectationGroupePedagogique> findOnlyBySectionId(int sectionId);
	
	public List<AffectationGroupePedagogique> findAffectationInGroupe(
			Integer diaId, Integer sectionId);
	
	public List<AffectationGroupePedagogique> findByAssociationGpId(
			Integer assocId);
	
	public  List<DossierInscriptionAdministrative> findEtudiantsOfSection(Integer idSection);

}