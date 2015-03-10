package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierInscriptionAdministrative;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.GroupePedagogique;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.ParcoursIndividualise;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */
 

public interface ParcoursIndividualiseDao {

	public  void persist(ParcoursIndividualise transientInstance);

	public  void remove(ParcoursIndividualise persistentInstance);

	public  ParcoursIndividualise merge(ParcoursIndividualise detachedInstance);

	public  ParcoursIndividualise findById(int id);
		
	public  List<ParcoursIndividualise> findAll();

	public  ParcoursIndividualise findByIdInscriptionAdministrative(int idInscriptionAdministrative);
	
	public List<ParcoursIndividualise>  findToValidate(String codeEtab);
	
	public List<ParcoursIndividualise>  findAdvanced(ParcoursIndividualise serchBo);
	
	public  ParcoursIndividualise findByIdInscriptionAdministrativeAndPeriode(int idInscriptionAdministrative, int idPeriode);
	
	
	public List<DossierInscriptionAdministrative>  findDossierInscription(DossierInscriptionAdministrative searchBo,
			GroupePedagogique groupePedagogique, Integer idSituation);

	public List<DossierInscriptionAdministrative> findDossierInscriptions(
			Integer idRattachementMc, Integer idPeriode,
			Integer idAnneeAcademique, Integer idSituation);
	
	public List<DossierInscriptionAdministrative> findDossierInscriptionsNotSubscribedOnExamen(
			Integer idRattachementMc, Integer idPeriode,
			Integer idAnneeAcademique, Integer idSituation, Integer idExamen, Integer idNiveau, String refCodeEtablissement);
}