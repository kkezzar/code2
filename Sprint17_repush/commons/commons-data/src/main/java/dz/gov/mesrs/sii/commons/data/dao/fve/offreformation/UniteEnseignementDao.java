package dz.gov.mesrs.sii.commons.data.dao.fve.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.UniteEnseignement;

/**
 * Interface générique pour la persistance des unités d'enseignement.
 * 
 * @author Kheireddine OMRANI
 * 
 */
public interface UniteEnseignementDao {

	void persist(UniteEnseignement transientInstance);

	void remove(UniteEnseignement persistentInstance);

	UniteEnseignement merge(UniteEnseignement detachedInstance);

	UniteEnseignement validate(int uniteEnseignementId);

	UniteEnseignement invalidate(int uniteEnseignementId);
	
	UniteEnseignement findById(int id);

	List<UniteEnseignement> find(String code, String libelleFr,
			String libelleAr, String abreviationFr, String abreviationAr);

	List<UniteEnseignement> findWithFullText(String fullTextKeyword);
	
	List<UniteEnseignement> findAll(); 

	UniteEnseignement findByCode(String code);

}