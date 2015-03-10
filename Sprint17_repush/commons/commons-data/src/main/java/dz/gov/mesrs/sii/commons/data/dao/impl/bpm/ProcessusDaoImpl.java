package dz.gov.mesrs.sii.commons.data.dao.impl.bpm;

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

import dz.gov.mesrs.sii.commons.data.dao.bpm.ProcessusDao;
import dz.gov.mesrs.sii.commons.data.dao.impl.fve.scolarite.GenericFveDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.bpm.Entite;
import dz.gov.mesrs.sii.commons.data.model.bpm.Processus;
import dz.gov.mesrs.sii.commons.data.model.recherche.Programme;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefDomaine;


@Service ("processusDao")
public class ProcessusDaoImpl extends GenericFveDaoImpl<Processus> implements ProcessusDao {

	private static final Logger log = LoggerFactory.getLogger(ProcessusDaoImpl.class.getName());

	/**
	 * [ProcessusDao.findByCodeEntite] Method 
	 * Overridden By : @author rlaib  on : 15 janv. 2015  14:48:44
	 * @param codeEntite
	 * @return
	 */
	@Override
	public List<Processus> findByCodeDomaine(String codeDomaine) {

		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Processus> c = cb.createQuery(Processus.class);
			Root<Processus> p = c.from(Processus.class);
			Join<Processus, Entite> pe = p.join("entite");
			Join<Entite, RefDomaine> ped = pe.join("refDomaine");
			c.select(p).distinct(true);
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(ped.get("identifiant"), codeDomaine);
			criteria.add(condition1);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<Processus> q = entityManager.createQuery(c);
			List<Processus> result = q.getResultList();
				for(Processus entity: result){
					if(entityManager.contains(entity)){
							entityManager.refresh(entity);
					}
				}
				return result;
		}
		catch (RuntimeException re) {

				log.error("findByCodeDomaine 'Processus' failed", re);
				throw re;
		}
	}

	/**
	 * [ProcessusDao.findByCodeEntite] Method 
	 * Overridden By : @author rlaib  on : 21 janv. 2015  16:40:40
	 * @param codeEntite
	 * @return
	 */
	@Override
	public List<Processus> findByCodeEntite(String codeEntite) {
		
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Processus> c = cb.createQuery(Processus.class);
			Root<Processus> p = c.from(Processus.class);
			Join<Processus, Entite> pe = p.join("entite");
			c.select(p).distinct(true);
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(pe.get("code"), codeEntite);
			criteria.add(condition1);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<Processus> q = entityManager.createQuery(c);
			List<Processus> result = q.getResultList();
				for(Processus entity: result){
					if(entityManager.contains(entity)){
							entityManager.refresh(entity);
					}
				}
				return result;
		}
		catch (RuntimeException re) {

				log.error("findByCodeEntite 'Processus' failed", re);
				throw re;
		}
	}

	/**
	 * [ProcessusDao.findByKeyword] Method 
	 * Overridden By : @author rlaib  on : 25 janv. 2015  15:32:06
	 * @param keyWord
	 * @return
	 */
	@Override
	public List<Processus> findByKeyWords(String keyWord) {
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Processus> c = cb.createQuery(Processus.class);
			Root<Processus> p = c.from(Processus.class);
			c.select(p).distinct(true);
			List<Predicate> criteria = new ArrayList<Predicate>();
			if(keyWord != null) {
				criteria.add(cb.like(cb.lower(p.<String>get("code")), "%" + keyWord.toLowerCase() + "%" ));
				criteria.add(cb.like(cb.lower(p.<String>get("libelleFr")), "%" + keyWord.toLowerCase() + "%" ));
				criteria.add(cb.like(cb.lower(p.<String>get("libelleAr")), "%" + keyWord.toLowerCase() + "%" ));
			}
			c.where(cb.or(criteria.toArray(new Predicate[0])));
			TypedQuery<Processus> q = entityManager.createQuery(c);
			List<Processus> result = q.getResultList();
				for(Processus entity: result){
					if(entityManager.contains(entity)){
							entityManager.refresh(entity);
					}
				}
				return result;
		}
		catch (RuntimeException re) {

				log.error("findByKeyWords 'Processus' failed", re);
				throw re;
		}
	
	}

	
}
