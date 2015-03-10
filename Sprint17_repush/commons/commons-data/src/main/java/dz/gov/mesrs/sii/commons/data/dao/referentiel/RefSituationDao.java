package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSituation;


public interface RefSituationDao {

	public abstract void persist(RefSituation transientInstance);
	public abstract void remove(RefSituation persistentInstance);
	public abstract RefSituation merge(RefSituation detachedInstance);
	public abstract RefSituation findCurrentSituation(Integer entityId, String entityName);	
	public abstract RefSituation findById(String id);
	public abstract List<RefSituation> findAll();
	public abstract List<RefSituation> findSituationsForIndividu(Integer idEntity);
	public abstract List<RefSituation> findSituationsForStructure(Integer idEntity);
	public abstract List<RefSituation> findSituationsForGroupe(Integer idEntity);
	public abstract List<RefSituation> findSituationsForContrat(Integer idEntity);
	public abstract List<RefSituation> findSituationsForCompte(Integer idEntity);
	public abstract List<RefSituation> findSituationsForLieu(Integer idEntity);
	public abstract List<RefSituation> findSituationsForEvenement(Integer idEntity);
	public abstract List<RefSituation> findSituationsForEquipement(Integer idEntity);
	public abstract List<RefSituation> findSituationsForReservation(Integer idEntity);
	public abstract List<RefSituation> findSituationsForEtablissement(Integer idEntity);
	public abstract List<RefSituation> findSituationsForDomaineLMD(Integer idEntity);
	public abstract List<RefSituation> findSituationsForFiliereLmd(Integer idEntity);
	public abstract List<RefSituation> findSituationsForSpecialiteLmd(Integer idEntity);
	
}