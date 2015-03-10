package dz.gov.mesrs.sii.commons.data.dao.fve.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AtomePedagogique;

/**
 * 
 * @author Kheireddine OMRANI
 *
 */
public interface AtomePedagogiqueDao {

	void persist(AtomePedagogique transientInstance);

	void remove(AtomePedagogique persistentInstance);
	
	void remove(int id);

	AtomePedagogique merge(AtomePedagogique detachedInstance);

	AtomePedagogique findById(int id);

	List<AtomePedagogique> findByQuery(String query);

	List<AtomePedagogique> findAll();

	List<AtomePedagogique> find(String code,
			Integer idMatiereConstitutive, String libelleFr, String libelleAr);

	List<AtomePedagogique> findByRattachementMC(int idRattachementMc);
	
	AtomePedagogique findByTypeAndRattachementMC(String codeType, int idRattachementMc);
	
	public List<AtomePedagogique> findApByGroupeId(int sectionId);
	
	public AtomePedagogique findUniqueByType(int idMc, int ncTypeId);
	
}