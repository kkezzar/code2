package dz.gov.mesrs.sii.commons.data.dao.fve.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.GroupeRepartitionUeAChoix;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.RepartitionUe;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.UniteEnseignement;

/**
 * 
 * @author Kheireddine OMRANI
 * 
 */
public interface RepartitionUeDao {

	public void persist(RepartitionUe transientInstance);
	
	public RepartitionUe merge(RepartitionUe detachedInstance);
	
	public void remove(int id);

	public void remove(int parcoursId, String refCodeSemestre, int ueId); 

	public RepartitionUe findById(int id);

	public List<RepartitionUe> find(String refCodeSemestre,
			Integer parcoursId, Integer ueId);
  
	public List<UniteEnseignement> findUniteEnseignement(int parcoursId, String refCodeSemestre);
	
	public List<UniteEnseignement> findUeOof(int oofId, String refCodeSemestre);
	
	public List<UniteEnseignement> findUeOof(int oofId, Integer periodeId);
	
	public List<RepartitionUe> findByParcours(int parcoursId);
	
	public List<RepartitionUe> findByParcoursAndPeriode(int parcoursId, int idPeriode);

	public List<UniteEnseignement> findUeOoffindUeOofAndPeriode(int oofId, int idPeriode);
	
	public GroupeRepartitionUeAChoix findGroupeRepartitionUeAChoixById(int IdGroupe);
	
	public List<UniteEnseignement> getAvailableUesPeriod(int parcoursId, int idPeriode);
	
	public List<RepartitionUe> findReparationUe(Integer oofId, Integer periodeId);
	
}

