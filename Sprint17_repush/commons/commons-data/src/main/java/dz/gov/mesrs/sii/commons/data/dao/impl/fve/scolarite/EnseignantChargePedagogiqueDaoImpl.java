/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.scolarite.EnseignantChargePedagogiqueDaoImpl.java] 
 * @author rlaib on : 12 oct. 2014  15:52:48
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.fve.scolarite;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.EnseignantChargePedagogiqueDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantChargePedagogique;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantFicheServices;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantFicheVoeux;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantServiceFait;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantVoeu;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantVoeuLigne;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;

/**
 * @author rlaib  on : 12 oct. 2014  15:52:48
 */
@Service ("enseignantChargePedagogiqueDao")
public class EnseignantChargePedagogiqueDaoImpl extends GenericFveDaoImpl<EnseignantChargePedagogique> implements
EnseignantChargePedagogiqueDao{

	private static final Logger log = LoggerFactory.getLogger(EnseignantChargePedagogiqueDaoImpl.class.getName());
	/**
	 * [EnseignantChargePedagogiqueDao.findByIdFicheServices] Method 
	 * @author rlaib  on : 25 oct. 2014  14:53:29
	 * @param idFiche
	 * @return
	 */
	@Override
	public List<EnseignantChargePedagogique> findByIdFicheServices(int idFiche) {
			
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<EnseignantChargePedagogique> c = cb.createQuery(EnseignantChargePedagogique.class);
			Root<EnseignantChargePedagogique> ecp = c.from(EnseignantChargePedagogique.class);
			Join<EnseignantChargePedagogique, EnseignantFicheServices> ecpefs = ecp.join("enseignantFicheServices");
			Join<EnseignantFicheServices, EnseignantFicheVoeux> efsefv = ecpefs.join("enseignantFicheVoeux");
			c.select(ecp).distinct(true);
			
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(efsefv.get("id"), idFiche);
			criteria.add(condition1);
	
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			
			TypedQuery<EnseignantChargePedagogique> q = entityManager.createQuery(c);
			List<EnseignantChargePedagogique> result = q.getResultList();
				for(EnseignantChargePedagogique entity: result){
					if(entityManager.contains(entity)){
							entityManager.refresh(entity);
					}
				}
				return result;
		}
		catch (RuntimeException re) {

				log.error("findByIdFicheServices 'EnseignantChargePedagogique' failed", re);
				throw re;
		}
	}

	@Override
	public DossierEmploye findEnseignant(Integer idGroupePedagogique,
			Integer idRattachement, Integer idAp, Integer idNiveau) {

		try {
			if (idGroupePedagogique == null ||idRattachement == null ||idAp == null ||idNiveau == null   ) {
				return null;
			}
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append(" select distinct ecp.enseignantVoeuLigne.enseignantVoeu.enseignantFicheVoeux.enseignant from EnseignantChargePedagogique ecp ");
			sqlQuery.append(" where ecp.groupePedagogique.id="
					+ idGroupePedagogique);
			sqlQuery.append(" and ecp.enseignantVoeuLigne.rattachementMc.id="
					+ idRattachement);
			sqlQuery.append(" and ecp.enseignantVoeuLigne.ap.id="
					+ idAp);
			sqlQuery.append(" and ecp.enseignantVoeuLigne.niveau.id="
					+ idNiveau);
			TypedQuery<DossierEmploye> query = entityManager.createQuery(
					new String(sqlQuery), DossierEmploye.class);
			List<DossierEmploye> result = query.getResultList();
			if (result == null || result.isEmpty()) {
				return null;
			}
			return result.get(0);

		} catch (RuntimeException re) {
			log.error("findSessionforStudent failed", re);
			throw re;
		}
	
	}

	/**
	 * [EnseignantChargePedagogiqueDao.findSyntheseChargesEnseignant] Method 
	 * @author Rafik  on : 8 déc. 2014  19:52:43
	 * @param idEtab
	 * @param idUser
	 * @param idAnnee
	 * @param idOf
	 * @param idPeriode
	 * @return
	 */
	@Override
	public List<Object[]> findSyntheseChargesEnseignant(
			Integer idEtab, Long idUser, Integer idAnnee,
			Integer idPeriode) {
	
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Object[]> c = cb.createQuery(Object[].class);
			Root<EnseignantChargePedagogique> root = c.from(EnseignantChargePedagogique.class);
			Join<EnseignantChargePedagogique, EnseignantVoeuLigne> ecpEvl = root.join("enseignantVoeuLigne");
			Join<EnseignantVoeuLigne, EnseignantVoeu> evlEv = ecpEvl.join("enseignantVoeu");
			Join<EnseignantVoeu, EnseignantFicheVoeux> evEfv = evlEv.join("enseignantFicheVoeux");
			Join<EnseignantFicheVoeux, DossierEmploye> efvEns = evEfv.join("enseignant");
			Join<EnseignantFicheVoeux, Nomenclature> efvNc = evEfv.join("periode");
			Join<EnseignantFicheVoeux, RefEtablissement> efvEtab = evEfv.join("refEtablissement");
			Join<EnseignantFicheVoeux, AnneeAcademique> efvAa = evEfv.join("anneeAcademique");
			Join<EnseignantChargePedagogique, EnseignantServiceFait> ecpEsf = root.join("enseignantServiceFaits",JoinType.LEFT);
			c.multiselect(root, cb.count(ecpEsf)).groupBy(root);
			List<Predicate> criteria = new ArrayList<Predicate>();
			ParameterExpression<Long> p = cb.parameter(Long.class, "idUser");
			criteria.add(cb.equal(efvEns.get("id"), p));
			Predicate condition2 = cb.equal(efvNc.get("id"), idPeriode);
			criteria.add(condition2);
			Predicate condition3 = cb.equal(efvEtab.get("id"), idEtab);
			criteria.add(condition3);
			Predicate condition4 = cb.equal(efvAa.get("id"), idAnnee);
			criteria.add(condition4);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<Object[]> q = entityManager.createQuery(c);
			if (idUser != null) { q.setParameter("idUser", idUser); }
			List<Object[]> result = q.getResultList();
			return result;
		}
		catch (RuntimeException re) {

				log.error("findSyntheseChargesEnseignant 'EnseignantChargePedagogique' failed", re);
				throw re;
		}
	}
	
}
