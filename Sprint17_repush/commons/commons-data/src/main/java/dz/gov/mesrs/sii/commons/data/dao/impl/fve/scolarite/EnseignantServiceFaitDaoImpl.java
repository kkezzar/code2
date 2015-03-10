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

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.EnseignantServiceFaitDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormation;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Periode;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantChargePedagogique;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantFicheServices;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantFicheVoeux;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantServiceFait;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantVoeuLigne;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;

/**
 * @author rlaib  on : 12 oct. 2014  15:52:48
 */
@Service ("enseignantServiceFaitDao")
public class EnseignantServiceFaitDaoImpl extends GenericFveDaoImpl<EnseignantServiceFait> implements
EnseignantServiceFaitDao {

	private static final Logger log = LoggerFactory.getLogger(EnseignantServiceFaitDaoImpl.class.getName());
	/**
	 * [EnseignantServiceFaitDao.findByEtabByUserByOfByPeriodeByYear] Method 
	 * @author rlaib  on : 29 oct. 2014  13:24:03
	 * @param idEtab
	 * @param idUser
	 * @param idAnnee
	 * @param idOf
	 * @param idPeriode
	 * @return
	 */
	@Override
	public List<EnseignantServiceFait> findByEtabByUserByOfByPeriodeByYearByCharge(
			Integer idEtab, Long idUser, Integer idAnnee, Integer idOf,
			Integer idPeriode, Integer idCharge) {

		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<EnseignantServiceFait> c = cb.createQuery(EnseignantServiceFait.class);
			Root<EnseignantServiceFait> esf = c.from(EnseignantServiceFait.class);
			Join<EnseignantServiceFait, EnseignantChargePedagogique> esfecp = esf.join("enseignantChargePedagogique");
			Join<EnseignantChargePedagogique, EnseignantFicheServices> ecpefs = esfecp.join("enseignantFicheServices");
			Join<EnseignantFicheServices, EnseignantFicheVoeux> efsefv = ecpefs.join("enseignantFicheVoeux");
			List<Predicate> criteria = new ArrayList<Predicate>();
			if(idOf != null) {
				Join<EnseignantChargePedagogique, EnseignantVoeuLigne> ecpevl = esfecp.join("enseignantVoeuLigne");
				Join<EnseignantVoeuLigne, OffreFormation> evlof = ecpevl.join("offreFormation");
				Predicate condition2 = cb.equal(evlof.get("id"), idOf);
				criteria.add(condition2);
			}
			if(idPeriode != null) {
				Join<EnseignantFicheVoeux, Periode> efvp = efsefv.join("periode");
				Predicate condition3 = cb.equal(efvp.get("id"), idPeriode);
				criteria.add(condition3);
			}
			if(idAnnee != null) {
					Join<EnseignantFicheVoeux, AnneeAcademique> efva = efsefv.join("anneeAcademique");
					Predicate condition1 = cb.equal(efva.get("id"), idAnnee);
					criteria.add(condition1);
			}
			if(idEtab != null) {
					Join<EnseignantFicheVoeux, RefEtablissement> efvre = efsefv.join("refEtablissement");
					Predicate condition4 = cb.equal(efvre.get("id"), idEtab);
					criteria.add(condition4);
			}
			if(idUser != null) {
					Join<EnseignantFicheVoeux, DossierEmploye> efven = efsefv.join("enseignant");
					ParameterExpression<Long> p = cb.parameter(Long.class, "idUser");
					criteria.add(cb.equal(efven.get("id"), p));
			}
			
			if(idCharge != null) {
				Predicate condition6 = cb.equal(esfecp.get("id"), idCharge);
				criteria.add(condition6);
			}
			
			c.select(esf).distinct(true);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			c.orderBy(cb.desc(esf.get("dateSeance")));
			TypedQuery<EnseignantServiceFait> q = entityManager.createQuery(c);
			// Setting params values
			if (idUser != null) { q.setParameter("idUser", idUser); }
			List<EnseignantServiceFait> result = q.getResultList();
			for(EnseignantServiceFait entity: result){
					if(entityManager.contains(entity)){
							entityManager.refresh(entity);
					}
			}
			return result;
		}
		catch (RuntimeException re) {

			log.error("findByEtabByUserByOfByPeriodeByYear 'EnseignantServiceFait' failed", re);
			throw re;
		}
	}

	
}
