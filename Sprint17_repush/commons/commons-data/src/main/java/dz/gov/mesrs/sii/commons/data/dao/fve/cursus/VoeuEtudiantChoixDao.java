package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.VoeuEtudiantChoix;

/**
 * 
 * @author Mounir.MESSAOUDI on : 28 sept. 2014 17:08:04
 */
public interface VoeuEtudiantChoixDao {

	public void persist(VoeuEtudiantChoix transientInstance);

	public void remove(VoeuEtudiantChoix persistentInstance);

	public VoeuEtudiantChoix merge(VoeuEtudiantChoix detachedInstance);

	public VoeuEtudiantChoix findById(int id);
	
	public List<VoeuEtudiantChoix> findAdvanced(VoeuEtudiantChoix searchBo);
	
	public List<VoeuEtudiantChoix> findByQuery(String query) ;
	
	public List<VoeuEtudiantChoix> findAll();
}