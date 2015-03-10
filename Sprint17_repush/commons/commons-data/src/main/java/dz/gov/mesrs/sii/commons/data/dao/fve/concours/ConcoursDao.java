package dz.gov.mesrs.sii.commons.data.dao.fve.concours;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.concours.Concours;

public interface ConcoursDao {
    
	String SEQUENCE_IDENTIFIANT_CONCOURS="cursus.seq_identifiant_concours";
	
	void persist(Concours concours);

	Concours merge(Concours concours);

	/**
	 * Rechercher un concours par id
	 * 
	 * @author Mounir.MESSAOUDI on : 30 oct. 2014 08:57:22
	 * @param id
	 * @return
	 */
	public Concours findById(Long id);

	Concours findUniqueOrNoneByExample(Concours concours);

	List<Concours> findAllByExample(Concours concours);

	void delete(Concours concours);

	String getNextConcoursIdentifiantSeq();

}
