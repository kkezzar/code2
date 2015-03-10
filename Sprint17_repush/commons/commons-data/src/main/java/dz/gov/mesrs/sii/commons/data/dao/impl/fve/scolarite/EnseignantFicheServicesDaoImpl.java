/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.scolarite.EnseignantFicheServicesDaoImpl.java] 
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

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.EnseignantFicheServicesDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Periode;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantFicheServices;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantFicheVoeux;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;

/**
 * @author rlaib  on : 12 oct. 2014  15:52:48
 */
@Service ("enseignantFicheServicesDao")
public class EnseignantFicheServicesDaoImpl extends GenericFveDaoImpl<EnseignantFicheServices> implements
EnseignantFicheServicesDao {

	private static final Logger log = LoggerFactory.getLogger(EnseignantFicheServicesDaoImpl.class.getName());
	/**
	 * [EnseignantFicheServicesDao.findFichesServicesByEtabByUserByOfByPeriodeByYear] Method 
	 * @author rlaib  on : 25 oct. 2014  14:25:49
	 * @param idEtab
	 * @param idUser
	 * @param idAnnee
	 * @param idOf
	 * @param idPeriode
	 * @return
	 */
	@Override
	public List<EnseignantFicheServices> findFichesServicesByEtabByUserByPeriodeByYear(Integer idEtab
			, Long idUser, Integer idAnnee, Integer idPeriode) {
		
		try {
					CriteriaBuilder cb = entityManager.getCriteriaBuilder();
					CriteriaQuery<EnseignantFicheServices> c = cb.createQuery(EnseignantFicheServices.class);
					Root<EnseignantFicheServices> efs = c.from(EnseignantFicheServices.class);
					
					Join<EnseignantFicheServices, EnseignantFicheVoeux> efsefv = efs.join("enseignantFicheVoeux");
					Join<EnseignantFicheVoeux, Periode> efvp = efsefv.join("periode");
					Join<EnseignantFicheVoeux, AnneeAcademique> efva = efsefv.join("anneeAcademique");
					Join<EnseignantFicheVoeux, RefEtablissement> efvre = efsefv.join("refEtablissement");
					Join<EnseignantFicheVoeux, DossierEmploye> efven = efsefv.join("enseignant");
					c.select(efs).distinct(true);
					
					List<Predicate> criteria = new ArrayList<Predicate>();
					if(idAnnee != null) {
						Predicate condition1 = cb.equal(efva.get("id"), idAnnee);
						criteria.add(condition1);
					}
				
					if(idPeriode != null) {
						Predicate condition3 = cb.equal(efvp.get("id"), idPeriode);
						criteria.add(condition3);
					}
					if(idEtab != null) {
						Predicate condition4 = cb.equal(efvre.get("id"), idEtab);
						criteria.add(condition4);
					}
			
					if(idUser != null) {
						ParameterExpression<Long> p = cb.parameter(Long.class, "idUser");
						criteria.add(cb.equal(efven.get("id"), p));
					}
					c.where(cb.and(criteria.toArray(new Predicate[0])));
			
					TypedQuery<EnseignantFicheServices> q = entityManager.createQuery(c);
					// Setting params values
					if (idUser != null) { q.setParameter("idUser", idUser); }
					List<EnseignantFicheServices> result = q.getResultList();
					for(EnseignantFicheServices entity: result){
						if(entityManager.contains(entity)){
								entityManager.refresh(entity);
						}
					}
					return result;
		}
		catch (RuntimeException re) {

				log.error("findFichesVoeuxByOfByPeriodeByYear 'EnseignantFicheServices' failed", re);
				throw re;
			}
	}

	/**
	 * [EnseignantFicheServicesDao.findFichesServicesByEtabBySituation] Method 
	 * @author rlaib  on : 25 oct. 2014  14:25:49
	 * @param idEtab
	 * @param idSituation
	 * @return
	 */
	@Override
	public List<EnseignantFicheServices> findFichesServicesByEtabBySituation(
			Integer idEtab, Integer idSituation) {

		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<EnseignantFicheServices> c = cb.createQuery(EnseignantFicheServices.class);
			Root<EnseignantFicheServices> efs = c.from(EnseignantFicheServices.class);
			Join<EnseignantFicheServices, EnseignantFicheVoeux> efsefv = efs.join("enseignantFicheVoeux");
			Join<EnseignantFicheVoeux, SituationEntite> efvse = efsefv.join("situation");
			Join<EnseignantFicheVoeux, RefEtablissement> efvre = efsefv.join("refEtablissement");
			c.select(efs).distinct(true);
			List<Predicate> criteria = new ArrayList<Predicate>();
		
			if(idEtab != null) {
					Predicate condition1 = cb.equal(efvre.get("id"), idEtab);
					criteria.add(condition1);
			}
			
			if(idSituation != null) {
					Predicate condition2 = cb.equal(efvse.get("id"), idSituation);
					criteria.add(condition2);
			}
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			
			TypedQuery<EnseignantFicheServices> q = entityManager.createQuery(c);
			List<EnseignantFicheServices> result = q.getResultList();
			for(EnseignantFicheServices entity: result){
					if(entityManager.contains(entity)){
							entityManager.refresh(entity);
					}
			}
			return result;
		}
		catch (RuntimeException re) {
				log.error("findFichesServicesByEtabBySituation 'EnseignantFicheServices' failed", re);
				throw re;
		}
	}

	
}
