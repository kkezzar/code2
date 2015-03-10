package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSpecialiteLmd;


public interface RefSpecialiteLmdDao {

	void persist(RefSpecialiteLmd transientInstance);

	void remove(RefSpecialiteLmd persistentInstance);

	RefSpecialiteLmd merge(RefSpecialiteLmd detachedInstance);

	RefSpecialiteLmd findById(int id);
	
	RefSpecialiteLmd findByIdentifiant(String identifiant);
	
	List<RefSpecialiteLmd> findByQuery(String query) ;
	
	List<RefSpecialiteLmd> findAll();
	
	List<RefSpecialiteLmd> findGeneric(String value);
		
	List<RefSpecialiteLmd> findAdvanced(RefSpecialiteLmd refSpecialiteLmd);

	List<RefSpecialiteLmd> findByCodeFilierelmd(String idFiliere);
	
	List<RefSpecialiteLmd> findByIdFilierelmd(Integer idFiliere);



}