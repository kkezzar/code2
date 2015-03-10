package dz.gov.mesrs.sii.commons.data.dao.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.bpm.DemandeI18n;


/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface DemandeI18nDao {

	public  void persist(DemandeI18n transientInstance);

	public  void remove(DemandeI18n persistentInstance);

	public  DemandeI18n merge(DemandeI18n detachedInstance);

	public  DemandeI18n findById(int id);
	
	public  List<DemandeI18n> findByQuery(String query) ;
	
	public  List<DemandeI18n> findAll();
	
	public List<DemandeI18n> findListDemandeI18nByDemande(int idDemande);
	
	public List<DemandeI18n> findAdvanced(DemandeI18n demandeI18n) ;

	public List<DemandeI18n> findListDemandsByTypeBySituaion(String codeTypeDemande, String codeSituation, Integer idDemande);
}