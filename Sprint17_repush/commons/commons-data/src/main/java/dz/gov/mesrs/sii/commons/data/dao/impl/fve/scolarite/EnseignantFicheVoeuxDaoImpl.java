/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.scolarite.EnseignantFicheVoeuxDaoImpl.java] 
 * @author rlaib on : 12 oct. 2014  15:52:48
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.fve.scolarite;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.EnseignantFicheVoeuxDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantFicheVoeux;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;

/**
 * @author rlaib  on : 12 oct. 2014  15:52:48
 */
@Service ("enseignantFicheVoeuxDao")
public class EnseignantFicheVoeuxDaoImpl extends GenericFveDaoImpl<EnseignantFicheVoeux> implements
		EnseignantFicheVoeuxDao {

	private static final Logger log = LoggerFactory.getLogger(EnseignantFicheVoeuxDaoImpl.class.getName());
	/**
	 * [EnseignantFicheVoeuxDao.findByEnseignantId] Method 
	 * @author rlaib  on : 12 oct. 2014  16:41:54
	 * @param idEnseignant
	 * @return
	 */
	@Override
	public List<EnseignantFicheVoeux> findByEnseignantId(int idAnnee, int idEnseignant) {
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<EnseignantFicheVoeux> c = cb.createQuery(EnseignantFicheVoeux.class);
			Root<EnseignantFicheVoeux> efv = c.from(EnseignantFicheVoeux.class);
			Join<EnseignantFicheVoeux, DossierEmploye> efve = efv.join("enseignant");
			Join<EnseignantFicheVoeux, AnneeAcademique> efva = efv.join("anneeAcademique");
			c.select(efv).distinct(true);
			
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(efva.get("id"), idAnnee);
			criteria.add(condition1);
			Predicate condition2= cb.equal(efve.get("id"), idEnseignant);
			criteria.add(condition2);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			
			TypedQuery<EnseignantFicheVoeux> q = entityManager.createQuery(c);
			List<EnseignantFicheVoeux> result = q.getResultList();
				for(EnseignantFicheVoeux entity: result){
					if(entityManager.contains(entity)){
							entityManager.refresh(entity);
					}
				}
				return result;
		}
		catch (RuntimeException re) {

				log.error("findByEnseignantId 'EnseignantFicheVoeux' failed", re);
				throw re;
		}
	}

	/**
	 * [EnseignantFicheVoeuxDao.findByEnseignantIdBySituationId] Method 
	 * @author rlaib  on : 12 oct. 2014  16:41:54
	 * @param idEnseignant
	 * @param idSituation
	 * @return
	 */
	@Override
	public List<EnseignantFicheVoeux> findByEnseignantIdBySituationId(int idAnnee,
			int idEnseignant, int idSituation) {
		try {
					CriteriaBuilder cb = entityManager.getCriteriaBuilder();
					CriteriaQuery<EnseignantFicheVoeux> c = cb.createQuery(EnseignantFicheVoeux.class);
					Root<EnseignantFicheVoeux> efv = c.from(EnseignantFicheVoeux.class);
					Join<EnseignantFicheVoeux, DossierEmploye> efve = efv.join("enseignant");
					Join<EnseignantFicheVoeux, SituationEntite> efvse = efv.join("situation");
					Join<EnseignantFicheVoeux, AnneeAcademique> efva = efv.join("anneeAcademique");
					c.select(efv).distinct(true);
					
					List<Predicate> criteria = new ArrayList<Predicate>();
					Predicate condition1 = cb.equal(efva.get("id"), idAnnee);
					criteria.add(condition1);
					Predicate condition2 = cb.equal(efve.get("id"), idEnseignant);
					criteria.add(condition2);
					Predicate condition3 = cb.equal(efvse.get("id"), idSituation);
					criteria.add(condition3);
					c.where(cb.and(criteria.toArray(new Predicate[0])));
					
					TypedQuery<EnseignantFicheVoeux> q = entityManager.createQuery(c);
					List<EnseignantFicheVoeux> result = q.getResultList();
					for(EnseignantFicheVoeux entity: result){
							if(entityManager.contains(entity)){
									entityManager.refresh(entity);
							}
					}
			return result;
		}
		catch (RuntimeException re) {
	
			log.error("findByEnseignantIdBySituationId 'EnseignantFicheVoeux' failed", re);
			throw re;
		}
	}

	/**
	 * [EnseignantFicheVoeuxDao.findAdvanced] Method 
	 * @author rlaib  on : 12 oct. 2014  16:57:51
	 * @param enseignantFicheVoeux
	 * @return
	 */
	@Override
	public List<EnseignantFicheVoeux> findAdvanced(
			EnseignantFicheVoeux enseignantFicheVoeux) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * [EnseignantFicheVoeuxDao.findFichesVoeuxByOfByPeriodeByYear] Method 
	 * @author rlaib  on : 18 oct. 2014  11:53:13
	 * @param idAnnee
	 * @param idOf
	 * @param idPeriode
	 * @return
	 */
	@Override
	public List<EnseignantFicheVoeux> findFichesByEtabByUserByOfByPeriodeByYear(Integer idEtab, Long idUser,
			Integer idAnnee, Integer idNcPeriode, boolean toSearch) {

		try {
							CriteriaBuilder cb = entityManager.getCriteriaBuilder();
							CriteriaQuery<EnseignantFicheVoeux> c = cb.createQuery(EnseignantFicheVoeux.class);
							Root<EnseignantFicheVoeux> efv = c.from(EnseignantFicheVoeux.class);
							Join<EnseignantFicheVoeux, Nomenclature> efvnc = efv.join("periode");
							Join<EnseignantFicheVoeux, AnneeAcademique> efva = efv.join("anneeAcademique");
							Join<EnseignantFicheVoeux, RefEtablissement> efvre = efv.join("refEtablissement");
							Join<EnseignantFicheVoeux, DossierEmploye> efven = efv.join("enseignant");
							c.select(efv).distinct(true);
							List<Predicate> criteria = new ArrayList<Predicate>();
							if(idAnnee != null) {
									Predicate condition1 = cb.equal(efva.get("id"), idAnnee);
									criteria.add(condition1);
							}
							
							if(idNcPeriode != null) {
									Predicate condition3 = cb.equal(efvnc.get("id"), idNcPeriode);
									criteria.add(condition3);
							}
							if(idEtab != null) {
									Predicate condition4 = cb.equal(efvre.get("id"), idEtab);
									criteria.add(condition4);
							}
							if(!toSearch) {
								ParameterExpression<Long> p = cb.parameter(Long.class, "idUser");
								criteria.add(cb.equal(efven.get("id"), p));
							}
							
							c.where(cb.and(criteria.toArray(new Predicate[0])));
							TypedQuery<EnseignantFicheVoeux> q = entityManager.createQuery(c);
							// Setting params values
							if (idUser != null && !toSearch) { q.setParameter("idUser", idUser); }
							List<EnseignantFicheVoeux> result = q.getResultList();
							for(EnseignantFicheVoeux entity: result){
									if(entityManager.contains(entity)){
											entityManager.refresh(entity);
									}
							}
							return result;
				}
				catch (RuntimeException re) {
				
					log.error("findFichesVoeuxByOfByPeriodeByYear 'EnseignantFicheVoeux' failed", re);
					throw re;
				}
	}

	/**
	 * [EnseignantFicheVoeuxDao.findFichesVoeuxByEtabBySituation] Method 
	 * @author rlaib  on : 20 oct. 2014  12:41:35
	 * @param idEtab
	 * @param idSituation
	 * @return
	 */
	@Override
	public List<EnseignantFicheVoeux> findFichesVoeuxByEtabBySituation(
			Integer idEtab,Integer idAnnee,  Integer idSituation) {
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<EnseignantFicheVoeux> c = cb.createQuery(EnseignantFicheVoeux.class);
			Root<EnseignantFicheVoeux> efv = c.from(EnseignantFicheVoeux.class);
			
			c.select(efv).distinct(true);
			List<Predicate> criteria = new ArrayList<Predicate>();
		
			if(idEtab != null) {
					Join<EnseignantFicheVoeux, RefEtablissement> efvre = efv.join("refEtablissement");
					Predicate condition1 = cb.equal(efvre.get("id"), idEtab);
					criteria.add(condition1);
			}
			
			if(idSituation != null) {
					Join<EnseignantFicheVoeux, SituationEntite> efvse = efv.join("situation");
					Predicate condition2 = cb.equal(efvse.get("id"), idSituation);
					criteria.add(condition2);
			}
			if(idAnnee != null) {
				Join<EnseignantFicheVoeux, AnneeAcademique> efva = efv.join("anneeAcademique");
				Predicate condition1 = cb.equal(efva.get("id"), idAnnee);
				criteria.add(condition1);
			}
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			
			TypedQuery<EnseignantFicheVoeux> q = entityManager.createQuery(c);
			List<EnseignantFicheVoeux> result = q.getResultList();
			for(EnseignantFicheVoeux entity: result){
					if(entityManager.contains(entity)){
							entityManager.refresh(entity);
					}
			}
			return result;
		}
		catch (RuntimeException re) {
				log.error("findFichesVoeuxByEtabBySituation 'EnseignantFicheVoeux' failed", re);
				throw re;
		}
	}

	/**
	 * [EnseignantFicheVoeuxDao.getEnseignantByIdUser] Method 
	 * @author rlaib  on : 29 oct. 2014  16:19:33
	 * @param idUser
	 * @return
	 */
	@Override
	public DossierEmploye getEnseignantByIdUser(Integer idUser) {

		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<DossierEmploye> c = cb.createQuery(DossierEmploye.class);
			Root<DossierEmploye> de = c.from(DossierEmploye.class);
			Join<DossierEmploye, RefIndividu> deri = de.join("refIndividu");
			c.select(de).distinct(true);
			List<Predicate> criteria = new ArrayList<Predicate>();
			if(idUser != null) {
					Predicate condition1 = cb.equal(deri.get("id"), idUser);
					criteria.add(condition1);
			}
			else
				return null;
		
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			
			TypedQuery<DossierEmploye> q = entityManager.createQuery(c);
			List<DossierEmploye> result = q.getResultList();
			for(DossierEmploye entity: result){
					if(entityManager.contains(entity)){
							entityManager.refresh(entity);
					}
			}
			if(result != null && result.size()==1)
				return result.get(0);
			
			return null;
		}
		catch (RuntimeException re) {
				log.error("getEnseignantByIdUser 'DossierEmploye' failed", re);
				throw re;
		}
	}

	/**
	 * [EnseignantFicheVoeuxDao.getEnseignantById] Method 
	 * @author rlaib  on : 29 oct. 2014  19:07:08
	 * @param id
	 * @return
	 */
	@Override
	public DossierEmploye getEnseignantById(Long id) {
		
		log.debug("getting DossierEmploye instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(DossierEmploye.class, id);
		} catch (RuntimeException re) {
			log.error("get DossierEmploye failed", re);
			throw re;
		}
	}

	/**
	 * [EnseignantFicheVoeuxDao.getAll] Method 
	 * @author rlaib  on : 30 oct. 2014  16:53:45
	 * @return
	 */
	@Override
	public List<DossierEmploye> getAllEnseignants() {

		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<DossierEmploye> c = cb.createQuery(DossierEmploye.class);
			Root<DossierEmploye> de = c.from(DossierEmploye.class);
			c.select(de).distinct(true);
			TypedQuery<DossierEmploye> q = entityManager.createQuery(c);
			List<DossierEmploye> result = q.getResultList();
			for(DossierEmploye entity: result){
					if(entityManager.contains(entity)){
							entityManager.refresh(entity);
					}
			}
			return result;
		}
		catch (RuntimeException re) {
				log.error("getAllEnseignants 'DossierEmploye' failed", re);
				throw re;
		}
	}

	/**
	 * [EnseignantFicheVoeuxDao.searchEnseignants] Method 
	 * @author rlaib  on : 11 nov. 2014  11:42:32
	 * @param query
	 * @return
	 */
	@Override
	public List<DossierEmploye> searchEnseignants(Integer idEtab, String query) {
		
		try {
			
			// Ceci est juste une recherche Enseignant par Nom/prenom
			
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<DossierEmploye> c = cb.createQuery(DossierEmploye.class);
			Root<DossierEmploye> de = c.from(DossierEmploye.class);
			Join<DossierEmploye, RefIndividu> deri = de.join("refIndividu");
			Join<DossierEmploye, RefEtablissement> dere = de.join("refEtablissement");
			List<Predicate> criteria = new ArrayList<Predicate>();
			criteria.add(cb.like(cb.lower(deri.<String>get("nomLatin")), "%" + query.toLowerCase() + "%" ));
			criteria.add(cb.like(cb.lower(deri.<String>get("prenomLatin")), "%" + query.toLowerCase() + "%" ));
			c.select(de).distinct(true);
			
			Predicate conditionEtab = null;
			if(idEtab != null) {
				conditionEtab = cb.equal(dere.get("id"), idEtab);
			}
			
			Predicate where = cb.conjunction();
			if(conditionEtab != null)
				where = cb.and(conditionEtab);
		
			if(criteria != null && criteria.size()>0)
				where = cb.and(where,cb.or(criteria.toArray(new Predicate[0])));

			c.where(where);
			TypedQuery<DossierEmploye> q = entityManager.createQuery(c);
			List<DossierEmploye> result = q.getResultList();
			for(DossierEmploye entity: result){
					if(entityManager.contains(entity)){
							entityManager.refresh(entity);
					}
			}
			return result;
		}
		catch (RuntimeException re) {
				log.error("searchEnseignants 'DossierEmploye' failed", re);
				throw re;
		}
	}

	
}
