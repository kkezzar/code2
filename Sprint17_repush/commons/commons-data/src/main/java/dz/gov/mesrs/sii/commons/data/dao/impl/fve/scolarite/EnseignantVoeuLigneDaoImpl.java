/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.scolarite.EnseignantVoeuDaoImpl.java] 
 * @author rlaib on : 12 oct. 2014  15:54:23
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.fve.scolarite;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.EnseignantVoeuLigneDao;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantFicheVoeux;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantVoeu;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantVoeuLigne;

/**
 * @author rlaib  on : 12 oct. 2014  15:54:23
 */
@Service ("enseignantVoeuLigneDao")
public class EnseignantVoeuLigneDaoImpl extends GenericFveDaoImpl<EnseignantVoeuLigne> implements
		EnseignantVoeuLigneDao {

	private static final Logger log = LoggerFactory.getLogger(EnseignantVoeuLigneDaoImpl.class.getName());
	/**
	 * [EnseignantVoeuLigneDao.findByIdVoeu] Method 
	 * @author rlaib  on : 15 oct. 2014  14:21:28
	 * @param idVoeu
	 * @return
	 */
	@Override
	public List<EnseignantVoeuLigne> findByIdVoeu(int idVoeu) {
			try {
				CriteriaBuilder cb = entityManager.getCriteriaBuilder();
				CriteriaQuery<EnseignantVoeuLigne> c = cb.createQuery(EnseignantVoeuLigne.class);
				Root<EnseignantVoeuLigne> evl = c.from(EnseignantVoeuLigne.class);
				Join<EnseignantVoeuLigne, EnseignantVoeu> evlev = evl.join("enseignantVoeu");
				c.select(evl).distinct(true);
				c.orderBy(cb.asc(evl.get("ouvertureOffreFormation")));
				List<Predicate> criteria = new ArrayList<Predicate>();
				Predicate condition1 = cb.equal(evlev.get("id"), idVoeu);
				criteria.add(condition1);
		
				c.where(cb.and(criteria.toArray(new Predicate[0])));
				
				TypedQuery<EnseignantVoeuLigne> q = entityManager.createQuery(c);
				List<EnseignantVoeuLigne> result = q.getResultList();
					for(EnseignantVoeuLigne entity: result){
						if(entityManager.contains(entity)){
								entityManager.refresh(entity);
						}
					}
					return result;
			}
			catch (RuntimeException re) {

					log.error("findIdVoeu 'EnseignantVoeuLigne' failed", re);
					throw re;
			}
	}

	/**
	 * [EnseignantVoeuLigneDao.findByIdFiche] Method 
	 * @author rlaib  on : 24 oct. 2014  15:18:57
	 * @param idFiche
	 * @return
	 */
	@Override
	public List<EnseignantVoeuLigne> findByIdFiche(int idFiche) {

		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<EnseignantVoeuLigne> c = cb.createQuery(EnseignantVoeuLigne.class);
			Root<EnseignantVoeuLigne> evl = c.from(EnseignantVoeuLigne.class);
			Join<EnseignantVoeuLigne, EnseignantVoeu> evlev = evl.join("enseignantVoeu");
			Join<EnseignantVoeu, EnseignantFicheVoeux> evefv = evlev.join("enseignantFicheVoeux");
			c.select(evl).distinct(true);
			
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(evefv.get("id"), idFiche);
			criteria.add(condition1);
	
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			
			TypedQuery<EnseignantVoeuLigne> q = entityManager.createQuery(c);
			List<EnseignantVoeuLigne> result = q.getResultList();
				for(EnseignantVoeuLigne entity: result){
					if(entityManager.contains(entity)){
							entityManager.refresh(entity);
					}
				}
				return result;
		}
		catch (RuntimeException re) {

				log.error("findByIdFiche 'EnseignantVoeuLigne' failed", re);
				throw re;
		}
	}

	
}
