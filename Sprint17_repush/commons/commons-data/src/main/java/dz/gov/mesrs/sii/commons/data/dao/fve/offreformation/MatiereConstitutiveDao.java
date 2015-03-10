package dz.gov.mesrs.sii.commons.data.dao.fve.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.MatiereConstitutive;

/**
 * 
 * @author Kheireddine OMRANI
 *
 */
public interface MatiereConstitutiveDao {

	void persist(MatiereConstitutive transientInstance);

	void remove(MatiereConstitutive persistentInstance);

	MatiereConstitutive merge(MatiereConstitutive detachedInstance);

	MatiereConstitutive findById(int id);

	List<MatiereConstitutive> findWithFullText(String fullTextKeyword);

	MatiereConstitutive validate(int matiereConstitutiveId);

	MatiereConstitutive invalidate(int matiereConstitutiveId);

	List<MatiereConstitutive> findAll(); 
 
	MatiereConstitutive findByCode(String code);
	
}