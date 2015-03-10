package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAffectation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;

public interface RefAffectationDao {

	public abstract void persist(RefAffectation transientInstance);

	public abstract void remove(RefAffectation persistentInstance);

	public abstract RefAffectation merge(RefAffectation detachedInstance);

	public abstract RefAffectation findById(int id);

	public List<RefAffectation> findStructuresForIndividu(Integer idIndividu);

	public List<RefAffectation> findStructuresOfGroupe(Integer idGroupe);

	public List<RefAffectation> findGroupesForIndividu(Integer idIndividu);

	public List<RefAffectation> findGroupesForStructure(Integer idStructure);

	public List<RefAffectation> findDomainesForStructure(Integer idStructure);

	public List<RefAffectation> findFilieresForStructure(Integer idStructure);

	public List<RefAffectation> findIndividusOfGroupe(Integer idGroupe);

	public List<RefAffectation> findIndividusOfGroupe(String codeGroupe);

	public List<RefAffectation> findIndividusOfStructure(Integer idStructure);

	public List<RefAffectation> findIndividusOfStructure(String codeStructure);

	public List<RefAffectation> findGroupesofGroupe(Integer idGroupe);

	public List<RefAffectation> findAffectationByIdIndividu(Integer id);

	public List<RefAffectation> findAffectationByIdIndividu(Integer etabId,
			Integer id);

	public List<RefAffectation> findStructuresForEvenement(Integer idEvenement);

	public List<RefAffectation> findGroupesForEvenement(Integer idEvenement);

	public List<RefAffectation> findIndividusOfEvenement(Integer idEvenement);

	public List<RefAffectation> findIndividusOfEvenement(String codeEvenement);

	public RefAffectation findGroupeParentForGroupe(String codeGroupe);

	public RefAffectation findGroupeParentForGroupe(Integer idGroupe);

	public List<RefAffectation> findStructuresByIndividuAndRole(
			Integer idIndividu, Integer idRole);

	public List<RefAffectation> findAffectationByIdGroupe(Integer etabId,
			Integer idGroupe);

	public List<RefIndividu> findIndividuByRole(Integer idEtab,
			Integer idRole);

}
